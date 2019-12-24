package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;

public class MemberHandler {
  static class Member {
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
  static final int MEMBER_SIZE = 100;
  static Member[] members = new Member[MEMBER_SIZE];
  static int memberCount = 0;
  public static Scanner keyboard;
  
  public static void addMember() {
    Member member = new Member();

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

    members[memberCount++] = member;
    System.out.println("저장하였습니다.");
  }

  public static void listMember() {
    for (int i = 0; i < memberCount; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
          m.no, m.name, m.email, m.grade,
          m.writeCount, m.commentCount, m.visitDateCount, m.registeredDate);
    }
  }
}
