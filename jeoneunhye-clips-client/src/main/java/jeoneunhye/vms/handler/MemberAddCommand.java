package jeoneunhye.vms.handler;
// "/member/add" 명령어 처리
import java.sql.Date;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberAddCommand implements Command {
  Prompt prompt;
  MemberDao memberDao;

  public MemberAddCommand(Prompt prompt, MemberDao memberDao) {
    this.prompt = prompt;
    this.memberDao = memberDao;
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
      memberDao.insert(member);

      System.out.println("회원을 저장하였습니다.");

    } catch (Exception e) {
      System.out.println("회원을 저장할 수 없습니다.");
    }
  }
}