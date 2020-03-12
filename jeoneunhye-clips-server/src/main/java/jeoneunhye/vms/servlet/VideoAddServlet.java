package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoAddServlet implements Servlet {
  VideoDao videoDao;

  public VideoAddServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Video video = new Video();

    out.println("주제? ");
    out.println("!{}!");
    out.flush();
    video.setSubject(in.nextLine());

    out.println("제목? ");
    out.println("!{}!");
    out.flush();
    video.setTitle(in.nextLine());

    out.println("주소? ");
    out.println("!{}!");
    out.flush();
    video.setUrl(in.nextLine());

    out.println("재생시간? ");
    out.println("!{}!");
    out.flush();
    video.setPlayTime(in.nextLine());

    out.println("업로더? ");
    out.println("!{}!");
    out.flush();
    video.setWriter(in.nextLine());

    out.println("업로드 날짜? ");
    out.println("!{}!");
    out.flush();
    video.setUploadDate(Date.valueOf(in.nextLine()));

    if (videoDao.insert(video) > 0) {
      out.println("영상을 저장하였습니다.");

    } else {
      out.println("영상을 저장할 수 없습니다.");
    }
  }
}