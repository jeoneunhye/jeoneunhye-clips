32_10 - 인터페이스를 이용하여 DAO 호출 규칙을 통일하기
===

### interface

- 사용자(예: Servlet)와 피사용자(예: DAO) 사이에 호출 규칙을 정의하는 문법이다.
- 호출 규칙을 정의해 두면 사용자 입장에서 피사용자측이 다른 객체로 바뀌더라도 코드를 변경할 필요가 없기 때문에 유지보수에 좋다.
- 피사용자 객체를 정의하는 개발자 입장에서도 인터페이스 규칙에 따라 만들면 되기 때문에 
  코드 작성과 테스트가 용이하다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/VideoDao.java 추가
- src/main/java/jeoneunhye/vms/dao/MemberDao.java 추가
- src/main/java/jeoneunhye/vms/dao/BoardDao.java 추가
- src/main/java/jeoneunhye/vms/dao/VideoObjectFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/json/VideoJsonFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/MemberObjectFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/json/MemberJsonFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/BoardObjectFileDao.java 변경
- src/main/java/jeoneunhye/vms/dao/json/BoardJsonFileDao.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/BoardXxxServlet.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) VideoXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현한다.

- jeoneunhye.vms.dao.VideoDao 인터페이스 생성
- jeoneunhye.vms.dao.VideoObjectFileDao 클래스 변경
    - VideoDao 인터페이스를 구현한다.
- jeoneunhye.vms.dao.json.VideoJsonFileDao 클래스 변경
    - VideoDao 인터페이스를 구현한다.

### 작업2) MemberXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현한다.

- jeoneunhye.vms.dao.MemberDao 인터페이스 생성
- jeoneunhye.vms.dao.MemberObjectFileDao 클래스 변경
    - MemberDao 인터페이스를 구현한다.
- jeoneunhye.vms.dao.json.MemberJsonFileDao 클래스 변경
    - MemberDao 인터페이스를 구현한다.

### 작업3) BoardXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현한다.

- jeoneunhye.vms.dao.BoardDao 인터페이스 생성
- jeoneunhye.vms.dao.BoardObjectFileDao 클래스 변경
    - BoardDao 인터페이스를 구현한다.
- jeoneunhye.vms.dao.json.BoardJsonFileDao 클래스 변경
    - BoardDao 인터페이스를 구현한다.

### 작업4) DAO 호출 규칙을 따르는 객체를 사용하여 데이터 요청 처리를 수행한다.
- jeoneunhye.vms.servlet.*Servlet 클래스 변경
    - DAO의 레퍼런스 타입을 XxxDao 인터페이스로 변경한다.  
        이렇게 상위 클래스의 레퍼런스를 사용하면 인터페이스 구현 객체끼리 변경할 때 편리하다.
- jeoneunhye.vms.DataLoaderListener 클래스 변경
    - contextInitialized 변수를 따로 만들지 않고 XxxDao의 구현 객체를 만들어 바로 context Map에 담도록 코드를 변경한다.
- jeoneunhye.vms.ServerApp 클래스 변경
    - DataLoaderListner에서 준비한 Map의 DAO 인터페이스 구현 객체를 꺼낸다.  
    구현 객체를 XxxDao 레퍼런스에 담아 형변환하여 Servlet 객체에 주입한다.