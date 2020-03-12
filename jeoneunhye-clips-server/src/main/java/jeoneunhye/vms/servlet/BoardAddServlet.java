package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
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

    out.println("제목? ");
    out.println("!{}!");
    out.flush();
    board.setTitle(in.nextLine());

    out.println("내용? ");
    out.println("!{}!");
    out.flush();
    board.setContents(in.nextLine());

    out.println("작성자? ");
    out.println("!{}!");
    out.flush();
    board.setWriter(in.nextLine());

    if (boardDao.insert(board) > 0) {
      out.println("게시글을 저장하였습니다.");

    } else {
      out.println("게시글을 저장할 수 없습니다.");
    }
  }
}