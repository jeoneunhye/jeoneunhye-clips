# 56_8 - 파일 업로드 기능 추가 

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/domain/Video.java 변경
- src/main/resources/jeoneunhye/vms/mapper/VideoMapper.xml 변경
- src/main/java/jeoneunhye/vms/servlet/VideoAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoDetailServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/VideoUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardDetailServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardUpdateServlet.java 변경

### 작업1) 영상 테이블의 사진 컬럼을 추가한다.

- Video 클래스 변경
    - 사진 필드 및 getter & setter 메서드를 추가한다.
- ALTER TABLE vms_video ADD photo varchar(255);
    - 영상 테이블에 사진 컬럼을 추가한다.
- VideoMapper.xml 파일 변경
    - 영상 추가, 상세 조회, 변경과 관련된 쿼리문에 사진 컬럼을 추가한다.

### 작업2) 영상 등록에 파일 업로드 기능을 추가한다.

- VideoAddServlet 클래스 변경
    - 입력 폼에 multipart/form-data 인코딩을 적용한다.
    - src/main/webapp/upload/video 폴더를 생성하고 사진 파일이 저장될 경로로 지정한다.
    - 서블릿 3.0에 추가된 멀티파트 데이터 처리 기능 활용하여 파일을 저장한다.

### 작업3) 영상 조회시 사진을 출력한다.

- VideoDetailServlet 클래스 변경
    - '<img>' 태그를 이용하여 사진을 출력한다.
    - 사진을 변경할 수 있도록 변경 폼을 multipart/form-data로 설정한다.

### 작업3) 영상 변경에 파일 업로드 기능을 추가한다.

- VideoUpdateServlet 클래스 변경
    - 멀티파트 형식으로 넘어온 데이터를 처리한다.

### 작업4) 사진게시판에 파일 업로드를 적용한다.

- PhotoBoardAddServlet 클래스 변경
    - 입력 폼에 multipart/form-data 인코딩을 적용한다.
    - src/main/webapp/upload/photoboard 폴더를 생성하고 사진 파일이 저장될 경로로 지정한다.
    - 서블릿 3.0에 추가된 멀티파트 데이터 처리 기능 활용하여 파일을 저장한다.
    - 사진을 여러 개 등록할 때 같은 이름을 사용하고 Collection에 담아 꺼내 사용한다.
- PhotoBoardDetailServlet 클래스 변경
    - img 태그를 이용하여 사진을 출력한다.
    - 변경 폼에 multipart/form-data 인코딩을 적용한다.
- PhotoBoardUpdateServlet 클래스 변경
    - 멀티파트 형식으로 넘어온 데이터를 처리한다.