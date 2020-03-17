package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("insert into vms_board(titl, conts, writer)"
          + " values('" + board.getTitle() + "', '" + board.getContents() + "', '"
          + board.getWriter() + "')");

      return result;
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (
        Connection con = dataSource.getConnection();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select board_id, titl, writer, cdt, vw_cnt from vms_board")) {

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

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select board_id, titl, conts, writer, cdt, vw_cnt from vms_board"
                + " where board_id=" + no)) {

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

  @Override
  public int update(Board board) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update vms_board set"
          + " titl='" + board.getTitle() + "',"
          + " conts='" + board.getContents() + "',"
          + " writer='" + board.getWriter() + "'"
          + " where board_id=" + board.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from vms_board where board_id=" + no);

      return result;
    }
  }
}