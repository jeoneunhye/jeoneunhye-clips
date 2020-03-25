package jeoneunhye.vms;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.sql.PlatformTransactionManager;
import jeoneunhye.sql.SqlSessionFactoryProxy;
import jeoneunhye.vms.dao.mariadb.BoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.MemberDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoBoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl;
import jeoneunhye.vms.dao.mariadb.VideoDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      InputStream inputStream = Resources.getResourceAsStream(
          "jeoneunhye/vms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(inputStream));

      context.put("videoDao", new VideoDaoImpl(sqlSessionFactory));
      context.put("memberDao", new MemberDaoImpl(sqlSessionFactory));
      context.put("boardDao", new BoardDaoImpl(sqlSessionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(sqlSessionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      context.put("transactionManager", txManager);

      context.put("sqlSessionFactory", sqlSessionFactory);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}