package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.MemberDao;

public class MemberDeleteServlet implements Servlet {
  MemberDao memberDao;

  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    if (memberDao.delete(no) > 0) {
      out.println("회원을 삭제하였습니다.");

    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }
}