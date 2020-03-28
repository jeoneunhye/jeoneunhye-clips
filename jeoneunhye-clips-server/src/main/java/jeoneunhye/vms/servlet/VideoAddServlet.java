package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

public class VideoAddServlet implements Servlet {
  VideoService videoService;

  public VideoAddServlet(VideoService videoService) {
    this.videoService = videoService;
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

    videoService.add(video);

    out.println("새 영상을 등록했습니다.");
  }
}