package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@Component
public class MemberSearchServlet {
  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/search")
  public void service(Scanner in, PrintStream out) throws Exception {
    String keyword = Prompt.getString(in, out, "검색어? ");

    List<Member> members = memberService.search(keyword);
    for (Member m : members) {
      out.printf("%d, %s, %s, %s, %s\n",
          m.getNo(), m.getId(), m.getPhone(), m.getEmail(), m.getRegisteredDate());
    }
  }
}