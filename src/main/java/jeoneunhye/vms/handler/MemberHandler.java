package jeoneunhye.vms.handler;

import jeoneunhye.util.ArrayList;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberHandler {
  Prompt prompt;
  ArrayList<Member> memberList;

  public MemberHandler(Prompt prompt) {
    this.prompt = prompt;
    this.memberList = new ArrayList<>();
  }

  public MemberHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    this.memberList = new ArrayList<>(capacity);
  }

  public void addMember() {
    Member member = new Member();

    member.setNo(prompt.inputInt("번호? "));
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setGrade(prompt.inputString("등급? "));
    member.setWriteCount(prompt.inputInt("작성글 수? "));
    member.setCommentCount(prompt.inputInt("작성댓글 수? "));
    member.setVisitDateCount(prompt.inputInt("방문일? "));
    member.setRegisteredDate(prompt.inputDate("가입일? "));

    this.memberList.add(member);

    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Member[] arr = new Member[this.memberList.size()];
    this.memberList.toArray(arr);
    for (Member m : arr) {
      System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
          m.getNo(), m.getName(), m.getEmail(), m.getGrade(),
          m.getWriteCount(), m.getCommentCount(), m.getVisitDateCount(), m.getRegisteredDate());
    }
  }

  public void detailMember() {
    int index = indexOfMember(prompt.inputInt("회원 번호? "));
    
    if (index == -1) {
      System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }
    
    Member member = this.memberList.get(index);
    
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("등급: %s\n", member.getGrade());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("작성글 수: %d\n", member.getWriteCount());
    System.out.printf("작성댓글 수: %d\n", member.getCommentCount());
    System.out.printf("방문일 수: %d\n", member.getVisitDateCount());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
  }
  
  public void updateMember() {
    int index = indexOfMember(prompt.inputInt("회원 번호? "));
    
    if (index == -1) {
      System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }
    
    Member oldMember = this.memberList.get(index);
    Member newMember = new Member();
    
    newMember.setNo(oldMember.getNo());
    newMember.setName(prompt.inputString(String.format("이름(%s)? ", oldMember.getName()),
        oldMember.getName()));
    newMember.setEmail(prompt.inputString(String.format("이메일(%s)? ", oldMember.getEmail()),
        oldMember.getEmail()));
    newMember.setPassword(prompt.inputString(String.format("암호(%s)? ", oldMember.getPassword()),
        oldMember.getPassword()));
    newMember.setGrade(prompt.inputString(String.format("등급(%s)? ", oldMember.getGrade()),
        oldMember.getGrade()));
    newMember.setWriteCount(prompt.inputInt(String.format("작성글 수(%d)? ", oldMember.getWriteCount()),
        oldMember.getWriteCount()));
    newMember.setCommentCount(prompt.inputInt(String.format("작성댓글 수(%d)? ", oldMember.getCommentCount()),
        oldMember.getCommentCount()));
    newMember.setVisitDateCount(oldMember.getVisitDateCount());
    newMember.setRegisteredDate(oldMember.getRegisteredDate());
    
    if (newMember.equals(oldMember)) {
      System.out.println("회원 변경을 취소하였습니다.");
      return;
    }
    
    this.memberList.set(index, newMember);
    
    System.out.println("회원을 변경하였습니다.");
  }
  
  public void deleteMember() {
    int index = indexOfMember(prompt.inputInt("회원 번호? "));
    
    if (index == -1) {
      System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }
    
    this.memberList.remove(index);
    
    System.out.println("회원을 삭제하였습니다.");
  }
  
  private int indexOfMember(int no) {
    for (int i = 0; i < this.memberList.size(); i++) {
      if (this.memberList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}