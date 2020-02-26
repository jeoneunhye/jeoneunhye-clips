# 11 - 패키지로 클래스를 분류하기

### 작업1) 사용자 명령을 처리하는 클래스를 별도의 패키지로 분류하라.

- 핸들러 패키지 생성
    - `jeoneunhye.vms.handler` 패키지 생성
- 핸들러 클래스를 `handler` 패키지로 이동
    - `LessonHandler`, `MemberHandler`, `BoardHandler` 클래스를 `jeoneunhye.vms.handler` 패키지로 옮긴다.
    - 다른 패키지에서 변수에 접근할 수 있도록 접근 제어를 `public` 으로 변경
- App.java 변경
    - 핸들러 클래스에 대해 import 문 추가