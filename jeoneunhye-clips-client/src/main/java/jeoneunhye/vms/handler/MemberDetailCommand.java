package jeoneunhye.vms.handler;
// "/member/detail" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberDetailCommand implements Command {
  Prompt prompt;
  MemberDao memberDao;

  public MemberDetailCommand(Prompt prompt, MemberDao memberDao) {
    this.prompt = prompt;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Member member = memberDao.findByNo(no);
      System.out.printf("아이디: %s\n", member.getId());
      System.out.printf("닉네임: %s\n", member.getNickname());
      System.out.printf("암호: %s\n", member.getPassword());
      System.out.printf("휴대폰번호: %s\n", member.getPhone());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("등록일: %s\n", member.getRegisteredDate());

    } catch (Exception e) {
      System.out.println("해당 회원을 조회할 수 없습니다.");
    }
  }
}