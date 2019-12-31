package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.domain.Video;

public class VideoHandler {

  static final int VIDEO_SIZE = 100;
  Scanner input;
  Video[] videos;
  int videoCount = 0;

  public VideoHandler(Scanner input) {
    this.input = input;
    this.videos = new Video[VIDEO_SIZE];
  }
  public void addVideo() {
    Video video = new Video();
    System.out.print("번호? ");
    video.setNo(input.nextInt());
    input.nextLine();
    System.out.print("주제? ");
    video.setSubject(input.nextLine());
    System.out.print("제목? ");
    video.setTitle(input.nextLine());
    System.out.print("업로드날짜? ");
    video.setUploadDate(Date.valueOf(input.nextLine()));
    System.out.print("재생시간? ");
    video.setPlayTime(input.nextLine());
    this.videos[this.videoCount++] = video;
    System.out.println("저장하였습니다.");
  }

  public void listVideo() {
    for (int i = 0; i < this.videoCount; i++) {
      Video v = this.videos[i];
      System.out.printf("%d, %s, %s, %s, %s\n",
          v.getNo(), v.getSubject(), v.getTitle(), v.getUploadDate(), v.getPlayTime());
    }
  }
}