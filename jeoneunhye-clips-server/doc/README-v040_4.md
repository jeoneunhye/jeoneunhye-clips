# 40_4 - Connection을 Thread에 보관하기: Transaction 관리자 도입하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/sql/PlatformTransactionManager.java 추가
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardDeleteServlet.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) Transaction 제어 코드를 캡슐화한다.

- PlatformTransactionManager 클래스 추가
    - ConnectionFactory 객체를 생성자로 주입받는다.
    - Transaction을 시작하는 beginTransaction(), commit(), rollback() 메서드를 정의한다.

### 작업2) PhotoBoardAddServlet에 Transaction 관리자를 적용한다.

- PhotoBoardAddServlet 클래스 변경
    - PlatformTransactionManager를 주입받는다.
    - Transaction 관리자를 통해 Transaction을 제어한다.

### 작업3) PhotoBoardUpdateServlet에 Transaction 관리자를 적용한다.

- PhotoBoardUpdateServlet 클래스 변경
    - PlatformTransactionManager를 주입받는다.
    - Transaction 관리자를 통해 Transaction을 제어한다.

### 작업4) PhotoBoardDeleteServlet에 Transaction 관리자를 적용한다.

- PhotoBoardDeleteServlet 클래스 변경
    - PlatformTransactionManager를 주입받는다.
    - Transaction 관리자를 통해 Transaction을 제어한다.

### 작업5) DataLoaderListener에서 Transaction 관리자를 준비한다.

- DataLoaderListener 클래스 변경
    - PlatformTransactionManager 객체를 생성한다.
    - ServerApp에서 사용할 수 있도록 Context Map에 담는다.

### 작업6) ConnectionFactory 대신 Transaction 관리자를 Servlet 객체에 주입한다.

- ServerApp 클래스 변경
    - Context Map에서 PlatformTransactionManager 객체를 꺼낸다.
    - 꺼낸 객체를 PhotoBoardAdd, PhotoBoardUpdate, PhotoBoardDelete 객체에 주입한다.

### 작업7) '/photoboard/add', '/photoboard/update', '/photoboard/delete' 요청을 테스트한다.