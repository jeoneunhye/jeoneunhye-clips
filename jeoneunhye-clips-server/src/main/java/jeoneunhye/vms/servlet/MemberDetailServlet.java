package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@WebServlet("/member/detail")
public class MemberDetailServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      MemberService memberService = iocContainer.getBean(MemberService.class);

      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>회원 상세정보</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("<h1>회원 상세정보</h1>");

      if (member != null) {
        out.println("<form action='update'>");
        out.printf("번호: <input name='no' type='text' readonly value='%d'><br>\n", member.getNo());
        out.printf("아이디: %s<br>\n", member.getId());
        out.printf("닉네임: <input name='nickname' type='text' value='%s'><br>\n", member.getNickname());
        out.printf("암호: <input name='password' type='password'><br>\n", member.getPassword());
        out.printf("휴대폰번호: <input name='tel' type='tel' value='%s'><br>\n", member.getPhone());
        out.printf("이메일: <input name='email' type='email' value='%s'><br>\n", member.getEmail());
        out.printf("등록일: %s<br>\n", member.getRegisteredDate());
        out.println("<p><button>변경</button>");
        out.printf("<a href='delete?no=%d'>삭제</a></p>\n", member.getNo());
        out.println("</form>");

      } else {
        out.println("<p>해당 번호의 회원이 없습니다.</p>");
      }

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}