package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

@WebServlet("/member/search")
public class MemberSearchServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      MemberService memberService = iocContainer.getBean(MemberService.class);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("  <title>회원 검색</title>");
      out.println("</head>");
      out.println("<body>");

      out.println("  <h1>회원 검색 결과</h1>");
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>아이디</th>");
      out.println("    <th>휴대폰번호</th>");
      out.println("    <th>이메일</th>");
      out.println("    <th>등록일</th>");
      out.println("  </tr>");

      String keyword = request.getParameter("keyword");

      List<Member> members = memberService.search(keyword);
      for (Member m : members) {
        out.printf("  <tr>"
            + "<td>%d</td>"
            + "<td><a href='detail?no=%d'>%s</a></td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "</tr>\n",
            m.getNo(),
            m.getNo(),
            m.getId(),
            m.getPhone(),
            m.getEmail(),
            m.getRegisteredDate());
      }
      out.println("</table>");

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}