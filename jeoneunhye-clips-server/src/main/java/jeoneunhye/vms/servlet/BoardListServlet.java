package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

@Component
public class BoardListServlet {
  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/list")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>게시글 목록</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("  <h1>게시글</h1>");
    out.println("  <a href='/board/addForm'>새 글</a><br>");
    out.println("  <table border='1'>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>제목</th>");
    out.println("    <th>작성자</th>");
    out.println("    <th>등록일</th>");
    out.println("    <th>조회수</th>");
    out.println("  </tr>");

    List<Board> boards = boardService.list();
    for (Board b : boards) {
      out.printf("  <tr>"
          + "<td>%d</td>"
          + "<td><a href='/board/detail?no=%d'>%s</a></td>"
          + "<td>%s</td>"
          + "<td>%s</td>"
          + "<td>%d</td>"
          + "</tr>\n",
          b.getNo(),
          b.getNo(),
          b.getTitle(),
          b.getWriter(),
          b.getWriteDate(),
          b.getViewCount());
    }
    out.println("</table>");

    out.println("</body>");
    out.println("</html>");
  }
}