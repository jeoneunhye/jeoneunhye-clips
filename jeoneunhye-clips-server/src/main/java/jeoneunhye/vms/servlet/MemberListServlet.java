package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@Component("/member/list")
public class MemberListServlet implements Servlet {
  MemberService memberService;

  public MemberListServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Member> members = memberService.list();
    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getId(), m.getPhone(), m.getEmail(), m.getRegisteredDate());
    }
  }
}