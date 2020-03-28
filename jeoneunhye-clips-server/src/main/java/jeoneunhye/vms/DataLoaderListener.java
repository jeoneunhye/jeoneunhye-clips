package jeoneunhye.vms;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.sql.PlatformTransactionManager;
import jeoneunhye.sql.SqlSessionFactoryProxy;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.dao.mariadb.BoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.MemberDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoBoardDaoImpl;
import jeoneunhye.vms.dao.mariadb.PhotoFileDaoImpl;
import jeoneunhye.vms.dao.mariadb.VideoDaoImpl;
import jeoneunhye.vms.service.impl.BoardServiceImpl;
import jeoneunhye.vms.service.impl.MemberServiceImpl;
import jeoneunhye.vms.service.impl.PhotoBoardServiceImpl;
import jeoneunhye.vms.service.impl.VideoServiceImpl;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      InputStream inputStream = Resources.getResourceAsStream(
          "jeoneunhye/vms/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(inputStream));

      VideoDao videoDao =  new VideoDaoImpl(sqlSessionFactory);
      MemberDao memberDao = new MemberDaoImpl(sqlSessionFactory);
      BoardDao boardDao = new BoardDaoImpl(sqlSessionFactory);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(sqlSessionFactory);
      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(sqlSessionFactory);

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      context.put("transactionManager", txManager);

      context.put("videoService", new VideoServiceImpl(videoDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("boardService", new BoardServiceImpl(boardDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(txManager, photoBoardDao, photoFileDao));

      context.put("sqlSessionFactory", sqlSessionFactory);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}