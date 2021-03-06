package jeoneunhye.vms.dao;

import java.util.List;
import java.util.Map;
import jeoneunhye.vms.domain.Member;

public interface MemberDao {
  public int insert(Member member) throws Exception;

  public List<Member> findAll() throws Exception;

  public Member findByNo(int no) throws Exception;

  public int update(Member member) throws Exception;

  public int delete(int no) throws Exception;

  List<Member> findByKeyword(String keyword) throws Exception;

  Member findByEmailAndPassword(Map<String, Object> params) throws Exception;
}