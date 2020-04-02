# 51_2 - Spring IoC 설정 파일(Java Config)을 분리하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/DatabaseConfig.java 추가
- src/main/java/jeoneunhye/vms/MybatisConfig.java 추가
- src/main/java/jeoneunhye/vms/AppConfig.java 변경

### 작업1) AppConfig에서 DB 관련 설정을 분리하기

- DatabaseConfig 클래스 추가
    - AppConfig에서 DB 관련 객체 생성 코드를 가져온다.
    - @Configuration 애노테이션을 지정하면 Spring IoC Container가 Java Config로 자동으로 인식한다.  
        IoC Container 객체를 생성할 때 아규먼트로 DatabaseConfig.class를 추가로 주입하지 않아도 된다.  
    단 AppConfig에서 @ComponentScan을 지정한 패키지에 포함되는 클래스여야 한다.
- AppConfig 클래스 변경
    - DatabaseConfig로 분리한 DB 관련 코드를 삭제한다.

### 작업2) AppConfig에서 Mybatis 관련 설정을 분리하기

- MybatisConfig 클래스 추가
    - AppConfig에서 Mybatis 관련 객체 생성 코드를 가져온다.
    - @Configuration 애노테이션을 지정하면 Spring IoC Container가 Java Config로 자동으로 인식한다.  
    IoC Container 객체를 생성할 때 아규먼트로 MybatisConfig.class를 추가로 주입하지 않아도 된다.  
    단 AppConfig에서 @ComponentScan을 지정한 패키지에 포함되는 클래스여야 한다.
- AppConfig 클래스 변경
    - MybatisConfig로 분리한 Mybatis 관련 코드를 삭제한다.