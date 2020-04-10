package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;

@Component
public class VideoAddFormServlet {
  @RequestMapping("/video/addForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>영상 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>영상 입력</h1>");
    out.println("<form action='/video/add'>");
    out.println("주제: <input name='subject' type='text'><br>");
    out.println("제목: <input name='title' type='text'><br>");
    out.println("주소:<br>");
    out.println("<textarea name='url' rows='1' cols='30'></textarea><br>");
    out.println("재생시간: <input name='playTime' type='text'><br>");
    out.println("업로더: <input name='uploader' type='text'><br>");
    out.println("업로드일: <input name='uploadDate' type='date'><br>");
    out.println("<button>등록</button>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}