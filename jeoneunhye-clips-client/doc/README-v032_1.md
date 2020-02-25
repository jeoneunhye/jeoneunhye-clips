32_1 - 자바 클라이언트 프로젝트 만들기
===

### 작업1) 프로젝트 폴더를 생성한다.

- 'jeoneunhye-clips/jeoneunhye-clips-client' 디렉토리를 생성한다.

### 작업2) 자바에 관련된 프로젝트 파일이나 폴더를 생성하고 초기화하는 명령을 실행한다.

- $ gradle init

### 작업3) 이클립스 IDE로 프로젝트를 실행한다.

- 'build.gradle' 변경
    - gradle 플러그인에 'eclipse'를 추가한다.
    - 'javaCompile'을 설정한다.   
```
tasks.withType(JavaCompile) {   
    options.encoding = 'UTF-8'   
    sourceCompatibility = '1.8'   
    targetCompatibility = '1.8'   
}
```

- $ gradle eclipse
    - gradle을 실행하여 이클립스 설정 파일을 생성한다.
- 이클립스에서 프로젝트 폴더를 import한다.

### 작업4) 프로젝트 시작 클래스를 생성한다.

- ServerApp.java
    - '영상 관리 시스템 클라이언트입니다.' 문구를 출력한다.