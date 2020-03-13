package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.domain.Video;

public class PhotoBoardAddServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  VideoDao videoDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, VideoDao videoDao,
      PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.videoDao = videoDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목? ");
    out.println("!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("내용? ");
    out.println("!{}!");
    out.flush();
    photoBoard.setContent(in.nextLine());

    out.println("영상 번호? ");
    out.println("!{}!");
    out.flush();
    int videoNo = Integer.parseInt(in.nextLine());

    Video video = videoDao.findByNo(videoNo);
    if (video == null) {
      out.println("영상 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setVideo(video);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();

      while (true) {
        out.println("사진 파일? ");
        out.println("!{}!");
        out.flush();

        String filepath = in.nextLine();
        if (filepath.length() == 0) {
          if (photoFiles.size() > 0) {
            break;

          } else {
            out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
            continue;
          }
        }

        // photoFiles.add(new PhotoFile(filepath, photoBoard.getNo()));
        photoFiles.add(new PhotoFile()
            .setFilepath(filepath)
            .setBoardNo(photoBoard.getNo()));
      }

      for (PhotoFile photoFile : photoFiles) {
        photoFileDao.insert(photoFile);
      }

      out.println("사진 게시글을 저장하였습니다.");

    } else {
      out.println("사진 게시글을 저장할 수 없습니다.");
    }
  }
}