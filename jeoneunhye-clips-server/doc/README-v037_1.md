# 37_1 - Application Server 구조로 변경: 규칙1에 따라 통신하는 서버 만들기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 클라이언트와 서버 사이의 기본 통신 규칙을 정한다.

- 요청과 응답은 `Stateless` 방식으로 처리한다.
    - 클라이언트가 요청할 때 서버와 연결하고, 서버에서 응답하면 연결을 끊는다.

통신 프로토콜 규칙 1) 단순한 명령어 전송과 실행 결과 수신

```
[클라이언트]                                        [서버]
서버에 연결 요청        -------------->           연결 승인
명령(CRLF)            -------------->            명령처리
화면 출력               <--------------           응답(CRLF)
화면 출력               <--------------           응답(CRLF)
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 작업2) 통신 프로토콜 규칙 1에 따라 클라이언트의 요청에 응답한다.

- jeoneunhye.vms.ServerApp 클래스 변경
    - 클라이언트 요청에 대해 간단한 인사말을 출력하도록 변경한다.