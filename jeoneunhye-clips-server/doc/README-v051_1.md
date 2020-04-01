# 51_1 - Spring IoC Container와 Mybatis Framework 연동하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/java/jeoneunhye/sql/PlatformTransactionManager.java 삭제
- src/main/java/jeoneunhye/sql/TransactionTemplate.java 삭제
- src/main/java/jeoneunhye/sql/TransactionCallback.java 삭제
- src/main/java/jeoneunhye/sql/SqlSessionFactoryProxy.java 삭제
- src/main/java/jeoneunhye/sql/SqlSessionProxy.java 삭제
- src/main/java/jeoneunhye/sql/MybatisDaoFactory.java 삭제
- src/main/resources/jeoneunhye/vms/conf/mybatis-config.xml 삭제
- src/main/java/jeoneunhye/vms/service/impl/PhotoBoardServiceImpl.java 변경
- src/main/java/jeoneunhye/vms/AppConfig.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) Mybatis를 Spring IoC Container와 연결할 때 사용할 의존 라이브러리를 가져온다.

- Spring IoC Container의 라이브러리 정보 찾기
    - mvnrepository.com 또는 search.maven.org에서
    `mybatis-spring`과 `spring-jdbc` 키워드로 라이브러리를 검색한다.
- build.gradle 변경
    - 빌드 설정 파일의 의존 라이브러리 정보에 Mybatis를 Spring IoC와 연결하는 어댑터 라이브러리와
    Spring JDBC 관련 라이브러리를 추가한다.
- $ gradle eclipse 명령 실행
    - $ gradle eclipse 명령을 실행하여 의존 라이브러리를 가져온다.
- Eclipse의 CLASSPATH 정보를 갱신한다.
    - 명령어를 실행한 후 이클립스에서 프로젝트를 갱신해야 한다.

### 작업2) Mybatis에서 관리했던 DB Connection Pool(DataSource)을 Spring IoC Container의 객체로 교체한다.

Spring IoC Container를 도입하면 가능한 대부분의 객체를 IoC Container에서 관리하도록 한다.  
따라서 Mybatis가 사용하는 객체도 Spring IoC Container에서 관리하도록 관리 주체를 변경한다.

- AppConfig 클래스 변경
    - `@PropertySource` 애노테이션을 추가하고 jdbc.properties 파일의 경로를 지정한다.
    - `@Value` 애노테이션을 통해 .peroperties 파일에서 해당하는 DB 연결 정보 프로퍼티 값을 필드에 주입한다.
    - DataSource 객체를 리턴하는 팩토리 메서드를 추가한다.

### 작업3) PlatformTransactionManager를 Spring IoC Container의 객체로 교체한다.

- TransactionCallback 인터페이스 삭제
- TransactionTemplate 클래스 삭제
- PlatformTransactionManager 클래스 삭제
- AppConfig 클래스 변경
    - DataSource를 주입하여 PlatformTransactionManager를 생성하는 메서드로 교체한다.

### 작업4) 기존의 PhotoBoardService 객체의 Transaction Manager를 Spring IoC Container로 교체한다.

- PhotoBoardServiceImpl 클래스 변경
    - Transaction 관련 클래스를 springframework.transaction.*로 교체한다.
    - Transaction을 다루는 코드를 Spring 사용법에 따라 변경한다.

```
사진 게시글을 추가하는 코드를 익명 클래스로 구현
    transactionTemplate.execute(new TransactionCallback<Object>() {
      @Override
      public Object doInTransaction(TransactionStatus status) {
        try {
          if (photoBoardDao.insert(photoBoard) == 0) {
            throw new Exception("사진 게시글 등록에 실패했습니다.");
          }
          
          photoFileDao.insert(photoBoard);
          
        } catch (Exception e) {
          status.setRollbackOnly();
        }
        
        return null;
      }
    });
```

```
사진 게시글을 추가하는 코드를 람다 문법으로 구현
    transactionTemplate.execute((status) -> {
        try {
          if (photoBoardDao.insert(photoBoard) == 0) {
            throw new Exception("사진 게시글을 등록할 수 없습니다.");
          }

          photoFileDao.insert(photoBoard);

        } catch (Exception e) {
          status.setRollbackOnly();
        }

      return null;
    });
```

### 작업5) Mybatis의 SqlSessionFactory를 Spring IoC Container용으로 준비한다.

- mybatis-config.xml 파일 삭제
- SqlSessionFactoryProxy 클래스 삭제
- SqlSessionProxy 클래스 삭제
- AppConfig 클래스 변경
    - `mybatis-spring` 라이브러리에서 제공하는 어댑터를 사용하여 SqlSessionFactory를 리턴하는 메서드로 교체한다.  
    => SqlSessionFactoryBean 객체에 DataSource, Domain 패키지 경로, SQL Mapper 파일의 위치 정보를 주입한다.

### 작업6) DAO 생성기를 Mybatis-Spring 어댑터로 교체한다.

- MybatisDaoFactory 클래스 삭제
- AppConfig 클래스 변경
    - `@MapperScan` 애노테이션을 추가하고 DAO 인터페이스가 있는 패키지 경로를 지정한다.
    - 수동으로 DAO 객체를 만드는 팩토리 메서드를 모두 제거한다.
    - MybatisDaoFactory 객체를 생성하는 팩토리 메서드를 삭제한다.
- ServerApp 클래스 변경
    - SqlSessionFactory 사용 코드를 제거한다.
    - Transaction은 Spring Framework에서 관리하기 때문에 직접 Thread를 통제하여 관리할 필요가 없다.