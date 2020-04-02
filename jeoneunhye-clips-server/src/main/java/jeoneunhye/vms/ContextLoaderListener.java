package jeoneunhye.vms;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.util.RequestHandler;
import jeoneunhye.util.RequestMapping;
import jeoneunhye.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {
  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext(AppConfig.class);
      printBeans(iocContainer);

      context.put("iocContainer", iocContainer);

      logger.debug("-----------------------------------");

      RequestMappingHandlerMapping handlerMapper = new RequestMappingHandlerMapping();

      String[] beanNames = iocContainer.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = iocContainer.getBean(beanName);

        Method method = getRequestHandler(component.getClass());
        if (method != null) {
          RequestHandler requestHandler = new RequestHandler(method, component);

          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }

      context.put("handlerMapper", handlerMapper);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void printBeans(ApplicationContext appCtx) {
    logger.debug("Spring IoC Container에 들어있는 객체들:");
    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      logger.debug(String.format("%s ==> %s\n",
          beanName,
          appCtx.getBean(beanName).getClass().getName()));
    }
  }

  private Method getRequestHandler(Class<?> type) {
    Method[] methods = type.getMethods();
    for (Method m : methods) {
      RequestMapping anno = m.getAnnotation(RequestMapping.class);
      if (anno != null) {
        return m;
      }
    }

    return null;
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}