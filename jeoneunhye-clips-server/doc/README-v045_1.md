# 45_1 - Java Proxy를 이용하여 DAO 구현체 자동 생성하기

### java.lang.reflect.Proxy

동적 프록시 클래스를 만들기 위한 정적 메서드를 제공한다.

```
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
```

- loader : 클래스 로더가 동적 프록시 클래스를 정의  
=> 클래스 로더는 동적 프록시가 생성되는 클래스 또는 인터페이스에 의해서 얻을 수 있다.  
   ex) MyClass.class.getClassLoader()
- interfaces : 동적 프록시 클래스에 의해 구현될 모든 인터페이스의 배열
- h : InvocationHandler를 구현하고 있는 클래스의 인스턴스

### java.lang.reflect.InvocationHandler

인터페이스 중 하나로 사용자에 의해 구현되어, 동적 프록시 클래스의 메서드를 호출한다.

```
Object invoke(Object proxy, Method m, Object[] args)
```

- proxy : 메서드가 호출될 프록시 인스턴스
- m : 프록시 인스턴스에서 호출되는 인터페이스 메서드
- args : 메서드 호출에서 전달된 인자들


## 작업 소스 및 결과

- src/main/resources/jeoneunhye/vms/mapper/VideoMapper.xml 변경
- src/main/resources/jeoneunhye/vms/mapper/MemberMapper.xml 변경
- src/main/resources/jeoneunhye/vms/mapper/BoardMapper.xml 변경
- src/main/resources/jeoneunhye/vms/mapper/PhotoBoardMapper.xml 변경
- src/main/resources/jeoneunhye/vms/mapper/PhotoFileMapper.xml 변경
- src/main/java/jeoneunhye/vms/dao/MemberDao.java 변경
- src/main/java/jeoneunhye/vms/service/impl/MemberServiceImpl.java 변경
- src/main/java/jeoneunhye/sql/MybatisDaoFactory.java 추가
- src/main/java/jeoneunhye/vms/dao/인터페이스를 제외한 클래스 모두 삭제
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경

### 작업1) InvocationHandler에서 SQL을 찾기 쉽도록 DAO 인터페이스의 메서드명과 SQL ID를 일치시킨다.

- XxxMapper.xml 파일 변경
    - namespace 값을 DAO 인터페이스 FQ name과 일치시킨다.
    - 메서드에서 사용할 SQL은 메서드 이름과 일치시킨다.
- MemberDao 인터페이스 변경
    - findByEmailAndPassword()의 파라미터 타입을 Map<String, String>으로 변경한다.
- MemberServiceImpl 클래스 변경
    - MemberDao의 findByEmailAndPassword()를 호출할 때 파라미터를 Map에 담아 넘긴다.

### 작업2) 복잡한 DAO 생성을 단순화하는 팩토리 클래스를 정의한다.

- MybatisDaoFactory 클래스 추가
    - java.lang.reflect.InvocationHandler의 invoke(Object, Method, Object[]) 메서드를 람다 문법으로 정의한다.  
    => 넘겨받은 파라미터로 namespace와 id, returnType을 알아내고 해당 SqlSession 실행문이 호출되도록 만든다.
    - java.lang.reflect.Proxy로 DAO 프록시 객체를 생성하는 팩토리 메서드 createDao(Class<T>)를 정의한다.  
    => 파라미터로 인터페이스의 타입 정보를 받아 Proxy.newProxyInstance 메서드를 호출하여 리턴한다.  
    => ClassLoader, 인터페이스의 타입 정보와 InvocationHandler를 주입한다.

### 작업3) DAO 객체 생성에 프록시 생성기를 적용한다.

- dao 패키지의 DAO 인터페이스 구현체 모두 제거
- DataLoaderListener 클래스 변경
    - DAO 프록시 객체를 생성할 MybatisDaoFactory 객체를 준비한다.
    - MybatisDaoFactory를 이용하여 DAO 구현체를 생성한다.