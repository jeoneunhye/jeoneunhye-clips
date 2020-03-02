// VMS 클라이언트
package jeoneunhye.vms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.handler.BoardAddCommand;
import jeoneunhye.vms.handler.BoardDeleteCommand;
import jeoneunhye.vms.handler.BoardDetailCommand;
import jeoneunhye.vms.handler.BoardListCommand;
import jeoneunhye.vms.handler.BoardUpdateCommand;
import jeoneunhye.vms.handler.Command;
import jeoneunhye.vms.handler.MemberAddCommand;
import jeoneunhye.vms.handler.MemberDeleteCommand;
import jeoneunhye.vms.handler.MemberDetailCommand;
import jeoneunhye.vms.handler.MemberListCommand;
import jeoneunhye.vms.handler.MemberUpdateCommand;
import jeoneunhye.vms.handler.VideoAddCommand;
import jeoneunhye.vms.handler.VideoDeleteCommand;
import jeoneunhye.vms.handler.VideoDetailCommand;
import jeoneunhye.vms.handler.VideoListCommand;
import jeoneunhye.vms.handler.VideoUpdateCommand;

public class ClientApp {
  Scanner keyScan = new Scanner(System.in);
  Prompt prompt = new Prompt(keyScan);

  public void service() {
    String serverAddr = null;
    int port = 0;

    try {
      serverAddr = prompt.inputString("서버? ");
      port = prompt.inputInt("포트? ");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyScan.close();
      return;
    }

    try (
        Socket socket = new Socket(serverAddr, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버에 연결되었습니다.");

      processCommand(out, in);

      System.out.println("서버 연결을 종료합니다.");

    } catch (Exception e) {
      System.out.print("예외 발생:");
      e.printStackTrace();
    }

    keyScan.close();
  }

  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();
    commandMap.put("/video/list", new VideoListCommand(out, in));
    commandMap.put("/video/add", new VideoAddCommand(prompt, out, in));
    commandMap.put("/video/detail", new VideoDetailCommand(prompt, out, in));
    commandMap.put("/video/update", new VideoUpdateCommand(prompt, out, in));
    commandMap.put("/video/delete", new VideoDeleteCommand(prompt, out, in));

    commandMap.put("/member/list", new MemberListCommand(out, in));
    commandMap.put("/member/add", new MemberAddCommand(prompt, out, in));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, out, in));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, out, in));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, out, in));

    commandMap.put("/board/add", new BoardAddCommand(prompt, out, in));
    commandMap.put("/board/list", new BoardListCommand(out, in));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, out, in));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, out, in));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, out, in));

    try {
      while (true) {
        String command;
        command = prompt.inputString("\n명령> ");
        if (command.length() == 0)
          continue;

        if (command.equals("quit") || command.equals("/server/stop")) {
          out.writeUTF(command);
          out.flush();
          System.out.println("서버: " + in.readUTF());
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
    } catch (Exception e) {
      System.out.println("프로그램 실행 중 오류 발생!");
    }

    keyScan.close();
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
  }
}