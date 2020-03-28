package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

public class BoardAddServlet implements Servlet {
  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Board board = new Board();
    board.setTitle(Prompt.getString(in, out, "제목? "));
    board.setContents(Prompt.getString(in, out, "내용? "));
    board.setWriter(Prompt.getString(in, out, "작성자? "));

    boardService.add(board);

    out.println("새 게시글을 등록했습니다.");
  }
}