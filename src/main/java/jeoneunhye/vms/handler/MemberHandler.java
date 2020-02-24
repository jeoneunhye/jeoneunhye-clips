package jeoneunhye.vms.handler;

import java.sql.Date;
import jeoneunhye.util.AbstractList;
import jeoneunhye.util.Iterator;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Member;

public class MemberHandler {
  Prompt prompt;
  AbstractList<Member> memberList;

  public MemberHandler(Prompt prompt, AbstractList<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }

  public void addMember() {
    Member member = new Member();
    member.setNo(prompt.inputInt("번호? "));
    member.setId(prompt.inputString("아이디? "));
    member.setNickname(prompt.inputString("닉네임? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhone(prompt.inputString("휴대폰번호? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    this.memberList.add(member);
    System.out.println("회원을 저장하였습니다.");
  }

  public void listMember() {
    Iterator<Member> iterator = memberList.iterator();

    while (iterator.hasNext()) {
      Member m = iterator.next();
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), m.getId(), m.getEmail(), m.getPhone());
    }
  }

  public void detailMember() {
    int index = indexOfMember(prompt.inputInt("회원 번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }
    
    Member member = this.memberList.get(index);
    System.out.printf("아이디: %s\n", member.getId());
    System.out.printf("닉네임: %s\n", member.getNickname());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("휴대폰번호: %s\n", member.getPhone());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("등록일: %s\n", member.getRegisteredDate());
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
    newMember.setId(prompt.inputString(
        String.format("아이디(%s)? ", oldMember.getId()), oldMember.getId()));
    newMember.setNickname(prompt.inputString(
        String.format("닉네임(%s)? ", oldMember.getNickname()), oldMember.getNickname()));
    newMember.setPassword(prompt.inputString(
        String.format("암호(%s)? ", oldMember.getPassword()), oldMember.getPassword()));
    newMember.setPhone(prompt.inputString(
        String.format("휴대폰번호(%s)? ", oldMember.getPhone()), oldMember.getPhone()));
    newMember.setEmail(prompt.inputString(
        String.format("이메일(%s)? ", oldMember.getEmail()), oldMember.getEmail()));
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