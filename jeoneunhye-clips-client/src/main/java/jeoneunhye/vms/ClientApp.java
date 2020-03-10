// VMS 클라이언트
package jeoneunhye.vms;

import java.io.PrintStream;
import java.net.Socket;
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
  Scanner keyScan = new Scanner(System.in);
  Prompt prompt = new Prompt(keyScan);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  HashMap<String, Command> commandMap = new HashMap<>();

  public ClientApp() throws Exception {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() {
    while (true) {
      String command;
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0) {
        continue;

      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;

      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;

      } else if (command.equals("quit")) {
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);
    }

    keyScan.close();
  }

  private void processCommand(String command) {
    String host = null;
    int port = 9999;
    String servletPath = null;

    try {
      if (!command.startsWith("http://")) {
        throw new Exception("명령어 형식이 옳지 않습니다.");
      }

      String url = command.substring(7);

      int index = url.indexOf('/');
      String[] str =
          url.substring(0, index)
          .split(":"); // {"localhost", "9999"}

      host = str[0];
      if (str.length == 2) {
        port = Integer.parseInt(str[1]);
      }

      servletPath = url.substring(index);
      System.out.printf("=> %s\n", servletPath);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    try (Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      out.println(servletPath);
      out.flush();

      while (true) {
        String response = in.nextLine();
        if (response.equals("!end!")) {
          System.out.println("서버 연결을 종료합니다.");
          break;
        }

        System.out.println(response);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
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

  public static void main(String[] args) throws Exception {
    System.out.println("영상 관리 시스템 클라이언트입니다.");

    ClientApp app = new ClientApp();
    app.service();
  }
}