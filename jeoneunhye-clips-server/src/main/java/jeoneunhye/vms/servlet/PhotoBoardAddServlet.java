package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.PhotoBoardService;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/photoboard/add")
public class PhotoBoardAddServlet extends HttpServlet {
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

      int videoNo = Integer.parseInt(request.getParameter("videoNo"));

      Video video = videoService.get(videoNo);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>사진 입력</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>사진 입력</h1>");
      out.println("<form action='add' method='post'>");
      out.printf("영상번호: <input name='videoNo' type='text' value='%d' readonly><br>\n",
          video.getNo());
      out.printf("영상: %s<br>\n", video.getTitle());
      out.println("제목: <input name='title' type='text'><br>");
      out.println("내용:<br>");
      out.println("<textarea name='content' rows='5' cols='60'></textarea><br>");
      out.println("<hr>");
      out.println("사진: <input name='photo1' type='file'><br>");
      out.println("사진: <input name='photo2' type='file'><br>");
      out.println("사진: <input name='photo3' type='file'><br>");
      out.println("사진: <input name='photo4' type='file'><br>");
      out.println("사진: <input name='photo5' type='file'><br>");
      out.println("<button>등록</button>");
      out.println("</form>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
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
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int videoNo = Integer.parseInt(request.getParameter("videoNo"));

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

      response.sendRedirect("list?videoNo=" + videoNo);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}