// VMS 서버
package jeoneunhye.vms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.dao.MemberDao;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.servlet.BoardAddServlet;
import jeoneunhye.vms.servlet.BoardDeleteServlet;
import jeoneunhye.vms.servlet.BoardDetailServlet;
import jeoneunhye.vms.servlet.BoardListServlet;
import jeoneunhye.vms.servlet.BoardUpdateServlet;
import jeoneunhye.vms.servlet.MemberAddServlet;
import jeoneunhye.vms.servlet.MemberDeleteServlet;
import jeoneunhye.vms.servlet.MemberDetailServlet;
import jeoneunhye.vms.servlet.MemberListServlet;
import jeoneunhye.vms.servlet.MemberUpdateServlet;
import jeoneunhye.vms.servlet.Servlet;
import jeoneunhye.vms.servlet.VideoAddServlet;
import jeoneunhye.vms.servlet.VideoDeleteServlet;
import jeoneunhye.vms.servlet.VideoDetailServlet;
import jeoneunhye.vms.servlet.VideoListServlet;
import jeoneunhye.vms.servlet.VideoUpdateServlet;

public class ServerApp {
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  Map<String, Servlet> servletMap = new HashMap<>();

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

    VideoDao videoDao = (VideoDao) context.get("videoDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");
    BoardDao boardDao = (BoardDao) context.get("boardDao");

    servletMap.put("/video/add", new VideoAddServlet(videoDao));
    servletMap.put("/video/list", new VideoListServlet(videoDao));
    servletMap.put("/video/detail", new VideoDetailServlet(videoDao));
    servletMap.put("/video/update", new VideoUpdateServlet(videoDao));
    servletMap.put("/video/delete", new VideoDeleteServlet(videoDao));

    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));

    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();

        System.out.println("클라이언트 연결 완료!");

        new Thread(() -> {
          processRequest(socket);
          System.out.println();
        }).start();

        System.out.println("-----클라이언트와의 연결을 종료하였습니다.");
      }

    } catch (Exception e) {
      System.out.println("연결 중 오류 발생!");
    }

    notifyApplicationDestroyed();
  }

  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      System.out.println("클라이언트와 데이터 통신 시작");

        String request = in.readUTF();
        System.out.println("클라이언트> " + request);

        Servlet servlet = servletMap.get(request);
        if (servlet != null) {
          try {
            servlet.service(in, out);

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());

            System.out.print("클라이언트 요청 처리 중 오류 발생: ");
            e.printStackTrace();
          }

        } else {
          notFound(out);
        }

        out.flush();
        System.out.println("클라이언트에게 응답 완료");

        return 0;

    } catch (Exception e) {
      System.out.print("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  public static void main(String[] args) {
    System.out.println("영상 관리 시스템 서버입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}