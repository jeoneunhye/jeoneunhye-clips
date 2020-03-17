package jeoneunhye.vms;

import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.util.ConnectionFactory;
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

    ConnectionFactory conFactory = new ConnectionFactory(
        jdbcUrl, username, password);
    context.put("connectionFactory", conFactory);

    context.put("videoDao", new VideoDaoImpl(conFactory));
    context.put("memberDao", new MemberDaoImpl(conFactory));
    context.put("boardDao", new BoardDaoImpl(conFactory));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
    context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }
}