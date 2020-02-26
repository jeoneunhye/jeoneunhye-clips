32_2 - 클라이언트로부터 메시지 수신받고 응답하기
===

### 작업1) 클라이언트와 연결할 소켓을 준비한다.

- ServerApp.java
    - 포트 번호를 주입한 서버 소켓을 준비한다.   
```
ServerSocket serverSocket = new ServerSocket(9999);
```
    - 클라이언트로부터 연결 요청이 왔을 때 연결을 수락하는 메서드를 호출한다.   
```
Socket socket = serverSocket.accept();
```

### 작업2) 클라이언트가 보낸 메시지를 읽고 응답 메시지를 전송한다.

- ServerApp.java
    - 클라이언트가 보낸 한 줄을 입력받을 Scanner와 출력 스트림 PrintStream 객체를 준비한다.   
```
Scanner in = new Scanner(socket.getInputStream());   
PrintStream out = new PrintStream(socket.getOutputStream());   
```
    - 클라이언트가 보낸 한 줄을 읽는다.   
```
String message = in.nextLine();   
```
    - 클라이언트에게 '영상 관리 시스템 서버입니다.' 메시지를 출력한다.   
```
out.println("영상 관리 시스템 서버입니다.");
```