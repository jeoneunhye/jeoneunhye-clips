// VMS 서버
package jeoneunhye.vms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.domain.Video;
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

  List<Video> videos;
  List<Member> members;
  List<Board> boards;

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

  @SuppressWarnings("unchecked")
  public void service() {
    notifyApplicationInitialized();

    videos = (List<Video>) context.get("videoList");
    members = (List<Member>) context.get("memberList");
    boards = (List<Board>) context.get("boardList");

    servletMap.put("/video/add", new VideoAddServlet(videos));
    servletMap.put("/video/list", new VideoListServlet(videos));
    servletMap.put("/video/detail", new VideoDetailServlet(videos));
    servletMap.put("/video/update", new VideoUpdateServlet(videos));
    servletMap.put("/video/delete", new VideoDeleteServlet(videos));

    servletMap.put("/member/add", new MemberAddServlet(members));
    servletMap.put("/member/list", new MemberListServlet(members));
    servletMap.put("/member/detail", new MemberDetailServlet(members));
    servletMap.put("/member/update", new MemberUpdateServlet(members));
    servletMap.put("/member/delete", new MemberDeleteServlet(members));

    servletMap.put("/board/add", new BoardAddServlet(boards));
    servletMap.put("/board/list", new BoardListServlet(boards));
    servletMap.put("/board/detail", new BoardDetailServlet(boards));
    servletMap.put("/board/update", new BoardUpdateServlet(boards));
    servletMap.put("/board/delete", new BoardDeleteServlet(boards));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();

        System.out.println("클라이언트 연결 완료!");

        if (processRequest(socket) == 9) {
          break;
        }

        System.out.println("-----클라이언트가 연결을 종료하였습니다.");
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

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트> " + request);

        switch (request) {
          case "quit":
            out.writeUTF("OK");
            out.flush();
            return 0;
          case "/server/stop":
            out.writeUTF("OK");
            out.flush();
            return 9;
        }

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
      }

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