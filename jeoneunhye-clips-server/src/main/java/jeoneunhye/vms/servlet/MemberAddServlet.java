package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@Component
public class MemberAddServlet {
  MemberService memberService;

  public MemberAddServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/add")
  public void service(Scanner in, PrintStream out) throws Exception {
    Member member = new Member();
    member.setId(Prompt.getString(in, out, "아이디? "));
    member.setNickname(Prompt.getString(in, out, "닉네임? "));
    member.setPassword(Prompt.getString(in, out, "암호? "));
    member.setPhone(Prompt.getString(in, out, "휴대폰번호? "));
    member.setEmail(Prompt.getString(in, out, "이메일? "));

    memberService.add(member);

    out.println("새 회원을 등록했습니다.");
  }
}