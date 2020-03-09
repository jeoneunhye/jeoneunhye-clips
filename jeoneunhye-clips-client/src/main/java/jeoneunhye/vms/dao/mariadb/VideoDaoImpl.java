package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoDaoImpl implements VideoDao {
  @Override
  public int insert(Video video) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/vmsdb", "eunhye", "1111");

        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "insert into vms_video(subject, titl, url, playtime, uploader, upload_dt)"
              + " values('" + video.getSubject() + "', '" + video.getTitle() + "', '"
              + video.getUrl() + "', '" + video.getPlayTime() + "', '"
              + video.getWriter() + "', '" + video.getUploadDate() + "')");

      return result;
    }
  }

  @Override
  public List<Video> findAll() throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/vmsdb", "eunhye", "1111");

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select video_id, subject, titl, playtime, uploader, upload_dt from vms_video")) {

      ArrayList<Video> list = new ArrayList<>();

      while (rs.next()) {
        Video video = new Video();
        video.setNo(rs.getInt("video_id"));
        video.setSubject(rs.getString("subject"));
        video.setTitle(rs.getString("titl"));
        video.setPlayTime(rs.getString("playtime"));
        video.setWriter(rs.getString("uploader"));
        video.setUploadDate(rs.getDate("upload_dt"));

        list.add(video);
      }

      return list;
    }
  }

  @Override
  public Video findByNo(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/vmsdb", "eunhye", "1111");

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select video_id, subject, titl, url, playtime, uploader, upload_dt from vms_video"
                + " where video_id=" + no)) {

      if (rs.next()) {
        Video video = new Video();
        video.setNo(rs.getInt("video_id"));
        video.setSubject(rs.getString("subject"));
        video.setTitle(rs.getString("titl"));
        video.setUrl(rs.getString("url"));
        video.setPlayTime(rs.getString("playtime"));
        video.setWriter(rs.getString("uploader"));
        video.setUploadDate(rs.getDate("upload_dt"));

        return video;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Video video) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/vmsdb", "eunhye", "1111");

        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("update vms_video set"
          + " subject='" + video.getSubject() + "',"
          + " titl='" + video.getTitle() + "',"
          + " url='" + video.getUrl() + "',"
          + " playtime='" + video.getPlayTime() + "',"
          + " writer='" + video.getWriter() + "',"
          + " uploadDate='" + video.getUploadDate() + "'"
          + " where video_id=" + video.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    Class.forName("org.mariadb.jdbc.Driver");

    try (
        Connection con = DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/vmsdb", "eunhye", "1111");

        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from vms_video where video_id=" + no);

      return result;
    }
  }
}