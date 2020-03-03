package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.json.MemberJsonFileDao;
import jeoneunhye.vms.domain.Member;

public class MemberAddServlet implements Servlet {
  MemberJsonFileDao memberDao;

  public MemberAddServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Member member = (Member) in.readObject();

    if (memberDao.insert(member) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 회원이 있습니다.");
    }
  }
}