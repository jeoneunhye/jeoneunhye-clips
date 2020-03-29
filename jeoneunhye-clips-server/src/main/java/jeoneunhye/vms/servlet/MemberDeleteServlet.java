package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.service.MemberService;

@Component("/member/delete")
public class MemberDeleteServlet implements Servlet {
  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    memberService.delete(no);

    out.println("회원을 삭제했습니다.");
  }
}