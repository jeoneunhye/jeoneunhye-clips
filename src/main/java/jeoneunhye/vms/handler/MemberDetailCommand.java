package jeoneunhye.vms.handler;
// "/member/detail" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberDetailCommand implements Command {
  Prompt prompt;
  List<Member> memberList;

  public MemberDetailCommand(Prompt prompt, List<Member> list) {
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

    Member member = this.memberList.get(index);
    System.out.printf("아이디: %s\n", member.getId());
    System.out.printf("닉네임: %s\n", member.getNickname());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("휴대폰번호: %s\n", member.getPhone());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
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