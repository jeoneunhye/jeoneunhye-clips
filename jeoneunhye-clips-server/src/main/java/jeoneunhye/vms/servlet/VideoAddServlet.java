package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/video/add")
public class VideoAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>영상 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>영상 입력</h1>");
    out.println("<form action='add' method='post'>");
    out.println("주제: <input name='subject' type='text'><br>");
    out.println("제목: <input name='title' type='text'><br>");
    out.println("주소:<br>");
    out.println("<textarea name='url' rows='1' cols='30'></textarea><br>");
    out.println("재생시간: <input name='playTime' type='text'><br>");
    out.println("업로더: <input name='uploader' type='text'><br>");
    out.println("업로드일: <input name='uploadDate' type='date'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      VideoService videoService = iocContainer.getBean(VideoService.class);

      Video video = new Video();
      video.setSubject(request.getParameter("subject"));
      video.setTitle(request.getParameter("title"));
      video.setUrl(request.getParameter("url"));
      video.setPlayTime(request.getParameter("playTime"));
      video.setWriter(request.getParameter("uploader"));
      video.setUploadDate(Date.valueOf(request.getParameter("uploadDate")));

      videoService.add(video);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>영상 입력</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>영상 입력 결과</h1>");
      out.println("<p>새 영상을 등록했습니다.</p>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}