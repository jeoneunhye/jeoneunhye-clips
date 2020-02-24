package jeoneunhye.vms.handler;
// "/board/add" 명령어 처리
import java.sql.Date;
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;

public class BoardAddCommand implements Command {
  Prompt prompt;
  List<Board> boardList;

  public BoardAddCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
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

    this.boardList.add(board);
    System.out.println("게시글을 저장하였습니다.");
  }
}