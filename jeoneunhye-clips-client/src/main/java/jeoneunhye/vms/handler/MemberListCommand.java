package jeoneunhye.vms.handler;
// "member/list" 명령어 처리
import java.util.List;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberListCommand implements Command {
  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      List<Member> members = memberDao.findAll();

      for (Member m : members) {
        System.out.printf("%d, %s, %s, %s, %s\n",
            m.getNo(), m.getId(), m.getNickname(), m.getEmail(), m.getPhone());
      }

    } catch (Exception e) {
      System.out.println("회원 목록을 조회할 수 없습니다.");
    }
  }
}