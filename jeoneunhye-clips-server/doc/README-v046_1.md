# 46_1 - 객체 생성을 자동화하는 IoC Container 사용하기

새 명령을 추가할 때마다 그 명령을 처리할 서블릿 객체를 생성하고 등록해야 한다.  
또한 데이터를 다루는 DAO와 비즈니스 로직 및 트랜잭션을 관리하는 서비스 객체를 생성하고 등록해야 한다.  
이를 자동화할 수 있다면, 새 명령을 추가하거나 새 클래스를 만들 때마다 직접 코드를 추가할 필요가 없을 것이다.  
객체 생성 및 등록을 자동화하는 객체를 IoC 컨테이너라 한다.

### IoC(Inversion Of Control): 제어의 역전, 흐름의 역행

- ex1) 의존 객체 생성  
보통의 실행 흐름은 객체를 사용하는 쪽에서 그 객체를 만드는 것이다.  
그런데 시스템 구조가 복잡해지면 이렇게 직접 객체를 만드는 방식은 비효율적이다.  
시스템 구조가 복잡할 경우 사용할 객체를 외부에서 주입받는 것이 유지보수에 좋다.  
이렇게 객체를 외부에서 주입하는 것은 보통의 실행 흐름을 역행하는 것이다.
- ex2) 메서드 호출  
보통 메서드를 만들면 실행 흐름에 따라 호출한다. 메서드를 호출하고, 실행이 끝나면 리턴한다.  
그런데 실행 계획에 따라 호출하는 것이 아니라
특정 상태에 있을 때 자동으로 호출되게 하는 경우도 필요하다.  
시스템이 시작될 때 특정 메서드를 자동으로 호출되게 하는 것이다.
(ex: 사용자가 마우스를 클릭했을 때 특정 메서드를 자동으로 호출)  
즉 개발자가 작성한 코드 흐름에 따라 호출하는 것이 아니라
특정 상태에 놓여졌을 때 뒤에서 자동으로 호출하는 방식이 필요할 때가 있다.  
보통 이런 메서드를 '이벤트 핸들러', '이벤트 리스너'라 부른다.  
또는 시스템 쪽에서 호출하는 메서드라는 의미로 '콜백(callback) 메서드'라고 부르기도 한다.

### IoC Container = Bean Container + 의존 객체 주입

개발자가 직접 객체를 생성하는 것이 아니다.  
객체 생성을 전담하는 역할자를 통해 객체가 준비된다.  
이 역할자를 '빈 컨테이너(bean container)'라고 부른다.  
여기에 객체가 사용할 의존 객체를 자동으로 주입하는 역할을 추가한다.  
즉 객체 스스로 자신이 사용할 객체를 만드는 것이 아니라
외부의 빈 컨테이너로부터 의존 객체를 주입받는 것이다.  
이런 역할까지 겸하는 것을 'IoC 컨테이너'라 부른다.  
IoC 컨테이너를 도입하면, 새 명령을 처리하는 서블릿이 추가되더라도
기존 코드를 변경할 필요가 없다.  
기능을 제거하고 싶다면 기존 코드를 손댈 필요없이 그냥 클래스를 지우면 된다.

### Reflection API

이미 로딩이 완료된 클래스에서 또 다른 클래스를 동적으로 로딩하여 생성자, 필드와 메서드 등을 사용하는 Java API다.  
구체적인 클래스 타입을 알지 못해도 클래스의 메서드, 타입, 필드에 접근할 수 있다.  
클래스의 패키지 정보, 접근 제한자, 수퍼 클래스, 애노테이션도 얻을 수 있다.  
컴파일 시간이 아니라 실행 시간에 동적으로 특정 클래스의 정보를 객체화를 통해 분석 및 추출해낼 수 있는 프로그래밍 기법이다.


## 작업 소스 및 결과

- src/main/java/jeoneunhye/util/ApplicationContext.java 추가
- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경
- src/main/java/jeoneunhye/vms/service/impl/VideoServiceImpl.java 변경
- src/main/java/jeoneunhye/vms/service/impl/MemberServiceImpl.java 변경
- src/main/java/jeoneunhye/vms/service/impl/BoardServiceImpl.java 변경
- src/main/java/jeoneunhye/vms/service/impl/BoardServiceImpl2.java 삭제
- src/main/java/jeoneunhye/vms/service/impl/PhotoBoardServiceImpl.java 변경
- src/main/java/jeoneunhye/util/Component.java 추가
- src/main/java/jeoneunhye/vms/ContexxtLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) IoC 컨테이너 클래스를 준비한다.

- ApplicationContext 클래스 추가
    - 클래스를 찾아 객체를 생성하는데, 객체가 필요로 하는 의존 객체가 있다면 의존 객체를 만들어 주입해 주는 역할을 한다.
    - reflection API를 사용한다.

### 작업2) 객체 생성 대상을 지정할 Component 애노테이션을 추가한다.

- Component 애노테이션 추가
    - 빈의 이름을 설정하는 애노테이션 @Component를 정의한다.
    - @Retention: 표시된 애노테이션이 어떻게 저장될지 지정한다.  
    (RetentionPolicy.RUNTIME): 컴파일한 후에도 *.class 파일에 이 애노테이션 정보를 유지하게 한다.
    JVM에서 이 애노테이션 정보를 꺼낼 수 있도록 허락한다.
    - Component의 이름을 지정할 수 있도록 프로퍼티 value()를 선언한다.  
    값을 지정하지 않는 경우 빈 문자열로 설정하도록 한다.
- XxxServlet 클래스 변경
    - 클래스에 Component 애노테이션을 적용하고 이름은 클라이언트의 명령어와 동일하게 지정한다.
- XxxServiceImpl 클래스 변경
    - 클래스에 Conponent 애노테이션을 적용한다.

### 작업3) ContextLoaderListener에서 애플리케이션을 실행할 때 사용할 객체나 환경을 준비한다.

- DataLoaderListener 클래스 변경
    - 이름을 ContextLoaderListener로 변경한다.  
    => 데이터를 로딩하고 저장하는 역할에서 확장하여 애플리케이션을 실행할 때 사용할 객체나 환경을 준비하는 역할을 한다.
- ContextLoaderListener 클래스 변경
    - Map<String, Object> beans에 Mybatis 관련 객체를 생성하여 보관한다.
    - IoC 컨테이너의 객체 생성 대상이 존재하는 상위 패키지 경로와 함께 Mybatis 관련 객체가 담긴 Map을 ApplicationContext 객체를 생성할 때 넘겨준다.
    - 애플리케이션이 실행될 때 생성한 객체 목록을 출력하도록 atx.printBeans() 메서드를 호출한다.
    - ApplicationContext 객체를 ServerApp에서 사용할 수 있도록 context Map에 보관한다.

### 작업4) IoC 컨테이너에 보관된 객체를 꺼내 사용한다.

- ServerApp 클래스 변경
    - ApplicationContext 객체를 context Map에서 꺼내 준비한다.
    - iocContainer.getBean("sqlSessionFactory")을 호출하여 SqlSessionFactory 객체를 리턴받는다.
    - iocContainer.getBean(request)을 호출하여 Servlet과 Service, Transaction 관련 객체를 생성한다.