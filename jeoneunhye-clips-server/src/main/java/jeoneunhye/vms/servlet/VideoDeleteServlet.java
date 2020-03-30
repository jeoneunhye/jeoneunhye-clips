package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoDeleteServlet {
  VideoService videoService;

  public VideoDeleteServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    videoService.delete(no);

    out.println("영상을 삭제했습니다.");
  }
}