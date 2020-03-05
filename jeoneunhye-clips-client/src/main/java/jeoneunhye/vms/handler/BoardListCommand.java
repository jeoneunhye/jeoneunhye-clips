package jeoneunhye.vms.handler;
// "/board/list" 명령어 처리
import java.util.List;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardListCommand implements Command {
  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      List<Board> boards = boardDao.findAll();

      for (Board b : boards) {
        System.out.printf("%d, %d, %s, %s, %s, %d\n",
            b.getNo(), b.getVideoNo(), b.getTitle(), b.getWriter(), b.getWriteDate(), b.getViewCount());
      }

    } catch (Exception e) {
      System.out.println("게시글 목록을 조회할 수 없습니다.");
    }
  }
}