32_11 - 프록시 패턴을 적용하여 서버에 요청하는 부분을 간결하기 만들기 
===

### proxy

- 프록시 객체의 역할은 실제 작업 객체의 사용을 간결하게 만드는 것이다.  
따라서 프록시 객체의 메서드를 호출하면 프록시 객체는 실제 작업을 하는 객체에 위임한다.
- 실제 작업을 하는 객체가 구현하는 인터페이스와 동일한 인터페이스를 구현한다.
- 프록시 객체는 작업 객체를 제공하는 쪽에서 정의해야 한다.  
작업 객체가 필요한 쪽에서는 프록시 객체를 통해 작업을 수행한다.
- 장점:  
    - 객체에 대한 보안을 제공한다. 일반적으로 클라이언트는 객체에 직접 접근할 수 없다. 이는 객체가 악의적인 활동에 의해 변형될 수 있기 때문이다.
    - 다른 서버에 존재하는 외부 객체에 대한 로컬 인터페이스를 제공한다. 분산 시스템 구조에서 클라이언트가 권한이 없어 원격으로 특정 커맨드를 수행하지 못하는 경우가 있다. 이런 경우 로컬 객체인 프록시에 요청을 보내고 프록시는 원격 서버에서 요청을 수행한다.
    - 무거운 객체, 특히 자주 사용되는 객체를 캐싱해 애플리케이션의 성능을 향상시킨다.
- 단점:  
    - 프록시 패턴을 사용하면 간혹 객체로부터의 응답이 느려지기도 한다. 프록시를 사용하고 객체를 처음으로 요청하면 초기화에 걸리는 시간 때문에 응답 시간이 더 길어질 수 있다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/proxy/BoardDaoProxy.java 추가
- src/main/java/jeoneunhye/vms/dao/proxy/LessonDaoProxy.java 추가
- src/main/java/jeoneunhye/vms/dao/proxy/MemberDaoProxy.java 추가

### 작업1) 서버와 클라이언트 사이에서 통신 연결을 수행할 proxy 클래스를 정의한다.

- proxy 객체를 사용하여 클라이언트가 서버에 직접 접근할 수 없도록 XxxDao의 사용법을 캡슐화한다.
- jeoneunhye.vms.dao.proxy 패키지 생성
- jeoneunhye.vms.dao.proxy.VideoDaoProxy 클래스 정의
    - VideoDao 인터페이스를 구현한다.
    - 클라이언트의 VideoXxxCommand 클래스에서 서버와 송수신했던 데이터 처리 요청 관련 코드를 복사한다.
- jeoneunhye.vms.dao.proxy.MemberDaoProxy 클래스 정의
    - MemberDao 인터페이스를 구현한다.
    - 클라이언트의 MemberXxxCommand 클래스에서 서버와 송수신했던 데이터 처리 요청 관련 코드를 복사한다.
- jeoneunhye.vms.dao.proxy.BoardDaoProxy 클래스 정의
    - BoardDao 인터페이스를 구현한다.
    - 클라이언트의 BoardXxxCommand 클래스에서 서버와 송수신했던 데이터 처리 요청 관련 코드를 복사한다.

### 작업2) 프록시 객체를 Client에 가져간 후 실행을 테스트한다.

- jeoneunhye-clips-client 폴더의 하위 경로로 클래스를 복사한다.
    - src/main/java/jeoneunhye/vms/dao/proxy/VideoDaoProxy.java 복사
    - src/main/java/jeoneunhye/vms/dao/proxy/MemberDaoProxy.java 복사
    - src/main/java/jeoneunhye/vms/dao/proxy/BoardDaoProxy.java 복사