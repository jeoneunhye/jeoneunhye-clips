package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@Component
public class MemberUpdateServlet {
  MemberService memberService;

  public MemberUpdateServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/update")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    Member newMember = new Member();
    newMember.setNo(Integer.parseInt(params.get("no")));
    newMember.setId(params.get("id"));
    newMember.setNickname(params.get("nickname"));
    newMember.setPassword(params.get("password"));
    newMember.setPhone(params.get("tel"));
    newMember.setEmail(params.get("email"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/member/list'>");
    out.println("<title>회원 변경</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>회원 변경 결과</h1>");

    memberService.update(newMember);

    out.println("<p>회원을 변경했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}