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

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>게시글 입력</h1>");
    out.println("<form action='add' method='post'>");
    out.println("제목: <input name='title' type='text'><br>");
    out.println("내용:<br>");
    out.println("<textarea name='content' rows='5' cols='60'></textarea><br>");
    out.println("작성자: <input name='writer' type='text'><br>");
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
      ApplicationContext iocContainer = (ApplicationContext) servletContext.getAttribute("iocContainer");

      BoardService boardService = iocContainer.getBean(BoardService.class);

      Board board = new Board();
      board.setTitle(request.getParameter("title"));
      board.setContents(request.getParameter("content"));
      board.setWriter(request.getParameter("writer"));

      if (boardService.add(board) > 0) {
        response.sendRedirect("list");

      } else {
        throw new Exception("게시글을 등록할 수 없습니다.");
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}