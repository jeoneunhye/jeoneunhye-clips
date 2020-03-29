package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

@Component("/board/list")
public class BoardListServlet implements Servlet {
  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Board> boards = boardService.list();
    for (Board b : boards) {
      out.printf("%d, %s, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getWriter(), b.getWriteDate(), b.getViewCount());
    }
  }
}