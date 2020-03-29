package jeoneunhye.vms.service.impl;

import java.util.HashMap;
import java.util.List;
import jeoneunhye.util.Component;
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
  public void add(Video video) throws Exception {
    videoDao.insert(video);
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
  public void update(Video video) throws Exception {
    videoDao.update(video);
  }

  @Override
  public void delete(int no) throws Exception {
    videoDao.delete(no);
  }

  @Override
  public List<Video> search(HashMap<String, Object> params) throws Exception {
    return videoDao.findByKeyword(params);
  }
}