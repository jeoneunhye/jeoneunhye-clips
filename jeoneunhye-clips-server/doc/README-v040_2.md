# 40_2 - Connection을 Thread에 보관하기: 한 Thread에서 Connection을 여러 번 사용할 때 발생하는 문제점 해결하기

#### 변경하기 전의 문제점 및 테스트

DAO가 작업을 수행(insert, findAll, findByNo, update, delete, ...)한 후에는  
try-with-resource 문장에 의해 Connection 객체의 close()가 자동 호출된다.  
따라서 Thread에 보관된 Connection 객체는 더이상 사용할 수 없다.
- 테스트:  
    "/photoboard/list" 명령을 실행하면  
    VideoDao.findByNo(int)가 실행된 후에 Connection이 닫히기 때문에  
    PhotoBoardDao.findAllByVideoNo(int)를 실행하면 오류가 발생한다.  
    이렇게 같은 Thread에서 Connection을 여러 번 사용할 경우에 오류가 발생한다.

#### 해결책

Connection을 사용하고 닫지 않는다.  

1. try-with-resources 블럭 밖에 Connection 변수를 두어 자원을 닫지 않는다.
    - 자원을 사용했으면 닫도록 하는 기존의 표준 코딩 방식에 어긋난다.
    - 이렇게 Thread에서 Connection을 여러 번 사용하는 경우라고 해서 특별하게 코딩한다면 유지보수하기가 어렵다.
2. Connection 객체의 close()를 호출할 때 진짜로 닫지 않도록 Customizing한다.
    - 기존 표준 코딩에 어긋나지 않는다. 즉 자원을 사용했으면 닫도록 코딩하는 규칙을 준수하고 있다.
    - Connection을 구현한 ConnectionProxy를 생성하여 메서드를 조작한다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/sql/ConnectionProxy.java 추가
- src/main/java/jeoneunhye/util/ConnectionFactory.java 변경
- src/main/java/jeoneunhye/vms/ServletApp.java 변경

### 작업1) Connection의 일을 대행할 Proxy를 정의한다.

- ConnectionProxy 클래스 추가
    - Connection을 대행하는 ConnectionProxy 클래스는 Connection 인터페이스를 구현한다.
    - close()를 호출해도 Connection을 닫지 않도록 customizing한다.
    - 모든 작업이 끝났을 때 Connection을 닫을 수 있도록 realClose() 메서드를 추가한다.
    - close()를 제외한 메서드는 원래의 Connection 객체를 생성자로 받아 위임한다.
        - context menu > source > generate delegate methods... 실행

### 작업2) ConnectionFactory가 ConnectionProxy 객체를 리턴하게 한다.

- ConnectionFactory 클래스 변경
    - 새 Connection 객체를 생성할 때 ConnectionProxy 객체를 리턴하도록 getConnection() 메서드를 변경한다.
    - 원래의 Connection 객체 대신에 ConnectionProxy를 리턴한다.

### 작업3) Thread에서 Connection을 제거하기 전에 서버와의 연결을 끊는다.

- ConnectionFactory 클래스 변경
    - ServerApp에서 Connection을 제거하고 닫기 위해 removeConnection()의 리턴 타입을 Connection으로 변경한다.
- ServerApp 클래스 변경
    - ConnectionFactory에서 리턴받은 Connection 객체에 대해 realClose()를 호출한다.