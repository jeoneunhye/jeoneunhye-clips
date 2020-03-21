# 42_1 - SQL 삽입 공격과 자바 시큐어 코딩: 사용자 로그인 기능 추가

DB 프로그래밍의 핵심은 JDBC API를 사용하여 SQL문을 실행하는 것이다.  
SQL문은 보통 사용자가 입력한 값을 가지고 작성하는데, 여기서 보안 문제가 발생한다.  
SQL을 잘 아는 사용자가 입력 값에 SQL 문법을 포함시켜서 내부 데이터를 조회한다거나 변경할 수 있다.  
이를 방지하기 위해서는 사용자가 입력한 값을 가지고 SQL문을 만들어서는 안 된다.

### SQL Injection

이용자의 입력값이 SQL 구문의 일부로 사용될 경우, 해커에 의해 조작된 SQL 구문이 데이터베이스에  그대로 전달되어 비정상적인 DB 명령을 실행시키는 공격 기법이다.  
SQL 인젝션은 DB에 비정상적인 쿼리가 실행되도록 하여 다음과 같은 목적을 달성하고자 한다.

1. 인증 우회
SQL 인젝션 공격의 대표적인 경우로, 로그인 폼(Form)을 대상으로 공격을 수행한다. 정상적인 계정 정보 없이도 로그인을 우회하여 인증을 획득할 수 있다.

2. DB 데이터 조작 및 유출
조작된 쿼리가 실행되도록 하여, 기업의 개인정보나 기밀정보에 접근하여 데이터를 획득할수 있다. 또한 데이터 값을 변경하거나 심지어 테이블을 몽땅 지워버릴 수도 있다.

3. 시스템 명령어 실행
일부 데이터베이스의 경우 확장 프로시저를 호출하여 원격으로 시스템 명령어를 수행할 수 있도록 한다. 시스템 명령어를 실행할 수 있다면 해당 서버의 모든 자원에 접근하고 데이터를 유출, 삭제 할 수 있다는 말이 된다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/MemberDao.java 변경
- src/main/java/jeoneunhye/vms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/jeoneunhye/vms/servlet/LoginServlet.java 추가
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 사용자 로그인 기능을 추가한다.

- vms_member 테이블의 암호 초기화
    - 암호화를 적용하기 위해 컬럼의 최대 문자 길이를 50byte로 변경한다.  
    ALTER TABLE vms_member MODIFY pwd varchar(50) NOT NULL;
    - 테스트하기 위해 모든 회원의 암호를 '1111'로 초기화 한다.  
      update vms_member set pwd=password('1111');
- MemberDao 인터페이스 변경
    - 이메일과 암호를 가지고 사용자를 조회하는 default 메서드를 정의한다.  
    Member findByEmailAndPassword(String email, String password)
- MemberDaoImpl 클래스 변경
    - findByEmailAndPassword 메서드를 오버라이딩한다.  
    - insert(), update()의 SQL문에서 암호를 입력하거나 변경할 때 암호화하도록
    SQL 함수 =password()를 추가한다.
- LoginServlet 클래스 추가
    - 사용자로부터 이메일과 암호를 입력받아 인증을 수행한다.
- ServerApp 클래스 변경
    - "/auth/login" 명령을 처리할 LoginServlet 객체를 맵에 추가한다.

'ClientApp' 실행 예:

```
명령> /auth/login
이메일?
user1@test.com
암호?
1111
'홍길동'님 환영합니다.

명령> /auth/login
이메일?
user1@test.com
암호?
2222
사용자가 정보가 유효하지 않습니다.
```

### 작업2) SQL 삽입 공격을 통해 유효하지 않은 사용자 정보로 로그인을 시도한다.

'ClientApp' 실행 예:

```
명령> /auth/login
이메일?
user3@test.com
암호?
aaa') or (email='user3@test.com' and 'a'='a
'user3'님 환영합니다.

```

분명히 잘못된 암호를 넣었는데도 불구하고 로그인에 성공했다. 

`MemberDao.findByEmailAndPassword(String email, String password)`의 
코드를 살펴보면 다음과 같이 사용자 입력한 값을 가지고 SQL문을 만들어 실행한다.

```
ResultSet rs = stmt.executeQuery(
            "select member_id, id, nickname, pwd, phone, email, cdt from vms_member"
                + " where email='" + email + "' and pwd=password('" + password + "')")
```

즉 다음과 같이 사용자가 입력한 값이 SQL 문장 안에 그대로 삽입된다.

```
select member_id, id, nickname, pwd, phone, email, cdt from vms_member
where email='user3@test.com' 
and pwd=password('aaa') or (email='user3@test.com' and 'a'='a')
```

사용자 암호 값 속에 포함된 
`aaa') or (email='user3@test.com' and 'a'='a` 문구 때문에
암호에 상관없이 where절의 조건은 무조건 true가 된다.  
쿼리 조건을 무력화하여 암호가 다르더라도 조건문에 포함된 사용자 이메일로 로그인에 성공하는 것이다.