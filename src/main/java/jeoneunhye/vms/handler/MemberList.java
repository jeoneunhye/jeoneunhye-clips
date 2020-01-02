package jeoneunhye.vms.handler;

import java.util.Arrays;
import jeoneunhye.vms.domain.Member;

public class MemberList {
  private static final int MEMBER_SIZE = 100;
  private Member[] members;
  private int memberCount = 0;
  
  public MemberList() {
    this.members = new Member[MEMBER_SIZE];
  }
  
  public MemberList(int capacity) {
    if (capacity > MEMBER_SIZE &&
        capacity < 10000) {
      this.members = new Member[capacity];
    } else {
      this.members = new Member[MEMBER_SIZE];
    }
}
  
  public void add(Member member) {
    if (this.memberCount == this.members.length) {
      int oldCapacity = this.members.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.members = Arrays.copyOf(members, newCapacity);
    }
    this.members[this.memberCount++] = member;
  }
  
  public Member[] toArray() {
    return Arrays.copyOf(this.members, this.memberCount);
  }
}