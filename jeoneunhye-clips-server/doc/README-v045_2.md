# 45_2 - Mybatis를 이용하여 DAO 구현체 자동 생성하기

## 작업 소스 및 결과

- src/main/java/jeoneunhye/vms/service/impl/BoardServiceImpl2.java 추가
- src/main/java/jeoneunhye/vms/DataLoaderListener.java 변경

### 작업1) BoardServiceImpl에 대해 Mybatis DAO 자동 생성기를 적용한다.

- BoardServiceImpl2 클래스 추가
    - 생성자로 BoardDao 구현체 대신 SqlSessionFactory를 넘겨받는다.
    - BoardDao를 사용할 때마다 SqlSession.getMapper(BoardDao.class)를 호출하여 구현체를 생성한다.  
    => Thread마다 SqlSession 객체를 구분해서 사용하기 때문에 메서드가 호출될 때마다 SqlSession 객체를 생성해야 한다.
- DataLoaderListener 클래스 변경
  - new BoardServiceImpl2(sqlSessionFactory)로 BoardService 구현체를 생성한다.