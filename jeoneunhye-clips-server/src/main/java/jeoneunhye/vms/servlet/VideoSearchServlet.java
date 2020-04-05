package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@Component
public class VideoSearchServlet {
  VideoService videoService;

  public VideoSearchServlet(VideoService videoService) {
    this.videoService = videoService;
  }

  @RequestMapping("/video/search")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    HashMap<String, Object> keywordMap = new HashMap<>();

    String keyword = params.get("subject");
    if (keyword.length() > 0) {
      keywordMap.put("subject", keyword);
    }

    keyword = params.get("title");
    if (keyword.length() > 0) {
      keywordMap.put("title", keyword);
    }

    keyword = params.get("playTime");
    if (keyword.length() > 0) {
      keywordMap.put("playTime", keyword);
    }

    keyword = params.get("uploader");
    if (keyword.length() > 0) {
      keywordMap.put("uploader", keyword);
    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>영상 검색</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("  <h1>영상 검색 결과</h1>");
    out.println("  <table border='1'>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>주제</th>");
    out.println("    <th>제목</th>");
    out.println("    <th>재생시간</th>");
    out.println("    <th>업로더</th>");
    out.println("    <th>업로드일</th>");
    out.println("  </tr>");

    List<Video> videos = videoService.search(keywordMap);
    for (Video v : videos) {
      out.printf("  <tr>"
          + "<td>%d</td> "
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

    out.println("</body>");
    out.println("</html>");
  }
}