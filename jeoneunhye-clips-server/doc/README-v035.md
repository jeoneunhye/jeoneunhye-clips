# 35 - 스레드 풀을 이용하여 스레드를 재사용하기

### Flyweight Design Pattern
동일하거나 유사한 객체들 사이에 가능한 많은 데이터를 서로 공유하여 사용하도록 하여 메모리 사용량을 최소화하는 디자인 패턴이다.  
스레드 풀링 기법은 이 디자인 패턴을 활용한 것이다.

### Thread의 wait()와 notify()
하나의 객체에서 멀티 스레드 환경일 때 하나의 스레드만 사용할수 있게 동기화 처리된 블록에서 사용해야 한다. 즉 synchronized 상태여야 한다.  

wait()
- 갖고 있던 고유 락을 해제하고 스레드를 잠들게 한다.

notify()
- 잠들어 있던 스레드 중 임의로 하나를 골라 깨운다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 스레드 풀을 사용하여 스레드를 관리한다.

- jeoneunhye.vms.ServerApp 클래스 변경
    - java.util.concurrent.ExcutorService 인터페이스와 java.util.concurrent.Executors 클래스를 이용하여 스레드 풀을 준비한다.  
    - `Executors.newCachedThreadPool()`  
    => 스레드 갯수보다 작업 갯수가 많으면 새 스레드를 생성하여 Runnable 구현 객체를 실행한다. 60초동안 추가된 스레드가 아무 작업을 하지 않으면 추가된 스레드를 종료하고 풀에서 제거한다.
    - 애플리케이션이 종료되는 시점에서 `executorService.shutdown()`을 호출한다.  
    => 스레드 풀을 다 사용했으면 종료하라고 해야 한다. (자원해제 목적)  
    스레드 풀을 당장 종료시키는 것이 아니다.  
    스레드 풀에 소속된 스레드들의 작업이 모두 끝나면 종료하라는 뜻이다.  
    호출하지 않아도 프로그램 실행에 지장은 없다.
    - 스레드를 생성할 때 스레드 풀을 사용한다.  
  스레드 풀을 사용할 때는 직접 스레드를 만들지 않는다. `new Thread();` (x)  
  Runnable 구현 객체를 생성하여 스레드 풀에 스레드가 실행할 코드를 제출한다.

```
    executorService.submit(new Runnable() {
    public void run() {...}
    });
```

```
    executorService.submit(() -> {...});
```