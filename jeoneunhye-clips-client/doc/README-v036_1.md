# 36_1 - DBMS를 도입하여 데이터 관리 맡기기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 생성
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 생성
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 생성
- src/main/java/jeoneunhye/vms/handler/VideoAddCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/VideoUpdateCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/VideoDeleteCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/BoardAddCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/BoardUpdateCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/BoardDeleteCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/MemberAddCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/MemberUpdateCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/MemberDeleteCommand.java 변경
- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) 애플리케이션에서 사용할 사용자와 데이터베이스를 MariaDB에 추가한다.

MariaDB에 연결하기 

```
$ mysql -u root -p
Enter password: 암호입력
...

MariaDB [(none)]>
```

사용자 생성하기

```
MariaDB [(none)]> CREATE USER 'eunhye'@'localhost' IDENTIFIED BY '1111';
```

데이터베이스 생성하기

```
MariaDB [(none)]> CREATE DATABASE vmsdb
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
```

사용자에게 DB 사용 권한을 부여하기

```
GRANT ALL ON vmsdb.* TO 'eunhye'@'localhost';
```

MariaDB에 `eunhye` 사용자 아이디로 다시 접속하기

```
$ mysql -u eunhye -p
Enter password: 1111
...

MariaDB [(none)]>
```

`eunhye` 아이디로 사용할 수 있는 데이터베이스 목록 보기

```
MariaDB [(none)]> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| test               |
| vmsdb              |
+--------------------+
3 rows in set (0.001 sec)

MariaDB [(none)]> 
```

### 작업2) 애플리케이션에서 사용할 테이블과 예제 데이터를 준비한다.

`eunhye` 아이디로 MariaDB에 접속한 후 기본으로 사용할 데이터베이스를 `vmsdb`로 설정하기

```
MariaDB [(none)]> use vmsdb;
...

Database changed
MariaDB [vmsdb]> 
```

애플리케이션에서 사용할 테이블 생성하기

```
다음 파일의 내용을 복사하여 MariaDB 명령창에 붙여 넣고 실행한다.
jeoneunhye-clips    (Git 저장소)
    /jeoneunhye-clips-server
        /db
            /project-ddl.sql  (테이블 정의 SQL문이 들어 있는 파일)
```

생성된 테이블에 예제 데이터 입력하기

```
다음 파일의 내용을 복사하여 MariaDB 명령창에 붙여 넣고 실행한다.
jeoneunhye-clips    (Git 저장소)
    /jeoneunhye-clips-server
        /db
            /project-data.sql  (INSERT SQL문이 들어 있는 파일)
```

### 작업3) 프로젝트 클라이언트에 `MariaDB` JDBC 드라이버를 추가한다.

- build.gradle
    - 의존 라이브러리에 MariaDB JDBC Driver 정보를 추가한다.
    - `mvnrepository.com`에서 키워드 `mariadb jdbc`로 검색하면 **MariaDB Java Client** 라이브러리를 찾을 수 있다.
    - 최신 버전의 라이브러리 정보를 가져오면 된다.

build.gradle 파일

```
plugins {
    id 'java'
    id 'application'
    id 'eclipse'
}

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'
}

mainClassName = 'App'

dependencies {
    implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.5.4'
    implementation 'com.google.guava:guava:23.0'
    testCompile 'junit:junit:4.12'
}

repositories {
    jcenter()
}
```

`gradle`을 실행하여 이클립스 설정 파일을 갱신하기

```
$ gradle eclipse
```

이클립스 워크스페이스의 프로젝트를 갱신하기
> 이클립스에서도 프로젝트에 `Refresh`를 수행해야 라이브러리가 적용된다.

### 작업4) MariaDB에서 제공하는 Proxy를 사용하여 DBMS와 연동하여 데이터 처리 작업을 수행할 DAO를 정의한다.

- jeoneunhye.vms.dao.mariadb 패키지 생성
- jeoneunhye.vms.dao.mariadb.VideoDaoImpl 클래스 추가
- jeoneunhye.vms.dao.mariadb.MemberDaoImpl 클래스 추가
- jeoneunhye.vms.dao.mariadb.BoardDaoImpl 클래스 추가

### 작업5) Command 객체의 기존 DAO를 MariaDB 연동 DAO로 교체한다.

- jeoneunhye.vms.ClientApp 클래스 변경
    - 서버 주소와 포트 번호를 받는 입력받는 코드를 삭제한다.
    - 서버와 통신하는 DaoProxyHelper 객체와  XxxDaoProxy 객체를 삭제한다.
    - "/server/stop"을 처리하는 Command 객체를 Map에서 삭제한다.
    - MariaDB와 연동하여 데이터를 처리하는 XxxDaoImpl 객체를 준비한다.

### 작업6) DBMS 연동에 맞춰서 Command 객체를 변경한다.

- jeoneunhye.vms.handler.XxxAddCommand 클래스 변경
- jeoneunhye.vms.handler.XxxUpdateCommand 클래스 변경
- jeoneunhye.vms.handler.XxxDeleteCommand 클래스 변경