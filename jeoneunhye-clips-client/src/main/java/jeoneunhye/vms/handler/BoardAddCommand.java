package jeoneunhye.vms.handler;
// "/board/add" 명령어 처리
import java.sql.Date;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardAddCommand implements Command {
  Prompt prompt;
  BoardDao boardDao;

  public BoardAddCommand(Prompt prompt, BoardDao boardDao) {
    this.prompt = prompt;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    Board board = new Board();
    board.setNo(prompt.inputInt("번호? "));
    board.setVideoNo(prompt.inputInt("영상 번호? "));
    board.setTitle(prompt.inputString("제목? "));
    board.setContents(prompt.inputString("내용? "));
    board.setWriter(prompt.inputString("작성자? "));
    board.setWriteDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    try {
      boardDao.insert(board);
      System.out.println("게시글을 저장하였습니다.");

    } catch (Exception e) {
      System.out.println("게시글을 저장할 수 없습니다.");
    }
  }
}