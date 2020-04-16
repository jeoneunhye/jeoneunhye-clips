# 56_2 - 서블릿을 이용하여 Spring IoC Container 준비하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ContextLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/servlet/AppInitServlet.java 추가
- src/main/webapp/WEB-INF/web.xml 추가

### 작업1) 서블릿에서 Spring IoC Container를 준비하여 공유하게 한다.

- ContextLoaderListener 클래스 변경
    - @WebListener 애노테이션을 주석으로 막아 Spring IoC Container를 준비하는 리스너를 비활성화한다.
- AppInitServlet 클래스 추가
    - HttpServlet을 상속받아 init()에 ServletContext에 Spring IoC Container를 준비하는 코드를 복사한다.
    - @WebServlet 애노테이션에 loadOnStartup 속성을 추가하여 웹 애플리케이션이 시작할 때 자동 생성되게 한다.
    - localhost:9999/jeoneunhye-clips-server/AppInitServlet을 접속하면 405번 응답 코드를 볼 수 있다.  
    => 클라이언트의 요청을 처리하는 용도가 아니기 때문에 service 또는 doGet, doPost 메서드를 오버라이딩하지 않았다.

#### 소스 정리

- v51_1에서 삭제하지 않은 sql.MybatisDaoFactory.java 삭제
- v55_1에서 삭제하지 않은 context.ApplicationContextListener.java 삭제