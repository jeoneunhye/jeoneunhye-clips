# 41_1 - Connection Pool 도입하기

### Pooling 기법

생성된 객체를 사용한 후에 버리지 않고 보관한다.  
객체가 필요할 때마다 빌려서 쓰고, 쓰고 난 후에는 반납한다.  
이점:
  - 객체 생성에 소요되는 시간을 줄일 수 있다.
  - 가비지 생성을 억제하기 때문에 메모리 낭비를 줄일 수 있다.  

ex) DB Connection Pool, Thread Pool 등  
GoF의 'Flyweight' 디자인 패턴과 유사하다.

### 작업 소스 및 결과

- src/main/java/jeoneunhye/util/ConnectionFactory.java 삭제
- src/main/java/jeoneunhye/sql/DataSource.java 추가
- src/main/java/jeoneunhye/sql/PlatformTransactionManager.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) DB Connection Pool 객체를 생성한다.

- DataSource 클래스 추가
    - JDBC API에서는 Connection 객체를 생성하고 관리하는 역할자를 DataSource로 정의하였다.  
      클래스명을 ConnectionFactory에서 DataSource로 변경한다.  
    => ConnectionFactory + Pooling 기능 = DataSource
    - 반납받은 Connection을 보관할 ArrayList를 준비한다.
    - Thread에서 Connection을 삭제하고 객체를 재사용하기 위해 List에 보관한다.
    - 애플리케이션을 종료할 때 호출할 clean()을 정의한다.  
    List에 보관된 Connection 객체를 모두 꺼내 종료한다.

### 작업2) PlatformTransactionManager가 DataSource를 사용하도록 변경한다.

- PlatformTransactionManager 클래스 변경
    - ConnectionFactory 대신 DataSource를 사용하도록 변경한다.

### 작업3) DAO가 DataSource를 사용하도록 변경한다.

- XxxDaoImpl 클래스 변경
    - ConnectionFactory 대신 DataSource를 사용하도록 변경한다.

### 작업4) DataSource를 DAO에 주입한다.

- DataLoaderListener 클래스 변경
    - ConnectionFactory 대신 DataSource를 사용하도록 변경한다.
    - DAO에 ConnectionFactory 대신 DataSource를 주입한다.
    - DataSource.clean()을 호출하여 애플리케이션이 종료될 때 모든 DB Connection을 닫는다.

### 작업5) 클라이언트 요청을 처리한 후 Connection을 닫지 말고 반납한다.

- ServerApp 클래스 변경
    - 클라이언트에게 응답한 후 Thread에서 Connection 객체를 제거한다.
    - 제거된 Connection 객체는 재사용하기 위해 닫지 않는다.

### 작업6) '/photoboard/add', '/photoboard/update', '/photoboard/delete' 요청을 테스트한다.