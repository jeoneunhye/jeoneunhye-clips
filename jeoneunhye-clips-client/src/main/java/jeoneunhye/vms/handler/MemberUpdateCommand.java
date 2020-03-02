package jeoneunhye.vms.handler;
// "/member/update" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberUpdateCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public MemberUpdateCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
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

      Member oldMember = (Member) in.readObject();

      Member newMember = new Member();
      newMember.setNo(oldMember.getNo());
      newMember.setId(prompt.inputString(
          String.format("아이디(%s)? ", oldMember.getId()), oldMember.getId()));
      newMember.setNickname(prompt.inputString(
          String.format("닉네임(%s)? ", oldMember.getNickname()), oldMember.getNickname()));
      newMember.setPassword(prompt.inputString(
          String.format("암호(%s)? ", oldMember.getPassword()), oldMember.getPassword()));
      newMember.setPhone(prompt.inputString(
          String.format("휴대폰번호(%s)? ", oldMember.getPhone()), oldMember.getPhone()));
      newMember.setEmail(prompt.inputString(
          String.format("이메일(%s)? ", oldMember.getEmail()), oldMember.getEmail()));
      newMember.setRegisteredDate(oldMember.getRegisteredDate());

      if (oldMember.equals(newMember)) {
        System.out.println("회원 변경을 취소하였습니다.");
        return;
      }

      out.writeUTF("/member/update");
      out.writeObject(newMember);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("회원을 변경하였습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}