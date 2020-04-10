package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;

@Component
public class BoardAddFormServlet {
  @RequestMapping("/board/addForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>게시글 입력</h1>");
    out.println("<form action='/board/add'>");
    out.println("제목: <input name='title' type='text'><br>");
    out.println("내용:<br>");
    out.println("<textarea name='content' rows='5' cols='60'></textarea><br>");
    out.println("작성자: <input name='writer' type='text'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}