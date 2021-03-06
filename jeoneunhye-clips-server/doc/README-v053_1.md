# 53_1 - Log4j 1.2.x를 사용하여 애플리케이션 로그 처리하기

애플리케이션의 실행 상태를 확인하고 싶을 때 보통 `System.out.println()`을 사용하여
변수의 값이나 메서드의 리턴 값, 객체의 필드 값을 출력한다.  
이 방식은 개발을 완료한 후 이런 코드를 찾아 제거하기가 매우 번거롭다는 문제가 있다.  
또한 콘솔 출력이 아닌 파일이나 네트워크로 출력하려면 별개의 코드를 작성해야 한다.  
이런 문제점을 해결하기 위해 나온 것이 `Log4j`라는 라이브러리다.  
로그의 출력 형식을 지정하는 기능이 있어
개발중에는 로그를 자세하게 출력하고 개발이 완료된 후에는 중요 로그만 출력하도록 조정할 수 있다.  
출력 대상도 콘솔, 파일, 네트워크, DB 등 다양하게 지정할 수 있다.

#### 로깅 레벨

로깅 레벨은 다음 여섯 개 중 하나를 지정할 수 있다.  

- FATAL : 애플리케이션을 중지해야 할 심각한 오류를 의미
- ERROR : 오류가 발생했지만, 애플리케이션을 계속 실행할 수 있는 상태를 의미
- WARN : 잠재적인 위험을 안고 있는 상태를 의미
- INFO : 애플리케이션의 진행 정보를 의미.
프로그램을 실행시키는 시스템 운영자를 위해 출력할 때 주로 사용한다.
- DEBUG : 애플리케이션의 내부 실행 상태를 의미.
프로그래밍 할 때 필요한 정보를 출력할 때 주로 사용한다.
- TRACE : DEBUG보다 더 자세한 상태를 의미

루트 로거에 로깅 레벨을 지정하면 그 하위 로거들에 모두 적용된다.  
하위 로거에 루트 로거의 레벨 대신 다른 레벨을 지정할 수 있다.


## 작업 소스 및 결과

- build.gradle 변경
- src/main/resources/log4j.properties 추가
- src/main/java/jeoneunhye/vms/AppConfig.java 변경
- src/main/java/jeoneunhye/vms/DatabaseConfig.java 변경
- src/main/java/jeoneunhye/vms/MybatisConfig.java 변경
- src/main/java/jeoneunhye/vms/ContextLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) Log4j 1.2.x 라이브러리를 추가한다.

- Log4j의 라이브러리 정보 찾기
    - mvnrepository.com에서 `log4j`를 검색한다.
- build.gradle 변경
    - 빌드 설정 파일의 의존 라이브러리 정보에 log4j 라이브러리를 추가한다.
- $ gradle eclipse 명령 실행
    - $ gradle eclipse 명령을 실행하여 의존 라이브러리를 가져온다.
- Eclipse의 CLASSPATH 정보를 갱신한다.
    - 명령어를 실행한 후 이클립스에서 프로젝트를 갱신해야 한다.

### 작업2) Log4j 설정 파일을 추가한다.

- log4j.properties 파일 추가
    - 자바 classpath 루트에 log4j 설정 파일을 둔다.
    log4j의 출력 범위와 출력 대상, 출력 형식을 설정하는 파일이다.

### 작업3) 각 클래스의 로그 출력을 Log4j로 전환한다.

- ServerApp 클래스 변경
    - 해당 클래스에서 로그 메시지를 남기기 위한 log4j의 logger를 static 필드로 준비한다.
    - 기존의 출력 메시지를 INFO, ERROR, DEBUG 레벨의 로그 메시지로 출력한다.
- ContextLoaderListener 클래스 변경
    - 해당 클래스에서 로그 메시지를 남기기 위한 log4j의 logger를 static 필드로 준비한다.
    - 기존의 출력 메시지를 DEBUG 레벨의 로그 메시지로 출력한다.
- AppConfig 클래스 변경
    - 해당 클래스에서 로그 메시지를 남기기 위한 log4j의 logger를 static 필드로 준비한다.
    - 기존의 출력 메시지를 DEBUG 레벨의 로그 메시지로 출력한다.
- DatabaseConfig 클래스 변경
    - 해당 클래스에서 로그 메시지를 남기기 위한 log4j의 logger를 static 필드로 준비한다.
    - 기존의 출력 메시지를 DEBUG 레벨의 로그 메시지로 출력한다.
- MybatisConfig 클래스 변경
    - 해당 클래스에서 로그 메시지를 남기기 위한 log4j의 logger를 static 필드로 준비한다.
    - 기존의 출력 메시지를 DEBUG 레벨의 로그 메시지로 출력한다.

### 작업4) Mybatis에 log4j를 설정한다.

- MybatisConfig 클래스 변경
    - org.apache.ibatis.logging.LogFactory.useLog4JLogging()를 호출하여 log4j 기능을 활성화시킨다.