package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberListServlet implements Servlet {
  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Member> members = memberDao.findAll();
    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getId(), m.getPhone(), m.getEmail(), m.getRegisteredDate());
    }
  }
}