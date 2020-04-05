package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
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
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Member member = new Member();
    member.setId(params.get("id"));
    member.setNickname(params.get("nickname"));
    member.setPassword(params.get("password"));
    member.setPhone(params.get("tel"));
    member.setEmail(params.get("email"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/member/list'>");
    out.println("<title>회원 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>회원 입력 결과</h1>");

    memberService.add(member);

    out.println("<p>새 회원을 등록했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}