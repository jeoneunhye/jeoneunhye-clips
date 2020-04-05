package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class PhotoBoardAddFormServlet {
  VideoService videoService;

  public PhotoBoardAddFormServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/photoboard/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int videoNo = Integer.parseInt(params.get("videoNo"));

    Video video = videoService.get(videoNo);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사진 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>사진 입력</h1>");
    out.println("<form action='/photoboard/add'>");
    out.printf("영상번호: <input name='videoNo' type='text' value='%d' readonly><br>\n",
        video.getNo());
    out.printf("영상: %s<br>\n", video.getTitle());
    out.println("제목: <input name='title' type='text'><br>");
    out.println("내용:<br>");
    out.println("<textarea name='content' rows='5' cols='60'></textarea><br>");
    out.println("<hr>");
    out.println("사진: <input name='photo1' type='file'><br>");
    out.println("사진: <input name='photo2' type='file'><br>");
    out.println("사진: <input name='photo3' type='file'><br>");
    out.println("사진: <input name='photo4' type='file'><br>");
    out.println("사진: <input name='photo5' type='file'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}