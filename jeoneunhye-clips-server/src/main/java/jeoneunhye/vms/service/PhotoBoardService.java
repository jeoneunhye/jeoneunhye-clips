package jeoneunhye.vms.service;

import java.util.List;
import jeoneunhye.vms.domain.PhotoBoard;

public interface PhotoBoardService {
  void add(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> listVideoPhoto(int videoNo) throws Exception;

  PhotoBoard get(int no) throws Exception;

  void update(PhotoBoard photoBoard) throws Exception;

  void delete(int no) throws Exception;
}