# 44_1 - UI 객체에서 비즈니스 로직 분리하기

이전 Servlet의 역할은 이와 같다.  
1. 클라이언트가 출력할 UI 담당: 서버 렌더링 방식
2. 트랜잭션 제어
3. DB와 연동하여 클라이언트의 요청 수행

### Presentation Layer

- UI를 담당한다.

### Business(Service) Layer

- 업무 로직을 담당한다.
- 트랜잭션 제어를 담당한다.

### Persistence Layer

- 데이터 저장을 담당한다.

비즈니스 로직을 별도의 클래스로 분리하면,
UI 구현 방식이 변경되더라도 비즈니스 로직을 재사용할 수 있다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/service/VideoService.java 추가
- src/main/java/jeoneunhye/vms/service/MemberService.java 추가
- src/main/java/jeoneunhye/vms/service/BoardService.java 추가
- src/main/java/jeoneunhye/vms/service/PhotoBoardService.java 추가
- src/main/java/jeoneunhye/vms/service/impl/VideoServiceImpl.java 추가
- src/main/java/jeoneunhye/vms/service/impl/MemberServiceImpl.java 추가
- src/main/java/jeoneunhye/vms/service/impl/BoardServiceImpl.java 추가
- src/main/java/jeoneunhye/vms/service/impl/PhotoBoardServiceImpl.java 추가
- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) VideoXxxServlet에서 비즈니스 로직을 분리한다.

- VideoService 인터페이스 추가
    - add(Video), list(), get(int), update(Video), delete(int), search(HashMap<String, Object>) 메서드를 정의한다.
- VideoServiceImpl 클래스 추가
    - 생성자에서 DAO 객체를 받고, Servlet에서 수행하던 비즈니스 로직 코드를 옮겨와 여기서 담당한다.
    - Service 객체의 메서드들은 별도의 작업을 하지 않고 DAO의 메서드를 호출하여 리턴받은 값을 리턴할 뿐이다.  
    Servlet에서 DAO가 아닌 Service를 사용하도록 구조를 변경했기 때문에 일관성을 유지하기 위한 것이다.
- VideoXxxServlet 클래스 변경
    - 생성자로 받는 객체를 DAO에서 Service로 변경한다.
    - 클라이언트로부터 데이터를 입력받는 코드와 Service가 작업하고 UI에 출력할 문구만 남긴다.
- DataLoaderListener 클래스 변경
    - DAO 객체를 생성하고 ServerApp에서 사용할 Service 객체에 DAO를 주입하여 Context Map에 담는다.
- ServerApp 클래스 변경
    - Service 객체를 Context Map에서 꺼내 Servlet에 주입한다.

### 작업2) MemberXxxServlet에서 비즈니스 로직을 분리한다.

- MemberService 인터페이스 추가
    - add(Member), list(), get(int), update(Member), delete(int),
    get(String, String), search(String) 메서드를 정의한다.
- MemberServiceImpl 클래스 추가
    - 생성자에서 DAO 객체를 받고, Servlet에서 수행하던 비즈니스 로직 코드를 옮겨와 여기서 담당한다.
    - Service 객체의 메서드들은 별도의 작업을 하지 않고 DAO의 메서드를 호출하여 리턴받은 값을 리턴할 뿐이다.  
    Servlet에서 DAO가 아닌 Service를 사용하도록 구조를 변경했기 때문에 일관성을 유지하기 위한 것이다.
- MemberXxxServlet, LoginServlet 클래스 변경
    - 생성자로 받는 객체를 DAO에서 Service로 변경한다.
    - 클라이언트로부터 데이터를 입력받는 코드와 Service가 작업하고 UI에 출력할 문구만 남긴다.
- DataLoaderListener 클래스 변경
    - DAO 객체를 생성하고 ServerApp에서 사용할 Service 객체에 DAO를 주입하여 Context Map에 담는다.
- ServerApp 클래스 변경
    - Service 객체를 Context Map에서 꺼내 Servlet에 주입한다.

### 작업3) BoardXxxServlet에서 비즈니스 로직을 분리한다.

- BoardService 인터페이스 추가
    - add(Board), list(), get(int), update(Board), delete(int) 메서드를 정의한다.
- BoardServiceImpl 클래스 추가
    - 생성자에서 DAO 객체를 받고, Servlet에서 수행하던 비즈니스 로직 코드를 옮겨와 여기서 담당한다.
    - Service 객체의 메서드들은 별도의 작업을 하지 않고 DAO의 메서드를 호출하여 리턴받은 값을 리턴할 뿐이다.  
    Servlet에서 DAO가 아닌 Service를 사용하도록 구조를 변경했기 때문에 일관성을 유지하기 위한 것이다.
- BoardXxxServlet 클래스 변경
    - 생성자로 받는 객체를 DAO에서 Service로 변경한다.
    - 클라이언트로부터 데이터를 입력받는 코드와 Service가 작업하고 UI에 출력할 문구만 남긴다.
- DataLoaderListener 클래스 변경
    - DAO 객체를 생성하고 ServerApp에서 사용할 Service 객체에 DAO를 주입하여 Context Map에 담는다.
- ServerApp 클래스 변경
    - Service 객체를 Context Map에서 꺼내 Servlet에 주입한다.

### 작업4) PhotoBoardXxxServlet에서 비즈니스 로직과 트랜잭션 코드를 분리한다.

- PhotoBoardService 인터페이스 추가
    - add(PhotoBoard), listVideoPhoto(int), get(int), update(PhotoBoard), delete(int) 메서드를 정의한다.
- PhotoBoardServiceImpl 클래스 추가
    - 생성자에서 PhotoBoardDao, PhotoFileDao 객체와 트랜잭션을 위한 PlatformTransactionManager 객체를 받고, Servlet에서 수행하던 비즈니스 로직 및 트랜잭션 제어 코드를 옮겨와 여기서 담당한다.
- PhotoBoardAddServlet 클래스 변경
    - PhotoBoardDao, VideoDao 객체를 사용하여 수행하던 비즈니스 로직과 트랜잭션 제어 코드를 Service 객체로 옮기고 사용한다.
    - 클라이언트로부터 데이터를 입력받는 코드와 Service가 작업하고 UI에 출력할 문구만 남긴다.
- PhotoBoardListServlet 클래스 변경
    - PhotoBoardDao, VideoDao 객체를 사용하여 수행하던 비즈니스 로직을 Service 객체로 옮기고 그 객체를 사용한다.
    - 클라이언트로부터 데이터를 입력받는 코드와 Service가 작업하고 UI에 출력할 문구만 남긴다.
- PhotoBoardDetailServlet 클래스 변경
    - PhotoBoardDao 객체를 사용하여 수행하던 비즈니스 로직을 Service 객체로 옮기고 그 객체를 사용한다.
    - 클라이언트로부터 데이터를 입력받는 코드와 Service가 작업하고 UI에 출력할 문구만 남긴다.
- PhotoBoardUpdateServlet, PhotoBoardDeleteServlet 클래스 변경
    - PhotoBoardDao 객체를 사용하여 수행하던 비즈니스 로직과 트랜잭션 제어 코드를 Service 객체로 옮기고 사용한다.
    - 클라이언트로부터 데이터를 입력받는 코드와 Service가 작업하고 UI에 출력할 문구만 남긴다.
- DataLoaderListener 클래스 변경
    - DAO 객체를 생성하고 ServerApp에서 사용할 Service 객체에 DAO를 주입하여 Context Map에 담는다.
- ServerApp 클래스 변경
    - Service 객체를 Context Map에서 꺼내 Servlet에 주입한다.