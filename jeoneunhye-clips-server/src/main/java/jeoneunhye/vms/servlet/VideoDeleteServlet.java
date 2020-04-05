package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoDeleteServlet {
  VideoService videoService;

  public VideoDeleteServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/delete")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/video/list'>");
    out.println("<title>영상 삭제</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>영상 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));

    videoService.delete(no);

    out.println("<p>영상을 삭제했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}