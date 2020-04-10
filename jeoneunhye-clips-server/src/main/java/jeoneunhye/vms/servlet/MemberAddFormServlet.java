package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;

@Component
public class MemberAddFormServlet {
  @RequestMapping("/member/addForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>회원 입력</h1>");
    out.println("<form action='/member/add'>");
    out.println("아이디: <input name='id' type='text'><br>");
    out.println("닉네임: <input name='nickname' type='text'><br>");
    out.println("암호: <input name='password' type='password'><br>");
    out.println("휴대폰번호: <input name='tel' type='tel'><br>");
    out.println("이메일: <input name='email' type='email'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}