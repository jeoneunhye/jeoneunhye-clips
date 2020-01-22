# 23 - 일반화를 통해 공통 메서드를 분리하고 인터페이스로 객체간의 사용 규칙 정의하기

### 작업1) 일반화(Generalization) 상속 기법을 사용하여
    ArrayList와 LinkedList의 공통점을 찾아 별도의 클래스로 분리하라.

- List.java
    - ArrayList와 LinkedList의 공통 멤버(필드와 메서드)를 선언한다.
    - 서브 클래스에서 반드시 재정의해야 하는 메서드는 추상 메서드로 구현하지 않고 놔 둔다.
- ArrayList.java
    - `List`를 상속받고 메서드를 재정의한다.
- LinkedList.java
    - `List`를 상속받고 메서드를 재정의한다.
- LessonHandler.java
    - ArrayList 또는 LinkedList를 직접 지정하는 대신에 추상 클래스를 필드로 선언한다.
    - 생성자에서 구체적으로 구현한 서브 클래스를 공급받도록 변경한다.
    - 따라서 특정 클래스(ArrayList나 LinkedList)에 의존하는 코드를 제거한다.
- MemberHandler.java
    - ArrayList 또는 LinkedList를 직접 지정하는 대신에 추상 클래스를 필드로 선언한다.
    - 생성자에서 구체적으로 구현한 서브 클래스를 공급받도록 변경한다.
    - 따라서 특정 클래스(ArrayList나 LinkedList)에 의존하는 코드를 제거한다.
- BoardHandler.java
    - ArrayList 또는 LinkedList를 직접 지정하는 대신에 추상 클래스를 필드로 선언한다.
    - 생성자에서 구체적으로 구현한 서브 클래스를 공급받도록 변경한다.
    - 따라서 특정 클래스(ArrayList나 LinkedList)에 의존하는 코드를 제거한다.
- App.java
    - 각 Handler가 사용할 의존 객체(List 객체)를 준비한다.
    - 각 Handler를 생성할 때 해당 의존 객체를 넘겨준다.
    
### 작업2) 일반화를 통해 추출한 수퍼 클래스를 추상 클래스로 정의하라.

- AbstractList.java
    - List 클래스의 이름을 AbstractList로 변경한다.
    - AbstractList 클래스를 추상 클래스로 선언한다.
    - AbstractList의 메서드를 추상 메서드로 정의한다.
    
### 작업3) 추상 클래스에서 추상 메서드를 추출하여 인터페이스로 정의하라.

- List.java
    - AbstractList 추상 클래스에 있는 추상 메서드를 추출하여 따로 메서드 사용 규칙을 정의한다.
- AbstractList.java
    - 추상 메서드를 list 인터페이스로 옮긴다.
    - List 규칙을 따른다.