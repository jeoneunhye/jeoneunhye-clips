package jeoneunhye.vms;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("번호? ");
      int no = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("주제? ");
      String subject = keyboard.nextLine();
      System.out.print("제목? ");
      String title = keyboard.nextLine();
      System.out.print("업로드날짜? ");
      String uploadDate = keyboard.nextLine();
      System.out.print("재생시간? ");
      String playTime = keyboard.nextLine();
      
      keyboard.close();
      System.out.println();
      
      System.out.printf("번호: %d\n", no);
      System.out.printf("주제: %s\n", subject);
      System.out.printf("제목: %s\n", title);
      System.out.printf("날짜: %s\n", uploadDate);
      System.out.printf("재생시간: %s\n", playTime);
    }
}