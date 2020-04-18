package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/video/detail")
public class VideoDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      VideoService videoService = iocContainer.getBean(VideoService.class);

      int no = Integer.parseInt(request.getParameter("no"));

      Video video = videoService.get(no);

      request.getRequestDispatcher("/header").include(request, response);

      out.println("<h1>영상 상세정보</h1>");

      if (video != null) {
        out.println("<form action='update' method='post' enctype='multipart/form-data'>");
        out.printf("<img src='../upload/video/%s' height='200'><br>\n", video.getPhoto());
        out.printf("번호: <input name='no' readonly type='text' value='%s'><br>\n", video.getNo());
        out.printf("주제: <input name='subject' type='text' value='%s'><br>\n", video.getSubject());
        out.printf("제목: <input name='title' type='text' value='%s'><br>\n", video.getTitle());
        out.printf("주소: <input name='url' type='text' value='%s'><br>\n", video.getUrl());
        out.printf("재생시간: <input name='playTime' type='text' value='%s'><br>\n", video.getPlayTime());
        out.printf("업로더: <input name='uploader' type='text' value='%s'><br>\n", video.getWriter());
        out.printf("업로드일: <input name='uploadDate' type='date' value='%s'><br>\n", video.getUploadDate());
        out.printf("사진: <input name='photo' type='file'><br>\n", video.getPhoto());
        out.println("<p><button>변경</button>");
        out.printf("<a href='delete?no=%d'>삭제</a>\n", video.getNo());
        out.printf("<a href='../photoboard/list?videoNo=%d'>사진 게시판</a></p>\n", video.getNo());
        out.println("</form>");

      } else {
        out.println("<p>해당 번호의 영상이 없습니다.</p>");
      }

      request.getRequestDispatcher("/footer").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}