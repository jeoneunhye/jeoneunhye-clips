package jeoneunhye.vms.handler;
// "/member/update" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberUpdateCommand implements Command {
  Prompt prompt;
  MemberDao memberDao;

  public MemberUpdateCommand(Prompt prompt, MemberDao memberDao) {
    this.prompt = prompt;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Member oldMember = null;
      try {
        oldMember = memberDao.findByNo(no);

      } catch (Exception e) {
        System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
        return;
      }

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

      memberDao.update(newMember);

      System.out.println("회원을 변경하였습니다.");

    } catch (Exception e) {
      System.out.println("회원을 변경할 수 없습니다.");
    }
  }
}