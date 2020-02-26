30 - 직렬화와 역직렬화(객체 복원)를 이용하여 객체를 통째로 읽고 쓰기
===

### 작업1) Domain 객체 단위로 읽고 출력한다.

- Video.java, Member.java, Board.java
    - `java.io.Serializable` 인터페이스를 구현하고 serialVersionUID 스태틱 변수의 값을 설정한다.
- App.java
    - 파일에서 데이터를 읽고 쓸 때 ObjectInputStream/ObjectOutputStream을 사용한다.
    - 파일에서 Domain 객체를 반복해서 읽고 쓴다.

### 작업2) ArrayList/LinkedList 객체를 통째로 읽고 출력한다.

- App.java
    - `java.io.Serializable` 구현체라면 직렬화/역직렬화가 가능하다.
    - 파일에서 Domain을 담고 있는 ArrayList, LinkedList 객체를 통째로 읽고 쓸 수 있다.

#### 실행 결과는 이전과 같다.