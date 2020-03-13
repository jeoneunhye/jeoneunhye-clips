package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;

public class PhotoBoardDetailServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard photoBoard = photoBoardDao.findByNo(no);
    if (photoBoard != null) {
      out.printf("영상: %s\n", photoBoard.getVideo().getTitle());
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("내용: %s\n", photoBoard.getContent());
      out.printf("작성일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d\n", photoBoard.getViewCount());

      List<PhotoFile> photoFiles = photoFileDao.findAll(photoBoard.getNo());
      for (PhotoFile photoFile : photoFiles) {
        out.printf("> %s\n", photoFile.getFilepath());
      }

    } else {
      out.println("해당 번호의 사진 게시글이 없습니다.");
    }
  }
}