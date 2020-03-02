package jeoneunhye.vms.handler;
// "/member/delete" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;

public class MemberDeleteCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberDeleteCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/member/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("회원을 삭제하였습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}