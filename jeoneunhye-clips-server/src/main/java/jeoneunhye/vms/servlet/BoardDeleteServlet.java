package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.service.BoardService;

@Component("/board/delete")
public class BoardDeleteServlet implements Servlet {
  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    boardService.delete(no);

    out.println("게시글을 삭제했습니다.");
  }
}