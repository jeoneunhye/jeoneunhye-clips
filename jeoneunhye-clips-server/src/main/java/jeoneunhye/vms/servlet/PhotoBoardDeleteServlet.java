package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.sql.PlatformTransactionManager;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;

public class PhotoBoardDeleteServlet implements Servlet {
  PlatformTransactionManager txManager;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PlatformTransactionManager txManager,
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.txManager = txManager;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    txManager.beginTransaction();

    try {
      photoFileDao.deleteAll(no);

      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }

      txManager.commit();
      out.println("사진 게시글을 삭제하였습니다.");

    } catch (Exception e) {
      txManager.rollback();
      out.println(e.getMessage());
    }
  }
}