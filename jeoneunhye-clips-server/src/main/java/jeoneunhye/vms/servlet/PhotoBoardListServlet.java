package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.PhotoBoardService;
import jeoneunhye.vms.service.VideoService;

public class PhotoBoardListServlet implements Servlet {
  PhotoBoardService photoBoardService;
  VideoService videoService;

  public PhotoBoardListServlet(PhotoBoardService photoBoardService, VideoService videoService) {
    this.photoBoardService = photoBoardService;
    this.videoService = videoService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("영상 번호? ");
    out.println("!{}!");
    out.flush();

    int videoNo = Integer.parseInt(in.nextLine());

    Video video = videoService.get(videoNo);
    if (video == null) {
      out.println("영상 번호가 유효하지 않습니다.");
      return;
    }

    out.printf("영상> %s\n", video.getTitle());
    out.println("-------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardService.listVideoPhoto(videoNo);
    for (PhotoBoard pb : photoBoards) {
      out.printf("%d, %s, %s, %d\n",
          pb.getNo(), pb.getTitle(), pb.getCreatedDate(), pb.getViewCount());
    }
  }
}