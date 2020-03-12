package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardDetailServlet implements Servlet {
  BoardDao boardDao;

  public BoardDetailServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Board board = boardDao.findByNo(no);
    if (board != null) {
      out.printf("제목: %s\n", board.getTitle());
      out.printf("내용: %s\n", board.getContents());
      out.printf("작성자: %s\n", board.getWriter());
      out.printf("작성일: %s\n", board.getWriteDate());
      out.printf("조회수: %d\n", board.getViewCount());

    } else {
      out.println("해당 번호의 게시글이 없습니다.");
    }
  }
}