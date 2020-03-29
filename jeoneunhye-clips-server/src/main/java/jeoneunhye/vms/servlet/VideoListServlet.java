package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component("/video/list")
public class VideoListServlet implements Servlet {
  VideoService videoService;

  public VideoListServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Video> videos = videoService.list();
    for (Video v : videos) {
      out.printf("%d, %s, %s, %s, %s, %s\n",
          v.getNo(), v.getSubject(), v.getTitle(), v.getPlayTime(), v.getWriter(), v.getUploadDate());
    }
  }
}