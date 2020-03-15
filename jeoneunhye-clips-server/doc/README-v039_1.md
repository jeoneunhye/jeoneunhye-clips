# 39_1 - Connection 개별화하기: 메서드 호출마다 DBMS와 연결하기

### Connection을 공유할 때 발생하는 문제점

멀티 스레드로 작업을 처리하는 서버가 하나의 Connection 객체를 공유하면  
다중 클라이언트의 요청을 동시에 처리할 때  
스레드간의 작업 결과가 제대로 반영되지 않는 문제가 발생한다.  
같은 Connection 객체를 사용하면 같은 클라이언트가 요청한 것으로 인식하기 때문에  
메서드 호출시마다 Connection을 연결하도록 변경한다.

### 메서드마다 커넥션을 구분하는 방식의 문제점: 트랜잭션 불가

메서드마다 별도의 커넥션을 사용하기 때문에  
PhotoBoardDao의 insert()와 PhotoFileDao의 insert()를 한 단위 작업으로 묶을 수 없다.  
즉 사진 게시글 입력과 첨부 파일 입력을 한 단위의 작업으로 다룰 수 없다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경

### 작업1) 각 메서드를 호출할 때 DBMS와 연결한다.

- DataLoaderListener 클래스 변경
    - Connection 객체를 생성하지 않는다.
    - 대신 DBMS 연결 정보(jdbcUrl, username, password)를 각각 준비하여 DAO 구현체를 생성할 때 넘겨준다.

- XxxDaoImpl 클래스 변경
    - 생성자에서 파라미터로 Connection 객체를 받는 대신에 DB 연결 정보를 받는다. 
    - 각 메서드에서 JDBC URL과 username, password를 사용하여 DBMS에 연결한다.

- PhotoBoardAddServlet, PhotoBoardUpdateServlet, PhotoBoardDeleteServlet 클래스 변경
    - DataLoaderListener의 Connection 객체로 수행하던 트랜잭션 작업 코드를 삭제한다.