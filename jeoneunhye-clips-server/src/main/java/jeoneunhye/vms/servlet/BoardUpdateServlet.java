package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

@Component
public class BoardUpdateServlet {
  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Board newBoard = new Board();
    newBoard.setNo(Integer.parseInt(params.get("no")));
    newBoard.setTitle(params.get("title"));
    newBoard.setContents(params.get("content"));
    newBoard.setWriter(params.get("writer"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/board/list'>");
    out.println("<title>게시글 변경</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>게시글 변경 결과</h1>");

    boardService.update(newBoard);

    out.println("<p>게시글을 변경했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}