# 33_2 - 요청할 때마다 프록시와 커맨드를 생성하는 부분 개선하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/proxy/XxxDaoProxy.java 변경
- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) 요청할 때마다 프록시의 메서드에서 소켓과 입출력 스트림을 준비하도록 변경한다.

- jeoneunhye.vms.dao.proxy.XxxDaoProxy 클래스 변경
    - 생성자 파라미터로 ClientApp에서 받아오던 입출력 스트림 in, out을 insert, findAll, findByNo, update, delete 메서드 호출시 소켓과 함께 직접 준비하여 사용하도록 변경한다.

### 작업2) ClientApp에서 프록시 객체 생성시 연결할 서버 주소와 포트 번호를 주입한다.

- jeoneunhye.vms.ClientApp 클래스 변경
    - ClientApp의 기본 생성자에서 입력받은 서버 주소와 포트 번호를 XxxDaoProxy 객체에 주입한다.