package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.DataLoaderListener;
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
    photoBoard.setTitle(Prompt.getString(in, out, "제목? "));
    photoBoard.setContent(Prompt.getString(in, out, "내용? "));

    int videoNo = Prompt.getInt(in, out, "영상 번호? ");

    Video video = videoDao.findByNo(videoNo);
    if (video == null) {
      out.println("영상 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setVideo(video);

    DataLoaderListener.con.setAutoCommit(false);

    try {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시글을 저장할 수 없습니다.");
      }

      List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
      for (PhotoFile photoFile : photoFiles) {
        photoFile.setBoardNo(photoBoard.getNo());

        photoFileDao.insert(photoFile);
      }

      DataLoaderListener.con.commit();
      out.println("사진 게시글을 저장하였습니다.");

    } catch (Exception e) {
      DataLoaderListener.con.rollback();
      out.println(e.getMessage());

    } finally {
      DataLoaderListener.con.setAutoCommit(true);
    }
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