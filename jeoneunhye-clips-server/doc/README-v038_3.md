# 38_3 - 트랜잭션 적용 전: 코드 리팩토링하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/util/Prompt.java 추가
- src/main/java/jeoneunhye/servlet/VideoAddServlet.java 변경
- src/main/java/jeoneunhye/servlet/VideoDetailServlet.java 변경
- src/main/java/jeoneunhye/servlet/VideoUpdateServlet.java 변경
- src/main/java/jeoneunhye/servlet/VideoDeleteServlet.java 변경
- src/main/java/jeoneunhye/servlet/MemberAddServlet.java 변경
- src/main/java/jeoneunhye/servlet/MemberDetailServlet.java 변경
- src/main/java/jeoneunhye/servlet/MemberUpdateServlet.java 변경
- src/main/java/jeoneunhye/servlet/MemberDeleteServlet.java 변경
- src/main/java/jeoneunhye/servlet/MemberSearchServlet.java 변경
- src/main/java/jeoneunhye/servlet/BoardAddServlet.java 변경
- src/main/java/jeoneunhye/servlet/BoardDetailServlet.java 변경
- src/main/java/jeoneunhye/servlet/BoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/servlet/BoardDeleteServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardDetailServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/servlet/PhotoBoardDeleteServlet.java 변경

### 작업1) 클라이언트에게 입력 값을 요구하는 코드를 메서드로 정의한다.

- jeoneunhye.util.Prompt 클래스 추가
    - PrintStream으로 !{}!를 출력하여 입력 값을 요구하는 반복되는 코드를 Prompt 클래스의 메서드로 분리한다.  
    => getInt, getString, getDate 메서드를 정의한다.
    
### 작업2) 클라이언트에게 입력 값을 요구하는 코드를 Prompt.getXxx() 호출로 대체한다.

- VideoAddServlet, VideoDetailServlet, VideoUpdateServlet, VideoDeleteServlet 클래스 변경
    - 영상 데이터의 입력 값을 요구하는 코드를 Prompt.getXxx() 호출로 대체한다.
- MemberAddServlet, MemberDetailServlet, MemberUpdateServlet, MemberDeleteServlet, MemberSearchServlet 클래스 변경
    - 회원 데이터의 입력 값을 요구하는 코드를 Prompt.getXxx() 호출로 대체한다.
- BoardAddServlet, BoardDetailServlet, BoardUpdateServlet, BoardDeleteServlet 클래스 변경
    - 게시글 데이터의 입력 값을 요구하는 코드를 Prompt.getXxx() 호출로 대체한다.
- PhotoBoardAddServlet, PhotoBoardDetailServlet, PhotoBoardUpdateServlet, PhotoBoardDeleteServlet 클래스 변경
    - 사진 게시글 데이터의 입력 값을 요구하는 코드를 Prompt.getXxx() 호출로 대체한다.

### 작업3) 사진 게시글 등록, 변경시 첨부 파일 입력 코드를 메서드로 정의한다.

- PhotoBoardAddServlet 클래스 변경
    - 첨부 파일을 입력하는 부분을 별도의 메서드 inputPhotoFiles로 분리한다.
- PhotoBoardUpdateServlet 클래스 변경
    - 첨부 파일 목록을 출력하는 부분을 별도의 메서드 printPhotoFile로 분리한다.
    - 첨부 파일을 입력하는 부분을 별도의 메서드 inputPhotoFiles로 분리한다.