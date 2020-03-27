package jeoneunhye.vms.dao.mariadb;

import java.util.List;
import java.util.Map;
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
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("VideoMapper.deleteVideo", no);
      return count;
    }
  }

  @Override
  public List<Video> findByKeyword(Map<String, Object> params) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("VideoMapper.selectVideo", params);
    }
  }
}