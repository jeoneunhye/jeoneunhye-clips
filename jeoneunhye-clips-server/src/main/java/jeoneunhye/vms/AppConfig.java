package jeoneunhye.vms;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import jeoneunhye.sql.MybatisDaoFactory;
import jeoneunhye.sql.PlatformTransactionManager;
import jeoneunhye.sql.SqlSessionFactoryProxy;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.dao.PhotoBoardDao;
import jeoneunhye.vms.dao.PhotoFileDao;
import jeoneunhye.vms.dao.VideoDao;

@ComponentScan(value = "jeoneunhye.vms")
public class AppConfig {
  @Bean
  public PlatformTransactionManager getTransactionManager(SqlSessionFactory sqlSessionFactory) {
    return new PlatformTransactionManager(sqlSessionFactory);
  }

  @Bean
  public VideoDao videoDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(VideoDao.class);
  }

  @Bean
  public MemberDao memberDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(MemberDao.class);
  }

  @Bean
  public BoardDao boardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(BoardDao.class);
  }

  @Bean
  public PhotoBoardDao photoBoardDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoBoardDao.class);
  }

  @Bean
  public PhotoFileDao photoFileDao(MybatisDaoFactory daoFactory) {
    return daoFactory.createDao(PhotoFileDao.class);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "jeoneunhye/vms/conf/mybatis-config.xml");

    return new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(inputStream));
  }

  @Bean
  public MybatisDaoFactory daoFactory(SqlSessionFactory sqlSessionFactory) {
    return new MybatisDaoFactory(sqlSessionFactory);
  }
}