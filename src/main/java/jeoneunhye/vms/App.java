package jeoneunhye.vms;

import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.util.Queue;
import jeoneunhye.util.Stack;
import jeoneunhye.vms.handler.BoardHandler;
import jeoneunhye.vms.handler.MemberHandler;
import jeoneunhye.vms.handler.VideoHandler;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();

  public static void main(String[] args) {    
    Prompt prompt = new Prompt(keyboard);

    VideoHandler videoHandler = new VideoHandler(prompt);
    MemberHandler memberHandler = new MemberHandler(prompt);
    BoardHandler boardHandler = new BoardHandler(prompt);

    String command;
    do {
      command = prompt();

      if (command.length() == 0)
        continue;

      commandStack.push(command);
      commandQueue.offer(command);

      switch(command) {
        case "/video/add":
          videoHandler.addVideo();
          break;
        case "/video/list":
          videoHandler.listVideo();
          break;
        case "/video/detail":
          videoHandler.detailVideo();
          break;
        case "/video/update":
          videoHandler.updateVideo();
          break;
        case "/video/delete":
          videoHandler.deleteVideo();
          break;
        case "/member/add":
          memberHandler.addMember();
          break;
        case "/member/list":
          memberHandler.listMember();
          break;
        case "/member/detail":
          memberHandler.detailMember();
          break;
        case "/member/update":
          memberHandler.updateMember();
          break;
        case "/member/delete":
          memberHandler.deleteMember();
          break;
        case "/board/add":
          boardHandler.addBoard();
          break;
        case "/board/list":
          boardHandler.listBoard();
          break;
        case "/board/detail":
          boardHandler.detailBoard();
          break;
        case "/board/update":
          boardHandler.updateBoard();
          break;
        case "/board/delete":
          boardHandler.deleteBoard();
          break;
        case "history":
          printCommandHistory();
          break;
        case "history2":
          printCommandHistory2();
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    }

    while (!command.equalsIgnoreCase("quit"));
    System.out.println("안녕!");

    keyboard.close();
  }

  static String prompt() {
    String command;
    System.out.print("\n명령> ");
    command = keyboard.nextLine();

    return command;
  }

  private static void printCommandHistory() {
    Stack<String> historyStack = commandStack.clone();

    int count = 0;

    while (!historyStack.empty()) {
      System.out.println(historyStack.pop());
      count++;

      if ((count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
  }

  private static void printCommandHistory2() {
    Queue<String> historyQueue = commandQueue.clone();

    int count = 0;

    while (historyQueue.size() > 0) {
      System.out.println(historyQueue.poll());

      if (++count % 5 == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
  }
}