package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.Video;

public class PhotoBoardListServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  VideoDao videoDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, VideoDao videoDao) {
    this.photoBoardDao = photoBoardDao;
    this.videoDao = videoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("영상 번호? ");
    out.println("!{}!");
    out.flush();

    int videoNo = Integer.parseInt(in.nextLine());

    Video video = videoDao.findByNo(videoNo);
    if (video == null) {
      out.println("영상 번호가 유효하지 않습니다.");
      return;
    }

    out.printf("영상> %s\n", video.getTitle());
    out.println("-------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardDao.findAllByVideoNo(videoNo);
    for (PhotoBoard pb : photoBoards) {
      out.printf("%d, %s, %s, %d\n",
          pb.getNo(), pb.getTitle(), pb.getCreatedDate(), pb.getViewCount());
    }
  }
}