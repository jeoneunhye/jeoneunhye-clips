# 42_2 - SQL 삽입 공격과 자바 시큐어 코딩: PreparedStatement로 전환하기

PreparedStatement를 이용하여 SQL 문과 값을 분리하여 실행하면
SQL 삽입 공격을 막을 수 있다.

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/mariadb/VideoDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/PhotoFileDaoImpl.java 변경

### 작업1) 기존의 Statement 객체를 PreparedStatement 객체로 모두 변경한다.

- XxxDaoImpl 클래스 변경
    - Statement를 PreparedStatement로 변경하고, Connection의 prepareStatement 메서드 아규먼트로 SQL문을 주입한다.

### 작업2) SQL 삽입 공격을 통해 유효하지 않은 사용자 정보로 로그인한다.

'ClientApp' 실행 예:

```
명령> /auth/login
이메일?
user3@test.com
암호?
aaa') or (email='user3@test.com' and 'a'='a
사용자의 정보가 유효하지 않습니다.
```