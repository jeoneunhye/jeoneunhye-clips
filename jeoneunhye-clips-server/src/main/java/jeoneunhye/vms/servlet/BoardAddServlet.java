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
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {
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
    out.println("<title>게시글 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>게시글 입력</h1>");
    out.println("<form action='add' method='post'>");
    out.println("제목: <input name='title' type='text'><br>");
    out.println("내용:<br>");
    out.println("<textarea name='content' rows='5' cols='60'></textarea><br>");
    out.println("작성자: <input name='writer' type='text'><br>");
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

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");

      BoardService boardService = iocContainer.getBean(BoardService.class);

      Board board = new Board();
      board.setTitle(request.getParameter("title"));
      board.setContents(request.getParameter("content"));
      board.setWriter(request.getParameter("writer"));

      if (boardService.add(board) > 0) {
        response.sendRedirect("list");

      } else {
        request.getSession().setAttribute("errorMessage", "게시글을 등록할 수 없습니다.");
        request.getSession().setAttribute("url", "board/list");
        response.sendRedirect("../error");
      }

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}