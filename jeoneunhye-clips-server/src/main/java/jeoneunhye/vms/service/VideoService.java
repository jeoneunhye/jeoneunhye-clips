package jeoneunhye.vms.service;

import java.util.HashMap;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public interface VideoService {
  int add(Video video) throws Exception;

  List<Video> list() throws Exception;

  Video get(int no) throws Exception;

  int update(Video video) throws Exception;

  int delete(int no) throws Exception;

  List<Video> search(HashMap<String, Object> params) throws Exception;
}