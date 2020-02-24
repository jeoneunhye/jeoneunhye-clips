package jeoneunhye.vms.handler;
import java.util.Iterator;
// "/board/list" 명령어 처리
import java.util.List;
import jeoneunhye.vms.domain.Board;

public class BoardListCommand implements Command {
  List<Board> boardList;

  public BoardListCommand(List<Board> list) {
    this.boardList = list;
  }

  @Override
  public void execute() {
    Iterator<Board> iterator = boardList.iterator();

    while (iterator.hasNext()) {
      Board b = iterator.next();
      System.out.printf("%d, %d, %s, %s, %s, %d\n",
          b.getNo(), b.getVideoNo(), b.getTitle(), b.getWriter(), b.getWriteDate(), b.getViewCount());
    }
  }
}
