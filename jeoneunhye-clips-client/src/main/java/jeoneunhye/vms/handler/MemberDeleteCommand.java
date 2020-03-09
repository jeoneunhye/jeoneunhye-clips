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

      if (memberDao.delete(no) > 0) {
        System.out.println("회원을 삭제하였습니다.");

      } else {
        System.out.println("해당 번호의 회원이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("회원을 삭제할 수 없습니다.");
    }
  }
}