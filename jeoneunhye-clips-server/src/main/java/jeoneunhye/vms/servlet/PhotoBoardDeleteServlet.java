package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.sql.PlatformTransactionManager;
import jeoneunhye.sql.TransactionTemplate;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;

public class PhotoBoardDeleteServlet implements Servlet {
  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PlatformTransactionManager txManager,
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    transactionTemplate.execute(() -> {
      photoFileDao.deleteAll(no);

      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }

      out.println("사진 게시글을 삭제하였습니다.");

      return null;
    });
  }
}