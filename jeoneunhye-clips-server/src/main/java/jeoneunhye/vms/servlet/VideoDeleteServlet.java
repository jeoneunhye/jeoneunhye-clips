package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.VideoDao;

public class VideoDeleteServlet implements Servlet {
  VideoDao videoDao;

  public VideoDeleteServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    if (videoDao.delete(no) > 0) {
      out.println("영상을 삭제하였습니다.");

    } else {
      out.println("영상을 삭제할 수 없습니다.");
    }
  }
}