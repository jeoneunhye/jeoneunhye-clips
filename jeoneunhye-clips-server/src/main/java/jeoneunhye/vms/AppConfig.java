package jeoneunhye.vms;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "jeoneunhye.vms")
public class AppConfig {
  public AppConfig() {
    System.out.println("AppConfig 객체 생성");
  }
}