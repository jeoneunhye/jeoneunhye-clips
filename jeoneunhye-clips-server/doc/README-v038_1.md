# 38_1 - 트랜잭션 적용 전: 사진 게시판 추가하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/domain/PhotoBoard.java 추가
- src/main/java/jeoneunhye/dao/PhotoBoardDao.java 추가
- src/main/java/jeoneunhye/dao/mariadb/PhotoBoardDaoImpl.java 추가
- src/main/java/jeoneunhye/servlet/PhotoBoardListServlet.java 추가
- src/main/java/jeoneunhye/servlet/PhotoBoardDetailServlet.java 추가
- src/main/java/jeoneunhye/servlet/PhotoBoardAddServlet.java 추가
- src/main/java/jeoneunhye/servlet/PhotoBoardUpdateServlet.java 추가
- src/main/java/jeoneunhye/servlet/PhotoBoardDeleteServlet.java 추가
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 사진 게시글의 정보를 담을 PhotoBoard 객체를 추가한다.

- jeoneunhye.vms.domain.PhotoBoard 클래스 추가
    - 사진 게시글의 데이터 타입을 정의한다.  
       영상 정보를 담을 Video 타입의 인스턴스 필드를 추가한다.

### 작업2) 영상 사진 게시판 데이터를 다룰 DAO를 생성한다.

- jeoneunhye.vms.dao.PhotoBoardDao 인터페이스 추가
    - 사진 게시물의 CRUD 관련 메서드 호출 규칙을 정의한다.
- jeoneunhye.vms.dao.mariadb.PhotoBoardDaoImpl 클래스 추가
    - PhotoBoardDao 인터페이스를 구현한다.
- jeoneunhye.vms.DataLoaderListener 클래스 변경
    - PhotoBoardDao 객체를 생성하여 context 맵에 보관한다.

### 작업3) '/photoboard/list' 명령을 처리한다.

- jeoneunhye.vms.servlet.PhotoBoardListServlet 클래스 추가
    - vms_photo와 vms_video를 조인하여 먼저 영상 제목을 출력한다.
    - 사진 게시글의 목록을 최신순으로 출력한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardListServlet` 객체를 생성하여 command 맵에 보관한다.

`ClientApp` 실행 예:

```
명령> /photoboard/list
영상 번호? 1
영상> xxxxx
----------------------------------------------------
  1, 게시글입니다           , 2020-3-11, 0
  2, 게시글입니다            , 2020-3-11, 0
  3, null                , 2020-3-11, 0
  4, 게시글입니다              , 2020-3-11, 0
```

### 작업4) '/photoboard/detail' 명령을 처리한다.

- jeoneunhye.vms.dao.mariadb.PhotoBoardDaoImpl 클래스 변경
    - 사진 게시글의 상세정보를 가져올 때 vms_photo와 vms_video를 조인한다.
    - vms_photo 데이터는 PhotoBoard에 저장하고, vms_video 데이터는 Lesson에 저장한다. 
- jeoneunhye.vms.servlet.PhotoBoardDetailServlet 추가
    - 사진 게시글의 상세정보를 출력한다.
- jeoneunhye.vms.ServerApp 변경
    - `PhotoBoardDetailServlet` 객체를 생성하여 command 맵에 보관한다.

`ClientApp` 실행 예:

```
명령> /photoboard/detail
번호?
6
제목: test1
내용: 테스트입니다.
작성일: 2020-3-11
조회수: 0
영상 제목: xxxx

명령> /photoboard/detail
번호?
5
해당 번호의 사진 게시글이 없습니다.
```

### 작업5) '/photoboard/add' 명령을 처리한다.

- jeoneunhye.vms.servlet.PhotoBoardAddServlet 클래스 추가
    - 사진 게시글을 등록할 때 영상 번호를 입력받는다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardAddServlet` 객체를 생성하여 command 맵에 보관한다.

`ClientApp` 실행 예:

```
명령> /photoboard/add
제목?
test1
영상 번호?
101
사진을 저장했습니다.

명령> /photoboard/add
제목?
test1
영상 번호?
200
영상 번호가 유효하지 않습니다.
```

### 작업6) '/photoboard/update' 명령을 처리한다.

- jeoneunhye.vms.servlet.PhotoBoardUpdateServlet 클래스 추가
    - 사진 게시글을 변경한다. 
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardUpdateServlet` 객체를 생성하여 command 맵에 보관한다.

`ClientApp` 실행 예:

```
명령> /photoboard/update
번호?
6
제목(test1)?
test1...xx
내용(테스트입니다)?
사진을 변경했습니다.

명령> /photoboard/update
번호?
600
해당 번호의 사진 게시글이 없습니다.
```

### 작업7): '/photoboard/delete' 명령을 처리한다.

- jeoneunhye.vms.servlet.PhotoBoardDeleteServlet 클래스 추가
    - 사진 게시글을 삭제한다. 
- jeoneunhye.vms.ServerApp 클래스 변경
    - `PhotoBoardDeleteServlet` 객체를 생성하여 command 맵에 보관한다.

`ClientApp` 실행 예:

```
명령> /photoboard/delete
번호?
6
사진 게시글을 삭제했습니다.

명령> /photoboard/delete
번호?
600
해당 번호의 사진 게시글이 없습니다.
```