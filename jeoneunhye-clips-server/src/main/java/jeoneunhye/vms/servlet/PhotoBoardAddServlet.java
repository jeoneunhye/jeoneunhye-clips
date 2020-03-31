package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import jeoneunhye.util.Prompt;
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
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목? "));
    photoBoard.setContent(Prompt.getString(in, out, "내용? "));

    int videoNo = Prompt.getInt(in, out, "영상 번호? ");

    Video video = videoService.get(videoNo);
    if (video == null) {
      out.println("영상 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setVideo(video);

    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    photoBoardService.add(photoBoard);

    out.println("새 사진 게시글을 등록하였습니다.");
  }

  private ArrayList<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();

    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일? ");
      if (filepath.length() == 0) {
        if (photoFiles.size() > 0) {
          break;

        } else {
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        }
      }

      photoFiles.add(new PhotoFile().setFilepath(filepath));
    }

    return photoFiles;
  }
}