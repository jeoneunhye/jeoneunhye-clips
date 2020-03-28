package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.service.VideoService;

public class VideoDeleteServlet implements Servlet {
  VideoService videoService;

  public VideoDeleteServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    videoService.delete(no);

    out.println("영상을 삭제했습니다.");
  }
}