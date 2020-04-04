# 54_1 - HTTP 프로토콜 적용하기

```
- HTTP Request
  GET /index.html HTTP/1.1 (요청 URL 정보 (Mehotd /URI HTTP버전))
  user-agent: MSIE 6.0; Window NT 5.0 (사용자 웹 브라우저 종류)
  accept: test/html; */* (요청 데이터 타입 (응답의 Content-type과 유사))
  cookie:name=value (쿠키(인증 정보))
  refere: http://abc.com (경유지 URL)
  host: www.abc.com (요청 도메인)
```

```
- HTTP Response
  HTTP/1.1 200 OK (프로토콜 버전 및 응답코드)
  Server: Apache (웹 서버 정보)
  Content-type: text/html (MIME Type)
  Content-length : 1593 (HTTP BODY Size)
  <html><head>..... (HTTP BODY Content)
```

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) HTTP 프로토콜에 따라 클라이언트 요청을 처리한다.

- ServerApp 클래스 변경
    - 클라이언트의 HTTP 요청에 응답하는 코드를 설정한다.

```
- HTTP 응답 코드
· 정보 전송
100 :  Continue (클라이언트로부터 일부 요청을 받았으며 나머지 정보를 계속 요청)
· 임시 응답
 101 : Switching protocols (클라이언트가 보낸 Upgrade 요청 헤더에 따른 서버의 프로토콜 변경을 알림)
· 성공
 200 : OK (요청이 성공적으로 수행)
 201 : Created (PUT 메소드에 의해 원격지 서버에 파일 생성됨)
 202 : Accepted (웹 서버가 명령을 수신)
 203 : Non-authoritative information (서버가 클라이언트 요구 중 일부만 전송)
 204 : No content (PUT, POST, DELETE 요청은 성공했지만 전송할 데이터가 없는 경우)
· 리다이렉션
 301 : Moved permanently (요구한 데이터를 변경된 타 URL에 요청 / Redirect된 경우)
 302 : Not temporarily
 304 : Not modified (컴퓨터 로컬의 캐시 정보를 이용함, 대개 gif 등은 웹 서버에 요청하지 않음)
· 클라이언트 요청 에러
 400 : Bad Request (사용자의 잘못된 요청을 처리할 수 없음)
 401 : Unauthorized (인증이 필요한 페이지를 요청한 경우)
 402 : Payment required(예약됨)
 403 : Forbidden (접근 금지, 디렉터리 리스팅 요청 및 관리자 페이지 접근 등을 차단)
 404 : Not found (요청한 페이지 없음)
 405 : Method not allowed (혀용되지 않은 http method 사용)
 407 : Proxy authentication required (프록시 인증 요구됨)
 408 : Request timeout (요청 시간 초과)
 410 : Gone (영구적으로 사용 금지)
 412 : Precondition failed (전체 조건 실패)
 414 : Request-URI too long (요청 URL 길이가 긴 경우)
· 서버 에러
 500 : Internal server error (내부 서버 오류)
 501 : Not implemented (웹 서버가 처리할 수 없음)
 503 : Service unnailable (서비스 제공 불가)
 504 : Gateway timeout (게이트웨이 시간 초과)
 505 : HTTP version not supported (해당 http 버전 미지원)
```

```
- HTTP Method
GET : 요청받은 URI의 정보를 검색하여 응답한다.
HEAD : GET방식과 동일하지만, 응답에 BODY가 없고 응답코드와 HEAD만 응답한다.
POST : 요청된 자원을 생성한다. 새로 작성된 리소스인 경우 HTTP 헤더 항목 Location : URI 주소를 포함하여 응답한다.
PUT : 요청된 자원을 수정한다. 내용 갱신을 위주로 Location : URI를 보내지 않아도 된다.
PATCH : PUT과 유사하게 요청된 자원을 수정할 때 사용한다. PUT은 자원 전체를 갱신하지만, PATCH는 자원의 일부를 교체하는 의미로 사용한다.
DELETE : 요청된 자원을 삭제할 것을 요청한다. (안전성 문제로 대부분의 서버에서 비활성)
CONNECT : 동적으로 터널 모드를 교환, 프락시 기능 요청시 사용한다.
TRACE : 원격지 서버에 루프백 메시지 호출하기 위해 테스트용으로 사용한다.
OPTIONS : 웹서버에서 지원되는 메서드의 종류를 확인할 경우 사용한다.
```

### 작업2) HTTP 요청/응답을 확인한다.

- ClientApp 대신 Web Browser를 클라이언트로 사용하여 데이터를 요구하지 않는 일반 요청에 대한 응답을 테스트한다.
    - 'http://localhost:9999/video/list', 'http://localhost:9999/member/list', 'http://localhost:9999/board/list'