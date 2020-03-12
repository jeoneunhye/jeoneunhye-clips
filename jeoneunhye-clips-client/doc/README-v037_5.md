# 37_5 - Application Server 구조로 변경: 서버 종료 명령 처리하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) 클라이언트에서 '/server/stop' 명령을 두 번 전송한다.

- jeoneunhye.vms.ClientApp 변경  
    - String 메서드 endsWith("/server/stop")을 이용하여 '/server/stop' 요청을 읽는다.
    - 사용자가 '/server/stop'을 입력했을 때 서버가 종료 작업을 즉시 할 수 있도록 한 번 더 요청문을 보낸다.