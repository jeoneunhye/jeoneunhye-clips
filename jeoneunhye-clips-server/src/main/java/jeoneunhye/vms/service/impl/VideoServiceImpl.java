package jeoneunhye.vms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoServiceImpl implements VideoService {
  VideoDao videoDao;

  public VideoServiceImpl(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public int add(Video video) throws Exception {
    return videoDao.insert(video);
  }

  @Override
  public List<Video> list() throws Exception {
    return videoDao.findAll();
  }

  @Override
  public Video get(int no) throws Exception {
    return videoDao.findByNo(no);
  }

  @Override
  public int update(Video video) throws Exception {
    return videoDao.update(video);
  }

  @Override
  public int delete(int no) throws Exception {
    return videoDao.delete(no);
  }

  @Override
  public List<Video> search(HashMap<String, Object> params) throws Exception {
    return videoDao.findByKeyword(params);
  }
}