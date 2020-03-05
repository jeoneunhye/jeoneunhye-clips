package jeoneunhye.vms.handler;
// "/member/delete" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.MemberDao;

public class MemberDeleteCommand implements Command {
  Prompt prompt;
  MemberDao memberDao;

  public MemberDeleteCommand(Prompt prompt, MemberDao memberDao) {
    this.prompt = prompt;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      memberDao.delete(no);

      System.out.println("회원을 삭제하였습니다.");

    } catch (Exception e) {
      System.out.println("회원을 삭제할 수 없습니다.");
    }
  }
}