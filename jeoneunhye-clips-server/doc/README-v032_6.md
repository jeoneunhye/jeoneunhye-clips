32_6 - 커맨드 패턴을 적용하여 요청 처리 메서드를 객체화하기 
===

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/servlet/Servlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/BoardListServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/BoardAddServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/BoardDetailServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/BoardUpdateServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/BoardDeleteServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoListServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoAddServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoDetailServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoUpdateServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoDeleteServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/MemberListServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/MemberAddServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/MemberDetailServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/MemberUpdateServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/MemberDeleteServlet.java 추가
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 커맨드 패턴의 인터페이스를 정의한다.

- jeoneunhye.vms.servlet 패키지 생성
- jeoneunhye.vms.servlet.Servlet 인터페이스를 정의한다.
    - Servlet 객체의 메서드를 호출하는 쪽인 ServerApp에서 입출력 스트림을 아규먼트로 넘겨주는 service(ObjectInputStream, ObjectOutputStream) 메서드를 정의한다.

### 작업2) 각각의 요청 처리 메서드를 인터페이스 규칙에 따라 클래스로 정의하여 객체화한다.
 
- *Servlet.java 추가
    - Servlet 인터페이스를 구현하는 클래스를 생성하고, 각각의 요청 처리 메서드 코드를 service 메서드 안에서 재정의한다.  
```
listVideo()를 VideoListServlet 클래스로 정의한다.  
addVideo()를 VideoAddServlet 클래스로 정의한다.  
detailVideo()를 VideoDetailServlet 클래스로 정의한다.  
updateVideo()를 VideoUpdateServlet 클래스로 정의한다.  
deleteVideo()를 VideoDeleteServlet 클래스로 정의한다.  
listMember()를 MemberListServlet 클래스로 정의한다.  
addMember()를 MemberAddServlet 클래스로 정의한다.  
detailMember()를 MemberDetailServlet 클래스로 정의한다.  
updateMember()를 MemberUpdateServlet 클래스로 정의한다.  
deleteMember()를 MemberDeleteServlet 클래스로 정의한다.  
listBoard()를 BoardListServlet 클래스로 정의한다.  
addBoard()를 BoardAddServlet 클래스로 정의한다.  
detailBoard()를 BoardDetailServlet 클래스로 정의한다.  
updateBoard()를 BoardUpdateServlet 클래스로 정의한다.  
deleteBoard()를 BoardDeleteServlet 클래스로 정의한다.  
```

### 작업3) Map을 사용하여 클라이언트가 요청한 해당 요청 처리 객체를 꺼내 메서드를 호출한다. 

- ServerApp.java 변경
    - 클라이언트의 명령어를 key로 받아 Servlet 객체를 꺼낼 HashMap을 생성한다.
    - service 메서드를 호출할 때 입출력 스트림을 파라미터로 넘겨줄 수 있도록, 통신을 위한 소켓과 입출력 스트림을 준비한 이후의 시점으로 코드를 둔다.