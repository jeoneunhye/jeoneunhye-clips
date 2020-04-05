package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

@Component
public class BoardAddServlet {
  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Board board = new Board();
    board.setTitle(params.get("title"));
    board.setContents(params.get("content"));
    board.setWriter(params.get("writer"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/board/list'>");
    out.println("<title>게시글 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>게시글 입력 결과</h1>");

    boardService.add(board);

    out.println("<p>새 게시글을 등록했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}