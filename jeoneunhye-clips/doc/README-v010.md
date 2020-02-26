# 10 - 클래스로 메서드를 분류하기

### 작업1) 영상 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- VideoHandler.java
    - 영상 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 영상관리와 관련된 변수와 메서드를 `VideoHandler` 클래스로 옮긴다.
- App.java
    - `VideoHandler` 클래스를 사용한다.


### 작업2) 회원 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- MemberHandler.java
    - 회원 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 회원관리와 관련된 변수와 메서드를 `MemberHandler` 클래스로 옮긴다.
- App.java
    - `MemberHandler` 클래스를 사용한다.


### 작업3) 게시물 데이터 처리와 관련된 메서드를 별도의 클래스로 분리하라.

- BoardHandler.java
    - 게시물 관리와 관련된 메서드를 담을 클래스를 만든다.
    - `App.java` 에서 게시물관리와 관련된 변수와 메서드를 `BoardHandler` 클래스로 옮긴다.
- App.java
    - `BoardHandler` 클래스를 사용한다.
