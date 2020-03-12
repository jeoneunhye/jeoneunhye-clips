# 37_5 - Application Server 구조로 변경: 서버 종료 명령 처리하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 클라이언트의 '/server/stop' 요청을 처리한다.

- jeoneunhye.vms.DataLoaderListener 클래스 변경
    - 애플리케이션이 종료될 때 Connection 객체를 닫는 코드를 추가한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - 서버 멈춤 여부를 알려주는 boolean serverStop 필드를 선언한다.
    - DB 커넥션 객체가 모든 스레드의 작업이 완료되었을 때 닫히도록 notifyApplicationDestroyed(); 코드를 맨 아래에 둔다.
    - processRequest의 리턴 타입을 int에서 void로 변경한다.
    - processRequest를 호출하고 '/server/stop' 명령을 받으면 serverStop은 true가 되고 service()로 리턴한다.
    - 클라이언트의 연결을 받는 반복문이 멈추고 모든 스레드의 작업이 끝나면, 종료 대기중이던 작업 스레드를 종료하고 DB 커넥션 객체를 닫는다.
    - Stateless 방식을 사용하기 때문에 serverStop 필드의 값을 true로 받고 바로 서버를 종료할 수 없다.  
    서버가 실제로 종료되는 시점은 **클라이언트의 요청을 한번 더 받아 연결**할 때이다.

```
0.5초마다 스레드의 작업 완료 여부를 검사한다.
isTerminated(): 모든 스레드의 작업이 완료되었을 때 true를 반환한다.

executorService.shutdown();

while (true) {
  if (executorService.isTerminated()) {
    break;
  }

  try {
    Thread.sleep(500);

  } catch (Exception e) {
    e.printStackTrace();
  }
}
```