package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/video/search")
public class VideoSearchServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      VideoService videoService = iocContainer.getBean(VideoService.class);

      HashMap<String, Object> keywordMap = new HashMap<>();

      String keyword = request.getParameter("subject");
      if (keyword.length() > 0) {
        keywordMap.put("subject", keyword);
      }

      keyword = request.getParameter("title");
      if (keyword.length() > 0) {
        keywordMap.put("title", keyword);
      }

      keyword = request.getParameter("playTime");
      if (keyword.length() > 0) {
        keywordMap.put("playTime", keyword);
      }

      keyword = request.getParameter("uploader");
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
            + "<td><a href='detail?no=%d'>%s</a></td>"
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

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}