package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jeoneunhye.sql.DataSource;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;

public class BoardDaoImpl implements BoardDao {
  DataSource dataSource;

  public BoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Board board) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "insert into vms_board(titl, conts, writer) values(?,?,?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContents());
      stmt.setString(3, board.getWriter());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "select board_id, titl, writer, cdt, vw_cnt from vms_board");

        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("titl"));
        board.setWriter(rs.getString("writer"));
        board.setWriteDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));

        list.add(board);
      }

      return list;
      }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select board_id, titl, conts, writer, cdt, vw_cnt from vms_board"
                + " where board_id=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_id"));
          board.setTitle(rs.getString("titl"));
          board.setContents(rs.getString("conts"));
          board.setWriter(rs.getString("writer"));
          board.setWriteDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));

          return board;

        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "update vms_board set titl=?, conts=?, writer=? where board_id=?")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContents());
      stmt.setString(3, board.getWriter());
      stmt.setInt(4, board.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "delete from vms_board where board_id=?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate();
    }
  }
}