package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
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
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Video video = new Video();
    video.setNo(Integer.parseInt(params.get("no")));
    video.setSubject(params.get("subject"));
    video.setTitle(params.get("title"));
    video.setUrl(params.get("url"));
    video.setPlayTime(params.get("playTime"));
    video.setWriter(params.get("uploader"));
    video.setUploadDate(Date.valueOf(params.get("uploadDate")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/video/list'>");
    out.println("<title>영상 변경</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>영상 변경 결과</h1>");

    videoService.update(video);

    out.println("<p>영상을 변경했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}