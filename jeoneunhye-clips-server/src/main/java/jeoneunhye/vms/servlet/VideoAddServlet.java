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

    request.getRequestDispatcher("/header").include(request, response);

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

    request.getRequestDispatcher("/footer").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");

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

      if (videoService.add(video) > 0) {
        response.sendRedirect("list");

      } else {
        throw new Exception("영상을 등록할 수 없습니다.");
      }

    } catch (Exception e) {
      request.getSession().setAttribute("errorMessage", e);
      request.getSession().setAttribute("url", "list");
      response.sendRedirect("../error");
    }
  }
}