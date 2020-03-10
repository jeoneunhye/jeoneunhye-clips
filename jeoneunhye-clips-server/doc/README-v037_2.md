# 37_2 - Application Server 구조로 변경: 통신 규칙1 + DAO + Servlet 적용

### Application Server Architecture

클라이언트가 서버에서 처리한 데이터를 받아 출력하는 작업을 서버가 위임하도록 한다.  
클라이언트에서는 UI만 제공하고 요청을 전송하면 서버가 모든 작업을 수행하기 때문에  
기능을 변경한 즉시 클라이언트에서 사용이 가능하다.  
Web을 추가하면 Web Application Server(WAS)가 된다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 추가
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 추가
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 추가
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoListServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberListServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardListServlet.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) MariaDB JDBC Driver를 프로젝트에 추가한다.

- build.gradle 변경
    - mvnrepository.com 또는 search.maven.org에서 'mariadb jdbc'를 검색한다.
    - 최신 라이브러리 정보를 dependencies {} 블록에 추가한다.
- 프로젝트의 이클립스 설정 파일 갱신
    - 'gradle cleanEclipse' 명령으로 기존 이클립스 설정을 제거한다.
    - 'gradle eclipse' 명령으로 이클립스 설정 파일을 생성한다.
    - 이클립스 IDE에서 프로젝트를 refresh한다.
- 프로젝트의 Referenced Libraries에 MariaDB가 추가되었는지 확인한다.

### 작업2) 클라이언트 프로젝트에서 MariaDB와 데이터를 통신하는 객체를 가져 온다.

- jeoneunhye.vms.dao.mariadb 패키지 생성
- jeoneunhye.vms.dao.mariadb.VideoDaoImpl 클래스 복사
- jeoneunhye.vms.dao.mariadb.MemberDaoImpl 클래스 복사
- jeoneunhye.vms.dao.mariadb.BoardDaoImpl 클래스 복사

### 작업3) Connection 객체를 준비하여 DAO 객체를 생성할 때 주입한다.

- jeoneunhye.vms.DataLoaderListener 클래스 변경
    - 애플리케이션이 실행되면 Connection 객체를 생성하는 메서드를 정의한다.
    - sql문으로 mariadb와 데이터를 통신하는 XxxDaoImpl 객체를 준비한다.

### 작업4) 통신 규칙1에 따라 동작하도록 VideoListServlet을 변경한다.

- jeoneunhye.vms.servlet.Servlet 인터페이스 변경
    - service(Scanner in, PrintStream out) 메서드를 선언한다.
    - 기존 구현체가 영향받지 않도록 default로 선언한다.
- jeoneunhye.vms.servlet.VideoListServlet 클래스 변경
    - service(Scanner in, PrintStream out) 메서드를 정의한다.
    - 통신 규칙1에 따라 클라이언트에게 결과를 응답하도록 변경한다.
        - 클라이언트의 VideoListCommand 클래스에서 코드를 복사한다.
        - DAO로부터 영상 데이터가 담긴 List를 받아 클라이언트가 출력할 내용을 전송한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - '/video/list' 요청을 처리할 VideoListServlet을 ServletMap에 등록한다.
    - 클라이언트 명령을 처리할 서블릿을 찾아 실행한다.

### 작업5) 통신 규칙1에 따라 동작하도록 MemberListServlet을 변경한다.

- jeoneunhye.vms.servlet.Servlet 인터페이스 변경
    - service(Scanner in, PrintStream out) 메서드를 선언한다.
    - 기존 구현체가 영향받지 않도록 default로 선언한다.
- jeoneunhye.vms.servlet.MemberListServlet 클래스 변경
    - service(Scanner in, PrintStream out) 메서드를 정의한다.
    - 통신 규칙1에 따라 클라이언트에게 결과를 응답하도록 변경한다.
        - 클라이언트의 MemberListCommand 클래스에서 코드를 복사한다.
        - DAO로부터 회원 데이터가 담긴 List를 받아 클라이언트가 출력할 내용을 전송한다.
  
### 작업6) 통신 규칙1에 따라 동작하도록 BoardListServlet을 변경한다.

- jeoneunhye.vms.servlet.Servlet 인터페이스 변경
    - service(Scanner in, PrintStream out) 메서드를 선언한다.
    - 기존 구현체가 영향받지 않도록 default로 선언한다.
- jeoneunhye.vms.servlet.BoardListServlet 클래스 변경
    - service(Scanner in, PrintStream out) 메서드를 정의한다.
    - 통신 규칙1에 따라 클라이언트에게 결과를 응답하도록 변경한다.
        - 클라이언트의 VideoListCommand 클래스에서 코드를 복사한다.
        - DAO로부터 게시글 데이터가 담긴 List를 받아 클라이언트가 출력할 내용을 전송한다.

### 작업7) 클라이언트의 목록 조회 요청을 XxxListServlet으로 처리한다.

- jeoneunhye.vms.ServerApp 클래스 변경
    - '/video/list' 요청을 처리할 VideoListServlet을 ServletMap에 등록한다.
    - '/member/list' 요청을 처리할 MemberListServlet을 ServletMap에 등록한다.
    - '/board/list' 요청을 처리할 BoardListServlet을 ServletMap에 등록한다.
    - 클라이언트 명령을 처리할 Servlet을 찾아 실행한다.