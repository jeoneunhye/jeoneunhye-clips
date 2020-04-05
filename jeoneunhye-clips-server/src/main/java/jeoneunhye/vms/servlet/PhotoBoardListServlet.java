package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.PhotoBoardService;
import jeoneunhye.vms.service.VideoService;

@Component
public class PhotoBoardListServlet {
  PhotoBoardService photoBoardService;
  VideoService videoService;

  public PhotoBoardListServlet(PhotoBoardService photoBoardService, VideoService videoService) {
    this.photoBoardService = photoBoardService;
    this.videoService = videoService;
  }

  @RequestMapping("/photoboard/list")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>강의 사진 목록</title>");
    out.println("</head>");
    out.println("<body>");

    try {
      int videoNo = Integer.parseInt(params.get("videoNo"));

      Video video = videoService.get(videoNo);
      if (video == null) {
        throw new Exception("영상 번호가 유효하지 않습니다.");
      }

      out.printf("  <h1>강의 사진 - %s</h1>", video.getTitle());
      out.printf("  <a href='/photoboard/addForm?videoNo=%d'>새 사진</a><br>\n", videoNo);
      out.println("  <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");
      out.println("    <th>제목</th>");
      out.println("    <th>등록일</th>");
      out.println("    <th>조회수</th>");
      out.println("  </tr>");

      List<PhotoBoard> photoBoards = photoBoardService.listVideoPhoto(videoNo);
      for (PhotoBoard pb : photoBoards) {
        out.printf("  <tr>"
            + "<td>%d</td>"
            + "<td><a href='/photoboard/detail?no=%d'>%s</a></td>"
            + "<td>%s</td> "
            + "<td>%d</td>"
            + "</tr>\n",
            pb.getNo(),
            pb.getNo(),
            pb.getTitle(),
            pb.getCreatedDate(),
            pb.getViewCount());
      }
      out.println("</table>");

    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }

    out.println("</body>");
    out.println("</html>");
  }
}