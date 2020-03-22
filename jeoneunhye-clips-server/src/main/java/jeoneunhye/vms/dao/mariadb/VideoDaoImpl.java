package jeoneunhye.vms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jeoneunhye.sql.DataSource;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoDaoImpl implements VideoDao {
  DataSource dataSource;

  public VideoDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Video video) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "insert into vms_video(subject, titl, url, playtime, uploader, upload_dt)"
                + " values(?,?,?,?,?,?)")) {
      
      stmt.setString(1, video.getSubject());
      stmt.setString(2, video.getTitle());
      stmt.setString(3, video.getUrl());
      stmt.setString(4, video.getPlayTime());
      stmt.setString(5, video.getWriter());
      stmt.setDate(6, video.getUploadDate());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Video> findAll() throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select video_id, subject, titl, playtime, uploader, upload_dt from vms_video");

        ResultSet rs = stmt.executeQuery()) {

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
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "select video_id, subject, titl, url, playtime, uploader, upload_dt from vms_video"
                + " where video_id=?")) {
        
        stmt.setInt(1, no);

        try (ResultSet rs = stmt.executeQuery()) {

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
  }

  @Override
  public int update(Video video) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "update vms_video set"
                + " subject=?, titl=?, url=?, playtime=?, uploader=?, upload_dt=?"
                + " where video_id=?")) {
      
      stmt.setString(1, video.getSubject());
      stmt.setString(2, video.getTitle());
      stmt.setString(3, video.getUrl());
      stmt.setString(4, video.getPlayTime());
      stmt.setString(5, video.getWriter());
      stmt.setDate(6, video.getUploadDate());
      stmt.setInt(7, video.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (
        Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "delete from vms_video where video_id=?")) {
      
      stmt.setInt(1, no);

      return stmt.executeUpdate();
    }
  }
}