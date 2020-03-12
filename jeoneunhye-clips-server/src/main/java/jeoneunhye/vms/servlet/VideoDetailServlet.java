package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoDetailServlet implements Servlet {
  VideoDao videoDao;

  public VideoDetailServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Video video = videoDao.findByNo(no);
    if (video != null) {
      out.printf("주제: %s\n", video.getSubject());
      out.printf("제목: %s\n", video.getTitle());
      out.printf("주소: %s\n", video.getUrl());
      out.printf("재생시간: %s\n", video.getPlayTime());
      out.printf("업로더: %s\n", video.getWriter());
      out.printf("업로드 날짜: %s\n", video.getUploadDate());

    } else {
      out.println("해당 영상을 조회할 수 없습니다.");
    }
  }
}