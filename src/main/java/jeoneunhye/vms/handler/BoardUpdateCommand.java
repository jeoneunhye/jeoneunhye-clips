package jeoneunhye.vms.handler;
// "/board/update" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;

public class BoardUpdateCommand implements Command {
  Prompt prompt;
  List<Board> boardList;

  public BoardUpdateCommand(Prompt prompt, List<Board> list) {
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

    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(oldBoard.getNo());
    newBoard.setVideoNo(prompt.inputInt(
        String.format("영상번호(%d)? ", oldBoard.getVideoNo()), oldBoard.getVideoNo()));
    newBoard.setTitle(prompt.inputString(
        String.format("제목(%s)? ", oldBoard.getTitle()), oldBoard.getTitle()));
    newBoard.setContents(prompt.inputString(
        String.format("내용(%s)? ", oldBoard.getContents()), oldBoard.getContents()));
    newBoard.setWriter(oldBoard.getWriter());
    newBoard.setWriteDate(oldBoard.getWriteDate());
    newBoard.setViewCount(oldBoard.getViewCount());

    if (oldBoard.equals(newBoard)) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    this.boardList.set(index, newBoard);
    System.out.println("게시글을 변경하였습니다.");
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
