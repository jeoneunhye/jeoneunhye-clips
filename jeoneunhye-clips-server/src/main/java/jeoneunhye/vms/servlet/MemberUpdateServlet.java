package jeoneunhye.vms.servlet;

import java.io.IOException;
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

      if (memberService.update(member) > 0) {
        response.sendRedirect("list");

      } else {
        request.getSession().setAttribute("errorMessage", "회원 번호가 유효하지 않습니다.");
        request.getSession().setAttribute("url", "member/list");
        response.sendRedirect("../error");
      }

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}