# 52_1 - 애노테이션을 이용하여 트랜잭션 제어하기

### Spring Framework의 선언식 트랜잭션 관리

프로그래밍 방식의 트랜잭션 관리를 이용하면
애플리케이션 코드가 스프링 전용 클래스와 결합되는 부작용이 있다.  
이와 달리 선언식 트랜잭션 관리를 사용할 때는
메서드나 클래스에 Spring의 @Transactional 애노테이션만 지정하면 된다.  
특정 메서드에 @Transactional 애노테이션을 지정하면 해당 메서드가 단일 트랜잭션 내에서 실행되며,  
특정 클래스의 모든 메서드를 단일 트랜잭션 내에서 실행하려면
해당 클래스에 @Transactional 애노테이션을 지정하면 된다.

### @EnableTransactionManagement

메서드에 @Transactional 애노테이션이 붙어있으면 해당 메서드를 트랜잭션으로 묶기 위해 트랜잭션 제어 코드가 삽입된 프록시 객체를 자동 생성한다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/DatabaseConfig.java 변경
- src/main/java/jeoneunhye/vms/service/impl/PhotoBoardServiceImpl.java 변경

### 작업1) 애노테이션으로 트랜잭션을 제어할 수 있도록 해당 기능을 활성화시킨다.

- DatabaseConfig 클래스 변경
    - @Transactional 애노테이션 기능을 활성화시키기 위해서 
    @EnableTransactionManagement 애노테이션을 추가로 선언한다.

### 작업2) @Transactional 애노테이션으로 트랜잭션을 제어한다.

- PhotoBoardServiceImpl 클래스 변경
    - 사진 게시글 추가, 변경, 삭제시 TransactionTemplate을 사용하는 대신 @Transactional 애노테이션을 붙인다.