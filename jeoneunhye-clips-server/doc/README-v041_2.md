# 41_2 - 트랜잭션 관리자를 사용하는 코드를 캡슐화하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/sql/TransactionCallback.java 추가
- src/main/java/jeoneunhye/sql/TransactionTemplate.java 추가
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/jeoneunhye/vms/servlet/PhotoBoardDeleteServlet.java 변경

### 작업1) PlatformTransactionManager를 사용하는 코드를 캡슐화하여 별도의 클래스로 분리한다.

- TransactionCallback 인터페이스 추가
    - TransactionTemplate이 Transaction 작업을 수행할 때 호출할 메서드 규칙을 정의한다.
- TransactionTemplate 클래스 추가
    - PlatformTransactionManager를 사용하는 코드를 메서드 execute(TransactionCallback)로 정의하여  
    반복되는 코드를 캡슐화하고 호출하는 코드를 단순화한다.
    - 반복 코드 안에서 특별하게 수행할 작업은 메서드의 파라미터로 받는다.  
    파라미터로 받은 객체를 실행 코드의 틀(Template) 안에서 실행한다.  
    try문 안에서 TransactionCallback.doInTransaction()을 호출하여 Transaction 작업을 수행하고  
    리턴 값을 Object result 필드에 담아 리턴한다.  
    Transaction 작업에 성공하면 PlatformTransactionManager.commit(),  
    실패하면 catch문에서 PlatformTransactionManager.rollback()을 호출한다.  
    리턴되는 결과가 없는 경우에는 TransactionCallback 대신 TransactionCallbackWithoutResult를 사용할 수도 있으나,  
    TransactionCallback에서 null을 리턴해도 무방하다.

### 작업2) Transaction을 사용할 곳에 TransactionTemplate을 적용한다.

- PhotoBoardAddServlet 클래스 변경
    - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate의 execute 메서드를 사용한다.
    - execute(TransactionCallback)의 doInTransaction()에 Transaction으로 묶어서 다룰 사진 게시글 및 사진 첨부 파일 등록 작업을 기술한다.
- PhotoBoardUpdateServlet 클래스 변경
    - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate의 execute 메서드를 사용한다.
    - execute(TransactionCallback)의 doInTransaction()에 Transaction으로 묶어서 다룰 사진 게시글 및 사진 첨부 파일 변경 작업을 기술한다.
- PhotoBoardDeleteServlet 클래스 변경
    - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate의 execute 메서드를 사용한다.
    - execute(TransactionCallback)의 doInTransaction()에 Transaction으로 묶어서 다룰 사진 게시글 및 사진 첨부 파일 삭제 작업을 기술한다.

사진 게시글 등록 과정에서 Transaction 작업을 수행할 때, 호출할 인터페이스의 메서드를 익명 클래스를 구현하여 오버라이딩

```
    transactionTemplate.execute(new TransactionCallback() {
      @Override
      public Object doInTransaction() throws Exception {
        if (photoBoardDao.insert(photoBoard) == 0) {
          throw new Exception("사진 게시글을 등록할 수 없습니다.");
        }

        for (PhotoFile photoFile : photoFiles) {
          photoFile.setBoardNo(photoBoard.getNo());

          photoFileDao.insert(photoFile);
        }

        out.println("사진 게시글을 등록하였습니다.");

        return null;
      }
    });
```

사진 게시글 등록 과정에서 Transaction 작업을 수행할 때, 호출할 인터페이스의 메서드를 람다 문법으로 구현하여 오버라이딩

```
    transactionTemplate.execute(() -> {
        if (photoBoardDao.insert(photoBoard) == 0) {
          throw new Exception("사진 게시글을 등록할 수 없습니다.");
        }

        for (PhotoFile photoFile : photoFiles) {
          photoFile.setBoardNo(photoBoard.getNo());

          photoFileDao.insert(photoFile);
        }

        out.println("사진 게시글을 등록하였습니다.");

        return null;
      });
```