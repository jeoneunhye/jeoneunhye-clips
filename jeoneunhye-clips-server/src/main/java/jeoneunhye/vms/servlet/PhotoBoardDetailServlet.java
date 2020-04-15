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
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.service.PhotoBoardService;

@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends HttpServlet {
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

      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));

      PhotoBoard photoBoard = photoBoardService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>사진 상세정보</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>사진 상세정보</h1>");

      if (photoBoard != null) {
        out.println("<form action='update' method='post'>");
        out.printf("번호: <input name='no' type='number' readonly value='%d'><br>\n", photoBoard.getNo());
        out.printf("영상: %s<br>\n", photoBoard.getVideo().getTitle());
        out.printf("제목: <input name='title' type='text' value='%s'><br>\n", photoBoard.getTitle());
        out.printf("내용:<br>");
        out.printf("<textarea name='content' rows='5' cols='60'>%s</textarea><br>\n", photoBoard.getContent());
        out.printf("작성일: %s<br>\n", photoBoard.getCreatedDate());
        out.printf("조회수: %d<br>\n", photoBoard.getViewCount());

        out.println("<hr>");
        out.println("사진 파일:<br>");
        out.println("<ul>\n");
        for (PhotoFile photoFile : photoBoard.getFiles()) {
          out.printf("  <li>%s</li>\n", photoFile.getFilepath());
        }
        out.println("</ul>");

        out.println("사진: <input name='photo1' type='file'><br>");
        out.println("사진: <input name='photo2' type='file'><br>");
        out.println("사진: <input name='photo3' type='file'><br>");
        out.println("사진: <input name='photo4' type='file'><br>");
        out.println("사진: <input name='photo5' type='file'><br>");

        out.println("<p><button>변경</button>");
        out.printf("<a href='delete?no=%d&videoNo=%d'>삭제</a></p>\n",
            photoBoard.getNo(), photoBoard.getVideo().getNo());
        out.println("</form>");

      } else {
        out.println("<p>해당 번호의 사진 게시글이 없습니다.</p>");
      }

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}