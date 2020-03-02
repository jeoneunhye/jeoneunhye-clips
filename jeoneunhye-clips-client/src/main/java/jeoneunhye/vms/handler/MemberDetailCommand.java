package jeoneunhye.vms.handler;
// "/member/detail" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberDetailCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberDetailCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Member member = (Member) in.readObject();
      System.out.printf("아이디: %s\n", member.getId());
      System.out.printf("닉네임: %s\n", member.getNickname());
      System.out.printf("암호: %s\n", member.getPassword());
      System.out.printf("휴대폰번호: %s\n", member.getPhone());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("등록일: %s\n", member.getRegisteredDate());

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}