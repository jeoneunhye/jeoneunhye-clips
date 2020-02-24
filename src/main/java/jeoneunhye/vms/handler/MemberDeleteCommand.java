package jeoneunhye.vms.handler;
// "/member/update" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberDeleteCommand implements Command {
  Prompt prompt;
  List<Member> memberList;

  public MemberDeleteCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }

  @Override
  public void execute() {
    int index = indexOfMember(prompt.inputInt("회원 번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    Member oldMember = this.memberList.get(index);
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

    if (newMember.equals(oldMember)) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }

    this.memberList.set(index, newMember);
    System.out.println("회원을 변경하였습니다.");
  }

  private int indexOfMember(int no) {
    for (int i = 0; i < this.memberList.size(); i++) {
      if (this.memberList.get(i).getNo() == no) {
        return i;
      }
    }

    return -1;
  }
}