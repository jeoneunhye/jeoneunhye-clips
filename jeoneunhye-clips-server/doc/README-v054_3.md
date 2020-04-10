# 54_3 - Apache HttpComponents 라이브러리를 이용하여 웹 서버 구현하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경
- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경

### 작업1) Apache HttpComponents 라이브러리를 프로젝트에 적용한다.

- 라이브러리 정보 알아내기
    - search.maven.org에서 `httpclient5`를 검색한다.
- build.gradle 변경
    - 빌드 설정 파일의 의존 라이브러리 정보에 httpclient5 라이브러리를 추가한다.
- $ gradle eclipse 명령 실행
    - 의존 라이브러리를 프로젝트에 가져온다.
- Eclipse의 CLASSPATH 정보를 갱신한다.
    - 명령어를 실행한 후 이클립스에서 프로젝트를 갱신해야 한다.

### 작업2) HTTP 요청을 받을 때 HttpComponents 라이브러리에 있는 클래스를 사용하여 처리한다.

- ServerApp 클래스 변경

### 작업3) Servlet의 request handler의 파라미터를 변경한다.

- XxxSevlet 클래스 변경
    - request handler의 파라미터를 PrintStream에서 PrintWriter로 변경한다.