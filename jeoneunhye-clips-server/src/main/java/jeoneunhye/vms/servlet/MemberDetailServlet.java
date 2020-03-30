package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@Component
public class MemberDetailServlet {
  MemberService memberService;

  public MemberDetailServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/detail")
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    Member member = memberService.get(no);
    if (member != null) {
      out.printf("아이디: %s\n", member.getId());
      out.printf("닉네임: %s\n", member.getNickname());
      out.printf("암호: %s\n", member.getPassword());
      out.printf("휴대폰번호: %s\n", member.getPhone());
      out.printf("이메일: %s\n", member.getEmail());
      out.printf("등록일: %s\n", member.getRegisteredDate());

    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }
}