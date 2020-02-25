31 - `Observer` 디자인 패턴을 적용하여 클래스 구조를 변경
===

### 작업1) App 클래스의 스태틱 필드와 메서드를 인스턴스 멤버로 전환한다.

- App.java
    - 스태틱 필드와 스태틱 메서드를 인스턴스 필드와 인스턴스 메서드로 전환한다.
    - 기존에 main()에 있던 코드를 인스턴스 메서드 `service()`로 이동하고   
     App 객체를 만들어 service()를 호출하도록 변경한다.

### 작업2) 애플리케이션이 시작하거나 종료될 때 호출될 옵저버의 규칙을 정의한다.

- ApplicationContextListener.java
    - Observer가 갖춰야 할 규칙을 인터페이스로 정의한다.
    - 애플리케이션이 시작할 때 자동으로 호출할 메서드 `contextInitialized`를 정의한다.
    - 애플리케이션을 종료하기 전에 자동으로 호출할 메서드 `contextDestroyed`를 정의한다.

### 작업3) App 객체에 옵저버를 등록하고 제거하고 실행시키는 기능을 추가한다.

- App.java
    - 옵저버를 등록하는 메서드 `addApplicationContextListener(ApplicationContextListener)`를 추가한다.
    - 옵저버를 제거하는 메서드 `removeApplicationContextListener(ApplicationContextListener)`를 추가한다.
    - 애플리케이션을 시작하고 종료할 때 호출할 옵저버들을 반복문으로 Set 객체 `listeners`에서 꺼내도록 한다.
    - 애플리케이션을 시작할 때(service()의 첫 문장) 실행할 옵저버를 메서드로 호출한다.
    - 애플리케이션을 종료할 때(service()의 마지막 문장) 실행할 옵저버를 메서드로 호출한다.
    - 옵저버에서 처리했거나 처리해야 할 데이터를 파라미터를 이용하여 다른 객체에서 주고 받을 수 있도록 Map 객체 `context`에 준비한다.