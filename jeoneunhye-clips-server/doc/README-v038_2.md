# 38_2 - 트랜잭션 적용 전: 사진 게시글에 첨부 파일 추가하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/domain/PhotoFile.java 추가 
- src/main/java/jeoneunhye/dao/PhotoFileDao.java 추가
- src/main/java/jeoneunhye/dao/mariadb/PhotoFileDaoImpl.java 추가
- src/main/java/jeoneunhye/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardDetailServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardDeleteServlet.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 영상 사진 게시판에 사진 파일을 첨부하는 기능을 추가한다.

- jeoneunhye.vms.domain.PhotoFile 클래스 추가
    - 사진 파일의 타입을 정의한다.
- jeoneunhye.vms.dao.PhotoFileDao 인터페이스 추가
    - 사진 파일의 CRUD 관련 메서드 호출 규칙을 정의한다.
- jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl 클래스 추가
    - PhotoFileDao 인터페이스를 구현한다.
- jeoneunhye.vms.DataLoaderListener 클래스 변경
    - PhotoFileDao 객체를 생성하여 context 맵에 보관한다.

### 작업2) '/photoboard/add' 명령을 처리한다.

사진 게시글을 입력할 때 사진 파일을 첨부할 수 있게 변경한다.

- jeoneunhye.vms.dao.mariadb.PhotoBoardDaoImpl 클래스 변경
    -  insert한 후에 자동 증가하는 Primary Key인 사진 게시글의 번호를 리턴받기 위해 insert() 메서드를 변경한다.  
    stmt.executeUpdate()의 두번째 파라미터를 Statement.RETURN_GENERATED_KEYS로 설정한다.  
    PK 값이 담긴 ResultSet에서 next()와 getInt(1)를 호출하여 값을 꺼낸다.  
       체인 방식으로 PhotoFile 객체의 setBoardNo 메서드를 호출하고 photoFile 객체의 setFilepath 메서드를 호출하여  
    인스턴스 필드에 사진 게시글 번호와 사진 파일을 담는다.

- jeoneunhye.vms.servlet.PhotoBoardAddServlet 클래스 변경
    - VideoDao 객체를 주입받아 영상 번호의 유효성을 검사한다.
    - 사진 게시글을 입력받을 때 첨부 파일도 입력받는다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardAddServlet` 객체에 VideoDao와 PhotoFileDao 객체를 주입한다. 

`ClientApp` 실행 예:

```
명령> /photoboard/add
제목?
okok2
영상 번호?
101
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?
   <== 입력없이 엔터를 치면?
최소 한 개의 사진 파일을 등록해야 합니다.
사진 파일?
a1.gif
사진 파일?
a2.gif
사진 파일?
a3.gif
사진 파일?

사진을 저장했습니다.
```

### 작업3) '/photoboard/detail' 명령을 처리한다.

사진 게시글을 출력할 때 첨부 파일 목록도 함께 출력한다.

- jeoneunhye.vms.dao.PhotoFileDao 인터페이스 변경
    - 사진 파일 목록을 리턴하는 메서드 findAll(int boardNo)를 정의한다.
- jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl 클래스 변경
    - PhotoFileDao 인터페이스에 추가된 메서드 findAll(int boardNo)를 구현한다.
- jeoneunhye.vms.servlet.PhotoBoardDetailServlet 클래스 변경
    - PhotoFileDao 의존 객체를 주입받는다.
    - 사진 게시글 다음에 첨부파일 목록을 출력한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardDetailServlet` 객체에 PhotoFileDao 객체를 주입한다. 

`ClientApp` 실행 예:

```
명령> /photoboard/detail
번호?
7
제목: test 사진 게시글
내용: 테스트입니다.
작성일: 2019-12-14
조회수: 0
영상: 기초 영어 회화
사진 파일:
> ppt1.jpeg
> pp2.jpeg
> pp3.jpeg
```

### 작업4) PhotoFile 객체의 생성자 및 셋터를 활용한다.

인스턴스의 초기 값을 설정할 수 있는 생성자를 추가한다.

생성자를 통해 인스턴스의 초기 값을 설정하기 I:

- jeoneunhye.vms.domain.PhotoFile 클래스 변경
    - PhotoFile(String filepath, int boardNo) 생성자를 추가한다.
- jeoneunhye.vms.servlet.PhotoBoardAddServlet 클래스 변경
    - PhotoFile(filepath, boardNo) 생성자를 사용한다.

생성자를 통해 인스턴스의 초기 값을 설정하기 II:

- jeoneunhye.vms.domain.PhotoFile 클래스 변경
    - PhotoFile(int no, String filepath, int boardNo) 생성자를 추가한다.
- jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl 클래스 변경
    - PhotoFile(no, filepath, boardNo) 생성자를 사용한다.

셋터 메서드를 통해 인스턴스의 초기 값을 설정하기:

- jeoneunhye.vms.domain.PhotoFile 클래스 변경
    - 셋터 메서드가 인스턴스 주소를 리턴하게 변경한다.
- jeoneunhye.vms.servlet.PhotoBoardAddServlet 클래스 변경
    - PhotoFile 객체를 만들 때 셋터 메서드로 값을 설정한다.
- jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl 클래스 변경
    - PhotoFile 객체를 만들 때 셋터 메서드로 값을 설정한다.

### 작업5) '/photoboard/update' 명령을 처리한다.

사진 게시글을 변경할 때 첨부 파일도 변경한다.

- jeoneunhye.vms.dao.PhotoFileDao 인터페이스 변경
    - 사진 파일을 삭제하는 메서드 deleteAll(int boardNo)를 추가한다.
- jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl 클래스 변경
    - PhotoFileDao 인터페이스에 추가된 메서드를 구현한다.
- jeoneunhye.vms.servlet.PhotoBoardUpdateServlet 클래스 변경
    - 사진 게시글의 첨부 파일을 변경한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardUpdateServlet` 객체에 PhotoFileDao 객체를 주입한다. 

`ClientApp` 실행 예:

```
명령> /photoboard/update
번호?
7
제목(okok2)?
안녕하세요
사진 파일:
> aaa1.jpeg
> aaa2.jpeg

사진은 일부만 변경할 수 없습니다.
전체를 새로 등록해야 합니다.
사진을 변경하시겠습니까?(Y/n)
y
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?

최소 한 개의 사진 파일을 등록해야 합니다.
사진 파일?
ppt1.jpeg
사진 파일?
pp2.jpeg
사진 파일?
pp3.jpeg
사진 파일?

사진을 변경했습니다.
```

### 작업6) '/photoboard/delete' 명령을 처리한다.

사진 게시글을 삭제할 때 첨부 파일도 삭제한다.

- jeoneunhye.vms.servlet.PhotoBoardDeleteServlet 클래스 변경
    - 첨부 파일 삭제를 할 때 사용할 PhotoFileDao 객체를 주입받는다.
    - 사진 게시글을 삭제하기 전에 첨부 파일을 먼저 삭제한다.
    - 그 후 사진 게시글을 삭제한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardDeleteServlet` 객체에 PhotoFileDao 객체를 주입한다.

`ClientApp` 실행 예:

```
명령> /photoboard/delete
번호?
99
해당 사진 게시글을 찾을 수 없습니다.

명령> /photoboard/delete
번호?
7
사진 게시글을 삭제했습니다.
```