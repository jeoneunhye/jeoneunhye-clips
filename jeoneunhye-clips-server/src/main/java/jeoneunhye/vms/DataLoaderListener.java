package jeoneunhye.vms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.dao.mariadb.BoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.MemberDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoBoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl;
import jeoneunhye.vms.dao.mariadb.VideoDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {
  public static Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost:3306/vmsdb", "eunhye", "1111");

    } catch (Exception e) {
      e.printStackTrace();
    }

    context.put("videoDao", new VideoDaoImpl(con));
    context.put("memberDao", new MemberDaoImpl(con));
    context.put("boardDao", new BoardDaoImpl(con));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(con));
    context.put("photoFileDao", new PhotoFileDaoImpl(con));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();

    } catch (Exception e) {}
  }
}