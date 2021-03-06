package jeoneunhye.vms.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.service.PhotoBoardService;

@Component
public class PhotoBoardServiceImpl implements PhotoBoardService {
  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardServiceImpl(PlatformTransactionManager txManager
      , PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Transactional
  @Override
  public void add(PhotoBoard photoBoard) throws Exception {
    if (photoBoardDao.insert(photoBoard) == 0) {
      throw new Exception("사진 게시글을 등록할 수 없습니다.");
    }

    photoFileDao.insert(photoBoard);
  }

  @Override
  public List<PhotoBoard> listVideoPhoto(int videoNo) throws Exception {
    return photoBoardDao.findAllByVideoNo(videoNo);
  }

  @Override
  public PhotoBoard get(int no) throws Exception {
    return photoBoardDao.findByNo(no);
  }

  @Transactional
  @Override
  public void update(PhotoBoard photoBoard) throws Exception {
    if (photoBoardDao.update(photoBoard) == 0) {
      throw new Exception("사진 게시글을 변경할 수 없습니다.");
    }

    if (photoBoard.getFiles() != null) {
      photoFileDao.deleteAll(photoBoard.getNo());

      photoFileDao.insert(photoBoard);
    }
  }

  @Transactional
  @Override
  public void delete(int no) throws Exception {
    photoFileDao.deleteAll(no);

    if (photoBoardDao.delete(no) == 0) {
      throw new Exception("해당 번호의 사진 게시글이 없습니다.");
    }
  }
}