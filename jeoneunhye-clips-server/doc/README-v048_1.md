# 48_1 - 인터페이스 대신 애노테이션으로 메서드 구분하기

인터페이스는 규칙이기 때문에 구현이 매우 엄격하다.  
메서드 이름에서 파라미터 타입/개수, 리턴 타입까지 정확하게 구현해야 한다.  
애노테이션을 사용하면 인터페이스보다 더 유현하게 규칙을 처리할 수 있다.

## 작업 소스 및 결과

- src/main/java/jeoneunhye/util/RequestMapping.java 추가
- src/main/java/jeoneunhye/vms/servlet/Servlet.java 삭제
- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경
- src/main/java/jeoneunhye/util/ApplicationContext.java 변경
- src/main/java/jeoneunhye/util/RequestHandler.java 추가
- src/main/java/jeoneunhye/util/RequestMappingHandlerMapping.java 추가
- src/main/java/jeoneunhye/vms/ContextLoaderListener.java 변경
- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 명령어를 처리할 메서드를 지정할 때 사용할 애노테이션을 정의한다.

- RequestMapping 애노테이션 추가
    - 실행 중에 메서드에 붙은 애노테이션 정보를 추출해야 한다.
    - 메서드에만 이 애노테이션을 붙일 수 있도록 사용 범위를 제한한다.
    - 메서드와 명령어를 연결하기 위해 명령어를 저장할 프로퍼티를 추가한다.

### 작업2) 명령어를 처리할 메서드에 @RequestMapping 애노테이션을 붙인다.

- Servlet 인터페이스 삭제
    - @RequestMapping 애노테이션으로 처리하기 때문에 삭제한다.
- XxxServlet 클래스 변경
    - 더이상 Servlet 인터페이스를 구현하지 않는다.
    - 메서드 선언부에 @RequestMapping 애노테이션과 명령 이름을 붙인다.

### 작업3) 특정 애노테이션이 붙은 객체의 이름 목록을 리턴하는 메서드를 추가한다.

- ApplicationContext 클래스 변경
    - getBeanNamesForAnnotation(Class<? extends Annotation>)을 추가한다.  
    => 객체 풀에 담긴 객체들 중에서 애노테이션이 붙은 객체를 찾고, 그 객체의 이름 목록을 배열로 리턴한다.

### 작업4) @Component가 붙은 객체 중에서 @RequestMapping이 붙은 메서드가 있는지 찾는다.

- ContextLoaderListener 클래스 변경
    - 작업3에서 선언한 메서드 getBeanNamesForAnnotation(Component.class)를 호출한다.  
    => ApplicationContext가 보관하고 있는 객체 중에서 @Component 애노테이션이 붙은 객체를 찾는다.
    - getRequestHandler(Class<?>)를 추가한다.  
    => @Component가 붙은 객체를 조사하여 @RequestMapping이 선언된 메서드가 있는지 알아낸다.  
    => Class.getMethods(), Method.getAnnotation(Class<?>)를 이용한다.

### 작업5) request handler의 정보를 저장할 클래스를 정의한다.

- RequestHandler 클래스 추가
    - 객체 주소, 객체 이름과 메서드 정보를 보관한다.
    - 생성자 파라미터로 메서드 정보와 @Component 애노테이션이 붙은 객체 주소를 주입받는다.
    - getPath(Method)를 호출하여 @RequestMapping 애노테이션이 붙은 메서드에 지정한 명령 이름을 알아낸다.
- ContextLoaderListener 클래스 변경
    - RequestHandler 객체를 생성한다.

### 작업6) request handler 목록을 보관할 클래스를 정의한다.

- RequestMappingHandlerMapping 클래스 추가
    - @RequestMapping 애노테이션이 붙은 메서드의 정보인 RequestHandler를 보관한다.
- ContextLoaderListener 클래스 변경
    - RequestHandler 객체를 RequestMappingHandlerMapping 객체에 보관한다.
    - 명령을 처리할 메서드를 찾을 수 있도록 명령 이름으로 메서드 정보를 저장한다.
    - ServerApp에서 RequestHandler를 꺼내 사용할 수 있도록 RequestMappingHandlerMapping 객체를
    Context Map에 보관한다.

### 작업7) RequestMappingHandlerMapping에 보관된 객체를 사용하여 명령을 처리한다.

- ServerApp 클래스 변경
    - Servlet Map을 제거하고 RequestMappingHandlerMapping 객체로 대체한다.
    - RequestHandler 객체를 RequestMappingHandlerMapping에서 꺼낸다.
    - RequestMappingHandlerMapping 객체에서 명령을 처리할 메서드를 꺼낸다.
    - Method.invoke(Object obj, Object... args)로 메서드를 호출하여 클라이언트에게 응답한다.  
    => obj: 메서드가 속해 있는 객체  
    => args: 메서드 호출에 필요한 인자