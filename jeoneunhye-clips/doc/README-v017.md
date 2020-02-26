# 17 - 다형성과 형변환 응용

## 작업1) Lesson, Member, Board를 모두 다룰 수 있는 List 클래스를 만들라.

- ArrayList.java
    - 필드명을 기본 여유크기는 DEFAULT_CAPACITY로, 배열은 list로, 저장한 갯수는 size로 변경한다.
    - Object를 사용하여 VideoList, MemberList, BoardList 클래스를 합쳐 한 클래스로 만든다.
    - 기존의 세 클래스는 삭제한다.
- VideoHandler.java
    - `ArrayList` 클래스를 사용하여 데이터를 처리한다.
- MemberHandler.java
    - `ArrayList` 클래스를 사용하여 데이터를 처리한다.
- BoardHandler.java
    - `ArrayList` 클래스를 사용하여 데이터를 처리한다.