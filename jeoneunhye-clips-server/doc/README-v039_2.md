# 39_2 - Connection 개별화하기: 커넥션 객체 생성에 Factory Method 패턴 적용하기

### Factory Method Design Pattern

객체 생성 과정이 복잡할 경우에 사용하는 설계 기법이다.  
new 연산자를 이용하여 직접 객체를 생성하는 대신 메서드를 통해 객체를 리턴받는다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/util/ConnectionFactory.java 추가
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoFileDaoImpl.java 변경

### 작업1) ConnectionFactory에서 Connection 객체를 만드는 팩토리 메서드를 정의한다.

Connection 객체는 DriverManager를 통해 생성하는데,  
이전 버전에서 DAO에서 url 연결 방식을 설정하기 때문에 연결 방식을 변경할 때마다 
DAO 구현체를 모두 변경해야 하는 불편함이 있다.  
이를 해결하기 위해 커넥션 객체 생성을 별도의 클래스 ConnectionFactory에게 위임한다.  
그리고 메서드를 통해 Connection 객체를 얻는다.

- ConnectionFactory 클래스 추가
    - Connection 객체를 생성하는 팩토리 메서드 getConnection 정의한다.  
    생성자로 받은 url 연결 방식을 이용해 DriveManager로 Connection 객체를 얻어 리턴한다.
- DataLoaderListener 클래스 변경
    - ConnectionFactory 객체를 생성한다.
    - DAO 구현체에 ConnectionFactory 객체를 주입한다.
- XxxDaoImpl 클래스 변경
    - 생성자에서 ConnectionFactory 객체를 받는다.
    - 직접 Connection 객체를 생성하는 대신 ConnectionFactory 객체의 메서드 getConnection()을 호출하여
  Connection 객체를 얻는다.