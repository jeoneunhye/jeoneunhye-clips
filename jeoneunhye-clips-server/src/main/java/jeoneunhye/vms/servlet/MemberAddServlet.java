package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
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
    member.setId(Prompt.getString(in, out, "아이디? "));
    member.setNickname(Prompt.getString(in, out, "닉네임? "));
    member.setPassword(Prompt.getString(in, out, "암호? "));
    member.setPhone(Prompt.getString(in, out, "휴대폰번호? "));
    member.setEmail(Prompt.getString(in, out, "이메일? "));

    if (memberDao.insert(member) > 0) {
      out.println("회원을 저장하였습니다.");

    } else {
      out.println("회원을 저장할 수 없습니다.");
    }
  }
}