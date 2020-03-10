package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardListServlet implements Servlet {
  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Board> boards = boardDao.findAll();
    for (Board b : boards) {
      out.printf("%d, %s, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getContents(), b.getWriteDate(), b.getViewCount());
    }
  }
}