package jeoneunhye.vms.dao.json;

import java.util.List;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoJsonFileDao extends AbstractJsonFileDao<Video> implements VideoDao {
  public VideoJsonFileDao(String filename) {
    super(filename);
  }

  @Override
  public int insert(Video video) throws Exception {
    if (indexOf(video.getNo()) > -1) {
      return 0;
    }

    list.add(video);
    saveData();

    return 1;
  }

  @Override
  public List<Video> findAll() throws Exception {
    return list;
  }

  @Override
  public Video findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  @Override
  public int update(Video video) throws Exception {
    int index = indexOf(video.getNo());
    if (index == -1) {
      return 0;
    }

    list.set(index, video);
    saveData();

    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();

    return 1;
  }

  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) {
        return i;
      }
    }

    return -1;
  }
}