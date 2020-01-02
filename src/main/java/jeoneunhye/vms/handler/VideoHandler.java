package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.domain.Video;

public class VideoHandler {

  Scanner input;
  VideoList videoList;
  
  public VideoHandler(Scanner input) {
    this.input = input;
    this.videoList = new VideoList();
  }
  
  public VideoHandler(Scanner input, int capacity) {
    this.input = input;
    this.videoList = new VideoList(capacity);
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
    
    this.videoList.add(video);
    
    System.out.println("저장하였습니다.");
  }

  public void listVideo() {
    Video[] videos = this.videoList.toArray();
    for (Video v : videos) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          v.getNo(), v.getSubject(), v.getTitle(), v.getUploadDate(), v.getPlayTime());
    }
  }
}