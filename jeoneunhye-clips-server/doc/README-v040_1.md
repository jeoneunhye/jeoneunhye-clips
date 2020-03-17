# 40_1 - Connection을 Thread에 보관하기: ThreadLocal을 사용하여 Thread에 값 보관하기

### Connection을 Thread에 보관하는 이유?

여러 개의 데이터 변경(insert/update/delete) 작업을 한 단위로 묶으려면 같은 Connection을 사용해야 한다.  
commit()/rollback()은 Connection 객체로 실행하기 때문이다. 즉 Transaction은 각각의 Connection별로 관리된다.  
Thread가 실행하는 데이터 변경 작업을 한 단위로 묶으려면, 그 Thread가 수행하는 데이터 변경 작업은 같은 Connection으로 실행해야 한다.  
DAO의 메서드가 실행될 때 사용하는 Connection은 Thread에서 꺼낸다.

### ThreadLocal

일반 변수의 수명은 특정 코드 블록(ex: 메서드 범위, for 블록 범위 등) 범위 내에서만 유효하다.  
반면 ThreadLocal을 이용하면 Thread 영역에 변수를 설정할 수 있기 때문에,  
특정 Thread가 실행하는 모든 코드에서 그 Thread에 설정된 변수 값을 사용할 수 있게 된다.  
ThreadPool 환경에서 ThreadLocal을 사용하는 경우 ThreadLocal 변수에 보관된 데이터의 사용이 끝나면  
반드시 해당 데이터를 삭제해 주어야 한다. 그렇지 않을 경우 재사용되는 Thread가 올바르지 않은 데이터를 참조할 수 있다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/util/ConnectionFactory.java 변경
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) ConnectionFactory에서 생성한 Connection 객체를 Thread에 보관한다.

- ConnectionFactory 클래스 변경
    - Connection 객체를 보관할 ThreadLocal 객체를 준비한다.
    - getConnection()에서 ThreadLocal.get()을 호출하여 Connection 객체를 꺼내 담는다.
    - Thread에 보관된 Connection 객체가 없다면 새로 생성하여 리턴한다.
    - 새로 생성한 Connection 객체는 ThreadLocal.set(Connection)을 호출하여 보관한다.
    - Thread에 보관된 Connection 객체가 있다면 그 객체를 꺼내 리턴한다.

#### 문제점

- 현재 ThreadPool(ExecutorService)을 이용하여 Thread를 관리하고 있다.
- Thread를 사용한 후(클라이언트 요청에 응답을 완료한 후)에 Thread를 버리지 않고 Pool에 보관했다가
  다음 클라이언트 요청에 재사용한다.
- Thread에 보관한 Connection은 DAO 작업이 끝난 후 닫힌다.
- 즉 다음 클라이언트 요청 처리 과정에서 Thread를 재사용할 때 닫힌 Connection을 사용하여 DAO가 작업할 때 오류가 발생한다.

#### 해결책

- 클라이언트에게 응답을 완료한 후에 Thread에 보관된 Connection 객체를 제거한다.
- 다음에 클라이언트 요청을 처리하기 위해 같은 Thread가 사용되더라도  
  이미 그 Thread에는 Connection 객체가 없기 때문에 ConnectionFactory는 새 Connection을 만들어 리턴할 것이다.

### 작업2) 클라이언트에 응답한 후 Thread에 보관된 Connection 객체를 제거한다.

- ConnectionFactory 클래스 변경
    - Thread에 보관된 Connection 객체를 제거하는 메서드 removeConnection을 정의한다.  
    ThreadLocal의 get()과 remove() 메서드를 이용한다.
- DataLoaderListener 클래스 변경
    - ConnectionFactory 객체를 생성한다.
    - ServerApp에서 ConnectionFactory를 사용할 수 있도록 Context Map에 보관한다.
- ServerApp 클래스 변경
    - DataLoaderListener에서 준비한 ConnectionFactory를 Context Map에서 꺼내 준비한다.
    - 클라이언트 요청을 처리한 후에 ConnectionFactory를 통해 Thread에서 Connection을 제거한다.

#### 현재 버전의 문제점

클라이언트의 'photoboard/*' 요청을 받아 실행할 때 두 개의 DAO가 작업하면서  
자원 해제 문법으로 닫힌 Connection을 사용하여 요청 처리가 제대로 되지 않는 문제가 있다.  
=> 요청 처리 중 오류 발생!  
createStatement() is called on closed connection