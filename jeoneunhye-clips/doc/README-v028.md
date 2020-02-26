28 - 파일 입출력 API의 활용
===

### 작업1) 데이터를 파일에 저장하고 로딩하는 메서드를 정의한다.

- App.java
    - 애플리케이션을 실행했을 때 파일에서 영상 데이터를 읽어오는 `loadVideoData()`를 정의한다.
    - 애플리케이션을 종료할 때 영상 데이터를 파일에 저장하는 `saveVideoData()`를 정의한다.
    - 영상 데이터를 저장할 ArrayList 객체를 스태틱 메서드에서 사용할 수 있도록 스태틱 필드로 전환한다.
  
    - 애플리케이션을 실행했을 때 파일에서 회원 데이터를 읽어오는 `loadMemberData()`를 정의한다.
    - 애플리케이션을 종료할 때 회원 데이터를 파일에 저장하는 `saveMemberData()`를 정의한다.
    - 회원 데이터를 저장할 ArrayList 객체를 스태틱 메서드에서 사용할 수 있도록 스태틱 필드로 전환한다.
  
    - 애플리케이션을 실행했을 때 파일에서 게시글 데이터를 읽어오는 `loadBoardData()`를 정의한다.
    - 애플리케이션을 종료할 때 게시글 데이터를 파일에 저장하는 `saveBoardData()`를 정의한다.
    - 게시글 데이터를 저장할 ArrayList 객체를 스태틱 메서드에서 사용할 수 있도록 스태틱 필드로 전환한다.

### 작업2) CSV 문자열을 객체로 전환하고, 객체를 CSV 문자열 형식으로 만드는 메서드를 도메인 클래스에서 정의한다.

- Video.java, Member.java, Board.java
    - `valueOf(String)`, `toCSVString()` 메서드를 정의한다.
  
### 작업3) Gradle 스크립트 파일에 Google JSON 라이브러리를 추가한다.

- build.gradle
    - https://mvnrepository.com/에서 GSON 코드를 복사하여 의존 라이브러리 블록에 추가한다.
    - 명령창에서 gradle eclipse를 실행하여 이클립스 설정 파일을 갱신한다.
    - 'Referenced Libraries' 노드에서 gson 라이브러리 파일이 추가된 것을 확인한다.

> ### JSON 데이터 포맷의 특징

> - 문자열로 데이터를 표현한다.
> - '{프로퍼티:값, ...}' 방식으로 객체의 값을 저장한다.
> - 바이너리 방식에 비해 데이터 커지는 문제가 있지만,
  모든 프로그래밍 언어에서 다룰 수 있다는 장점이 있다.
> - 그래서 이기종 플랫폼(OS, 프로그래밍 언어 등) 간에 데이터를 교환할 때 많이 사용한다. 

### 작업4) 파일 입출력 도구에 버퍼 기능을 추가한다.

- App.java
    - loadXxxData(), saveXxxData()의 FileReader/FileWriter 객체에 BufferedReader/BufferedWriter를 붙여 입출력 속도를 향상하는 기능을 추가한다.

#### 실행 결과

App을 실행한 후 게시글 데이터를 입력한다.
```
명령> /board/add   
번호? 1   
영상 번호? 1   
제목? test1   
내용? 안녕하세요.   
작성자? nickname1   
저장하였습니다.   

명령> /board/add   
번호? 2   
영상 번호? 2   
제목? test2   
내용? 게시글을 올려주세요.   
작성자? nickname2   
저장하였습니다.   

명령> /board/list   
  1, 1, test1, 안녕하세요., nickname1, 0   
  2, 2, test2, 게시글을 올려주세요., nickname2, 0   

명령> quit   
안녕!   
```

종료한 후에 다시 App을 실행하면 이전에 입력한 내용을 확인할 수 있다.   
```
명령> /board/list   
  1, 1, test1, 안녕하세요., nickname1, 0   
  2, 2, test2, 게시글을 올려주세요., nickname2, 0   

명령>    
```