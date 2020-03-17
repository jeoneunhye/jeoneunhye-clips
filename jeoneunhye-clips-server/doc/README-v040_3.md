# 40_3 - Connection을 Thread에 보관하기: Transaction 적용하기

여러 개의 데이터 변경(insert/update/delete) 작업을 한 단위로 다루려면
그 작업을 수행할 때 같은 Connection을 사용해야 한다.  
클라이언트 요청이 들어오면 Thread가 그 요청 처리를 담당한다.  
따라서 Thread가 실행되는 동안 수행하는 데이터 변경 작업을
Transaction으로 묶고 싶다면 같은 Connection을 사용해야 한다.  
이런 이유로 Thread에 Connection 객체를 보관하는 것이다.  

Thread에 Connection을 보관하면 Thread가 실행하는 동안
같은 Connection을 사용하여 데이터 변경 작업을 수행하면  
한 단위의 작업으로 묶어 제어할 수 있게 된다.
즉 모든 작업이 성공했을 때 테이블에 그 결과를 적용하고,
단 한 개의 작업이라도 실패하면 이전에 했던 작업을 모두 취소하는 것이 가능해진다.

### 메서드 별로 Connection을 개별화한 상태에서 Transaction 적용하기

server 39_1 ~ 40_2 버전에서 Connection을 메서드에서 준비하여 사용하였다.  
이런 관계로 PhotoBoardAddServlet/PhotoBoardUpdateServlet/PhotoBoardDeleteServlet에
있었던 Transaction 처리 코드를 제거하였다.  
다시 현 상태에서 Transaction 제어 코드를 추가한다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardDeleteServlet.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) PhotoBoardAddServlet에 Transaction을 적용한다.

- PhotoBoardAddServlet 클래스 변경
    - ConnectionFactory 인스턴스 필드를 추가하고 생성자로 주입받는다.
    - ConnectionFactory를 통해 Connection을 얻은 후에 Transaction을 제어한다.

### 작업2) PhotoBoardUpdateServlet에 Transaction을 적용한다.

- PhotoBoardUpdateServlet 클래스 변경
    - ConnectionFactory 인스턴스 필드를 추가하고 생성자로 주입받는다.
    - ConnectionFactory를 통해 Connection을 얻은 후에 Transaction을 제어한다.

### 작업3) PhotoBoardDeleteServlet에 Transaction을 적용한다.

- PhotoBoardDeleteServlet 클래스 변경
    - ConnectionFactory 인스턴스 필드를 추가하고 생성자로 주입받는다.
    - ConnectionFactory를 통해 Connection을 얻은 후에 Transaction을 제어한다.

### 작업4) Transaction 작업을 수행할 Servlet 객체에 ConnectionFactory를 주입한다.

- ServerApp 클래스 변경
    - PhotoBoardAddServlet, PhotoBoardUpdateServlet, PhotoBoardDeleteServlet 객체에
    ConectionFactory를 추가로 주입한다.

### 작업5) '/photoboard/add', '/photoboard/update', '/photoboard/delete' 요청을 테스트한다.