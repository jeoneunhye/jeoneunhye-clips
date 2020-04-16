package jeoneunhye.vms.service;

import java.util.List;
import jeoneunhye.vms.domain.Member;

public interface MemberService {
  int add(Member member) throws Exception;

  List<Member> list() throws Exception;

  Member get(int no) throws Exception;

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  Member get(String email, String password) throws Exception;

  List<Member> search(String keyword) throws Exception;
}