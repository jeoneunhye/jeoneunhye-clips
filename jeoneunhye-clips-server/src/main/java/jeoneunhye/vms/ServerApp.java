// VMS 서버
package jeoneunhye.vms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.domain.Video;

public class ServerApp {
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

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

  void service() {
    notifyApplicationInitialized();

    List<Video> videoList = (List<Video>) context.get("videoList");
    List<Member> memberList = (List<Member>) context.get("memberList");
    List<Board> boardList = (List<Board>) context.get("boardList");

    notifyApplicationDestroyed();
  }

  public static void main(String[] args) {
    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

    /*
    try (
        ServerSocket serverSocket = new ServerSocket(9999)) {

      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트 연결 완료!");

        processRequest(socket);

        System.out.println("--------------------------------------");
      }

    } catch (Exception e) {
      System.out.println("연결 중 오류 발생!");
      return;
    }
     */
  }

  /*
  static void processRequest(Socket clientSocket) {
    try (
        Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      System.out.println("입출력 스트림 준비 완료");

      String message = in.nextLine();
      System.out.println("클라이언트> " + message);

      out.println("영상 관리 시스템 서버입니다.");
      System.out.println("클라이언트로 메시지 전송 완료");

    } catch (Exception e) {
      System.out.print("예외 발생:");
      e.printStackTrace();
    }
  }
   */
}