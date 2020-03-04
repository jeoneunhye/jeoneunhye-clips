package jeoneunhye.vms.dao;

import java.util.List;
import jeoneunhye.vms.domain.Video;

public interface VideoDao {
  public int insert(Video video) throws Exception;

  public List<Video> findAll() throws Exception;

  public Video findByNo(int no) throws Exception;

  public int update(Video video) throws Exception;

  public int delete(int no) throws Exception;
}