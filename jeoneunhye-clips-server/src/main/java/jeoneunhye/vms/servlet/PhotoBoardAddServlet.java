package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.PhotoBoardService;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/photoboard/add")
public class PhotoBoardAddServlet extends GenericServlet {
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
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int videoNo = Integer.parseInt(request.getParameter("videoNo"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list?videoNo=" + videoNo + "'>");
      out.println("<title>사진 입력</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>사진 입력 결과</h1>");

      try {
        Video video = videoService.get(videoNo);
        if (video == null) {
          throw new Exception("영상 번호가 유효하지 않습니다.");
        }

        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setTitle(request.getParameter("title"));
        photoBoard.setContent(request.getParameter("content"));
        photoBoard.setVideo(video);

        List<PhotoFile> photoFiles = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
          String filepath = request.getParameter("photo" + i);
          if (filepath.length() > 0) {
            photoFiles.add(new PhotoFile().setFilepath(filepath));
          }
        }

        if (photoFiles.size() == 0) {
          throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
        }

        photoBoard.setFiles(photoFiles);

        photoBoardService.add(photoBoard);

        out.println("<p>새 사진 게시글을 등록하였습니다.</p>");

      } catch (Exception e) {
        out.printf("<p>%s</p>\n", e.getMessage());
      }

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}