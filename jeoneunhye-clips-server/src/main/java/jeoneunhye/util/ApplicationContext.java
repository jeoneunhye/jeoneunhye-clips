package jeoneunhye.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import org.apache.ibatis.io.Resources;

public class ApplicationContext {
  // @Component 애노테이션이 붙은 Concrete 클래스를 담을 저장소 준비
  ArrayList<Class<?>> componentClasses = new ArrayList<>();

  // 객체 저장소 준비
  HashMap<String, Object> objPool = new HashMap<>();

  public ApplicationContext(String packageName, HashMap<String, Object> beans) throws Exception {
    // Mybatis 관련 객체는 ApplicationContext에서 자동으로 생성하지 못한다.
    // IoC 컨테이너 외부인 ContextLoaderListener에서 생성한다.
    // 파라미터로 받은 Map에서 생성해 놓은 객체를 꺼내 객체 풀에 보관한다.
    Set<String> keySet = beans.keySet();
    for (String key : keySet) {
      objPool.put(key, beans.get(key));
    }

    // 패키지명을 파라미터로 넘겨받아 파일 시스템 경로로 바꾼 다음 전달한다.
    File path = Resources.getResourceAsFile(packageName.replace('.', '/'));

    // 해당 경로에서 클래스 파일만 추출하고, Component 애노테이션이 붙은 Concrete 클래스를 찾아 List에 담는다.
    findClasses(path, packageName);

    for (Class<?> clazz : componentClasses) {
      try {
        createObject(clazz);

      } catch (Exception e) {
        System.out.printf("%s 클래스의 객체를 생성할 수 없습니다.\n", clazz.getName());
      }
    }
  }

  // ContextLoaderListener에서 특정 애노테이션이 붙은 객체들을 찾고
  // 그 객체(빈)의 이름이 담긴 배열을 리턴한다.
  public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
    ArrayList<String> beanNames = new ArrayList<>();

    Set<String> beanNameSet = objPool.keySet();
    for (String beanName : beanNameSet) {
      Object obj = objPool.get(beanName);

      if (obj.getClass().getAnnotation(annotationType) != null) {
        beanNames.add(beanName);
      }
    }

    String[] names = new String[beanNames.size()];
    beanNames.toArray(names);

    return names;
  }

  // ContextLoaderListener에서 IoC 컨테이너에 보관된 객체를 확인한다.
  public void printBeans() {
    System.out.println("-----------------------------------");
    Set<String> beanNames = objPool.keySet();
    for (String beanName : beanNames) {
      System.out.printf("%s =====> %s\n", beanName, objPool.get(beanName).getClass().getName());
    }
  }

  // ServerApp에서 객체 이름으로 생성한 객체를 찾아 꺼낸다.
  public Object getBean(String name) {
    return objPool.get(name);
  }

  private Object createObject(Class<?> clazz) throws Exception {
    // 클래스의 첫번째 생성자 정보를 알아낸다.
    Constructor<?> constructor = clazz.getConstructors()[0];
    // 생성자 파라미터 정보를 배열에 담는다.
    Parameter[] params = constructor.getParameters();

    // 생성자 파라미터 객체를 배열에 담는다.
    Object[] paramValues = getParameterValues(params);

    // 객체를 생성하고 객체 풀에 보관한다.
    Object obj = constructor.newInstance(paramValues);
    objPool.put(getBeanName(clazz), obj);

    System.out.println(clazz.getName() + " 객체 생성!");

    return obj;
  }

  // 생성된 객체의 이름을 관리한다.
  private String getBeanName(Class<?> clazz) {
    Component compAnno = clazz.getAnnotation(Component.class);
    if (compAnno == null || compAnno.value().length() == 0) {
      // @Component 애노테이션이 없거나 이름을 지정하지 않았으면
      return clazz.getName();
      // 클래스 이름을 빈의 이름으로 사용한다.
    }

    return compAnno.value();
  }

  private Object[] getParameterValues(Parameter[] params) throws Exception {
    Object[] paramValues = new Object[params.length];
    System.out.print("생성자 파라미터: {");
    for (int i = 0; i < paramValues.length; i++) {
      paramValues[i] = getParameterValue(params[i].getType());
      System.out.printf("%s ==> %s, ", params[i].getType().getSimpleName(),
          paramValues[i].getClass().getName());
    }
    System.out.println("}");

    return paramValues;
  }

  private Object getParameterValue(Class<?> type) throws Exception {
    // 먼저 객체 저장소에 파라미터 객체가 있는지 검사한다.
    Collection<?> objs = objPool.values();
    for (Object obj : objs) {
      if (type.isInstance(obj)) {
        // 있으면 같은 객체를 또 만들지 않고 기존의 생성된 객체를 리턴한다.
        return obj;
      }
    }

    // 객체풀에 파라미터 타입에 맞는 객체가 없다면 파라미터 타입에 맞는 클래스를 찾는다.
    Class<?> availableClass = findAvailableClass(type);
    if (availableClass == null) {
      // 파라미터에 해당하는 적절한 클래스를 찾지 못했으면 객체를 생성할 수 없다.
      return null;
    }

    return createObject(availableClass);
  }

  private Class<?> findAvailableClass(Class<?> type) throws Exception {
    // component class 목록에서 파라미터에 해당하는 클래스가 있는지 조사한다.
    for (Class<?> clazz : componentClasses) {
      if (type.isInterface()) {
        // 파라미터가 인터페이스라면 각각의 클래스에 대해 그 인터페이스를 구현했는지 검사한다.
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> interfaceInfo : interfaces) {
          if (interfaceInfo == type) {
            return clazz;
          }
        }
      } else if (isChildClass(clazz, type)) {
        // 파라미터가 클래스라면 각각의 클래스에 대해 같은 타입인지 수퍼 클래스인지 검사한다.
        return clazz;
      }
    }

    // 파라미터에 해당하는 타입이 concrete class 목록에 없다면 null을 리턴한다.
    return null;
  }

  private boolean isChildClass(Class<?> clazz, Class<?> type) {
    // 수퍼 클래스로 따라 올라가면서 같은 타입인지 검사한다.
    if (clazz == type) {
      return true;

    } else if (clazz == Object.class) {
      // 더 이상 상위 클래스가 없다면
      return false;
    }

    return isChildClass(clazz.getSuperclass(), type);
  }

  private void findClasses(File path, String packageName) throws Exception {
    // FileFilter를 사용하여 중첩 파일을 제외한 *.class 파일만을 배열에 담는다.
    File[] files = path.listFiles(file -> {
      if (file.isDirectory()
          || (file.getName().endsWith(".class") && !file.getName().contains("$")))
        return true;
      return false;
    });

    for (File f : files) {
      String className = String.format("%s.%s", packageName, f.getName().replace(".class", ""));
      if (f.isFile()) {
        Class<?> clazz = Class.forName(className);
        if (isComponentClass(clazz)) {
          componentClasses.add(clazz);
        }
      } else {
        findClasses(f, className);
      }
    }
  }

  private boolean isComponentClass(Class<?> clazz) {
    if (clazz.isInterface() // 인터페이스인 경우
        || clazz.isEnum() // Enum 타입인 경우
        || Modifier.isAbstract(clazz.getModifiers()) // 추상 클래스인 경우
        ) {
      return false; // 객체를 생성할 수 없다.
    }

    // 클래스에서 @Component 애노테이션 정보를 추출한다.
    Component compAnno = clazz.getAnnotation(Component.class);
    if (compAnno == null) {
      return false;
    }

    // 오직 @Component 애노테이션이 붙은 일반 클래스만이 객체 생성 대상이다.
    return true;
  }
}