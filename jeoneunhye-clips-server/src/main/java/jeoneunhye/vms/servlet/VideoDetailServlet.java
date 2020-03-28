package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

public class VideoDetailServlet implements Servlet {
  VideoService videoService;

  public VideoDetailServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    Video video = videoService.get(no);
    if (video != null) {
      out.printf("주제: %s\n", video.getSubject());
      out.printf("제목: %s\n", video.getTitle());
      out.printf("주소: %s\n", video.getUrl());
      out.printf("재생시간: %s\n", video.getPlayTime());
      out.printf("업로더: %s\n", video.getWriter());
      out.printf("업로드 날짜: %s\n", video.getUploadDate());

    } else {
      out.println("해당 번호의 영상이 없습니다.");
    }
  }
}