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
  static int[] writeCount = new int[SIZE];
  static int[] commentCount = new int[SIZE];
  static int[] visitDateCount = new int[SIZE];
  static Date[] registeredDate = new Date[SIZE];
  static int count = 0;
  
  static Member[] members = new Member[SIZE];
  static public class Member {
    int no;
    String name;
    String email;
    String password;
    String grade;
    int writeCount;
    int commentCount;
    int visitDateCount;
    Date registeredDate;
    int count;
  }
  public static void main(String[] args) {
    
    inputUsers();
    System.out.println();
    printUsers();
  }

  static void inputUsers() {
    Scanner keyboard = new Scanner(System.in);
    for (int i = 0; i < SIZE; i++) {
      members[i] = new Member();
    }
    String response;
    for (int i = 0; i < SIZE; i++) {
      Member member = members[i];
      
      System.out.print("번호? ");
      member.no = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("이름? ");
      member.name = keyboard.nextLine();
      System.out.print("이메일주소? ");
      member.email = keyboard.nextLine();
      System.out.print("암호? ");
      member.password = keyboard.nextLine();
      System.out.print("등급? ");
      member.grade = keyboard.nextLine();
      System.out.print("작성글 수? ");
      member.writeCount = keyboard.nextInt();
      System.out.print("작성댓글 수? ");
      member.commentCount = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("방문일? ");
      member.visitDateCount = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("가입일? ");
      member.registeredDate = Date.valueOf(keyboard.nextLine());

      count++;
      members[i] = member;
      
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
      Member member = members[i];
      System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
          member.no, member.name, member.email, member.grade,
          member.writeCount, member.commentCount, member.visitDateCount, member.registeredDate);
    }
  }
}
