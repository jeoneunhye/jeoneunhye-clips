package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardUpdateServlet implements Servlet {
  BoardDao boardDao;

  public BoardUpdateServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    Board oldBoard = boardDao.findByNo(no);
    if (oldBoard == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board newBoard = new Board();
    newBoard.setNo(no);
    newBoard.setTitle(Prompt.getString(in, out, String.format("제목(%s)? \n", oldBoard.getTitle())
        , oldBoard.getTitle()));
    newBoard.setContents(Prompt.getString(in, out, String.format("내용(%s)? \n", oldBoard.getContents())
        , oldBoard.getContents()));
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