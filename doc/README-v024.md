24 - `Iterator` 구현체를 이용하여 자료구조와 상관없이 일관된 방법으로 데이터 조회하기
===

### 작업1) List, Stack, Queue가 같은 Iterator 인터페이스를 구현하도록 만들어 데이터 조회 방법을 통일한다.

- Iterator.java   
    - 인터페이스로 값을 꺼내는 규칙을 정의한다.   
- List.java
    - Iterator 객체를 리턴하는 메서드를 정의한다.   
- AbstractList.java, Stack.java, Queue.java
    - Iterator 구현체를 익명 클래스로 만들어 리턴하는 `iterator()` 메서드를 구현한다.   
    - 객체를 한 번만 사용할 거라면 따로 클래스를 생성하지 않고 `익명 클래스`로 인터페이스를 구현한다.   

### 작업2) Stack과 Queue에서 값을 꺼낼 때 Iterator를 사용하도록 변경한다.

- App.java
    - 'history', 'history2' 명령을 처리할 때 직접 Stack과 Queue 객체를 사용하지 않고   
    Iterator 구현체를 이용하여 데이터를 꺼내도록 변경한다.
    - App에서 준비한 Stack과 Queue를 호출할 때 파라미터로 넘겨주면 일관된 iterator 메서드를   
             호출하여 데이터 존재 여부를 확인하고 데이터를 꺼낼 수 있다.

### 작업3) Handler 클래스에서 ArrayList, LinkedList의 목록을 꺼내는 메서드의 코드를 변경한다.

- VideoHandler.java, MemberHandler.java, BoardHandler.java
     - listVideo(), listMember(), listBoard()를 호출하면   
     ArrayList와 LinkedList의 수퍼 클래스인 AbstractList의 iterator()를 이용하여   
           데이터 존재 여부를 확인하고 데이터를 조회하도록 구현한다.
           
#### 실행 결과는 이전과 같다.