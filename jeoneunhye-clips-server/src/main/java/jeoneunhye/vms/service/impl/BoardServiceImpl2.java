package jeoneunhye.vms.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

public class BoardServiceImpl2 implements BoardService {
  SqlSessionFactory sqlSessionFactory;

  public BoardServiceImpl2(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

      boardDao.insert(board);
    }
  }

  @Override
  public List<Board> list() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

      return boardDao.findAll();
    }
  }

  @Override
  public Board get(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

      return boardDao.findByNo(no);
    }
  }

  @Override
  public void update(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

      boardDao.update(board);
    }
  }

  @Override
  public void delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

      boardDao.delete(no);
    }
  }
}