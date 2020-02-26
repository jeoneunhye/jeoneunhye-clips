// VMS 서버
package jeoneunhye.vms;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
  public static void main(String[] args) {
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
  }

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
}