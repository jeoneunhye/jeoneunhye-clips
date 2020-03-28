package jeoneunhye.vms.service;

import java.util.List;
import jeoneunhye.vms.domain.Member;

public interface MemberService {
  void add(Member member) throws Exception;

  List<Member> list() throws Exception;

  Member get(int no) throws Exception;

  void update(Member member) throws Exception;

  void delete(int no) throws Exception;

  Member get(String email, String password) throws Exception;

  List<Member> search(String keyword) throws Exception;
}