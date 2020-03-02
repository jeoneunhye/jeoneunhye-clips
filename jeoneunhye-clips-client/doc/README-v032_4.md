32_4 - 서버에 데이터를 요청하는 기능 추가하기
===

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/domain/Video.java 추가
- src/main/java/jeoneunhye/vms/domain/Member.java 추가
- src/main/java/jeoneunhye/vms/domain/Board.java 추가
- src/main/java/jeoneunhye/vms/handler/VideoAddCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/VideoListCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/VideoDetailCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/VideoUpdateCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/VideoDeleteCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/MemberAddCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/MemberListCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/MemberDetailCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/MemberUpdateCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/MemberDeleteCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/BoardAddCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/BoardListCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/BoardDetailCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/BoardUpdateCommand.java 추가
- src/main/java/jeoneunhye/vms/handler/BoardDeleteCommand.java 추가
- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) project v31 폴더의 domain 패키지에서 도메인 클래스들을 가져온다.

- jeoneunhye.vms.domain 패키지 생성
    - Video, Member, Board 클래스를 해당 패키지로 이전한다.

### 작업2) project v31 폴더의 handler 패키지에서 명령을 처리하는 클래스들을 가져온다.

- jeoneunhye.vms.handler 패키지 생성
    - 영상 데이터에 관한 명령을 처리하는 클래스를 해당 패키지로 이전한다.    
    VideoAddCommand, VideoListCommand, VideoDetailCommand, VideoUpdateCommand, VideoDeleteCommand 클래스 추가

    - 회원 데이터에 관한 명령을 처리하는 클래스를 해당 패키지로 이전한다.    
    MemberAddCommand, MemberListCommand, MemberDetailCommand, MemberUpdateCommand, MemberDeleteCommand 클래스 추가

    - 게시글 데이터에 관한 명령을 처리하는 클래스를 해당 패키지로 이전한다.    
    BoardAddCommand, BoardListCommand, BoardDetailCommand, BoardUpdateCommand, BoardDeleteCommand 클래스 추가

### 작업3) Command 구현 객체가 서버와 통신할 수 있도록 입출력 도구를 제공한다.

- ClientApp.java 변경
    - 서버와 연결하는 소켓을 사용한다.
    - 서버와 통신할 수 있는 입출력 도구를 각각의 Command 구현 객체에 제공한다.    
        직렬화, 역직렬화된 데이터를 입출력하는 `ObjectInputStream`과 `ObjectOutputStream` 도구를 사용한다.
    - 각각의 Command 구현 객체는 명령창에서 입력받은 문자열 key를 이용하여 HashMap에서 꺼낸다.    
        명령어에 따라 다른 기능을 하지만 동일한 이름의 메서드를 호출하여 코드를 간결화한다.
    
### 작업4) Command 구현 객체가 서버와 통신하여 작업하도록 코드를 변경한다.

- [Video/Member/Board]/[Add/List/Detail/Update/Delete]/Command.java 변경
    - ClientApp 클래스에서 Command 구현 객체를 생성하여 메서드를 호출할 때 `소켓이 연결된 입출력 도구`를 사용할 수 있도록 생성자를 변경한다.
    - 데이터를 읽고 쓸 때 서버에 요청하도록 execute()를 변경한다.