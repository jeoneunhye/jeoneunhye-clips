# 55_1 - JavaEE의 Servlet 기술 도입하기

### JavaSE, JavaEE, JavaME

JavaSE(Java Standard Edition)  
자바 프로그래밍 언어의 핵심 기능들을 제공하는 표준 에디션이다.  
기초적인 타입부터 네트워킹, 보안, 데이터베이스 처리, 그래픽 사용자 인터페이스 개발은 물론  
XML 파싱에 이르는 고수준의 클래스들을 모두 다룰 수 있다.  
주요 패키지로는 java.lang.*, java.io.*, java.util.*, java.awt.*, javax.rmi.*, javax.net.* 등이 있다.  

JavaEE(Java Enterprise Edition)  
자바로 구현되는 웹 프로그래밍에서 가장 많이 사용되는 JSP, Servlet을 비롯하여  
데이터베이스에 연동하는 JDBC, 그 외에도 JSTL, EL, EJB 등의 많은 기술들이 포함되어 있다.  

JavaMe(Java Micro Edition)  
모바일, IoT(Embedded)과 같은 소형 가상 머신으로 동작시킬 수 있는 기능과 API를 제공한다.  

### JavaEE 버전별 Tomcat, Servlet, JSP, EJB 지원 사양

Servlet, JSP 프로그래밍을 할 때는 사용중인 WAS 혹은 서블릿 컨테이너가  
어떤 버전의 Java EE 구현체인지 확인하고 그에 맞는 서블릿, JSP 버전의 API를 사용해야 한다.  

JavaEE 5(2006): Tomcat 6, Servlet 2.5, JSP 2.1, EJB 3.0  
JavaEE 6(2009): Tomcat 7, Servlet 3.0, JSP 2.2, EJB 3.1  
JavaEE 7(2013): Tomcat 8, Servlet 3.1, JSP 2.3, EJB 3.2  
JavaEE 8(2017): Tomcat 9, Servlet 4.0, JSP 2.3, EJB 3.2

### WAS(Web Application Server)와 Servlet Container

Servlet은 자바 기반의 CGI 프로그램으로 CGI 규칙에 따라 웹 서버와 데이터를 주고 받는다.  
이때 웹 서버와 프로그램 사이의 데이터를 주고받는 규칙을 CGI(Common Gateway Interface)라고 한다.  
Servlet Container란 Servlet의 생성, 실행, 소멸을 관리하는 프로그램이다. (ex) Apache의 Tomcat, Resin, Jetty 등  
WAS란 Servlet Container와 같이 웹 기술을 기반으로 동작하는
Application Server의 생성, 실행, 소멸을 관리하는 프로그램이다.  
(ex) TMax의 JEUS, Oracle의 WebLogic, IBM의 WebSphere, RodHat의 JBOSS, Apache의 Geronemo, Oracle의 glassFish 등  
주로 데이터베이스 조회나 일반적인 비즈니스 로직을 처리하여 사용자에게 결과를 제공한다.  
JavaEE 기술 사양을 준수하여 만든 서버로 JavaEE 구현체라고도 한다.  
Servlet Container가 Servlet 대신 웹 서버와 데이터를 주고 받는다.

```
Web Browser -> (요청) -> Web Server ->   (요청 위임)   -> Servlet Container ->       (실행)       -> Program
            <- (응답) <- Web Server <- (결과:CGI 규칙) <- Servlet Container <- (결과: Servlet 규칙) <-
```


## 작업 소스 및 결과

- build.gradle 변경
- src/main/java/jeoneunhye/vms/ContextLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/context/ApplicationContextListener.java 삭제
- src/main/java/jeoneunhye/vms/context/ 삭제
- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 삭제
- src/main/java/jeoneunhye/vms/util/RequestMapping.java 삭제
- src/main/java/jeoneunhye/vms/util/RequestMappingHandlerMapping.java 삭제
- src/main/java/jeoneunhye/vms/util/RequestHandler.java 삭제

### 작업1) 서블릿 컨테이너를 설치 및 설정한다.

- tomcat.apache.org 사이트에서 Servlet Container를 다운로드한다.
- 특정 폴더에 압축 풀고 로그인 설정을 한다.
    - 관리자 ID/PWD를 등록한다.
        - $톰캣홈/conf/tomcat-users.xml
    - 관리자 로그인을 활성화한다.
        - $톰캣홈/conf/Catalina/localhost/manager.xml
- 톰캣 서버를 실행하고 웹 브라우저를 통해 접속을 확인한다.

### 작업2) JavaEE Servlet 기술을 사용하기 위한 라이브러리를 프로젝트에 적용한다.

- build.gradle 변경
    - search.maven.org에서 `servlet-api`를 검색한다.
    - 의존 라이브러리 블록에 추가한다.
    - 컴파일할 때만 사용할 것이기 때문에 빌드 파일에 포함되지 않도록 옵션을 compileOnly로 설정한다.
- $ gradle eclipse 실행
    - 이클립스 설정 파일을 갱신한다.
- 이클립스 IDE에서 프로젝트를 refresh한다.

### 작업3) JavaEE의 Servlet 기술을 사용하여 Spring IoC Container를 준비한다.

- ContextLoaderListener 클래스 변경
    - Servlet 기술에서 제공하는 ServletContextListener 인터페이스를 구현한다.
    - Servlet Container가 이 객체를 관리하도록 @WebListener 애노테이션을 추가한다.
    - ServletContext.setAttribute를 호출하여 준비한 Spring IoC Container를 보관한다.
    - Servlet 객체를 생성하고 메서드를 호출하는 것을 Spring IoC Container가 아닌 Servlet Container에게 위임했다.
- context 패키지 및 하위 클래스 삭제
    - javax.servlet 패키지를 사용하므로 삭제한다.

### 작업4) 기존의 서블릿 클래스를 JavaEE의 Servlet 기술을 적용하여 변경한다.

- XxxServlet 클래스 변경
    - 클래스의 @Component -> @WebServlet("/board/add") 애노테이션으로 변경한다.
    - GenericServlet 상속 및 직렬화하고 추상메서드 service(ServletRequest, ServletResponse)를 오버라이딩한다.
    - 메서드의 @RequestMapping("/board/addForm") 애노테이션을 삭제한다.
- ServerApp, RequestHandler, RequestMappingHandlerMapping 클래스, RequestMapping 애노테이션 삭제
    - Servlet Container로 구동하여 더이상 사용하지 않는 클래스와 애노테이션을 삭제한다.

### 작업5) 웹 애플리케이션을 빌드한다.

- build.gradle 변경
    - 웹 애플리케이션 배치 파일을 생성하기 위해 `war` 플러그인을 추가한다.
- $ gradle build를 실행한다.
    - 'build/libs/jeoneunhye-clips-server.war' 파일이 생성된다.

### 작업6) 톰캣 서버에 배치한다.

- $톰캣홈/webapps/ 폴더에 war 파일을 놓는다.
- $톰캣홈/bin/startup.bat 실행
    - 톰캣 서버가 webapps 폴더에 jeoneunhye-clips-server.war 파일의 이름과 동일한 폴더를 만들고 압축을 푼다.

### 작업7) 웹 애플리케이션을 실행한다.

- /board/list 실행
    - http://localhost:포트번호/웹애플리케이션이름/board/list
    - 웹애플리케이션 이름은 webapps/ 폴더에 압축을 푼 디렉토리 이름이다.  
    예) http://localhost:9999/jeoneunhye-clips-server/board/list
- '새 글' 링크 클릭
    - 화면을 찾을 수 없다.
    - 이유? 링크에 절대 경로를 사용한다. => "/board/addForm"
    - 웹 애플리케이션을 배치하면 기본으로 프로젝트명이 웹 애플리케이션 이름이 된다.
    - 이 웹 애플리케이션에 있는 서블릿을 실행하려면 항상 웹 애플리케이션 이름을 사용하여 실행해야 한다.
    예) /jeoneunhye-clips-server/board/addForm  
    => 상대 경로를 지정하여 해결한다.

### 작업8) URL 링크를 상대 경로로 바꾼다.

- XxxServlet 클래스 변경
    - 링크를 연결하는 Servlet의 `<a>` 태그 주소를 절대 경로가 아닌 상대 경로로 바꾼다. (ex) /video/list -> list