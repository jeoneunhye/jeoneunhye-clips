# 56_1 - HttpServlet 클래스를 상속받아 서블릿 구현하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/servlet/XxxServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/XxxFormServlet.java 삭제

### 작업1) 게시글 입력 폼과 입력 처리 서블릿을 한 클래스로 합친다.

- BoardAddServlet 클래스 변경 및 BoardAddFormServlet 클래스 삭제
    - 상속 대상을 GenericServlet에서 HttpServlet으로 변경한다.
    - BoardAddFormServlet의 service 메서드에 있던 코드를 doGet 메서드로 옮긴다.
    - doGet 메서드의 입력 폼 태그에 method='post'를 추가한다.
    - BoardAddServlet의 service 메서드에 있던 코드를 doPost 메서드로 옮긴다.
    - doPost 메서드에 요청받은 내용을 UTF-8로 인코딩하는 메서드를 추가한다.

### 작업2) 게시물 변경 폼과 변경 처리 서블릿을 한 클래스로 합친다.

- BoardUpdateServlet 클래스 변경 및 BoardUpdateFormServlet 클래스 삭제
    - 상속 대상을 GenericServlet에서 HttpServlet으로 변경한다.
    - BoardUpdateFormServlet의 service 메서드에 있던 코드를 doGet 메서드로 옮긴다.
    - doGet 메서드의 변경 폼 태그에 method='post'를 추가한다.
    - BoardUpdateServlet의 service 메서드에 있던 코드를 doPost 메서드로 옮긴다.
    - doPost 메서드에 요청받은 내용을 UTF-8로 인코딩하는 메서드를 추가한다.

### 작업3) 게시물 목록 조회 및 상세 조회, 삭제 서블릿을 변경한다.

- BoardListServlet 클래스 변경
    - 상속 대상을 GenericServlet에서 HttpServlet으로 변경한다.
    - service 메서드의 기존 코드를 doGet 메서드에서 오버라이딩하도록 변경한다.
    - 게시글 입력 링크의 주소를 'addForm'에서 'add'로 변경한다.
- BoardDetailServlet 클래스 변경
    - 상속 대상을 GenericServlet에서 HttpServlet으로 변경한다.
    - service 메서드의 기존 코드를 doGet 메서드에서 오버라이딩하도록 변경한다.
    - 게시글 변경 링크의 주소를 'updateForm'에서 'update'로 변경한다.
- BoardDeleteServlet 클래스 변경
    - 상속 대상을 GenericServlet에서 HttpServlet으로 변경한다.
    - service 메서드의 기존 코드를 doGet 메서드에서 오버라이딩하도록 변경한다.

### 작업4) 나머지 모든 서블릿을 위의 규칙에 따라 변경한다.

- XxxServlet 클래스 변경