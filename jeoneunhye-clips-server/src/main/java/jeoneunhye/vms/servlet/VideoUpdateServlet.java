package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/video/update")
public class VideoUpdateServlet extends GenericServlet {
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

      Video video = new Video();
      video.setNo(Integer.parseInt(request.getParameter("no")));
      video.setSubject(request.getParameter("subject"));
      video.setTitle(request.getParameter("title"));
      video.setUrl(request.getParameter("url"));
      video.setPlayTime(request.getParameter("playTime"));
      video.setWriter(request.getParameter("uploader"));
      video.setUploadDate(Date.valueOf(request.getParameter("uploadDate")));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>영상 변경</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>영상 변경 결과</h1>");

      videoService.update(video);

      out.println("<p>영상을 변경했습니다.</p>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}