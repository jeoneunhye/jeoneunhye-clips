package jeoneunhye.vms.service.impl;

import java.util.List;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.service.MemberService;

public class MemberServiceImpl implements MemberService {
  MemberDao memberDao;

  public MemberServiceImpl (MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public void update(Member member) throws Exception {
    memberDao.update(member);
  }

  @Override
  public void delete(int no) throws Exception {
    memberDao.delete(no);
  }

  @Override
  public Member get(String email, String password) throws Exception {
    return memberDao.findByEmailAndPassword(email, password);
  }

  @Override
  public List<Member> search(String keyword) throws Exception {
    return memberDao.findByKeyword(keyword);
  }
}