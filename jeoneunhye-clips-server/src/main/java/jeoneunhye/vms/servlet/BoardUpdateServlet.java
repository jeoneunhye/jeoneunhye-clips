package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardUpdateServlet implements Servlet {
  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Board oldBoard = boardDao.findByNo(no);
    if (oldBoard == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board newBoard = new Board();
    newBoard.setNo(no);

    out.printf("제목(%s)? \n", oldBoard.getTitle());
    out.println("!{}!");
    out.flush();
    newBoard.setTitle(in.nextLine());

    out.printf("내용(%s)? \n", oldBoard.getContents());
    out.println("!{}!");
    out.flush();
    newBoard.setContents(in.nextLine());

    newBoard.setWriter(oldBoard.getWriter());
    newBoard.setWriteDate(oldBoard.getWriteDate());
    newBoard.setViewCount(oldBoard.getViewCount());

    if (boardDao.update(newBoard) > 0) {
      out.println("게시글을 변경하였습니다.");

    } else {
      out.println("게시글을 변경할 수 없습니다.");
    }
  }
}