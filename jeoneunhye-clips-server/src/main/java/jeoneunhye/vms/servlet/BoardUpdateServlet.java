package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

@Component
public class BoardUpdateServlet {
  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/update")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    Board oldBoard = boardService.get(no);
    if (oldBoard == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Board newBoard = new Board();
    newBoard.setNo(no);
    newBoard.setTitle(Prompt.getString(in, out,
        String.format("제목(%s)? \n", oldBoard.getTitle())));
    newBoard.setContents(Prompt.getString(in, out,
        String.format("내용(%s)? \n", oldBoard.getContents())));
    newBoard.setWriter(oldBoard.getWriter());
    newBoard.setWriteDate(oldBoard.getWriteDate());
    newBoard.setViewCount(oldBoard.getViewCount());

    boardService.update(newBoard);

    out.println("게시글을 변경했습니다.");
  }
}