33_1 - 서버의 Stateless 통신 방식에 맞춰 클라이언트 변경하기
===

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) 통신 방식을 `Stateful`에서`Stateless` 방식으로 변경한다.

- jeoneunhye.vms.ClientApp 클래스 변경
    - 서버에게 한 번의 요청 처리를 받고 연결을 끊는다.
    - 명령창에 입력한 문자열을 Stack과 Queue에 보관하여 "history", "history2"로  나열하고, "quit"을 입력하면 클라이언트를 종료하는 코드를 processCommand()에서 service()로 이동한다. 
    - 이 때 "quit"을 입력하면 서버로 요청을 보내지 않고 바로 클라이언트의  service 메서드를 멈추도록 한다.
    - stack과 queue 객체는 인스턴스 필드로 선언한 뒤 생성자로 받도록 한다.
    - service 메서드에서 정의한 서버와 연결할 소켓과 입출력 스트림을 준비하는 try~ catch...문을 processCommand 메서드로 이동한다. 이제 입출력 스트림은 processCommand에서 준비하기 때문에 파라미터로 받을 필요가 없다.
    - 명령창에서 command 문자열을 입력받는 코드가 service()로 이동했기 때문에 commandMap.get 메서드를 호출하기 위한 command를 파라미터로 넘겨주도록 변경한다. 
    - 한 번 요청을 받아 데이터 처리를 수행한 서버와는 연결을 끊는다.
    - service와 processCommand 인스턴스 메서드 모두 사용할 필드인 서버 주소 host와 포트 번호 port는 인스턴스 필드로 선언한다.
    
    - if의 조건식으로 String.equals("/server/stop") 메서드를 사용하여 서버에 Command를 전송하는 코드를 변경한다.  
    => 익명 클래스로 Command 구현 객체 생성하여 commandMap에 담아 사용한다.  
```
      commandMap.put("/server/stop", new Command() {  
        @Override  
        public void execute() {  
          try {  
            out.writeUTF(command);  
            out.flush();  
            System.out.println("서버: " + in.readUTF());  
            System.out.println("안녕!");  
          } catch (Exception e) {}  
        }  
      });  
```  
    => 람다식으로 Command 구현 객체를 생성하여  commandMap에 담아 사용한다.  
```
commandMap.put("/server/stop", () -> {  
        try {  
          out.writeUTF(command);  
          out.flush();  
          System.out.println("서버: " + in.readUTF());  
          System.out.println("안녕!");  
        } catch (Exception e) {}  
      });  
```

- 해당 버전에서는 한 번의 요청을 처리하고 서버와 연결을 끊는다. 데이터 변경 처리 요청의 경우 "/detail"과 "/update" 두 번의 요청을 보내는데 "*/update"를 보내려는 순간 socket이 닫혀 있어 보낼 수 없는 오류가 발생하는 문제점이 있다.