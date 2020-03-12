package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberDaoImpl implements MemberDao {
  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("insert into vms_member(id, nickname, pwd, phone, email)"
          + " values('" + member.getId() + "', '" + member.getNickname() + "', '"
          + member.getPassword() + "', '" + member.getPhone() + "', '" + member.getEmail() + "')");

      return result;
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select member_id, id, phone, email, cdt from vms_member")) {

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setId(rs.getString("id"));
        member.setPhone(rs.getString("phone"));
        member.setEmail(rs.getString("email"));
        member.setRegisteredDate(rs.getDate("cdt"));

        list.add(member);
      }

      return list;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select member_id, id, nickname, pwd, phone, email, cdt from vms_member"
                + " where member_id=" + no)) {

      if (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setId(rs.getString("id"));
        member.setNickname(rs.getString("nickname"));
        member.setPassword(rs.getString("pwd"));
        member.setPhone(rs.getString("phone"));
        member.setEmail(rs.getString("email"));
        member.setRegisteredDate(rs.getDate("cdt"));

        return member;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update vms_member set"
          + " nickname='" + member.getNickname() + "',"
          + " pwd='" + member.getPassword() + "',"
          + " phone='" + member.getPhone() + "',"
          + " email='" + member.getEmail() + "'"
          + " where member_id=" + member.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from vms_member where member_id=" + no);

      return result;
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select member_id, id, phone, email, cdt from vms_member"
                + " where id like '%" + keyword + "%'"
                + " or phone like '%" + keyword + "%'"
                + " or email like '%" + keyword + "%'")) {

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setId(rs.getString("id"));
        member.setPhone(rs.getString("phone"));
        member.setEmail(rs.getString("email"));
        member.setRegisteredDate(rs.getDate("cdt"));

        list.add(member);
      }

      return list;
    }
  }
}