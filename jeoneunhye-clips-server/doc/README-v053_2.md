# 53_2 - Log4j 2.x를 사용하여 애플리케이션 로그 처리하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/resources/log4j2.xml 추가
- src/main/java/jeoneunhye/vms/AppConfig.java 변경
- src/main/java/jeoneunhye/vms/DatabaseConfig.java 변경
- src/main/java/jeoneunhye/vms/MybatisConfig.java 변경
- src/main/java/jeoneunhye/vms/ContextLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) Log4j 2.x 라이브러리를 추가한다.

- 라이브러리 정보 알아내기
    - mvnrepository.com에서 `log4j-core`를 검색한다.
- build.gradle 변경
    - 빌드 설정 파일의 의존 라이브러리 정보에 log4j 라이브러리를 추가한다.
- $ gradle eclipse 명령 실행
    - $ gradle eclipse 명령을 실행하여 의존 라이브러리를 가져온다.
- Eclipse의 CLASSPATH 정보를 갱신한다.
    - 명령어를 실행한 후 이클립스에서 프로젝트를 갱신해야 한다.

### 작업2) Log4j 설정 파일을 추가한다.

- log4j2.xml 파일 추가
    - 자바 classpath 루트에 log4j 설정 파일을 둔다.
    - log4j의 출력 범위와 출력 대상, 출력 형식을 설정하는 파일이다.

### 작업3) 각 클래스의 로그 출력 도구를 Log4j 2로 전환한다.

- ServerApp 클래스 변경
- ContextLoaderListener 클래스 변경
- AppConfig 클래스 변경
- DatabaseConfig 클래스 변경
- MybatisConfig 클래스 변경

### 작업4) Mybatis에 log4j 2를 설정한다.

- MybatisConfig 클래스 변경
    - org.apache.ibatis.logging.LogFactory.useLog4J2Logging() 호출하여 log4j 2 기능을 활성화시킨다.