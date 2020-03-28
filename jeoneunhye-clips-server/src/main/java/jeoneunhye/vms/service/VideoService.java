package jeoneunhye.vms.service;

import java.util.HashMap;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public interface VideoService {
  void add(Video video) throws Exception;

  List<Video> list() throws Exception;

  Video get(int no) throws Exception;

  void update(Video video) throws Exception;

  void delete(int no) throws Exception;

  List<Video> search(HashMap<String, Object> params) throws Exception;
}