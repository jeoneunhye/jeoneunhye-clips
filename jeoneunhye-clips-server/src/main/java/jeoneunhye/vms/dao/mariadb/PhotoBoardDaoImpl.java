package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jeoneunhye.sql.DataSource;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.Video;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  DataSource dataSource;

  public PhotoBoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "insert into vms_photo(titl,conts,video_id) values(?,?,?)"
                , Statement.RETURN_GENERATED_KEYS)) {
      
      stmt.setString(1, photoBoard.getTitle());
      stmt.setString(2, photoBoard.getContent());
      stmt.setInt(3, photoBoard.getVideo().getNo());

      int result = stmt.executeUpdate();

      try (ResultSet generatedKeySet = stmt.getGeneratedKeys()) {

        generatedKeySet.next();

        photoBoard.setNo(generatedKeySet.getInt(1));
      }

      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByVideoNo(int videoNo) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select photo_id, titl, cdt, vw_cnt, video_id from vms_photo"
                + " where video_id=?"
                + " order by photo_id desc")) {
      
      stmt.setInt(1, videoNo);

      try (ResultSet rs = stmt.executeQuery()) {
  
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
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
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
                + " where photo_id=?")) {
      
      stmt.setInt(1, no);

          try (ResultSet rs = stmt.executeQuery()) {
  
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
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "update vms_photo set titl=?, conts=? where photo_id=?")) {
      
      stmt.setString(1, photoBoard.getTitle());
      stmt.setString(2, photoBoard.getContent());
      stmt.setInt(3, photoBoard.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "delete from vms_photo where photo_id=?")) {
      
      stmt.setInt(1, no);

      return stmt.executeUpdate();
    }
  }
}