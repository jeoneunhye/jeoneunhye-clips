# 37_3 - Application Server 구조로 변경: 규칙2에 따라 통신하는 클라이언트 만들기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ClientApp.java 변경
- src/main/java/jeoneunhye/vms/handler/ 폴더 삭제
- src/main/java/jeoneunhye/vms/dao/ 폴더 삭제
- src/main/java/jeoneunhye/vms/domain/ 폴더 삭제

### 작업1) 서버의 추가 데이터 입력 요구에 응답할 수 있도록 통신 규칙을 변경한다.

프로토콜 통신 규칙2) 사용자의 추가 입력을 포함

```
[클라이언트]                                        [서버]
서버에 연결 요청         -------------->            연결 승인
명령(CRLF)             -------------->           명령처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 작업2) 서버 요청에 응답한다.

- jeoneunhye.vms.ClientApp 클래스 변경

### 작업3) 정규표현식을 사용하여 입력한 문자열에서 프로토콜, 서버 주소, 포트 번호, 명령어를 추출한다.

- jeoneunhye.vms.ClientApp 클래스 변경
    - java.util.regex.Pattern과 java.util.regex.Matcher 객체를 사용한다.