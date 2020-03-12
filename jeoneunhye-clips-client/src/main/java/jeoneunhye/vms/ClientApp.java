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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  @SuppressWarnings("unused")
  private void processCommand(String command) {
    String protocol = null;
    String host = null;
    int port = 9999;
    String servletPath = null;

    try {
      Pattern[] pattern = new Pattern[2];
      pattern[0] = Pattern.compile("^([a-zA-Z]*)://([\\w\\d.]*):([0-9]{0,5})(.*)$");
      pattern[1] = Pattern.compile("^([a-zA-Z]*)://([\\w\\d.]*)(.*)$");

      Matcher matcher = null;
      for (Pattern p : pattern) {
        matcher = p.matcher(command);
        if (matcher.find())
          break;
      }

      protocol = matcher.group(1);
      host = matcher.group(2);

      if (matcher.groupCount() == 3) {
        servletPath = matcher.group(3);

      } else {
        port = Integer.parseInt(matcher.group(3));
        servletPath = matcher.group(4);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    try (
        Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      out.println(servletPath);
      out.flush();

      while (true) {
        String response = in.nextLine();
        if (response.equals("!end!")) {
          System.out.println("서버 연결을 종료합니다.");
          break;

        } else if (response.equals("!{}!")) {
          String input = prompt.inputString("");
          out.println(input);

        } else {
          System.out.println(response);
        }
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