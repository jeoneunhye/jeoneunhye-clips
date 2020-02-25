package jeoneunhye.vms.handler;
// "/member/add" 명령어 처리
import java.sql.Date;
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberAddCommand implements Command {
  Prompt prompt;
  List<Member> memberList;

  public MemberAddCommand(Prompt prompt, List<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
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

    this.memberList.add(member);
    System.out.println("회원을 저장하였습니다.");
  }
}