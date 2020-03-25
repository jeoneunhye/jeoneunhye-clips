package jeoneunhye.vms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoDaoImpl implements VideoDao {
  SqlSessionFactory sqlSessionFactory;

  public VideoDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Video video) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("VideoMapper.insertVideo", video);
      sqlSession.commit();

      return count;
    }
  }

  @Override
  public List<Video> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("VideoMapper.selectVideo");
    }
  }

  @Override
  public Video findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("VideoMapper.selectDetail", no);
    }
  }

  @Override
  public int update(Video video) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("VideoMapper.updateVideo", video);
      sqlSession.commit();

      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("VideoMapper.deleteVideo", no);
      sqlSession.commit();

      return count;
    }
  }
}