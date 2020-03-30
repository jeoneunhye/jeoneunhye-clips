package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoListServlet {
  VideoService videoService;

  public VideoListServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/list")
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Video> videos = videoService.list();
    for (Video v : videos) {
      out.printf("%d, %s, %s, %s, %s, %s\n",
          v.getNo(), v.getSubject(), v.getTitle(), v.getPlayTime(), v.getWriter(), v.getUploadDate());
    }
  }
}