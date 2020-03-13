package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.Video;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  Connection con;

  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "insert into vms_photo(titl,conts,video_id) values('"
              + photoBoard.getTitle() + "', '" + photoBoard.getContent() + "', '"
              + photoBoard.getVideo().getNo() + ")");

      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByVideoNo(int videoNo) throws Exception {
    try (
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select photo_id, titl, cdt, vw_cnt, video_id"
                + " from vms_photo"
                + " where video_id=" + videoNo
                + " order by photo_id desc")) {

      ArrayList<PhotoBoard> list = new ArrayList<>();

      while (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        list.add(photoBoard);
      }

      return list;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select"
                + " p.photo_id,"
                + " p.titl,"
                + " p.conts,"
                + " p.cdt,"
                + " p.vw_cnt,"
                + " v.video_id,"
                + " v.titl video_title"
                + " from vms_photo p"
                + " inner join vms_video v on p.video_id=v.video_id"
                + " where photo_id=" + no)) {

      if (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setContent(rs.getString("conts"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        Video video = new Video();
        video.setNo(rs.getInt("video_id"));
        video.setTitle(rs.getString("video_title"));

        photoBoard.setVideo(video);

        return photoBoard;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "update vms_photo set"
              + " titl='" + photoBoard.getTitle()
              + "', conts='" + photoBoard.getContent()
              + "' where photo_id=" + photoBoard.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "delete from vms_photo where photo_id=" + no);

      return result;
    }
  }
}