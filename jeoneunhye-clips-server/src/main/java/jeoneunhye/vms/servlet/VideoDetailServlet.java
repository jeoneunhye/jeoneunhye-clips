package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/video/detail")
public class VideoDetailServlet extends GenericServlet {
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

      int no = Integer.parseInt(request.getParameter("no"));

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
        out.println("<form action='update'>");
        out.printf("번호: <input name='no' readonly type='text' value='%s'><br>\n", video.getNo());
        out.printf("주제: <input name='subject' type='text' value='%s'><br>\n", video.getSubject());
        out.printf("제목: <input name='title' type='text' value='%s'><br>\n", video.getTitle());
        out.printf("주소: <input name='url' type='text' value='%s'><br>\n", video.getUrl());
        out.printf("재생시간: <input name='playtime' type='text' value='%s'><br>\n", video.getPlayTime());
        out.printf("업로더: <input name='uploader' type='text' value='%s'><br>\n", video.getWriter());
        out.printf("업로드일: <input name='uploadDate' type='date' value='%s'><br>\n", video.getUploadDate());
        out.println("<p>");
        out.println("<button>변경</button>");
        out.printf("<a href='delete?no=%d'>삭제</a>\n", video.getNo());
        out.printf("<a href='list?videoNo=%d'>사진 게시판</a>\n", video.getNo());
        out.println("</p>");
        out.println("</form>");

      } else {
        out.println("<p>해당 번호의 영상이 없습니다.</p>");
      }

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}