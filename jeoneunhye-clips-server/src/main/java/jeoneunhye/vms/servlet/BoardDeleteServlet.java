package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.BoardDao;

public class BoardDeleteServlet implements Servlet {
  BoardDao boardDao;

  public BoardDeleteServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    if (boardDao.delete(no) > 0) {
      out.println("게시글을 삭제하였습니다.");

    } else {
      out.println("해당 번호의 게시글이 없습니다.");
    }
  }
}