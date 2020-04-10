package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoDetailServlet {
  VideoService videoService;

  public VideoDetailServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/detail")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    int no = Integer.parseInt(params.get("no"));

    Video video = videoService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>영상 상세정보</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>영상 상세정보</h1>");

    if (video != null) {
      out.println("<form action='/video/update'>");
      out.printf("번호: <input name='no' readonly type='text' value='%s'><br>\n", video.getNo());
      out.printf("주제: <input name='subject' type='text' value='%s'><br>\n", video.getSubject());
      out.printf("제목: <input name='title' type='text' value='%s'><br>\n", video.getTitle());
      out.printf("주소: <input name='url' type='text' value='%s'><br>\n", video.getUrl());
      out.printf("재생시간: <input name='playtime' type='text' value='%s'><br>\n", video.getPlayTime());
      out.printf("업로더: <input name='uploader' type='text' value='%s'><br>\n", video.getWriter());
      out.printf("업로드일: <input name='uploadDate' type='date' value='%s'><br>\n", video.getUploadDate());
      out.println("<p>");
      out.println("<button>변경</button>");
      out.printf("<a href='/video/delete?no=%d'>삭제</a>\n", video.getNo());
      out.printf("<a href='/photoboard/list?videoNo=%d'>사진 게시판</a>\n", video.getNo());
      out.println("</p>");
      out.println("</form>");

    } else {
      out.println("<p>해당 번호의 영상이 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}