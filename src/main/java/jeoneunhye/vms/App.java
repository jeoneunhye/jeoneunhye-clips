package jeoneunhye.vms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.domain.Member;
import jeoneunhye.vms.domain.Video;
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

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    ArrayList<Video> videoList = new ArrayList<>();
    commandMap.put("/video/add", new VideoAddCommand(prompt, videoList));
    commandMap.put("/video/list", new VideoListCommand(videoList));
    commandMap.put("/video/detail", new VideoDetailCommand(prompt, videoList));
    commandMap.put("/video/update", new VideoUpdateCommand(prompt, videoList));
    commandMap.put("/video/delete", new VideoDeleteCommand(prompt, videoList));

    ArrayList<Member> memberList = new ArrayList<>();
    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));

    LinkedList<Board> boardList = new LinkedList<>();
    commandMap.put("/board/add", new BoardAddCommand(prompt, boardList));
    commandMap.put("/board/list", new BoardListCommand(boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));

    String command;
    while (true) {
      command = prompt();
      if (command.length() == 0)
        continue;

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

  static String prompt() {
    String command;
    System.out.print("\n명령> ");
    command = keyboard.nextLine();

    return command;
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
  }
}