# 56_6 - Cookie를 활용하여 사용자 이메일을 보관하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/servlet/LoginServlet.java 변경

### 작업1) 쿠키를 이용하여 로그인 처리 후에 사용자 이메일을 웹 브라우저로 보내기

- LoginServlet 클래스 변경
    - 로그인을 처리할 때 클라이언트가 보낸 이메일을 쿠키로 보관하게 한다.