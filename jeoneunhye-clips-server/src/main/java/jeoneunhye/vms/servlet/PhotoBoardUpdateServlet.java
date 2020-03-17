package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.util.ConnectionFactory;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;

public class PhotoBoardUpdateServlet implements Servlet {
  ConnectionFactory conFactory;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardUpdateServlet(ConnectionFactory conFactory,
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.conFactory = conFactory;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = (Prompt.getInt(in, out, "번호? "));

    PhotoBoard oldPhotoBoard = photoBoardDao.findByNo(no);
    if (oldPhotoBoard == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }

    PhotoBoard newPhotoBoard = new PhotoBoard();
    newPhotoBoard.setNo(no);
    newPhotoBoard.setTitle(Prompt.getString(in, out, String.format("제목(%s)? \n", oldPhotoBoard.getTitle()),
        oldPhotoBoard.getTitle()));
    newPhotoBoard.setContent(Prompt.getString(in, out, String.format("내용(%s)? \n", oldPhotoBoard.getContent()),
        oldPhotoBoard.getContent()));
    newPhotoBoard.setCreatedDate(oldPhotoBoard.getCreatedDate());
    newPhotoBoard.setViewCount(oldPhotoBoard.getViewCount());

    Connection con = conFactory.getConnection();
    con.setAutoCommit(false);

    try {
      if (photoBoardDao.update(newPhotoBoard) == 0) {
        throw new Exception("사진 게시글을 변경할 수 없습니다.");
      }

      printPhotoFiles(out, no);

      out.println();
      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");

      String response = Prompt.getString(in, out, "사진을 변경하시겠습니까?(Y/n) ");
      if (response.equalsIgnoreCase("y")) {
        photoFileDao.deleteAll(no);

        ArrayList<PhotoFile> newPhotoFiles = inputPhotoFiles(in, out);
        for (PhotoFile newPhotoFile : newPhotoFiles) {
          newPhotoFile.setBoardNo(newPhotoBoard.getNo());

          photoFileDao.insert(newPhotoFile);
        }
      }

      con.commit();
      out.println("사진 게시글을 변경하였습니다.");

    } catch (Exception e) {
      con.rollback();
      out.println(e.getMessage());

    } finally {
      con.setAutoCommit(true);
    }
  }

  private ArrayList<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    ArrayList<PhotoFile> newPhotoFiles = new ArrayList<>();

    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일? ");
      if (filepath.length() == 0) {
        if (newPhotoFiles.size() > 0) {
          break;

        } else {
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        }
      }

      newPhotoFiles.add(new PhotoFile().setFilepath(filepath));
    }

    return newPhotoFiles;
  }

  private void printPhotoFiles(PrintStream out, int boardNo) throws Exception {
    out.println("사진 파일:");

    List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(boardNo);
    for (PhotoFile oldphotoFile : oldPhotoFiles) {
      out.printf("> %s\n", oldphotoFile.getFilepath());
    }
  }
}