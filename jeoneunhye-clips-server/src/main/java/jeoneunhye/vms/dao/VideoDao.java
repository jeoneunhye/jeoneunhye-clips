package jeoneunhye.vms.dao;

import java.util.List;
import java.util.Map;
import jeoneunhye.vms.domain.Video;

public interface VideoDao {
  public int insert(Video video) throws Exception;

  public List<Video> findAll() throws Exception;

  public Video findByNo(int no) throws Exception;

  public int update(Video video) throws Exception;

  public int delete(int no) throws Exception;

  List<Video> findByKeyword(Map<String, Object> params) throws Exception;
}