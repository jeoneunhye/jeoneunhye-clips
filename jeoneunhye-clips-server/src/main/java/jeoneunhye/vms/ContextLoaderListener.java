package jeoneunhye.vms;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.sql.MybatisDaoFactory;
import jeoneunhye.sql.PlatformTransactionManager;
import jeoneunhye.sql.SqlSessionFactoryProxy;
import jeoneunhye.util.ApplicationContext;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.dao.VideoDao;

public class ContextLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      HashMap<String, Object> beans = new HashMap<>();

      InputStream inputStream = Resources.getResourceAsStream(
          "jeoneunhye/vms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(inputStream));
      beans.put("sqlSessionFactory", sqlSessionFactory);

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      beans.put("videoDao", daoFactory.createDao(VideoDao.class));
      beans.put("memberDao", daoFactory.createDao(MemberDao.class));
      beans.put("boardDao", daoFactory.createDao(BoardDao.class));
      beans.put("photoBoardDao", daoFactory.createDao(PhotoBoardDao.class));
      beans.put("photoFileDao", daoFactory.createDao(PhotoFileDao.class));

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      beans.put("transactionManager", txManager);

      ApplicationContext appCtx = new ApplicationContext("jeoneunhye.vms", beans);
      appCtx.printBeans();

      context.put("iocContainer", appCtx);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}