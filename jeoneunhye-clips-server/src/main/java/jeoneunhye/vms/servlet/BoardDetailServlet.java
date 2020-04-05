package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

@Component
public class BoardDetailServlet {
  BoardService boardService;

  public BoardDetailServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/detail")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int no = Integer.parseInt(params.get("no"));

    Board board = boardService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 상세정보</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>게시글 상세정보</h1>");

    if (board != null) {
      out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", board.getNo());
      out.printf("제목: %s<br>\n", board.getTitle());
      out.printf("내용: %s<br>\n", board.getContents());
      out.printf("작성자: %s<br>\n", board.getWriter());
      out.printf("작성일: %s<br>\n", board.getWriteDate());
      out.printf("조회수: %d<br>\n", board.getViewCount());
      out.printf("<p><a href='/board/delete?no=%d'>삭제</a>\n", board.getNo());
      out.printf("<p><a href='/board/updateForm?no=%d'>변경</a>\n", board.getNo());

    } else {
      out.println("<p>해당 번호의 게시글이 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}