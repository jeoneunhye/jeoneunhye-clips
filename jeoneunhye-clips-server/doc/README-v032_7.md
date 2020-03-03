32_7 - 데이터 처리 코드를 별도의 클래스로 정의하여 객체화하기
===

### DAO(Data Access Object)

- 데이터 처리를 수행하는 객체다.
- 데이터 처리 방식을 캡슐화하여 객체를 일관성있게 사용한다.
- 즉 데이터 처리 방식(배열, 스택, 큐, 맵, 파일, 데이터베이스 등)에 상관없이
    클래스로 포장(캡슐화)하여 메서드 호출 방식을 통일되게 만든다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/VideoObjectFileDao.java 추가
- src/main/java/jeoneunhye/vms/dao/MemberObjectFileDao.java 추가
- src/main/java/jeoneunhye/vms/dao/BoardObjectFileDao.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 데이터를 처리하는 DAO 클래스를 정의한다.

- jeoneunhye.vms.dao 패키지 생성
    - VideoObjectFileDao, MemberObjectFileDao, BoardObjectFileDao 클래스 생성

- XxxObjectFileDao.java 추가
    - 파일명을 생성자 파라미터로 받도록 하여 파일을 변경할 일이 있을 때 코드를 쉽게 바꿀 수 있도록 한다.
    - 해당 객체를 생성하면 List를 준비하고 파일의 데이터를 통째로 읽어 List에 담아 놓는다.  
    객체의 메서드를 호출하면 바로 데이터를 처리할 수 있도록 하는 준비 절차를 생성자 블록에서 하는 것이다. 
    - 각각의 Servlet 객체에서 수행하던 데이터 추가/조회/변경/삭제 코드를 해당 클래스의 메서드에 정의한다.  
    메서드는 `insert(), findAll(), findByNo(), update(), delete()`로 명명한다.

### 작업2) 영상 데이터를 처리하는 VideoObjectFileDao 객체를 적용한다.

- DataLoaderListener.java 변경
    - List 객체와 파일에서 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
    - 애플리케이션이 실행될 때 호출할 메서드에 VideoObjectFileDao 객체를 생성하도록 구현한다.
- ServerApp.java 변경
    - 사용하지 않는 List 인스턴스 필드를 제거한다.
    - Map으로 VideoObjectFileDao를 꺼내고 관련 Servlet 객체에 주입한다.
- VideoXxxServlet.java 변경
    - 생성자 파라미터로 List 객체를 받는 대신 VideoObjectFileDao 객체를 넘겨 받는다.
    - 영상 데이터를 저장, 조회, 변경, 삭제할 때 VideoObjectFileDao 객체의 메서드를 호출하여 처리한다.  
    Servlet 객체는 Dao 객체가 처리한 영상 데이터를 가지고 클라이언트와 송수신하는 기능을 수행하는 것이다.


### 작업3) 회원 데이터를 처리하는 MemberObjectFileDao 객체를 적용한다.

- DataLoaderListener.java 변경
    - List 객체와 파일에서 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
    - 애플리케이션이 실행될 때 호출할 메서드에 MemberObjectFileDao 객체를 생성하도록 구현한다.
- ServerApp.java 변경
    - 사용하지 않는 List 인스턴스 필드를 제거한다.
    - Map으로 MemberObjectFileDao를 꺼내고 관련 Servlet 객체에 주입한다.
- MemberXxxServlet.java 변경
    - 생성자 파라미터로 List 객체를 받는 대신 MemberObjectFileDao 객체를 넘겨 받는다.
    - 회원 데이터를 저장, 조회, 변경, 삭제할 때 MemberObjectFileDao 객체의 메서드를 호출하여 처리한다.  
    Servlet 객체는 Dao 객체가 처리한 회원 데이터를 가지고 클라이언트와 송수신하는 기능을 수행하는 것이다.

### 작업4) 게시글 데이터를 처리하는 BoardObjectFileDao 객체를 적용한다.

- DataLoaderListener.java 변경
    - List 객체와 파일에서 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
    - 애플리케이션이 실행될 때 호출할 메서드에 BoardObjectFileDao 객체를 생성하도록 구현한다.
- ServerApp.java 변경
    - 사용하지 않는 List 인스턴스 필드를 제거한다.
    - Map으로 BoardObjectFileDao를 꺼내고 관련 Servlet 객체에 주입한다.
- BoardXxxServlet.java 변경
    - 생성자 파라미터로 List 객체를 받는 대신 BoardObjectFileDao 객체를 넘겨 받는다.
    - 게시글 데이터를 저장, 조회, 변경, 삭제할 때 BoardObjectFileDao 객체의 메서드를 호출하여 처리한다.  
    Servlet 객체는 Dao 객체가 처리한 게시글 데이터를 가지고 클라이언트와 송수신하는 기능을 수행하는 것이다.