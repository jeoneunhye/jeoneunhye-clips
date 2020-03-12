package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberAddServlet implements Servlet {
  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Member member = new Member();

    out.println("아이디? ");
    out.println("!{}!");
    out.flush();
    member.setId(in.nextLine());

    out.println("닉네임? ");
    out.println("!{}!");
    out.flush();
    member.setNickname(in.nextLine());

    out.println("암호? ");
    out.println("!{}!");
    out.flush();
    member.setPassword(in.nextLine());

    out.println("휴대폰번호? ");
    out.println("!{}!");
    out.flush();
    member.setPhone(in.nextLine());

    out.println("이메일? ");
    out.println("!{}!");
    out.flush();
    member.setEmail(in.nextLine());

    if (memberDao.insert(member) > 0) {
      out.println("회원을 저장하였습니다.");

    } else {
      out.println("회원을 저장할 수 없습니다.");
    }
  }
}