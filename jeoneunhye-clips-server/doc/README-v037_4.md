# 37_4 - Application Server 구조로 변경: 회원 검색 기능 추가 

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/MemberDao.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/servlet/MemberSearchServlet.java 추가
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 회원 검색 기능을 추가한다.

Application Server 방식은 새 기능을 추가하더라도 사용자 PC에 클라이언트 프로그램을 재설치할 필요가 없다는 이점이 있다.  
검색 기능을 추가한 후 이를 확인한다.

- jeoneunhye.vms.dao.MemberDao 클래스 변경
    - Member가 담긴 List를 리턴하는 findByKeyword(String) default 메서드를 정의한다.
- jeoneunhye.vms.dao.mariadb.MemberDaoImpl 클래스 변경
    - 사용자가 입력한 단어를 포함하는 아이디 또는 이메일, 휴대폰 번호로 회원을 조회하는 findByKeyword(String) 메서드를 구현한다.
- jeoneunhye.vms.servlet.MemberSearchServlet 추가
    - 클라이언트에게 검색 키워드를 요청한다.
    - 클라이언트가 보낸 키워드를 가지고 mariaDB에서 해당하는 회원을 찾는다.
    - 검색한 결과로 목록 형태의 출력물을 생성하여 클라이언트에게 보낸다.
- jeoneunhye.vms.ServerApp 변경
    - '/member/search' 명령을 처리할 MemberSearchServlet 객체를 ServletMap에 등록한다.