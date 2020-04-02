package jeoneunhye.vms;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("jeoneunhye.vms.dao")
public class MybatisConfig {
  public MybatisConfig() {
    System.out.println("MybatisConfig 객체 생성");
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx)
      throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("jeoneunhye.vms.domain");
    sqlSessionFactoryBean
    .setMapperLocations(appCtx.getResources("classpath:jeoneunhye/vms/mapper/*Mapper.xml"));

    return sqlSessionFactoryBean.getObject();
  }
}