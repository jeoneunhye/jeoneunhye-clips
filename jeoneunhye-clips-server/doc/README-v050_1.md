# 50_1 - Spring IoC Container 도입하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/java/jeoneunhye/util/ApplicationContext.java 삭제
- src/main/java/jeoneunhye/vms/AppConfig.java 추가
- src/main/java/jeoneunhye/vms/ContextLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경
- src/main/java/jeoneunhye/util/Component.java 삭제
- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경
- src/main/java/jeoneunhye/vms/service/impl/XxxServiceImpl.java 변경

### 작업1) Spring IoC Container 라이브러리를 가져온다.

- Spring IoC Container의 라이브러리 정보 찾기
    - mvnrepository.com 또는 search.maven.org에서 `spring-context` 키워드로 라이브러리를 검색한다.
- build.gradle 변경
    - 빌드 설정 파일의 의존 라이브러리 정보에 Spring IoC Container를 추가한다.
- $ gradle eclipse 명령 실행
    - $ gradle eclipse 명령을 실행하여 의존 라이브러리를 가져온다.
- Eclipse의 CLASSPATH 정보를 갱신한다.
    - 명령어를 실행한 후 이클립스에서 프로젝트를 갱신해야 한다.

### 작업2) Spring IoC Container의 설정 정보를 제공하는 클래스를 정의한다.

- AppConfig 클래스 추가
    - Spring IoC Container가 객체를 생성하기 위해 탐색할 패키지 경로를 @ComponentScan 애노테이션으로 설정한다.  
    => 지정한 패키지 및 그 하위 패키지를 모두 검사하여 @Component 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.

### 작업3) Spring IoC Container를 생성한다.

- ApplicationContext 클래스 삭제
    - Spring IoC Container로 대체한다.
- ContextLoaderListener 클래스 변경
    - Spring Framework에서 제공하는 클래스를 사용하여 IoC Container를 생성한다.
    - ApplicationConfigApplicationContext의 아규먼트로 Spring IoC Container의 설정 정보를 담고 있는 클래스 타입을 지정한다.
    - printBeans(ApplicationContext)를 추가하여 Spring IoC Container에 들어 있는 객체를 출력해 본다.
- ServerApp 클래스 변경
    - Spring IoC Container의 ApplicationContext로 교체한다.

### 작업4) Spring의 @Component 애노테이션으로 교체한다.

- Component 애노테이션 제거
- XxxServlet 클래스 변경
    - Spring의 @Component로 교체한다.
- serviceImpl 클래스 변경
    - Spring의 @Component로 교체한다.
- ContextLoaderListener 클래스 변경
    - Spring의 @Component로 교체한다.

### 작업5) Spring IoC Container가 자동으로 생성할 수 없는 객체의 경우 설정 클래스에서 메서드를 추가한다.

- AppConfig 클래스 변경
    - Mybatis와 연결할 SqlSessionFactory 객체 생성 메서드를 추가하고 @Bean 애노테이션을 적용한다.
    - Service 객체를 생성할 때 의존 객체인 DAO를 주입받기 위해
    MybatisDaoFactory와 DAO 객체 생성 메서드를 추가하고 @Bean 애노테이션을 적용한다.
- ContextLoaderListener 클래스 변경
    - IoC Container에 저장할 객체를 보관할 Map과 객체 생성 코드를 제거한다.