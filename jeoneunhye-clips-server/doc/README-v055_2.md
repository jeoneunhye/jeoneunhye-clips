# 55_2 - 이클립스 웹 프로젝트로 전환하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/webapp/index.html 추가

### 작업1) 이클립스에 톰캣 서버 환경을 추가한다.

- Window 메뉴/Preferences/Server/Runtime Environment 설정 추가
    - Tomcat v9.0 버전을 지정하고 설치 디렉토리에 톰캣홈폴더의 경로를 입력한다.

### 작업2) 웹 애플리케이션 테스트 서버를 구축한다.

- Servers 뷰/New Server로 'test'를 서버를 생성한다.

### 작업3) 웹 프로젝트로 전환한다.

- build.gradle 변경
    - 'eclipse' 플러그인을 'eclipse-wtp'으로 변경한다.
- $ gradle eclipse 실행
    - .settings/ 폴더에 웹 프로젝트 관련 설정 파일이 추가된다.
    - .project 파일에 웹 프로젝트 관련 설정 정보가 추가된다.
- 이클립스 IDE에서 프로젝트를 refresh한다.
    - 프로젝트 아이콘에 지구본 모양이 추가된다.

### 작업4) 테스트 서버에 웹 프로젝트를 등록한다.

- Servers 뷰/test 서버에 대해 컨텍스트 메뉴 - 'Add and Remove...' 메뉴를 선택하여 웹 프로젝트를 추가한다.

### 작업5) 테스트 서버를 시작한다.

- '테스트서버/컨텍스트 메뉴/Publish'를 클릭하여 배치 폴더에 웹 애플리케이션을 배치한다.
    - '홈폴더/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/' 폴더를 확인한다.
- '테스트서버/컨텍스트 메뉴/Start'를 클릭하여 서버를 실행한다.

### 작업6) 웹 애플리케이션을 테스트한다.

- http://localhost:9999/ 실행 테스트
    - 테스트 서버에는 루트 웹 애플리케이션이 배치되지 않았기 때문에 오류가 발생한다.
- http://localhost:9999/jeoneunhye-clips-server/board/list 실행

### 작업7) 정적자원 폴더를 추가한다.

- src/main/webapp/ 폴더 생성
    - 정적자원을 두는 폴더를 추가한다.
- $ gradle eclipse를 실행하여 설정 파일을 생성한다.
    - eclipse 프로젝트 설정 파일에 생성한 디렉토리 정보를 포함하도록 명령어를 실행한다.

### 작업8) 웹 애플리케이션 기본 HTML 파일을 생성한다.

- index.html 파일 생성
    - 메인 페이지에 Navigation bar를 생성한다.