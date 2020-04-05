package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.PhotoBoardService;
import jeoneunhye.vms.service.VideoService;

@Component
public class PhotoBoardAddServlet {
  PhotoBoardService photoBoardService;
  VideoService videoService;

  public PhotoBoardAddServlet(PhotoBoardService photoBoardService, VideoService videoService) {
    this.photoBoardService = photoBoardService;
    this.videoService = videoService;
  }

  @RequestMapping("/photoboard/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int videoNo = Integer.parseInt(params.get("videoNo"));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh'"
        + " content='2;url=/photoboard/list?videoNo=" + videoNo + "'>");
    out.println("<title>사진 입력</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>사진 입력 결과</h1>");

    try {
      Video video = videoService.get(videoNo);
      if (video == null) {
        throw new Exception("영상 번호가 유효하지 않습니다.");
      }

      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(params.get("title"));
      photoBoard.setContent(params.get("content"));
      photoBoard.setVideo(video);

      List<PhotoFile> photoFiles = new ArrayList<>();

      for (int i = 1; i <= 5; i++) {
        String filepath = params.get("photo" + i);
        if (filepath.length() > 0) {
          photoFiles.add(new PhotoFile().setFilepath(filepath));
        }
      }

      if (photoFiles.size() == 0) {
        throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
      }

      photoBoard.setFiles(photoFiles);

      photoBoardService.add(photoBoard);

      out.println("<p>새 사진 게시글을 등록하였습니다.</p>");

    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }

    out.println("</body>");
    out.println("</html>");
  }
}