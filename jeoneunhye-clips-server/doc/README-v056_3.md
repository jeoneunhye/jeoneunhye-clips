# 56_3 - Redirect와 Refresh의 활용

### Redirect와 Refresh

- Redirect

클라이언트의 요청을 받아 콘텐트를 보내는 대신 다른 페이지의 URL을 알려줄 때 사용한다.  
웹 브라우저는 응답받는 즉시 해당 페이지를 요청한다.  
웹 서버로부터 콘텐트(message-body)를 받지 않았기 때문에 어떤 것도 출력하지 않고 바로 다른 페이지로 이동한다.

- Refresh

서버로부터 응답을 받고 내용을 출력한 후 일정 시간이 지나면 특정 URL을 요청하도록 한다.  
보통 웹 페이지를 자동으로 이동시키고 싶을 때 사용한다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/service/VideoService.java 변경
- src/main/java/jeoneunhye/vms/service/MemberService.java 변경
- src/main/java/jeoneunhye/vms/service/BoardService.java 변경
- src/main/java/jeoneunhye/vms/service/impl/VideoServiceImpl.java 변경
- src/main/java/jeoneunhye/vms/service/impl/MemberServiceImpl.java 변경
- src/main/java/jeoneunhye/vms/service/impl/BoardServiceImpl.java 변경
- src/main/java/jeoneunhye/vms/servlet/ErrorServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/XxxAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/XxxUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/XxxDeleteServlet.java 변경

### 작업1) 오류 내용을 출력할 서블릿을 만든다.

- ErrorServlet 클래스 추가
    - 세션에 보관된 메시지를 출력한다.

### 작업2) 추가/변경/삭제 서블릿에 리프래시, 리다이렉트를 적용한다.

- XxxService 인터페이스 및 XxxServiceImpl 클래스 변경
    - Service 인터페이스와 구현체의 메서드 add, update, delete의 리턴 타입을 int로 변경한다.

- XxxAddServlet, XxxUpdateServlet, XxxDeleteServlet 클래스 변경
    - 정상적으로 실행했을 경우 목록 화면으로 리다이렉트한다.
    - 오류가 발생했을 경우 ErrorServlet으로 리다이렉트한다.