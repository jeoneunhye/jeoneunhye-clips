package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.domain.PhotoBoard;

public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard oldPhotoBoard = photoBoardDao.findByNo(no);
    if (oldPhotoBoard == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }

    PhotoBoard newPhotoBoard = new PhotoBoard();
    newPhotoBoard.setNo(no);

    out.printf("제목(%s)? \n", oldPhotoBoard.getTitle());
    out.println("!{}!");
    out.flush();
    newPhotoBoard.setTitle(in.nextLine());

    out.printf("내용(%s)? \n", oldPhotoBoard.getContent());
    out.println("!{}!");
    out.flush();
    newPhotoBoard.setContent(in.nextLine());

    newPhotoBoard.setCreatedDate(oldPhotoBoard.getCreatedDate());
    newPhotoBoard.setViewCount(oldPhotoBoard.getViewCount());

    if (photoBoardDao.update(newPhotoBoard) > 0) {
      out.println("사진 게시글을 변경하였습니다.");

    } else {
      out.println("사진 게시글을 변경할 수 없습니다.");
    }
  }
}