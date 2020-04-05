package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.service.BoardService;

@Component
public class BoardDeleteServlet {
  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/delete")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/board/list'>");
    out.println("<title>게시글 삭제</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>게시글 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));

    boardService.delete(no);

    out.println("<p>게시글을 삭제했습니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}