26 - `커맨드(Command)` 디자인 패턴 적용하기
===

### 작업1) 명령하는 클래스와 명령을 처리하는 클래스 사이의 규칙을 정의한다.

- Command.java
    - 인터페이스를 생성하여 App 클래스와 명령을 처리하는 클래스 사이의 호출 규칙을 정의한다.

### 작업2) 명령을 처리하는 Handler 클래스의 메서드들을 객체로 분리한다.

- VideoHandler.java, MemberHandler.java, BoardHandler.java
    - 각각의 메서드를 `Command` 규칙을 구현하는 객체로 분리한다.
    - 명령 처리 메서드 execute()에 데이터를 **추가/조회/변경/삭제**하는 코드를 구현한다.
  
### 작업3) `Map`을 이용하여 `Command` 객체들을 관리한다.

- App.java
    - `commandMap`이라는 Map 객체는 사용자의 명령어를 key로 받아 Command 구현체를 꺼낸다.
    - 각 명령에 대해 조건문으로 분기하는 부분을 간략하게 변경한다.
    - 명령어가 추가될 때마다 해당 클래스에 메서드를 추가하는 대신   
    새 클래스를 추가하기 때문에 기존 소스를 손대지 않아 유지보수에 좋다.   
    즉 기존 소스에 영향을 끼치지 않고 새 기능을 추가하는 방식이다.   
    인터페이스를 이용하면 메서드 호출 규칙을 단일화하여 코딩의 일관성을 높여준다.   

#### 실행 결과는 이전과 같다.