package jeoneunhye.vms.dao;

import java.util.List;
import jeoneunhye.vms.domain.PhotoBoard;

public interface PhotoBoardDao {
  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoBoard> findAllByVideoNo(int videoNo) throws Exception;

  PhotoBoard findByNo(int no) throws Exception;

  int update(PhotoBoard photoBoard) throws Exception;

  int delete(int no) throws Exception;
}