package jeoneunhye.vms.handler;
// "/board/delete" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.BoardDao;

public class BoardDeleteCommand implements Command {
  Prompt prompt;
  BoardDao boardDao;

  public BoardDeleteCommand(Prompt prompt, BoardDao boardDao) {
    this.prompt = prompt;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      boardDao.delete(no);

      System.out.println("게시글을 삭제하였습니다.");

    } catch (Exception e) {
      System.out.println("게시글을 삭제할 수 없습니다.");
    }
  }
}