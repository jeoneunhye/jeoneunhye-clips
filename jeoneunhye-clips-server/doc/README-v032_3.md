32_3 - VMS 관리 데이터를 파일에서 로딩하고, 파일로 저장하기
===

### 작업1) project v31에서 데이터를 저장하고 로딩하는 코드를 가져온다.

- jeoneunhye.vms.domain 패키지를 생성한다.
- jeoneunhye.vms.domain.Video 클래스를 가져온다.
- jeoneunhye.context 패키지를 생성한다.
- jeoneunhye.context.ApplicationContextListener 클래스를 가져온다.
- jeoneunhye.vms.DataLoaderListener를 가져온다.
- ServerApp.java 변경
    - 옵저버를 등록하고, 호출하는 코드를 적용한다.