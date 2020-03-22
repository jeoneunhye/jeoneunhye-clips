package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jeoneunhye.sql.DataSource;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.domain.Member;

public class MemberDaoImpl implements MemberDao {
  DataSource dataSource;

  public MemberDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Member member) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "insert into vms_member(id, nickname, pwd, phone, email)"
                + " values(?,?,password(?),?,?)")) {

      stmt.setString(1, member.getId());
      stmt.setString(2, member.getNickname());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhone());
      stmt.setString(5, member.getEmail());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select member_id, id, phone, email, cdt from vms_member");

        ResultSet rs = stmt.executeQuery()) {

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
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select member_id, id, nickname, pwd, phone, email, cdt from vms_member"
                + " where member_id=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {

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
  }

  @Override
  public int update(Member member) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "update vms_member set"
                + " nickname=?, pwd=password(?), phone=?, email=? where member_id=?")) {

      stmt.setString(1, member.getNickname());
      stmt.setString(2, member.getPassword());
      stmt.setString(3, member.getPhone());
      stmt.setString(4, member.getEmail());
      stmt.setInt(5, member.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "delete from vms_member where member_id=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select member_id, id, phone, email, cdt from vms_member"
                + " where id like ? or phone like ? or email like ?")) {

      String value = "%" + keyword + "%";
      stmt.setString(1, value);
      stmt.setString(2, value);
      stmt.setString(3, value);

      try (ResultSet rs = stmt.executeQuery()) {

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
  
  @Override
  public Member findByEmailAndPassword(String email, String password) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select member_id, id, nickname, pwd, phone, email, cdt from vms_member"
                + " where email=? and pwd=password(?)")) {

      stmt.setString(1, email);
      stmt.setString(2, password);

      try (ResultSet rs = stmt.executeQuery()) {

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
  }
}