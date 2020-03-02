package jeoneunhye.vms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.vms.domain.Board;

public class ServerAppTest {
  public static void main(String[] args) {
    String serverAddr = null;
    int port = 0;

    Scanner keyScan = new Scanner(System.in);

    try {
      System.out.print("서버? ");
      serverAddr = keyScan.nextLine();

      System.out.print("포트? ");
      port = Integer.parseInt(keyScan.nextLine());

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다.");
      keyScan.close();
      return;
    }

    try (Socket socket = new Socket(serverAddr, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버에 연결되었습니다.");

      System.out.print("명령> ");
      String message = keyScan.nextLine();

      out.writeUTF(message);
      out.flush();
      System.out.println("메시지를 전송하였습니다.");

      String request = in.readUTF();
      System.out.println("서버> " + request);

      // "/board/list" 명령어 처리
      if (request.equals("OK")) {
        List<Board> boards = (List<Board>) in.readObject();
        for (Board b : boards) {
          System.out.println(b);
        }

      } else {
        System.out.println(in.readUTF());
      }

      System.out.println("서버 연결을 종료합니다.");

    } catch (Exception e) {
      System.out.print("예외 발생:");
      e.printStackTrace();
    }

    keyScan.close();
  }
}