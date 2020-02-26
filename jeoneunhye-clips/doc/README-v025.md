25 - 자바 컬렉션 API 사용하기
===

### 작업1) ArrayList, LinkedList, Stack, Queue 클래스를 자바 컬렉션 API로 교체한다.

- VideoHandler.java, MemberHandler.java, BoardHandler.java
    - List를 `java.util.List` 인터페이스로 교체한다.
- App.java
    - 핸들러를 생성할 때 자바 컬렉션 API에서 제공하는 `java.util.List`의 구현체를 넘겨준다.
    - 명령 내역을 저장할 때 자바 컬렉션 API에서 제공하는 `java.util.Deque`와 `java.util.Queue` 구현체를 사용한다.

#### 실행 결과는 이전과 같다.