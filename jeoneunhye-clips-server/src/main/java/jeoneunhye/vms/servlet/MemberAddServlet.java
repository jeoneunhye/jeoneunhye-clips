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

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>회원 입력</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>회원 입력</h1>");
      out.println("<form action='add' method='post'>");
      out.println("아이디: <input name='id' type='text'><br>");
      out.println("닉네임: <input name='nickname' type='text'><br>");
      out.println("암호: <input name='password' type='password'><br>");
      out.println("휴대폰번호: <input name='tel' type='tel'><br>");
      out.println("이메일: <input name='email' type='email'><br>");
      out.println("<button>등록</button>");
      out.println("</form>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

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
      member.setId(request.getParameter("id"));
      member.setNickname(request.getParameter("nickname"));
      member.setPassword(request.getParameter("password"));
      member.setPhone(request.getParameter("tel"));
      member.setEmail(request.getParameter("email"));

      memberService.add(member);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println("<title>회원 입력</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>회원 입력 결과</h1>");
      out.println("<p>새 회원을 등록했습니다.</p>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}