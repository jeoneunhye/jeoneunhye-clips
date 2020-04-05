package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.service.PhotoBoardService;

@Component
public class PhotoBoardDetailServlet {
  PhotoBoardService photoBoardService;

  public PhotoBoardDetailServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/detail")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int no = Integer.parseInt(params.get("no"));

    PhotoBoard photoBoard = photoBoardService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사진 상세정보</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>사진 상세정보</h1>");

    if (photoBoard != null) {
      out.println("<form action='/photoboard/update'>");
      out.printf("번호: <input name='no' type='number' readonly value='%d'><br>\n", photoBoard.getNo());
      out.printf("영상: %s<br>\n", photoBoard.getVideo().getTitle());
      out.printf("제목: <input name='title' type='text' value='%s'><br>\n", photoBoard.getTitle());
      out.printf("내용:<br>");
      out.printf("<textarea name='content' rows='5' cols='60'>%s</textarea><br>\n", photoBoard.getContent());
      out.printf("작성일: %s<br>\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d<br>\n", photoBoard.getViewCount());

      out.println("<hr>");
      out.println("사진 파일:<br>");
      out.println("<ul>\n");
      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf("  <li>%s</li>\n", photoFile.getFilepath());
      }
      out.println("</ul>");

      out.println("사진: <input name='photo1' type='file'><br>");
      out.println("사진: <input name='photo2' type='file'><br>");
      out.println("사진: <input name='photo3' type='file'><br>");
      out.println("사진: <input name='photo4' type='file'><br>");
      out.println("사진: <input name='photo5' type='file'><br>");

      out.println("<p><button>변경</button>");
      out.printf("<a href='/photoboard/delete?no=%d&videoNo=%d'>삭제</a></p>\n",
          photoBoard.getNo(), photoBoard.getVideo().getNo());
      out.println("</form>");

    } else {
      out.println("<p>해당 번호의 사진 게시글이 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}