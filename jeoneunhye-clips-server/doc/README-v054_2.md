# 54_2 - 출력 Content에 HTML 형식 적용하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/VideoDao.java 변경
- src/main/java/jeoneunhye/vms/dao/MemberDao.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardAddFormServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/BoardUpdateFormServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/BoardXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberAddFormServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/MemberXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/ViodeAddFormServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardAddFormServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/LoginFormServlet.java 추가
- src/main/java/jeoneunhye/vms/servlet/LoginServlet.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 게시글 목록을 출력할 때 HTML 형식으로 Content를 출력한다.

- BoardListServlet 클래스 변경
    - html5 기본 문법을 적용한다.

### 작업2) 웹 브라우저에 게시글 데이터 입력을 요구한다.

- BoardAddFormServlet 클래스 추가
    - 웹 브라우저에 게시글 데이터 입력을 요구하는 HTML을 보낸다.  
    텍스트 입력 박스에 이름을 부여하여 입력받은 데이터를 서버로 전송하도록 한다.
- BoardListServlet 클래스 변경
    - /board/addForm을 요청하는 링크를 추가한다.

### 작업3) 웹 브라우저가 보낸 데이터 받기

- ServerApp 클래스 변경
    - request-uri에서 자원의 경로와 데이터를 분리한다.
    ex) /board/add?title=aaaa  
    => 자원의 경로: /board/add  
    => 데이터: title=aaaa

### 작업4) 웹브라우저가 보낸 게시글 데이터 저장하기

- BoardAddServlet 클래스 변경
    - 웹 브라우저가 보낸 게시글을 입력한다.
    - 웹 브라우저에 게시글 입력 결과를 보낸다.

### 작업5) 게시글 상세 정보를 출력하기

- BoardDetailServlet 클래스 변경
    - 웹 브라우저가 보낸 번호의 게시글을 가져온다.
    - 웹 브라우저에 게시글 상세 정보를 HTML 형식으로 만들어 보낸다.
- BoardListServlet 클래스 변경
    - 해당 번호의 게시글의 /board/detail을 요청하는 링크를 추가한다.

### 작업6) 게시글 삭제하기

- BoardDeleteServlet 클래스 변경
    - 웹 브라우저가 보낸 번호의 게시글을 삭제한다.
    - 웹 브라우저에게 게시글 삭제 결과를 HTML 형식으로 만들어 보낸다.
- BoardDetailServlet 클래스 변경
    - /board/delete을 요청하는 링크를 추가한다.

### 작업7) 게시글 변경 폼 만들기

- BoardDetailServlet 클래스 변경
    - /board/updateForm을 요청하는 링크를 추가한다.
- BoardUpdateFormServlet 클래스 추가
    - 웹 브라우저에 게시글 데이터 변경을 요구하는 HTML을 보낸다.

### 작업8) 게시글 변경하기

- BoardUpdateServlet 클래스 변경
    - 웹 브라우저가 보낸 게시글을 변경한다.
    - 웹 브라우저에 게시글 변경 결과를 보낸다.

### 작업9) 회원 관리 서블릿을 모두 변경하기

- MemberDao 인터페이스 변경
    - default 메서드를 추상 메서드로 전환한다.
- MemberAddFormServlet 클래스 추가
- MemberXxxServlet 클래스 변경
- ServerApp 클래스 변경

### 작업10) 영상 관리 서블릿을 모두 변경하기

- VideoDao 인터페이스 변경
    - default 메서드를 추상 메서드로 전환한다.
- VideoAddFormServlet 클래스 추가
- VideoXxxServlet 클래스 변경

### 작업11) 사진 게시글 관리 서블릿을 모두 변경하기

- PhotoBoardAddFormServlet 클래스 추가
- PhotoBoardXxxServlet 클래스 변경

### 작업12) 로그인 서블릿을 모두 변경하기

- LoginFormServlet 클래스 추가
- LoginServlet 클래스 변경