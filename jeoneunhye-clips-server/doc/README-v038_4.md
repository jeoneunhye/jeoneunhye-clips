# 38_4 - 트랜잭션 적용 후: 사진 게시글 입력과 첨부 파일 입력을 한 단위로 다루기

### TRANSACTION

데이터 처리의 한 단위이다. 서버에서 발생하는 SQL문들이 하나의 논리적인 작업 단위로써 성공하거나 실패하는 일련의 SQL문을 트랜잭션이라 보면 된다.
트랜잭션을 근거로 데이터의 일관성을 보증한다.

### COMMIT과 ROLLBACK의 장점
- 데이터의 일관성을 제공 한다.
- 데이터를 영구적으로 변경하기 전에 데이터 변경을 확인하게 한다.
- 관련된 작업을 논리적으로 그룹화 할 수 있다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardDeleteServlet.java 변경

### 작업1) 트랜잭션을 사용하기 전의 문제점을 확인한다.

사진 게시물을 입력할 때 첨부 파일 일부는 DB 컬럼에서 허용된 길이보다 더 긴 값을 갖게 한다.  
이 때 오류가 발생하는데, 그럼에도 불구하고 사진 게시글이 정상적으로 저장되고  
오류가 발생하기 전에 입력한 첨부 파일이 정상적으로 저장되는 것을 확인한다.

`ClientApp` 실행 예:

```
명령> /photoboard/add
제목?
test
내용?
테스트입니다.
영상 번호?
103
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?
ok1.gif
사진 파일?
ok2.gif
사진 파일?
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
사진 파일?

java.sql.SQLDataException: (conn=12) Data too long for column 'PATH' at row 1 : (conn=12) Data too long for column 'PATH' at row 1

명령> /photoboard/detail
번호?
1
영상: 103
제목: test
내용: 테스트입니다.
작성일: 2020-3-14
조회수: 0
사진 파일:
> ok1.gif
> ok2.gif
```

### 작업2) DataLoaderListener에서 DB 연결 객체를 변경한다.

- DataLoaderListener 클래스 변경
    - 다른 패키지의 클래스에서 Connection 도구를 사용할 수 있도록 접근 제한자를 public으로 선언한다.
    - 객체를 생성하지 않고 클래스 필드의 메서드를 바로 호출할 수 있도록 Connection 도구를 static 필드로 선언한다.

### 작업3) 사진 게시글 입력과 첨부 파일 입력을 한 단위로 다룬다.

- PhotoBoardAddServlet 클래스 변경
    - 게시글 입력과 첨부 파일 입력 부분을 실행하기 전(DBMS 데이터 변경 작업 전)에 Connection 객체의 autoCommit을 false로 설정한다.  
    이 이후의 데이터 변경 작업은 모두 임시 테이블에 보관된다.
    - 사진 게시글과 첨부 파일을 입력하는 코드를 try-catch문으로 묶어 성공하면 commit()을, 예외가 발생하면 rollback()을 호출한다.
    - 다음 Connection 객체를 사용하는 Servlet을 위해 자동 commit으로 설정한다.  
    성공하든 예외가 발생하든 처리하도록 finally문에서 autoCommit을 true로 설정한다.

### 작업4) 사진 게시글 변경과 첨부 파일 삭제, 입력을 한 단위로 다룬다.

- PhotoBoardUpdateServlet 클래스 변경
    - 게시글 변경과 첨부 파일 삭제, 입력 부분을 실행하기 전(DBMS 데이터 변경 작업 전)에 Connection 객체의 autoCommit을 false로 설정한다.  
    이 이후의 데이터 변경 작업은 모두 임시 테이블에 보관된다.
    - 사진 게시글과 첨부 파일을 변경하는 코드를 try-catch문으로 묶어 성공하면 commit()을, 예외가 발생하면 rollback()을 호출한다.
    - 다음 Connection 객체를 사용하는 Servlet을 위해 자동 commit으로 설정한다.  
    성공하든 예외가 발생하든 처리하도록 finally문에서 autoCommit을 true로 설정한다.

### 작업5) 사진 게시글 삭제와 첨부 파일 삭제를 한 단위로 다룬다. 

- PhotoBoardDeleteServlet 클래스 변경
    - 게시글 삭제와 첨부 파일 삭제를 실행하기 전(DBMS 데이터 변경 작업 전)에 Connection 객체의 autoCommit을 false로 설정한다.  
    - 첨부 파일과 사진 게시글을 삭제하는 코드를 try-catch문으로 묶어 성공하면 commit()을, 예외가 발생하면 rollback()을 호출한다.
    - 다음 Connection 객체를 사용하는 Servlet을 위해 자동 commit으로 설정한다.  
    성공하든 예외가 발생하든 처리하도록 finally문에서 autoCommit을 true로 설정한다.