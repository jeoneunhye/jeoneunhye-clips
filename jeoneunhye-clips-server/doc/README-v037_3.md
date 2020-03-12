# 37_3 - Application Server 구조로 변경: 통신 규칙2 추가 및 Servlet, DAO에 적용 

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/servlet/VideoAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoDetailServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoDeleteServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberDetailServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberDeleteServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardDetailServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardDeleteServlet.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 서버가 클라이언트에게 추가 데이터 입력을 요구할 수 있도록 통신 규칙을 변경한다. 

프로토콜 통신 규칙2) 사용자에게 추가 입력을 요구

```
[클라이언트]                                        [서버]
서버에 연결 요청         -------------->            연결 승인
명령(CRLF)             -------------->           명령처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 작업2) 통신 규칙2에 따라 번호를 입력받을 수 있도록 XxxDetailServlet을 변경한다.

- jeoneunhye.vms.servlet.XxxDetailServlet 클래스 변경
    - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현한다.
    - 통신 규칙2에 따라 클라이언트에게 상세 조회할 데이터의 번호를 요구한다.
    - 통신 규칙1에 따라 응답한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - '/xxx/detail' 명령을 처리할 서블릿을 맵에 추가한다.

### 작업3) 통신 규칙2에 따라 데이터를 입력받을 수 있도록 XxxAddServlet을 변경한다.

- jeoneunhye.vms.servlet.XxxAddServlet 클래스 변경
    - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현한다.
    - 통신 규칙2에 따라 클라이언트에게 데이터를 요구한다.
    - 통신 규칙1에 따라 응답한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - '/xxx/add' 명령을 처리할 서블릿을 맵에 추가한다.

### 작업4) 통신 규칙2에 따라 데이터를 변경할 수 있도록 XxxUpdateServlet을 변경한다.

- jeoneunhye.vms.servlet.XxxUpdateServlet 클래스 변경
    - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현한다.
    - 통신 규칙2에 따라 클라이언트에게 데이터 변경을 요구한다.
    - 통신 규칙1에 따라 응답한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - '/xxx/update' 명령을 처리할 서블릿을 맵에 추가한다.

### 작업5) 통신 규칙2에 따라 데이터를 삭제할 수 있도록 XxxDeleteServlet을 변경한다.

- jeoneunhye.vms.servlet.XxxDeleteServlet 클래스 변경
    - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현한다.
    - 통신 규칙2에 따라 클라이언트에게 데이터의 번호를 요구한다.
    - 통신 규칙1에 따라 응답한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - '/xxx/delete' 명령을 처리할 서블릿을 맵에 추가한다.