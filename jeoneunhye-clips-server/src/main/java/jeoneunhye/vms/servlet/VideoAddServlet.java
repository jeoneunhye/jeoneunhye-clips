package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoAddServlet {
  VideoService videoService;

  public VideoAddServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/add")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    Video video = new Video();
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
    out.println("<title>영상 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>영상 입력 결과</h1>");

    videoService.add(video);

    out.println("<p>새 영상을 등록했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}