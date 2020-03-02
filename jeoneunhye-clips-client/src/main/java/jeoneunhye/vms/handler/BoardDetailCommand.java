package jeoneunhye.vms.handler;
// "/board/detail" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;

public class BoardDetailCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardDetailCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
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

      Board board = (Board) in.readObject();
      System.out.printf("영상번호: %d\n", board.getVideoNo());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("내용: %s\n", board.getContents());
      System.out.printf("작성자: %s\n", board.getWriter());
      System.out.printf("작성일: %s\n", board.getWriteDate());
      System.out.printf("조회수: %d\n", board.getViewCount());

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}