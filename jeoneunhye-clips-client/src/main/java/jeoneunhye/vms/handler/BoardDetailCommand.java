package jeoneunhye.vms.handler;
// "/board/detail" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardDetailCommand implements Command {
  Prompt prompt;
  BoardDao boardDao;

  public BoardDetailCommand(Prompt prompt, BoardDao boardDao) {
    this.prompt = prompt;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Board board = boardDao.findByNo(no);
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("내용: %s\n", board.getContents());
      System.out.printf("작성자: %s\n", board.getWriter());
      System.out.printf("작성일: %s\n", board.getWriteDate());
      System.out.printf("조회수: %d\n", board.getViewCount());

    } catch (Exception e) {
      System.out.println("해당 게시글을 조회할 수 없습니다.");
    }
  }
}