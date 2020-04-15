package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      MemberService memberService = iocContainer.getBean(MemberService.class);

      Member member = new Member();
      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setId(request.getParameter("id"));
      member.setNickname(request.getParameter("nickname"));
      member.setPassword(request.getParameter("password"));
      member.setPhone(request.getParameter("tel"));
      member.setEmail(request.getParameter("email"));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>회원 변경</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>회원 변경 결과</h1>");

      memberService.update(member);

      out.println("<p>회원을 변경했습니다.</p>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}