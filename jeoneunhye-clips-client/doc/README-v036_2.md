# 36_2 - DB Connection 객체 공유하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) 한 개의 DB Connection 객체를 세 DAOImpl 객체가 공유하여 사용한다.

- jeoneunhye.vms.ClientApp 클래스 변경
    - DB 연결 객체를 기본 생성자에서 준비한다.
- jeoneunhye.vms.dao.mariadb.XxxDaoImpl 클래스 변경
    - 각 메서드는 해당 객체가 생성될 때 준비된 Connection 객체를 사용한다.