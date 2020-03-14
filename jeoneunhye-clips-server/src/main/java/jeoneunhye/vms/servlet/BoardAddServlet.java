package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardAddServlet implements Servlet {
  BoardDao boardDao;

  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Board board = new Board();
    board.setTitle(Prompt.getString(in, out, "제목? "));
    board.setContents(Prompt.getString(in, out, "내용? "));
    board.setWriter(Prompt.getString(in, out, "작성자? "));

    if (boardDao.insert(board) > 0) {
      out.println("게시글을 저장하였습니다.");

    } else {
      out.println("게시글을 저장할 수 없습니다.");
    }
  }
}