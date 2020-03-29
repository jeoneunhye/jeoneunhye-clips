package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Component;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@Component("/member/update")
public class MemberUpdateServlet implements Servlet {
  MemberService memberService;

  public MemberUpdateServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    Member oldMember = memberService.get(no);
    if (oldMember == null) {
      out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member newMember = new Member();
    newMember.setNo(no);
    newMember.setId(oldMember.getId());
    newMember.setNickname(Prompt.getString(in, out,
        String.format("닉네임(%s)? \n", oldMember.getNickname())));
    newMember.setPassword(Prompt.getString(in, out,
        String.format("암호(%s)? \n", oldMember.getPassword())));
    newMember.setPhone(Prompt.getString(in, out,
        String.format("휴대폰번호(%s)? \n", oldMember.getPhone())));
    newMember.setEmail(Prompt.getString(in, out,
        String.format("이메일(%s)? \n", oldMember.getEmail())));
    newMember.setRegisteredDate(oldMember.getRegisteredDate());

    memberService.update(newMember);

    out.println("회원을 변경했습니다.");
  }
}