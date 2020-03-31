package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import jeoneunhye.util.Prompt;
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
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    PhotoBoard photoBoard = photoBoardService.get(no);
    if (photoBoard != null) {
      out.printf("영상: %s\n", photoBoard.getVideo().getTitle());
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("내용: %s\n", photoBoard.getContent());
      out.printf("작성일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d\n", photoBoard.getViewCount());

      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf("> %s\n", photoFile.getFilepath());
      }

    } else {
      out.println("해당 번호의 사진 게시글이 없습니다.");
    }
  }
}