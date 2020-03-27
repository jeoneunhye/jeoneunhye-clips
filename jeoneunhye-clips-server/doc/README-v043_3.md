# 43_3 - Mybatis의 dynamic sql 문법 사용하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/util/Prompt.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경
- src/main/java/jeoneunhye/vms/mapper/VideoMapper.java 변경
- src/main/java/jeoneunhye/vms/mapper/MemberMapper.java 변경
- src/main/java/jeoneunhye/vms/mapper/BoardMapper.java 변경
- src/main/java/jeoneunhye/vms/mapper/PhotoFileMapper.java 변경
- src/main/java/jeoneunhye/vms/dao/VideoDao.java 변경
- src/main/java/jeoneunhye/vms/dao/PhotoFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoSearchServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/MemberUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardUpdateServlet.java 변경

### 작업1) `sql` 태그를 사용하여 공통 SQL 코드를 분리한다.

- VideoMapper.xml, MemberMapper.xml, BoardMapper.xml 파일 변경
    - 공통으로 사용되는 select문의 컬럼을 sql 태그로 분리한다.
    - include 태그를 이용하여 분리한 sql문을 기존의 select문에 적용한다.

### 작업2) `foreach` 태그를 사용하여 insert문 생성하기

사진 게시글의 첨부 파일 여러 개를 insert 한 번에 입력하기

- PhotoBoardAddServlet 클래스 변경
    - PhotoBoard의 setFiles 메서드를 호출하여 첨부 파일 목록을 한 번에 insert한다.
- PhotoBoardUpdateServlet 클래스 변경
    - PhotoBoard의 setFiles 메서드를 호출하여 첨부 파일 목록을 한 번에 insert한다.
- PhotoFileDao 인터페이스 변경
    - insert 메서드의 파라미터를 PhotoFile에서 PhotoBoard로 변경한다.
- PhotoFileDaoImpl 클래스 변경
    - insert 메서드의 파라미터를 PhotoFile에서 PhotoBoard로 변경한다.
- PhotoFileMapper.xml 파일 변경
    - insertPhotoFile SQL문을 변경한다.
    => PhotoBoard를 파라미터로 받아 첨부 파일을 한꺼번에 저장한다.
    => `foreach` 태그를 적용하여 여러 개의 값을 한 번에 insert한다.

### 작업3) `set` 태그를 사용하여 update할 때 일부 컬럼만 변경한다.

데이터를 변경할 때 일부 컬럼의 값만 변경하기

- Prompt 클래스 변경
    - 클라이언트가 보낸 값을 지정된 타입으로 변경할 수 없을 때 null, 0을 리턴하도록 예외 처리 문법을 적용한다.
- VideoMapper.xml 파일 변경
    - updateVideo SQL문을 변경한다.  
    => `where` 태그를 적용하여 Video 객체의 필드가 null이 아니거나 공백이 아닌 조건을 걸고 해당하는 컬럼만 변경하도록 한다.
- VideoUpdateServlet 클래스 변경
    - 클라이언트가 값을 보내지 않은 항목은 빈 문자열이나 null, 0으로 설정한다.  
    => 이 경우 update 대상 컬럼에서 제외된다.
- MemberMapper.xml 파일 변경
    - updateMember SQL문을 변경한다.  
    => `where` 태그를 적용하여 Member 객체의 필드가 null이 아니거나 공백이 아닌 조건을 걸고 해당하는 컬럼만 변경하도록 한다.
- MemberUpdateServlet 클래스 변경
    - 클라이언트가 값을 보내지 않은 항목은 빈 문자열이나 null, 0으로 설정한다.  
    => 이 경우 update 대상 컬럼에서 제외된다.
- BoardMapper.xml 파일 변경
    - updateBoard SQL문을 변경한다.  
    => `where` 태그를 적용하여 Board 객체의 필드가 null이 아니거나 공백이 아닌 조건을 걸고 해당하는 컬럼만 변경하도록 한다.
- BoardUpdateServlet 클래스 변경
    - 클라이언트가 값을 보내지 않은 항목은 빈 문자열이나 null, 0으로 설정한다.  
    => 이 경우 update 대상 컬럼에서 제외된다.

### 작업4) `where` 태그를 사용하여 검색 조건을 변경한다.

주제, 제목, 재생시간, 업로더, 업로드일을 입력받아 영상을 검색하는 기능을 추가한다.
검색 조건은 AND 연산으로 처리한다.

- VideoMapper.xml 파일 변경
    - selectVideo SQL문을 변경한다.  
    => `where` 태그를 적용하여 조건을 만족하는 데이터를 찾는다.
- VideoDao 인터페이스 변경
    - List<Video>를 리턴하는 findByKeyword() default 메서드를 추가로 정의한다.
- VideoDaoImpl 클래스 변경
    - 검색어를 파라미터로 넘겨 Mybatis로 SQL문을 실행하고 List<Video>를 결과로 리턴받는다.
- VideoSearchServlet 클래스 추가
    - 여러 조건을 입력받아 selectVideo SQL문의 아규먼트로 넘기고 검색한다.
- ServerApp 클래스 변경
    - VideoSearchServlet 객체를 등록한다.