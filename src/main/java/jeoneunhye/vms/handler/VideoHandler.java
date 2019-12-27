package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.domain.Video;

public class VideoHandler {

  static final int VIDEO_SIZE = 100;
  public static Scanner keyboard;
  Video[] videos = new Video[VIDEO_SIZE];
  int videoCount = 0;

  public void addVideo() {
    Video video = new Video();
    System.out.print("번호? ");
    video.no = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("주제? ");
    video.subject = keyboard.nextLine();
    System.out.print("제목? ");
    video.title = keyboard.nextLine();
    System.out.print("업로드날짜? ");
    video.uploadDate = Date.valueOf(keyboard.nextLine());
    System.out.print("재생시간? ");
    video.playTime = keyboard.nextLine();
    this.videos[this.videoCount++] = video;
    System.out.println("저장하였습니다.");
  }

  public void listVideo() {
    for (int i = 0; i < this.videoCount; i++) {
      Video v = this.videos[i];
      System.out.printf("%d, %s, %s, %s, %s\n",
          v.no, v.subject, v.title, v.uploadDate, v.playTime);
    }
  }
}