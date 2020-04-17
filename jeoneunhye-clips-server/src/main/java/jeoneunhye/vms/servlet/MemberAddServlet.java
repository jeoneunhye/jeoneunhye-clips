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

      request.getRequestDispatcher("/header").include(request, response);

      out.println("<h1>회원 입력</h1>");
      out.println("<form action='add' method='post'>");
      out.println("아이디: <input name='id' type='text'><br>");
      out.println("닉네임: <input name='nickname' type='text'><br>");
      out.println("암호: <input name='password' type='password'><br>");
      out.println("휴대폰번호: <input name='tel' type='tel'><br>");
      out.println("이메일: <input name='email' type='email'><br>");
      out.println("<button>등록</button>");
      out.println("</form>");

      request.getRequestDispatcher("/footer").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

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
      member.setId(request.getParameter("id"));
      member.setNickname(request.getParameter("nickname"));
      member.setPassword(request.getParameter("password"));
      member.setPhone(request.getParameter("tel"));
      member.setEmail(request.getParameter("email"));

      if (memberService.add(member) > 0) {
        response.sendRedirect("list");

      } else {
        throw new Exception("회원을 등록할 수 없습니다.");
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}