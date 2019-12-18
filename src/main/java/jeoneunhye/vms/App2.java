package jeoneunhye.vms;
import java.util.Scanner;

public class App2 {
public static void main(String[] args) {
  Scanner keyboard = new Scanner(System.in);
  System.out.print("번호? ");
  int no = keyboard.nextInt();
  keyboard.nextLine();
  System.out.print("이름? ");
  String name = keyboard.nextLine();
  System.out.print("이메일주소? ");
  String email = keyboard.nextLine();
  System.out.print("암호? ");
  String password = keyboard.nextLine();
  System.out.print("등급? ");
  String grade = keyboard.nextLine();
  System.out.print("작성글 수? ");
  int textCount = keyboard.nextInt();
  System.out.print("작성댓글 수? ");
  int commentCount = keyboard.nextInt();
  keyboard.nextLine();
  System.out.print("방문일? ");
  int visitDate = keyboard.nextInt();
  keyboard.nextLine();
  System.out.print("가입일? ");
  String registeredDate = keyboard.nextLine();
  
  keyboard.close();
  System.out.println();
  
  System.out.printf("번호: %d\n", no);
  System.out.printf("이름: %s\n", name);
  System.out.printf("이메일: %s\n", email);
  System.out.printf("암호: %s\n", password);
  System.out.printf("등급: %s\n", grade);
  System.out.printf("활동기록: 글 %1$d개, 댓글 %2$d개\n", textCount, commentCount);
  System.out.printf("방문일: %d\n", visitDate);
  System.out.printf("가입일: %s\n", registeredDate);
  }
}
