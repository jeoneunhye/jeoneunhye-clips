# 16 - UI 코드와 데이터 처리 코드를 분리하기
 
## 작업1) VideoHandler에서 데이터 처리 코드를 분리하라.

- VideoList.java
    - `VideoHandler`에서 데이터 처리 코드를 VideoList 클래스로 옮긴다.
    - 영상 데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    - 영상 데이터를 저장하는 add() 메서드를 정의한다.
    - 기본 생성자와 배열의 초기 크기를 설정하는 생성자를 정의한다.  
- VideoHandler.java
    - `VideoList` 클래스를 사용하여 데이터를 처리한다.

## 작업2) MemberHandler에서 데이터 처리 코드를 분리하라.

- MemberList.java
    - `MemberHandler`에서 데이터 처리 코드를 이 클래스로 옮긴다.
    - 회원 데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    - 회원 데이터를 저장하는 add() 메서드를 정의한다.
    - 기본 생성자와 배열의 초기 크기를 설정하는 생성자를 정의한다.  
- MemberHandler.java
    - `MemberList` 클래스를 사용하여 데이터를 처리한다.

## 작업3) BoardHandler에서 데이터 처리 코드를 분리하라.

- BoardList.java
    - `BoardHandler`에서 데이터 처리 코드를 이 클래스로 옮긴다.
    - 게시물 데이터 배열을 리턴하는 toArray() 메서드를 정의한다.
    - 게시물 데이터를 저장하는 add() 메서드를 정의한다.
    - 입력받은 번호와 비교하여 일치하면 배열 주소를 리턴하는 get() 메서드를 정의한다.
    - 기본 생성자와 배열의 초기 크기를 설정하는 생성자를 정의한다.  
- BoardHandler.java
    - `BoardList` 클래스를 사용하여 데이터를 처리한다.