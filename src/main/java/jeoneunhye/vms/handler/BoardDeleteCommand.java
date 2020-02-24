package jeoneunhye.vms.handler;
// "/board/delete" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;

public class BoardDeleteCommand implements Command {
  Prompt prompt;
  List<Board> boardList;

  public BoardDeleteCommand(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfBoard(prompt.inputInt("글번호? "));
    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }

    this.boardList.remove(index);
    System.out.println("게시글을 삭제하였습니다.");
  }

  private int indexOfBoard(int no) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
    }

    return -1;
  }
}
