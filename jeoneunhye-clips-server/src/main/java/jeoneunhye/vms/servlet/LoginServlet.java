package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class LoginServlet implements Servlet {
  MemberDao memberDao;

  public LoginServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    String email = Prompt.getString(in, out, "이메일? ");
    String password = Prompt.getString(in, out, "암호? ");

    Member member = memberDao.findByEmailAndPassword(email, password);
    if (member != null) {
      out.printf("'%s'님 환영합니다.\n", member.getId());

    } else {
      out.println("사용자 정보가 유효하지 않습니다.");
    }
  }
}