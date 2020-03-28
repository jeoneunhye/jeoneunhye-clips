package jeoneunhye.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisDaoFactory {
  InvocationHandler invocationHandler;

  public MybatisDaoFactory(SqlSessionFactory sqlSessionFactory) {
    invocationHandler = (proxy, method, args) -> {
      // 파라미터로 넘겨받은 프록시 객체와 메서드를 이용하여
      // 실행할 SQL 아이디를 알아낸다.
      // 인터페이스명.메서드명 => SQL의 namespace.id
      Class<?> clazz = proxy.getClass();
      Class<?> daoInterface = clazz.getInterfaces()[0];

      String interfaceName = daoInterface.getName();
      String methodName = method.getName();

      String sqlId = String.format("%s.%s", interfaceName, methodName);
      System.out.println(sqlId);

      try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        Class<?> returnType = method.getReturnType();
        // 파라미터로 넘겨받은 메서드의 리턴 타입을 알아낸다.
        // 리턴 타입에 따라 호출할 메서드를 결정한다.
        if (returnType == List.class) {
          return (args == null) ? sqlSession.selectList(sqlId) :
            sqlSession.selectList(sqlId, args[0]);

        } else if (returnType == void.class) {
          return sqlSession.update(sqlId, args[0]);

        } else {
          return sqlSession.selectOne(sqlId, args[0]);
        }
      }
    };
  }

  // 객체 생성 과정이 복잡한 경우 이와 같이 factory method가 실행하도록 만든다.
  // T가 필드가 아닌 Class의 '타입 파라미터'임을 알려줄 수 있도록 리턴 타입 앞에 <T>라고 선언한다.
  @SuppressWarnings("unchecked")
  public <T> T createDao(Class<T> daoInterface) {
    return (T) Proxy.newProxyInstance(
        this.getClass().getClassLoader(),
        new Class[] {daoInterface},
        invocationHandler);
  }
}