package jeoneunhye.vms;

import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.dao.mariadb.BoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.MemberDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoBoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl;
import jeoneunhye.vms.dao.mariadb.VideoDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    String jdbcUrl = "jdbc:mariadb://localhost:3306/vmsdb";
    String username = "eunhye";
    String password = "1111";

    context.put("videoDao", new VideoDaoImpl(jdbcUrl, username, password));
    context.put("memberDao", new MemberDaoImpl(jdbcUrl, username, password));
    context.put("boardDao", new BoardDaoImpl(jdbcUrl, username, password));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(jdbcUrl, username, password));
    context.put("photoFileDao", new PhotoFileDaoImpl(jdbcUrl, username, password));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }
}