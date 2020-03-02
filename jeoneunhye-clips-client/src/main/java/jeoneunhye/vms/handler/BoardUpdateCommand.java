package jeoneunhye.vms.handler;
// "/board/update" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;

public class BoardUpdateCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardUpdateCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/board/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Board oldBoard = (Board) in.readObject();

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

      out.writeUTF("/board/update");
      out.writeObject(newBoard);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("게시물을 변경하였습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
