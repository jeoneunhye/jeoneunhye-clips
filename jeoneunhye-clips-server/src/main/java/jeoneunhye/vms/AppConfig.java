package jeoneunhye.vms;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "jeoneunhye.vms")
public class AppConfig {
  static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("AppConfig 객체 생성");
  }
}