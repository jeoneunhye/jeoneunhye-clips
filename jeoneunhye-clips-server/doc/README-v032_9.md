32_9 - 파일에 데이터를 저장하고 로딩할 때 JSON 형식 사용하기
===

## JSON(JavaScript Object Notation)

- 서버와 클라이언트 간에 데이터를 저장하거나 전송할 때 사용하는 경량의 DATA 교환 형식이다.
- Javascript에서 객체를 만들 때 사용하는 표현식으로, 사람과 기계 모두 이해하기 쉬우며 용량이 작아서 XML 포맷과 비등하게 많이 사용되는 포맷이다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/json/AbstractJsonFileDao.java 추가
- src/main/java/jeoneunhye/vms/dao/json/VideoJsonFileDao.java 추가
- src/main/java/jeoneunhye/vms/dao/json/BoardJsonFileDao.java 추가
- src/main/java/jeoneunhye/vms/dao/json/MemberJsonFileDao.java 추가
- src/main/java/jeoneunhye/vms/servlet/VideoXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) JSON 형식으로 데이터를 저장하고 로딩할 수퍼 클래스를 정의한다.

- jeoneunhye.vms.dao.json 패키지 생성
- jeoneunhye.vms.dao.json.AbstractJsonFileDao 클래스 생성
    - AbstractObjectFileDao 클래스의 코드를 복사하고, 파일의 데이터를 저장하고 로딩하는 코드를 Gson 라이브러리에서 제공한 메서드를 사용하도록 변경한다.  
         클래스의 타입 파라미터 정보를 알아내기 위해 Type, ParameterizedType 객체를 이용한다.  
    AbstractJsonFileDao 클래스와 같이 generic이 적용된 경우, 파일 데이터를 Json 형식으로 읽고 해당하는 타입의 객체로 만들어 List에 담으려면 불가피하다.

### 작업2) AbstractJsonFileDao를 상속받는 VideoJsonFileDao 클래스를 정의한다.

- jeoneunhye.vms.dao.json.VideoJsonFileDao 클래스 추가
    - VideoObjectFileDao 클래스의 코드를 복사하고 AbstractJsonFileDao 클래스를 상속받도록 변경한다.

### 작업3) AbstractJsonFileDao를 상속받는 MemberJsonFileDao 클래스를 정의한다.

- jeoneunhye.vms.dao.json.MemberJsonFileDao 클래스 추가
    - MemberObjectFileDao 클래스의 코드를 복사하고 AbstractJsonFileDao 클래스를 상속받도록 변경한다.

### 작업4) AbstractJsonFileDao를 상속받는 BoardJsonFileDao 클래스를 정의한다.

- jeoneunhye.vms.dao.json.BoardJsonFileDao 클래스 추가
    - BoardObjectFileDao 클래스의 코드를 복사하고 AbstractJsonFileDao 클래스를 상속받도록 변경한다.
    
### 작업5) *Servlet 클래스에서 XxxObjectFileDao 대신 XxxJsonFileDao를 사용하도록 변경한다.

- jeoneunhye.vms.servlet.VideoXxxServlet 클래스 변경
    - 생성자 파라미터로 VideoJsonFileDao 객체를 넘겨 받는다.
- jeoneunhye.vms.servlet.MemberXxxServlet 클래스 변경
    - 생성자 파라미터로 MemberJsonFileDao 객체를 넘겨 받는다.
- jeoneunhye.vms.servlet.BoardXxxServlet 클래스 변경
    - 생성자 파라미터로 BoardJsonFileDao 객체를 넘겨 받는다.

### 작업6) 애플리케이션이 시작될 때 XxxObjectFileDao 대신 XxxJsonFileDao 객체를 준비한다.

- jeoneunhye.vms.DataLoaderListener 클래스 변경
    - video.json 파일의 영상 데이터를 처리하는 VideoJsonFileDao 객체를 준비한다.
    - member.json 파일의 회원 데이터를 처리하는 MemberJsonFileDao 객체를 준비한다.
    - board.json 파일의 게시글 데이터를 처리하는 BoardJsonFileDao 객체를 준비한다.
        
### 작업7) XxxObjectFileDao 대신 XxxJsonFileDao를 Servlet 객체에 주입한다.

- jeoneunhye.vms.ServerApp 클래스 변경
    - DataLoaderListner에서 준비한 XxxJsonFileDao를 각각의 Servlet 객체에 주입하도록 변경한다.