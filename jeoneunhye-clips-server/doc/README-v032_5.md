32_5 - 특정 기능을 수행하는 코드를 메서드로 분리하기
===

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/ServerApp.java 변경

### 작업1) 클라이언트의 요청을 처리하는 코드를 기능별로 분리한다.

- ServerApp.java 변경
    - if~ else... 분기문에 작성한 코드를 별도의 메서드로 분리하여 정의한다.  
```
listVideo(): 영상 목록 조회 요청 처리  
addVideo(): 영상 등록 요청 처리  
detailVideo(): 영상 조회 요청 처리  
updateVideo(): 영상 변경 요청 처리  
deleteVideo(): 영상 삭제 요청 처리  
listMember(): 회원 목록 조회 요청 처리  
addMember(): 회원 등록 요청 처리  
detailMember(): 회원 조회 요청 처리  
updateMember(): 회원 변경 요청 처리  
deleteMember(): 회원 삭제 요청 처리  
listBoard(): 게시글 목록 조회 요청 처리  
addBoard(): 게시글 등록 요청 처리  
detailBoard(): 게시글 조회 요청 처리  
updateBoard(): 게시글 변경 요청 처리  
deleteBoard(): 게시글 삭제 요청 처리
```
    - List 객체를 인스턴스 필드로 선언하여 아래의 인스턴스 메서드에서 사용할 수 있도록 한다.
    - 클라이언트로부터 입력받은 명령어로 서버에서 처리할 메서드를 호출한다.  
    간단한 문자열을 받아 처리할 때는 if문보다 switch문을 사용하는 것이 편리하다.