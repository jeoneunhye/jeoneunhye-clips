// VMS 서버
package jeoneunhye.vms;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.util.RequestHandler;
import jeoneunhye.util.RequestMappingHandlerMapping;

public class ServerApp {
  static Logger logger = LogManager.getLogger(ServerApp.class);

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();

  boolean serverStop = false;

  ApplicationContext iocContainer;

  RequestMappingHandlerMapping handlerMapper;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() {
    notifyApplicationInitialized();

    iocContainer = (ApplicationContext) context.get("iocContainer");

    handlerMapper = (RequestMappingHandlerMapping) context.get("handlerMapper");

    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      logger.info("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();

        logger.info("클라이언트 연결 완료!");

        executorService.submit(() -> {
          processRequest(socket);

          logger.info("-----클라이언트 요청 처리 완료");
        });

        logger.info("-----클라이언트가 연결을 종료하였습니다.");
        if (serverStop) {
          break;
        }
      }

    } catch (Exception e) {
      logger.error("연결 중 오류 발생!: %s" + e.getMessage());
    }

    executorService.shutdown();

    while (true) {
      if (executorService.isTerminated()) {
        break;
      }

      try {
        Thread.sleep(500);

      } catch (Exception e) {
        StringWriter strWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(strWriter));
        logger.debug(strWriter.toString());
      }
    }

    notifyApplicationDestroyed();

    logger.info("서버를 종료합니다.");
  }

  void processRequest(Socket clientSocket) {
    try (
        Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      logger.info("데이터 통신 시작");

      String request = in.nextLine();
      logger.info(String.format("=> %s\n", request));

      if (request.equalsIgnoreCase("/server/stop")) {
        serverStop = true;
        out.println("OK");
        out.println("!end!");
        out.flush();
        return;
      }

      RequestHandler requestHandler = handlerMapper.getHandler(request);
      if (requestHandler != null) {
        try {
          requestHandler.getMethod().invoke(requestHandler.getBean(),
              in, out);

        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생!");
          out.println(e.getMessage());

          logger.info("클라이언트 요청 처리 중 오류 발생:");
          StringWriter strWriter = new StringWriter();
          e.printStackTrace(new PrintWriter(strWriter));
          logger.debug(strWriter.toString());
        }

      } else {
        notFound(out);
      }

      out.println("!end!");
      out.flush();
      logger.info("클라이언트에게 응답 완료");

    } catch (Exception e) {
      logger.error("예외 발생: ");
      StringWriter strWriter = new StringWriter();
      e.printStackTrace(new PrintWriter(strWriter));
      logger.debug(strWriter.toString());
    }
  }

  private void notFound(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다.");
    logger.info("해당 명령을 지원하지 않습니다.");
  }

  public static void main(String[] args) {
    logger.info("영상 관리 시스템 서버입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();
  }
}