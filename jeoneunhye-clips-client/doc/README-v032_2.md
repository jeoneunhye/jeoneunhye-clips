32_2 - 간단한 메시지를 서버에 전송하고 응답받기
===

### 소스 및 결과

- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) 소켓을 이용하여 서버와 연결한다.

- ClientApp.java
    - 서버 주소와 포트 번호를 소켓에 주입하여 서버에게 연결을 요청한다.

### 작업2) 서버에 요청 메시지를 전송하고 응답 메시지를 수신한다.

- ClientApp.java
     - 서버에게 보낼 메시지를 출력할 PrintStream 객체와   
     서버로부터 응답 메시지를 한 줄 읽을 Scanner 객체를 준비한다.   
```
PrintStream out = new PrintStream(socket.getOutputStream());   
Scanner in = new Scanner(socket.getInputStream());
```
    - PrintStream 출력 객체를 이용하여 Scanner로 명령창에서 입력받은 메시지를 서버에 출력한다.   
```
String sendMsg = keyScan.nextLine();   
out.println(sendMsg);
```
    - Scanner 객체를 이용하여 서버에서 입력받은 응답 메시지를 한 줄 읽어 출력한다.   
```
String message = in.nextLine();   
System.out.println("서버> " + message);
```