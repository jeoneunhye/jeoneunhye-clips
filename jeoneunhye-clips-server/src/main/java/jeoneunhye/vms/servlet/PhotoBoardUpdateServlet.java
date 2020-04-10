package jeoneunhye.vms.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.service.PhotoBoardService;

@Component
public class PhotoBoardUpdateServlet {
  PhotoBoardService photoBoardService;

  public PhotoBoardUpdateServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/update")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    int no = Integer.parseInt(params.get("no"));

    PhotoBoard photoBoard = photoBoardService.get(no);
    photoBoard.setTitle(params.get("title"));
    photoBoard.setContent(params.get("content"));

    List<PhotoFile> photoFiles = new ArrayList<>();

    for (int i = 1; i <= 5; i++) {
      String filepath = params.get("photo" + i);
      if (filepath.length() > 0) {
        photoFiles.add(new PhotoFile().setFilepath(filepath));
      }
    }

    if (photoFiles.size() > 0) {
      photoBoard.setFiles(photoFiles);

    } else {
      photoBoard.setFiles(null);
    }

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='2;url=/photoboard/list?videoNo=%d'>",
        photoBoard.getVideo().getNo());
    out.println("<title>사진 변경</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>사진 변경 결과</h1>");

    try {
      photoBoardService.update(photoBoard);
      out.println("<p>사진을 변경했습니다.</p>");

    } catch (Exception e) {
      out.println("<p>해당 번호의 사진 게시글이 없습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}