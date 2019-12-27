package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.domain.Member;

public class MemberHandler {

  static final int MEMBER_SIZE = 100;
  Scanner input;
  Member[] members;
  int memberCount = 0;

  public MemberHandler(Scanner input) {
    this.input = input;
    this.members = new Member[MEMBER_SIZE];
  }
  public void addMember() {
    Member member = new Member();

    System.out.print("번호? ");
    member.no = input.nextInt();
    input.nextLine();
    System.out.print("이름? ");
    member.name = input.nextLine();
    System.out.print("이메일주소? ");
    member.email = input.nextLine();
    System.out.print("암호? ");
    member.password = input.nextLine();
    System.out.print("등급? ");
    member.grade = input.nextLine();
    System.out.print("작성글 수? ");
    member.writeCount = input.nextInt();
    System.out.print("작성댓글 수? ");
    member.commentCount = input.nextInt();
    input.nextLine();
    System.out.print("방문일? ");
    member.visitDateCount = input.nextInt();
    input.nextLine();
    System.out.print("가입일? ");
    member.registeredDate = Date.valueOf(input.nextLine());

    this.members[this.memberCount++] = member;
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    for (int i = 0; i < this.memberCount; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
          m.no, m.name, m.email, m.grade,
          m.writeCount, m.commentCount, m.visitDateCount, m.registeredDate);
    }
  }
}