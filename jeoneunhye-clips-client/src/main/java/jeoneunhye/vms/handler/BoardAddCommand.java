package jeoneunhye.vms.handler;
// "board/add" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;

public class BoardAddCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardAddCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
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
      out.writeUTF("/board/add");

      out.writeObject(board);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("게시글을 저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}