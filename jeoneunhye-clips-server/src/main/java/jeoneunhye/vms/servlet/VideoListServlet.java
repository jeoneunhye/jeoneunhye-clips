package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoListServlet {
  VideoService videoService;

  public VideoListServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/list")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>영상 목록</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("  <h1>영상</h1>");
    out.println("  <a href='/video/addForm'>새 영상</a><br>");
    out.println("  <table border='1'>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>주제</th>");
    out.println("    <th>제목</th>");
    out.println("    <th>재생시간</th>");
    out.println("    <th>업로더</th>");
    out.println("    <th>업로드일</th>");
    out.println("  </tr>");

    List<Video> videos = videoService.list();
    for (Video v : videos) {
      out.printf("  <tr>"
          + "<td>%d</td>"
          + "<td><a href='/video/detail?no=%d'>%s</a></td>"
          + "<td>%s</td>"
          + "<td>%s</td>"
          + "<td>%s</td>"
          + "<td>%s</td>"
          + "</tr>\n",
          v.getNo(),
          v.getNo(),
          v.getSubject(),
          v.getTitle(),
          v.getPlayTime(),
          v.getWriter(),
          v.getUploadDate());
    }

    out.println("</table>");
    out.println("<hr>");
    out.println("<form action='/video/search'>");
    out.println("주제: <input name='subject' type='text'><br>");
    out.println("제목: <input name='title' type='text'><br>");
    out.println("재생시간: <input name='playTime' type='text'><br>");
    out.println("업로더: <input name='uploader' type='text'><br>");
    out.println("업로드일: <input name='uploadDate' type='date'><br>");
    out.println("<button>검색</button>");

    out.println("</body>");
    out.println("</html>");
  }
}