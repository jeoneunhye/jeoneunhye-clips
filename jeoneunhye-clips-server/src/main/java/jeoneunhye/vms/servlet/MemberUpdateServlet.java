package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberUpdateServlet implements Servlet {
  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Member oldMember = memberDao.findByNo(no);
    if (oldMember == null) {
      out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member newMember = new Member();
    newMember.setNo(no);
    newMember.setId(oldMember.getId());

    out.printf("닉네임(%s)? \n", oldMember.getNickname());
    out.println("!{}!");
    out.flush();
    newMember.setNickname(in.nextLine());

    out.printf("암호(%s)? \n", oldMember.getPassword());
    out.println("!{}!");
    out.flush();
    newMember.setPassword(in.nextLine());

    out.printf("휴대폰번호(%s)? \n", oldMember.getPhone());
    out.println("!{}!");
    out.flush();
    newMember.setPhone(in.nextLine());

    out.printf("이메일(%s)? \n", oldMember.getEmail());
    out.println("!{}!");
    out.flush();
    newMember.setEmail(in.nextLine());

    newMember.setRegisteredDate(oldMember.getRegisteredDate());

    if (memberDao.update(newMember) > 0) {
      out.println("회원을 변경하였습니다.");

    } else {
      out.println("회원을 변경할 수 없습니다.");
    }
  }
}