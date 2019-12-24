package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;

public class VideoHandler {
  static class Video {
    int no;
    String subject;
    String title;
    Date uploadDate;
    String playTime;
  }
  static final int VIDEO_SIZE = 100;
  static Video[] videos = new Video[VIDEO_SIZE];
  static int videoCount = 0;
  public static Scanner keyboard;

  public static void addVideo() {
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
    videos[videoCount++] = video;
    System.out.println("저장하였습니다.");
  }

  public static void listVideo() {
    for (int i = 0; i < videoCount; i++) {
      Video v = videos[i];
      System.out.printf("%d, %s, %s, %s, %s\n",
          v.no, v.subject, v.title, v.uploadDate, v.playTime);
    }
  }
}
