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
import jeoneunhye.vms.dao.proxy.BoardDaoProxy;
import jeoneunhye.vms.dao.proxy.MemberDaoProxy;
import jeoneunhye.vms.dao.proxy.VideoDaoProxy;
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

  Deque<String> commandStack;
  Queue<String> commandQueue;

  String host;
  int port;

  public ClientApp() {
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() {
    try {
      host = prompt.inputString("서버? ");
      port = prompt.inputInt("포트? ");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyScan.close();
      return;
    }

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
    VideoDaoProxy videoDao = new VideoDaoProxy(host, port);
    MemberDaoProxy memberDao = new MemberDaoProxy(host, port);
    BoardDaoProxy boardDao = new BoardDaoProxy(host, port);

    HashMap<String, Command> commandMap = new HashMap<>();
    commandMap.put("/video/list", new VideoListCommand(videoDao));
    commandMap.put("/video/add", new VideoAddCommand(prompt, videoDao));
    commandMap.put("/video/detail", new VideoDetailCommand(prompt, videoDao));
    commandMap.put("/video/update", new VideoUpdateCommand(prompt, videoDao));
    commandMap.put("/video/delete", new VideoDeleteCommand(prompt, videoDao));

    commandMap.put("/member/list", new MemberListCommand(memberDao));
    commandMap.put("/member/add", new MemberAddCommand(prompt, memberDao));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberDao));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberDao));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberDao));

    commandMap.put("/board/add", new BoardAddCommand(prompt, boardDao));
    commandMap.put("/board/list", new BoardListCommand(boardDao));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, boardDao));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, boardDao));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, boardDao));

      commandMap.put("/server/stop", () -> {
        try {
        try (Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

          System.out.println("서버와 연결하였습니다.");

          out.writeUTF(command);
          out.flush();
          System.out.println("서버: " + in.readUTF());
          System.out.println("안녕!");
        }
        } catch (Exception e) {}
      });

      Command commandHandler = commandMap.get(command);
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        return;
      }

      commandHandler.execute();

      System.out.println("서버 연결을 종료합니다.");
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