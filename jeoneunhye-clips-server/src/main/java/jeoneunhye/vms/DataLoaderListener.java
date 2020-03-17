package jeoneunhye.vms;

import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.sql.DataSource;
import jeoneunhye.sql.PlatformTransactionManager;
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

    DataSource dataSource = new DataSource(
        jdbcUrl, username, password);
    context.put("dataSource", dataSource);

    context.put("videoDao", new VideoDaoImpl(dataSource));
    context.put("memberDao", new MemberDaoImpl(dataSource));
    context.put("boardDao", new BoardDaoImpl(dataSource));
    context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
    context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));

    PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
    context.put("transactionManager", txManager);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }
}