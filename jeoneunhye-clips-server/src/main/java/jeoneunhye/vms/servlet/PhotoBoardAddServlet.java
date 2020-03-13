package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.Video;

public class PhotoBoardAddServlet implements Servlet {
  PhotoBoardDao photoBoardDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목? ");
    out.println("!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("내용? ");
    out.println("!{}!");
    out.flush();
    photoBoard.setContent(in.nextLine());

    out.println("영상 번호? ");
    out.println("!{}!");
    out.flush();

    Video video = new Video();
    video.setNo(Integer.parseInt(in.nextLine()));

    photoBoard.setVideo(video);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("사진 게시글을 저장하였습니다.");

    } else {
      out.println("사진 게시글을 저장할 수 없습니다.");
    }
  }
}