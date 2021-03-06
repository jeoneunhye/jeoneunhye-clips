package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.PhotoBoardService;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/photoboard/list")
public class PhotoBoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int videoNo = 0;

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      VideoService videoService = iocContainer.getBean(VideoService.class);
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      request.getRequestDispatcher("/header").include(request, response);

      try {
        videoNo = Integer.parseInt(request.getParameter("videoNo"));

        Video video = videoService.get(videoNo);
        if (video == null) {
          throw new Exception("영상 번호가 유효하지 않습니다.");
        }

        out.printf("  <h1>강의 사진 - <a href='../lesson/detail?no=%d'>%s</a></h1>",
            videoNo, video.getTitle());
        out.printf("  <a href='add?videoNo=%d'>새 사진</a><br>\n", videoNo);
        out.println("  <table border='1'>");
        out.println("  <tr>");
        out.println("    <th>번호</th>");
        out.println("    <th>제목</th>");
        out.println("    <th>등록일</th>");
        out.println("    <th>조회수</th>");
        out.println("  </tr>");

        List<PhotoBoard> photoBoards = photoBoardService.listVideoPhoto(videoNo);
        for (PhotoBoard pb : photoBoards) {
          out.printf("  <tr>"
              + "<td>%d</td>"
              + "<td><a href='detail?no=%d'>%s</a></td>"
              + "<td>%s</td> "
              + "<td>%d</td>"
              + "</tr>\n",
              pb.getNo(),
              pb.getNo(),
              pb.getTitle(),
              pb.getCreatedDate(),
              pb.getViewCount());
        }
        out.println("</table>");

      } catch (Exception e) {
        out.printf("<p>%s</p>\n", e.getMessage());
      }

      request.getRequestDispatcher("/footer").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?videoNo=" + videoNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}