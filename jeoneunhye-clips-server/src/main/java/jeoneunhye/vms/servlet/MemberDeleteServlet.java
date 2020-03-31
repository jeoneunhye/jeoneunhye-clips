package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.service.MemberService;

@Component
public class MemberDeleteServlet {
  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/delete")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    memberService.delete(no);

    out.println("회원을 삭제했습니다.");
  }
}