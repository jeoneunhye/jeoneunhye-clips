package jeoneunhye.vms.handler;
// "/board/list" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jeoneunhye.vms.domain.Board;

public class BoardListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public BoardListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.writeUTF("/board/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Board> boards = (List<Board>) in.readObject();

      for (Board b : boards) {
        System.out.printf("%d, %d, %s, %s, %s, %d\n",
            b.getNo(), b.getVideoNo(), b.getTitle(), b.getWriter(), b.getWriteDate(), b.getViewCount());
      }

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}