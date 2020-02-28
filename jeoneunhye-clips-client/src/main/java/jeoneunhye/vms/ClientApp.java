// VMS 클라이언트
package jeoneunhye.vms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.handler.Command;

public class ClientApp {
  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  public void service() {
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();

    String command;
    while (true) {
      command = prompt();
      if (command.length() == 0)
        continue;

      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;

      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;

      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);
      if (commandHandler != null) {
        try {
          commandHandler.execute();

        } catch (Exception e) {
          System.out.printf("명령 실행 중 오류 발생: %s\n", e.getMessage());
        }

      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }

    keyboard.close();
  }

  String prompt() {
    String command;
    System.out.print("\n명령> ");
    command = keyboard.nextLine();

    return command;
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if ((count % 5) == 0) {
        String str = prompt.inputString(":");
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("영상 관리 시스템 클라이언트입니다.");

    ClientApp app = new ClientApp();
    app.service();

    /*
    String serverAddr = null;
    int port = 0;

    Scanner keyScan = new Scanner(System.in);

    try {
      System.out.print("서버? ");
      serverAddr = keyScan.nextLine();

      System.out.print("포트? ");
      port = Integer.parseInt(keyScan.nextLine());

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyScan.close();
      return;
    }

    try (
        Socket socket = new Socket(serverAddr, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      System.out.println("서버 연결 완료!");

      System.out.print("메시지를 입력하세요: ");
      String sendMsg = keyScan.nextLine();

      out.println(sendMsg);
      System.out.println("메시지 전송을 완료했습니다.");

      String message = in.nextLine();
      System.out.println("서버> " + message);

      System.out.println("서버 연결을 종료합니다.");

    } catch (Exception e) {
      System.out.print("예외 발생:");
      e.printStackTrace();
    }

    keyScan.close();
     */
  }
}