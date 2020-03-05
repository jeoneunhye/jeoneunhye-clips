package jeoneunhye.vms.handler;
// "/board/update" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardUpdateCommand implements Command {
  Prompt prompt;
  BoardDao boardDao;

  public BoardUpdateCommand(Prompt prompt, BoardDao boardDao) {
    this.prompt = prompt;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Board oldBoard = boardDao.findByNo(no);

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
        System.out.println("게시물 변경을 취소하였습니다.");
        return;
      }

      boardDao.update(newBoard);

      System.out.println("게시물을 변경하였습니다.");

    } catch (Exception e) {
      System.out.println("게시글을 변경할 수 없습니다.");
    }
  }
}
