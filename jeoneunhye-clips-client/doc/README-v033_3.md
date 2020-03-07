# 33_3 - 서버 연결 부분 캡슐화하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/dao/proxy/Worker.java 추가
- src/main/java/jeoneunhye/vms/dao/proxy/DaoProxyHelper.java 추가
- src/main/java/jeoneunhye/vms/dao/proxy/XxxDaoProxy.java 변경
- src/main/java/jeoneunhye/vms/ClientApp.java 변경

### 작업1) 서버와 통신을 담당할 실제 작업의 규칙을 정의한다.

- jeoneunhye.vms.dao.proxy.Worker 인터페이스 정의
    - DaoProxyHelper가 작업을 수행할 때 일시적으로 사용하는 도구를 정의한다.
    - 리턴 타입이 Object인 execute(ObjectInputStream, ObjectOutputStream out) 메서드를 정의한다.  
    => Primitive Data Type도 리턴할 수 있도록 Object로 설정하여 Auto-boxing되도록 한다.

### 작업2) DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의한다.

- jeoneunhye.vms.dao.proxy.DaoProxyHelper 클래스 정의
    - DaoProxy가 서버와 *통신*할 때 필요한 준비 작업을 수행한다.
    - 그래서 프록시의 네트워크 통신을 돕는 DaoProxyHelper라고 한다.
    - 메서드 request(Worker)의 리턴 타입은 Worker의 execute 메서드와 동일하게 Object로 설정한다.  
    => Worker.execute(in, out) 메서드를 실행할 때 필요한 소켓과 입출력 스트림을 준비해 주는 역할이다.
    - Worker 구현 객체를 파라미터로 받아 Worker.execute를 호출하여 리턴 값을 리턴한다.

### 작업3) DaoProxyHelper를 사용하도록 XxxDaoProxy를 변경한다.

- jeoneunhye.vms.dao.proxy.XxxDaoProxy 클래스 변경
    - host와 port 필드를 제거하고 DaoProxyHelper 객체를 생성자에서 파라미터로 받아 준비한다.
    - 각 기능 메서드 insert, findAll, findByNo, update, delete의 기존 코드를 Worker 인터페이스를 구현한 람다식 안에 넣어 execute 메서드를 정의하고, daoProxyHelper의 request 메서드의 아규먼트로 준다.
    - request 메서드가 리턴한 Object 타입의 값을 형변환하여 리턴한다.

- BoardDaoProxy의 insert 메서드에서 로컬 클래스로 리턴 값 정의하기

```
  public int insert(Board board) throws Exception {
    class InsertWorker implements Worker {
      // Worker의 execute를 호출하려면 in, out 객체가 필요하기 때문에
      // Worker 인터페이스를 구현한 로컬 클래스를 만들어 메서드를 정의한다.
          @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/board/add");
        out.writeObject(board);
        out.flush();
        
        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }

        return 1;
      }
    }

    InsertWorker worker = new InsertWorker();
    // Worker 인터페이스를 구현한 로컬 클래스의 객체를 생성하여 request의 파라미터로 넘겨 준다.

    int resultState = (int) daoProxyHelper.request(worker); //  Integer -> int
    // daoProxyHelper.request를 호출하면 소켓과 입출력 스트림을 준비하고
    // Worker 객체가 호출한 execute(in, out)의 리턴 값을 리턴한다.

    return resultState;
  }
```

- BoardDaoProxy의 insert 메서드에서 익명 클래스로 리턴 값 정의하기

```
  public int insert(Board board) throws Exception {
    Worker worker = new Worker() {
      // Worker 인터페이스를 구현한 익명 클래스를 쉽게 Worker 구현체라고 부른다.
      // 상위 Worker 레퍼런스에 Worker 구현체를 담은 것이다.
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/board/add");
        out.writeObject(board);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }

        return 1;
      }
    };

    Object result = daoProxyHelper.request(worker);

    return (int) result;
  }
```

- BoardDaoProxy의 insert 메서드에서 필드를 생성하지 않고 리턴 값에 메서드를 바로 호출하여 정의하기

```
  public int insert(Board board) throws Exception {
    return (int) daoProxyHelper.request(new Worker() {
      @Override
      public Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        out.writeUTF("/board/add");
        out.writeObject(board);
        out.flush();

        String response = in.readUTF();
        if (response.equals("FAIL")) {
          throw new Exception(in.readUTF());
        }
        return 1;
      }
    });
  }
```

- BoardDaoProxy의 insert 메서드에서 람다식으로 구현하여 리턴 값 정의하기

```
  public int insert(Board board) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
    out.writeUTF("/board/add");
    out.writeObject(board);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
    });
  }
```

### 작업4) ClientApp에서 DaoProxyHelper 객체를 생성하고 XxxDaoProxy 객체가 Helper 객체를 사용하도록 변경한다.

- jeoneunhye.vms.ClientApp 클래스 변경
    - commandMap 객체를 인스턴스 필드에서 준비한다.
    - XxxDaoProxy 객체와 Command 객체가 ClientApp이 로딩될 때 준비되도록 코드를 생성자 블록 안에 넣는다.
    - DaoProxyHelper 객체 또한 생성자 블록에서 준비하여 VideoDaoProxy, MemberDaoProxy, BoardDaoProxy 객체에 주입한다.