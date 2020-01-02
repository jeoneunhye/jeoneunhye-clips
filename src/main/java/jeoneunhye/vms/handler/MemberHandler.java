package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.domain.Member;

public class MemberHandler {

  Scanner input;
  MemberList memberList;
  
  public MemberHandler(Scanner input) {
    this.input = input;
    this.memberList = new MemberList();
  }
  
  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    this.memberList = new MemberList(capacity);
  }
  
  public void addMember() {
    Member member = new Member();

    System.out.print("번호? ");
    member.setNo(input.nextInt());
    input.nextLine();
    System.out.print("이름? ");
    member.setName(input.nextLine());
    System.out.print("이메일주소? ");
    member.setEmail(input.nextLine());
    System.out.print("암호? ");
    member.setPassword(input.nextLine());
    System.out.print("등급? ");
    member.setGrade(input.nextLine());
    System.out.print("작성글 수? ");
    member.setWriteCount(input.nextInt());
    System.out.print("작성댓글 수? ");
    member.setCommentCount(input.nextInt());
    input.nextLine();
    System.out.print("방문일? ");
    member.setVisitDateCount(input.nextInt());
    input.nextLine();
    System.out.print("가입일? ");
    member.setRegisteredDate(Date.valueOf(input.nextLine()));

    this.memberList.add(member);
    
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Member[] members = this.memberList.toArray();
    for (Member m : members) {
      System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getGrade(),
          m.getWriteCount(), m.getCommentCount(), m.getVisitDateCount(), m.getRegisteredDate());
    }
  }
}