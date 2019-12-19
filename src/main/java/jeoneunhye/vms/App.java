package jeoneunhye.vms;
import java.sql.Date;
import java.util.Scanner;

public class App {
  static int SIZE = 100;
  static int count = 0;
  static int[] no = new int[SIZE];
  static String[] subject = new String[SIZE];
  static String[] title = new String[SIZE];
  static Date[] uploadDate = new Date[SIZE];
  static String[] playTime = new String[SIZE];

  static public class Video {
    int no;
    String subject;
    String title;
    Date uploadDate;
    String playTime;
  }
  static Video[] videos = new Video[SIZE];
  
  public static void main(String[] args) {
    
    inputVideo();
    System.out.println();
    printVideo();
  }

  static void inputVideo() {
    Scanner keyboard = new Scanner(System.in);
    String response;
    
    for (int i = 0; i < SIZE; i++) {
      videos[i] = new Video();
    }
    
    for (int i = 0; i < SIZE; i++) {
      
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

      count++;
      videos[i] = video;
      
      System.out.println("더 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();

  }

  static void printVideo() {
    for (int i = 0; i < count; i++) {
      Video video = videos[i];
      System.out.printf("%d, %s, %s, %s, %s\n",
          video.no, video.subject, video.title, video.uploadDate, video.playTime);
    }
  }
}
