package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
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
    video.setSubject(Prompt.getString(in, out, "주제? "));
    video.setTitle(Prompt.getString(in, out, "제목? "));
    video.setUrl(Prompt.getString(in, out, "주소? "));
    video.setPlayTime(Prompt.getString(in, out, "재생시간? "));
    video.setWriter(Prompt.getString(in, out, "업로더? "));
    video.setUploadDate(Prompt.getDate(in, out, "업로드 날짜? "));

    if (videoDao.insert(video) > 0) {
      out.println("영상을 저장하였습니다.");

    } else {
      out.println("영상을 저장할 수 없습니다.");
    }
  }
}