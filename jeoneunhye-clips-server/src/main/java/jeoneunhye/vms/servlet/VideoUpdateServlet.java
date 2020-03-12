package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoUpdateServlet implements Servlet {
  VideoDao videoDao;

  public VideoUpdateServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Video oldVideo = videoDao.findByNo(no);
    if (oldVideo == null) {
      out.println("해당 번호의 영상이 없습니다.");
      return;
    }

    Video newVideo = new Video();
    newVideo.setNo(oldVideo.getNo());

    out.printf("주제(%s)? \n", oldVideo.getSubject());
    out.println("!{}!");
    out.flush();
    newVideo.setSubject(in.nextLine());

    out.printf("제목(%s)? \n", oldVideo.getTitle());
    out.println("!{}!");
    out.flush();
    newVideo.setTitle(in.nextLine());

    out.printf("주소(%s)? \n", oldVideo.getUrl());
    out.println("!{}!");
    out.flush();
    newVideo.setUrl(in.nextLine());

    out.printf("재생시간(%s)? \n", oldVideo.getPlayTime());
    out.println("!{}!");
    out.flush();
    newVideo.setPlayTime(in.nextLine());

    out.printf("업로더(%s)? \n", oldVideo.getWriter());
    out.println("!{}!");
    out.flush();
    newVideo.setWriter(in.nextLine());

    out.printf("업로드 날짜(%s)? \n", oldVideo.getUploadDate());
    out.println("!{}!");
    out.flush();
    newVideo.setUploadDate(Date.valueOf(in.nextLine()));

    if (videoDao.update(newVideo) > 0) {
      out.println("영상을 변경하였습니다.");

    } else {
      out.println("영상을 변경할 수 없습니다.");
    }
  }
}