package jeoneunhye.vms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static final int SIZE = 100;
  static int[] no = new int[SIZE];
  static String[] name = new String[SIZE];
  static String[] email = new String[SIZE];
  static String[] password = new String[SIZE];
  static String[] grade = new String[SIZE];
  static int[] textCount = new int[SIZE];
  static int[] commentCount = new int[SIZE];
  static int[] visitDate = new int[SIZE];
  static Date[] registeredDate = new Date[SIZE];
  static int count = 0;

  public static void main(String[] args) {
    inputUsers();
    System.out.println();
    printUsers();
  }

  static void inputUsers() {
    Scanner keyboard = new Scanner(System.in);
    String response;
    for (int i = 0; i < SIZE; i++) {
      System.out.print("번호? ");
      no[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("이름? ");
      name[i] = keyboard.nextLine();
      System.out.print("이메일주소? ");
      email[i] = keyboard.nextLine();
      System.out.print("암호? ");
      password[i] = keyboard.nextLine();
      System.out.print("등급? ");
      grade[i] = keyboard.nextLine();
      System.out.print("작성글 수? ");
      textCount[i] = keyboard.nextInt();
      System.out.print("작성댓글 수? ");
      commentCount[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("방문일? ");
      visitDate[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("가입일? ");
      registeredDate[i] = Date.valueOf(keyboard.nextLine());

      count++;

      System.out.println("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();
  }

  static void printUsers() {
    for (int i = 0; i < count; i++) {
      System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
          no[i], name[i], email[i], grade[i],
          textCount[i], commentCount[i], visitDate[i], registeredDate[i]);
    }
  }
}
