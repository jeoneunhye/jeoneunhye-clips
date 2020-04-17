# 56_4 - Forwarding과 Including 활용

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/servlet/HeaderServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/FooterServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경

### 작업1) 오류가 발생하면 ErrorServlet으로 포워딩한다.

- XxxServlet 클래스 변경
    - 정상적으로 실행했을 경우 목록 창으로 리다이렉트한다.
    - 오류가 발생했을 경우 ErrorServlet으로 포워딩한다.
- ErrorServlet 클래스 변경
    - ServletRequest 보관소에서 오류 객체를 꺼내 오류 내용을 출력한다.
    - doGet 메서드 대신 GET/POST 요청을 모두 처리할 수 있는 service 메서드를 오버라이딩한다.

### 작업2) 화면의 상단과 하단을 출력할 서블릿을 만들고 각 페이지에 포함한다.

- HeaderServlet 클래스 추가
- FooterServlet 클래스 추가
- XxxServlet 클래스 변경
    - Header와 Footer를 인클루딩한다.