// VMS 서버
package jeoneunhye.vms;

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

  public void service() {
    notifyApplicationInitialized();

    try (ServerSocket serverSocket = new ServerSocket(9999)) {

      System.out.println("클라이언트 연결 대기중...");

      while (true) {
        Socket socket = serverSocket.accept();

        System.out.println("클라이언트 연결 완료!");

        if (processRequest(socket) == 9) {
          break;
        }

        System.out.println("-----클라이언트 요청 처리 완료-----");
      }

    } catch (Exception e) {
      System.out.println("연결 중 오류 발생!");
    }

    notifyApplicationDestroyed();
  }

  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      System.out.println("입출력 스트림 준비 완료");

      while (true) {
        String request = in.readUTF();
        System.out.println("클라이언트> " + request);

        if (request.equals("quit")) {
          out.writeUTF("OK");
          out.flush();
          break;
        }

        if (request.equals("/server/stop")) {
          out.writeUTF("OK");
          out.flush();
          return 9;
        }

        List<Video> videos = (List<Video>) context.get("videoList");
        List<Member> members = (List<Member>) context.get("memberList");
        List<Board> boards = (List<Board>) context.get("boardList");

        if (request.equals("/video/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(videos);

        } else if (request.equals("/video/add")) {
          try {
            Video video = (Video) in.readObject();

            int i = 0;
            for (; i < videos.size(); i++) {
              if (videos.get(i).getNo() == video.getNo()) {
                break;
              }
            }

            if (i == videos.size()) {
              videos.add(video);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 영상이 있습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/video/detail")) {
          try {
            int no = in.readInt();

            Video video = null;
            for (Video v : videos) {
              if (v.getNo() == no) {
                video = v;
                break;
              }
            }

            if (video != null) {
              out.writeUTF("OK");
              out.writeObject(video);

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 영상이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/video/update")) {
          try {
            Video video = (Video) in.readObject();

            int index = -1;
            for (int i = 0; i < videos.size(); i++) {
              if (videos.get(i).getNo() == video.getNo()) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              videos.set(index, video);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 영상이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/video/delete")) {
          try {
            int no = in.readInt();

            int index = -1;
            for (int i = 0; i < videos.size(); i++) {
              if (videos.get(i).getNo() == no) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              videos.remove(index);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 영상이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(members);

        } else if (request.equals("/member/add")) {
          try {
            Member member = (Member) in.readObject();

            int i = 0;
            for (; i < members.size(); i++) {
              if (members.get(i).getNo() == member.getNo()) {
                break;
              }
            }

            if (i == members.size()) {
              members.add(member);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 회원이 있습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/detail")) {
          try {
            int no = in.readInt();

            Member member = null;
            for (Member m : members) {
              if (m.getNo() == no) {
                member = m;
                break;
              }
            }

            if (member != null) {
              out.writeUTF("OK");
              out.writeObject(member);

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/update")) {
          try {
            Member member = (Member) in.readObject();

            int index = -1;
            for (int i = 0; i < members.size(); i++) {
              if (members.get(i).getNo() == member.getNo()) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              members.set(index, member);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/member/delete")) {
          try {
            int no = in.readInt();

            int index = -1;
            for (int i = 0; i < members.size(); i++) {
              if (members.get(i).getNo() == no) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              members.remove(index);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 회원이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/board/list")) {
          out.writeUTF("OK");
          out.reset();
          out.writeObject(boards);

        } else if (request.equals("/board/add")) {
          try {
            Board board = (Board) in.readObject();

            int i = 0;
            for (; i < boards.size(); i++) {
              if (boards.get(i).getNo() == board.getNo()) {
                break;
              }
            }

            if (i == boards.size()) {
              boards.add(board);
              System.out.println("게시글을 저장하였습니다.");
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("같은 번호의 게시글이 있습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/board/detail")) {
          try {
            int no = in.readInt();

            Board board = null;
            for (Board b : boards) {
              if (b.getNo() == no) {
                board = b;
                break;
              }
            }

            if (board != null) {
              out.writeUTF("OK");
              out.writeObject(board);

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시글이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/board/update")) {
          try {
            Board board = (Board) in.readObject();

            int index = -1;
            for (int i = 0; i < boards.size(); i++) {
              if (boards.get(i).getNo() == board.getNo()) {
                index = i;
                break;
              }
            }

            if (index != -1) {
              boards.set(index, board);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시글이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if (request.equals("/board/delete")) {
          try {
            int no = in.readInt();

            int index = -1;

            for (int i = 0; i < boards.size(); i++) {
              if (boards.get(i).getNo() == no) {
                index = i;
                break;
              }
            }

            if (index == -1) {
              boards.remove(index);
              out.writeUTF("OK");

            } else {
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시글이 없습니다.");
            }

          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else {
          out.writeUTF("FAIL");
          out.writeUTF("요청한 명령을 처리하지 못했습니다.");
        }

        out.flush();
      }

      System.out.println("클라이언트로 메시지 전송 완료");
      return 0;

    } catch (Exception e) {
      System.out.print("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  }

  public static void main(String[] args) {
    System.out.println("영상 관리 시스템 서버입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}