package jeoneunhye.vms;
import java.util.Scanner;

public class App3 {
public static void main(String[] args) {
  Scanner keyboard = new Scanner(System.in);
  
  System.out.print("번호? ");
  int no = keyboard.nextInt();
  keyboard.nextLine();
  System.out.print("제목? ");
  String title = keyboard.nextLine();
  System.out.print("내용? ");
  String content = keyboard.nextLine();
  System.out.print("작성일? ");
  String writeDate = keyboard.nextLine();
  int count = 0;

  keyboard.close();
  System.out.println();
  
  System.out.printf("번호: %d\n", no);
  System.out.printf("제목: %s\n", title);
  System.out.printf("내용: %s\n", content);
  System.out.printf("작성일: %s\n", writeDate);
  System.out.printf("조회수: %d\n", count);
  }
}
