package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;

public class PhotoBoardUpdateServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard oldPhotoBoard = photoBoardDao.findByNo(no);
    if (oldPhotoBoard == null) {
      out.println("해당 번호의 사진 게시글이 없습니다.");
      return;
    }

    PhotoBoard newPhotoBoard = new PhotoBoard();
    newPhotoBoard.setNo(no);

    out.printf("제목(%s)? \n", oldPhotoBoard.getTitle());
    out.println("!{}!");
    out.flush();
    newPhotoBoard.setTitle(in.nextLine());

    out.printf("내용(%s)? \n", oldPhotoBoard.getContent());
    out.println("!{}!");
    out.flush();
    newPhotoBoard.setContent(in.nextLine());

    newPhotoBoard.setCreatedDate(oldPhotoBoard.getCreatedDate());
    newPhotoBoard.setViewCount(oldPhotoBoard.getViewCount());

    if (photoBoardDao.update(newPhotoBoard) > 0) {
      out.println("사진 파일:");

      List<PhotoFile> oldPhotoFiles = photoFileDao.findAll(no);
      for (PhotoFile oldphotoFile : oldPhotoFiles) {
        out.printf("> %s\n", oldphotoFile.getFilepath());
      }

      out.println();
      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      out.println("사진을 변경하시겠습니까?(Y/n) ");
      out.println("!{}!");
      out.flush();

      String response = in.nextLine();
      if (response.equalsIgnoreCase("y")) {
        photoFileDao.deleteAll(no);

        out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
        out.println("파일명 입력없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

        ArrayList<PhotoFile> newPhotoFiles = new ArrayList<>();

        while (true) {
          out.println("사진 파일? ");
          out.println("!{}!");
          out.flush();

          String filepath = in.nextLine();
          if (filepath.length() == 0) {
            if (newPhotoFiles.size() > 0) {
              break;

            } else {
              out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
              continue;
            }
          }

          newPhotoFiles.add(new PhotoFile()
              .setFilepath(filepath)
              .setBoardNo(newPhotoBoard.getNo()));
        }

        for (PhotoFile newPhotoFile : newPhotoFiles) {
          photoFileDao.insert(newPhotoFile);
        }
      }

      out.println("사진 게시글을 변경하였습니다.");

    } else {
      out.println("사진 게시글을 변경할 수 없습니다.");
    }
  }
}