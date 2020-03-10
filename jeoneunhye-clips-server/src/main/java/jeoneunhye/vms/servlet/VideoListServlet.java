package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoListServlet implements Servlet {
  VideoDao videoDao;

  public VideoListServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Video> videos = videoDao.findAll();
    for (Video v : videos) {
      out.printf("%d, %s, %s, %s, %s, %s\n",
          v.getNo(), v.getSubject(), v.getTitle(), v.getPlayTime(), v.getWriter(), v.getUploadDate());
    }
  }
}