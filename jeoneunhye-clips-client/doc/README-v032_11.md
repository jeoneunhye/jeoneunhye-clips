32_11 - 서버에서 제공한 프록시 객체를 사용하여 데이터 처리하기
===

### proxy

- 프록시 객체의 역할은 실제 작업 객체의 사용을 간결하게 만드는 것이다.  
따라서 프록시 객체의 메서드를 호출하면 프록시 객체는 실제 작업을 하는 객체에 위임한다.
- 실제 작업을 하는 객체가 구현하는 인터페이스와 동일한 인터페이스를 구현한다.
- 프록시 객체는 작업 객체를 제공하는 쪽에서 정의해야 한다.  
작업 객체가 필요한 쪽에서는 프록시 객체를 통해 작업을 수행한다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/VideoDao.java 추가
- src/main/java/jeoneunhye/vms/dao/MemberDao.java 추가
- src/main/java/jeoneunhye/vms/dao/BoardDao.java 추가
- src/main/java/jeoneunhye/vms/dao/proxy/VideoDaoProxy.java 추가
- src/main/java/jeoneunhye/vms/dao/proxy/MemberDaoProxy.java 추가
- src/main/java/jeoneunhye/vms/dao/proxy/BoardDaoProxy.java 추가
- src/main/java/jeoneunhye/vms/handler/VideoXxxCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/MemberXxxCommand.java 변경
- src/main/java/jeoneunhye/vms/handler/BoardXxxCommand.java 변경
- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) Server v31_11에서 DAO Proxy와 관련된 클래스를 복사한다.

- 통신 규칙을 이해하고 있는 서버 개발자로부터 클라이언트와의 통신 규칙을 전달받아 사용한다.
- jeoneunhye.vms.dao 패키지 생성
- jeoneunhye.vms.dao.VideoDao, MemberDao, BoardDao 인터페이스 추가
- jeoneunhye.vms.dao.proxy 패키지 생성
- jeoneunhye.vms.dao.proxy.VideoDaoProxy 클래스 추가
- jeoneunhye.vms.dao.proxy.MemberDaoProxy 클래스 추가
- jeoneunhye.vms.dao.proxy.BoardDaoProxy 클래스 추가

### 작업2) VideoXxxCommand 객체가 VideoDaoProxy 객체를 사용하여 데이터를 처리하도록 변경한다.

- jeoneunhye.vms.handler.VideoXxxCommand 클래스 변경
    - 입출력 스트림 필드 ObjectInputStream과 ObjectOutputStream을 제거하고 VideoDao 필드를 선언한다.
    - 생성자 파라미터로 VideoDaoProxy 객체를 받도록 변경한다.
    - 프록시 객체의 메서드를 호출하여 데이터를 처리한다.
- jeoneunhye.vms.ClientApp 클래스 변경
    - VideoDaoProxy 객체를 생성한다.
    - VideoXxxCommand 객체에 주입한다.
    
### 작업3) MemberXxxCommand 객체가 MemberDaoProxy 객체를 사용하여 데이터를 처리하도록 변경한다.

- jeoneunhye.vms.handler.MemberXxxCommand 클래스 변경
    - 입출력 스트림 필드 ObjectInputStream과 ObjectOutputStream을 제거하고 MemberDao 필드를 선언한다.
    - 생성자 파라미터로 MemberDaoProxy 객체를 받도록 변경한다.
    - 프록시 객체의 메서드를 호출하여 데이터를 처리한다.
- jeoneunhye.vms.ClientApp 클래스 변경
    - MemberDaoProxy 객체를 생성한다.
    - MemberXxxCommand 객체에 주입한다.

### 작업4) BoardXxxCommand 객체가 BoardDaoProxy 객체를 사용하여 데이터를 처리하도록 변경한다.

- jeoneunhye.vms.handler.BoardXxxCommand 클래스 변경
    - 입출력 스트림 필드 ObjectInputStream과 ObjectOutputStream을 제거하고 BoardDao 필드를 선언한다.
    - 생성자 파라미터로 BoardDaoProxy 객체를 받도록 변경한다.
    - 프록시 객체의 메서드를 호출하여 데이터를 처리한다.
- jeoneunhye.vms.ClientApp 클래스 변경
    - BoardDaoProxy 객체를 생성한다.
    - BoardXxxCommand 객체에 주입한다.