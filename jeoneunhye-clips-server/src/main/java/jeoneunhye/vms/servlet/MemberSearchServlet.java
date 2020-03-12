package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberSearchServlet implements Servlet {
  MemberDao memberDao;

  public MemberSearchServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("검색어? ");
    out.println("!{}!");
    out.flush();

    String keyword = in.nextLine();

    List<Member> members = memberDao.findByKeyword(keyword);
    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getId(), m.getPhone(), m.getEmail(), m.getRegisteredDate());
    }
  }
}