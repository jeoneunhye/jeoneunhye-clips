package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoUpdateServlet {
  VideoService videoService;

  public VideoUpdateServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/update")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    Video oldVideo = videoService.get(no);
    if (oldVideo == null) {
      out.println("해당 번호의 영상이 없습니다.");
      return;
    }

    Video newVideo = new Video();
    newVideo.setNo(oldVideo.getNo());
    newVideo.setSubject(Prompt.getString(in, out,
        String.format("주제(%s)? \n", oldVideo.getSubject())));
    newVideo.setTitle(Prompt.getString(in, out,
        String.format("제목(%s)? \n", oldVideo.getTitle())));
    newVideo.setUrl(Prompt.getString(in, out,
        String.format("주소(%s)? \n", oldVideo.getUrl())));
    newVideo.setPlayTime(Prompt.getString(in, out,
        String.format("재생시간(%s)? \n", oldVideo.getPlayTime())));
    newVideo.setWriter(Prompt.getString(in, out,
        String.format("업로더(%s)? \n", oldVideo.getWriter())));
    newVideo.setUploadDate(Prompt.getDate(in, out,
        String.format("업로드 날짜(%s)? \n", oldVideo.getUploadDate())));

    videoService.update(newVideo);

    out.println("영상을 변경했습니다.");
  }
}