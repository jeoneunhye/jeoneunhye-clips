# 43_1 - Mybatis SQL Mapper Framework로 JDBC 코드를 대체하기

### Mybatis

Java Persistence Framework의 하나로 XML 서술자나 Annotation을 사용하여 SQL문으로 객체를 연결한다.  
MyBatis에서 가장 핵심적인 객체는 SQLSession과 SQLSessionFactory다.  
SqlSessionFactory는 몇 가지 방법으로 SqlSession을 생성하기 위한 메서드를 포함하고 있다.  
SQLSession을 통해서 Connection을 생성하거나 원하는 SQL을 전달하고, 결과를 리턴받는 구조로 작성한다.  
Mybatis는 Connection의 close()를 자동으로 수행하고, 리턴 타입을 지정하는 경우 자동으로 객체 생성 및
ResultSet 처리가 가능하다.


## 작업 소스 및 결과

- build.gradle 변경
- src/main/resources/jeoneunhye/vms/conf/mybatis-config.xml 추가
- src/main/resources/jeoneunhye/vms/conf/jdbc.properties 추가
- src/main/resources/jeoneunhye/vms/mapper/VideoMapper.xml 추가
- src/main/resources/jeoneunhye/vms/mapper/MemberMapper.xml 추가
- src/main/resources/jeoneunhye/vms/mapper/BoardMapper.xml 추가
- src/main/resources/jeoneunhye/vms/mapper/PhotoBoardMapper.xml 추가
- src/main/resources/jeoneunhye/vms/mapper/PhotoFileMapper.xml 추가
- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/domain/PhotoBoard.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardDetailServlet.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 프로젝트에 MyBatis 라이브러리를 추가한다.

- build.gradle 파일 변경
    - 의존 라이브러리 블록에 `mybatis` persistence framework 라이브러리를 등록한다.
- gradle을 이용하여 eclipse 설정 파일을 갱신한다.  
    => $ gradle eclipse
- eclipse에서 프로젝트를 갱신한다.

### 작업2) MyBatis 설정 파일을 준비한다.

- jdbc.properties 파일 추가
    - MyBatis 설정 파일에서 참고할 DBMS 접속 정보를 등록한다.
- mybatis-config.xml 파일 추가
    - MyBatis 설정 파일이다.
    - DBMS 서버의 접속 정보를 갖고 있는 jdbc.properties 파일의 경로를 등록한다.
    - DBMS 서버 정보를 설정한다.
    - DB 커넥션 풀을 설정한다.

### 작업3) VideoDaoImpl에 Mybatis를 적용한다.

- VideoDaoImpl 클래스 변경
    - SQL문을 복사하여 VideoMapper.xml로 옮긴다.
    - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- VideoMapper.xml 파일 추가
    - VideoDaoImpl에 있던 SQL문을 이 파일로 옮긴다.
- mybatis-config.xml 파일 변경
    - VideoMapper 파일의 경로를 등록한다.
- DataLoaderListener 클래스 변경
    - SqlSessionFactory 객체를 준비한다.
    - VideoDaoImpl에 주입한다.

### 작업4) MemberDaoImpl에 Mybatis를 적용한다.

- MemberDaoImpl 클래스 변경
    - SQL을 뜯어내어 MemberMapper.xml로 옮긴다.
    - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- MemberMapper.xml 파일 추가
    - MemberDaoImpl 에 있던 SQL문을 이 파일로 옮긴다.
- mybatis-config.xml 파일 변경
    - MemberMapper 파일의 경로를 등록한다.
- DataLoaderListener 클래스 변경
    - SqlSessionFactory 객체를 준비한다.
    - MemberDaoImpl 에 주입한다.

### 작업5) BoardDaoImpl에 Mybatis를 적용한다.

- BoardDaoImpl 클래스 변경
    - SQL을 뜯어내어 BoardMapper.xml로 옮긴다.
    - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- BoardMapper.xml 파일 추가
    - BoardDaoImpl에 있던 SQL문을 이 파일로 옮긴다.
- mybatis-config.xml 파일 변경
    - BoardMapper 파일의 경로를 등록한다.
- DataLoaderListener 클래스 변경
    - SqlSessionFactory 객체를 준비한다.
    - BoardDaoImpl에 주입한다.

### 작업6) PhotoBoardDaoImpl에 Mybatis를 적용한다.

- PhotoBoard 클래스 변경
    - List<PhotoFile> files 필드를 추가한다.
    - getFiles, setFiles 메서드를 정의한다.
- PhotoBoardDetailServlet 클래스 변경
    - PhotoFileDao 주입을 제거한다.
    - PhotoBoardDao로 첨부 파일까지 모두 가져온다.
- PhotoBoardDaoImpl 클래스 변경
    - SQL을 뜯어내어 PhotoBoardMapper.xml로 옮긴다.
    - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- PhotoBoardMapper.xml 파일 추가
    - PhotoBoardDaoImpl에 있던 SQL문을 이 파일로 옮긴다.
- mybatis-config.xml 파일 변경
    - PhotoBoardMapper 파일의 경로를 등록한다.
- DataLoaderListener 클래스 변경
    - SqlSessionFactory 객체를 준비한다.
    - PhotoBoardDaoImpl에 주입한다.
- ServerApp 클래스 변경
    - PhotoBoardDetailServlet에 PhotoFileDao 주입을 제거한다.

### 작업7) PhotoFileDaoImpl에 Mybatis를 적용한다.

- PhotoFileDaoImpl 클래스 변경
    - SQL을 뜯어내어 PhotoFileMapper.xml로 옮긴다.
    - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- PhotoFileMapper.xml 파일 추가
    - PhotoFileDaoImpl에 있던 SQL문을 이 파일로 옮긴다.
- mybatis-config.xml 파일 변경
    - PhotoFileMapper 파일의 경로를 등록한다.
- DataLoaderListener 클래스 변경
    - SqlSessionFactory 객체를 준비한다.
    - PhotoFileDaoImpl에 주입한다.

### 작업8) 기존의 트랜잭션이 작동하지 않음을 확인한다.

- 사진 게시글을 등록한다.
- 사진 파일을 등록할 때, 오류가 발생하도록 긴 파일명을 입력한다.
- 오류가 발생한 후에 사진 게시글이 등록되었는지 취소되었는지 확인한다.
- 취소되지 않은 이유는 Mybatis의 SqlSession이 자체적으로 Connection을 관리하기 때문이다.