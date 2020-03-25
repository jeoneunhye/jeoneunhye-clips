# 43_2 - Mybatis + Transaction 적용

## 작업 소스 및 결과

- src/main/java/jeoneunhye/sql/SqlSessionProxy.java 추가
- src/main/java/jeoneunhye/sql/SqlSessionFactoryProxy.java 추가
- src/main/java/jeoneunhye/sql/PlatformTransactionManager.java 변경
- src/main/java/jeoneunhye/sql/DataSource.java 삭제
- src/main/java/jeoneunhye/sql/ConnectionProxy.java 삭제
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoFileDaoImpl.java 변경

### 작업1) SqlSession 프록시를 생성한다.

- SqlSessionProxy 클래스 추가
    - SqlSession 인터페이스를 구현한다.
    - SqlSession 구현체를 생성자로 주입받는다.
    - close()를 호출할 때 닫지 않도록 오버라이딩한다.

### 작업2) SqlSessionFactory 프록시를 생성한다.

- SqlSessionFactoryProxy 클래스 추가
    - SqlSessionFactory 인터페이스를 구현한다.
    - 생성한 SqlSession 객체를 Thread에 보관하기 위해 ThreadLocal 필드를 추가한다.
    - openSession(boolean) 메서드를 오버라이딩한다.

### 작업3) PlatformTransactionManager를 변경한다.

- PlatformTransactionManager 클래스 변경
    - 생성자로 주입받는 객체를 DataSource에서 SqlSessionFactory로 변경한다.
    - beginTransaction(), commit(), rollback() 메서드가 Connection이 아닌 SqlSession을 다루도록 변경한다.
- DataLoaderListener 클래스 변경
    - DataSource 객체를 준비하고 종료시 clean()을 호출하는 코드를 삭제한다. 
    - 트랜잭션 제어 기능을 수행하기 위해 SqlSessionFactory의 오리지날 객체를 프록시 객체에 담아 사용한다.
    - SqlSessionFactory 객체를 ServerApp에서 사용할 수 있도록 context Map에 담는다.
    - PlatformTransactionManager를 준비할 때 DataSource 대신 SqlSessionFactory를 주입한다.
- DataSource 클래스 삭제
    - Mybatis로 교체하고 더이상 사용되지 않는 클래스를 삭제한다.
- ConnectionProxy 클래스 삭제
    - Mybatis로 교체하고 더이상 사용되지 않는 클래스를 삭제한다.

### 작업4) Thread 작업을 종료했을 때 SqlSession을 진짜로 닫는다.

- ServerApp 클래스 변경
    - DataSource 객체를 받아 realClose()를 호출하는 코드를 삭제한다.
    - Thread의 작업이 종료하는 시점에 SqlSessionFactoryProxy에서 오버라이딩한 closeSession()을 호출한다.

### 작업5) DAO에서 commit()/rollback()을 제거한다.

- XxxDaoImpl 클래스 변경
    - 위 작업을 통해 트랜잭션 제어는 PlatformTransactionManager를 통해 기능하도록 만들었다.  
    DAO에서 commit(), rollback()을 호출하는 코드를 제거한다.