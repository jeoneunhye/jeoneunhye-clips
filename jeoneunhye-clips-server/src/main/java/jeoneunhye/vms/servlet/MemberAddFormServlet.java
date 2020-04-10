package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/addForm")
public class MemberAddFormServlet extends GenericServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest request, ServletResponse response)
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
      out.println("<form action='add'>");
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
}