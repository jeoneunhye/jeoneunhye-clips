27 - 잘못된 형식의 데이터를 입력했을 때 시스템이 종료되지 않도록 예외 처리하기
===

### 작업1) 명령 처리 메서드를 `try ~ catch ... 블록`으로 묶는다. 

- App.java
    - `commandHandler.execute()`를 try 블록 안에 묶고   
    실행 중 오류가 발생했을 때 메시지를 출력하는 코드를 catch 블록에 추가한다.

#### 실행 결과

```
명령> /video/add
번호? 1
주제? 영화
제목? 인터스텔라 리뷰
주소? youtube.com/watch?v=
재생시간? 15:00
업로더? uploader
업로드 날짜? 20191-1
명령어 실행 중 오류 발생 : java.lang.IllegalArgumentException

명령> 
```