package jeoneunhye.vms.handler;
// "/member/add" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberAddCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberAddCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    Member member = new Member();
    member.setNo(prompt.inputInt("번호? "));
    member.setId(prompt.inputString("아이디? "));
    member.setNickname(prompt.inputString("닉네임? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhone(prompt.inputString("휴대폰번호? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("회원을 저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}