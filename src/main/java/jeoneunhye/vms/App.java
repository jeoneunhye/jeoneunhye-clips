package jeoneunhye.vms;

import java.util.Scanner;
import jeoneunhye.vms.handler.BoardHandler;
import jeoneunhye.vms.handler.MemberHandler;
import jeoneunhye.vms.handler.VideoHandler;

public class App {
  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    VideoHandler.keyboard = keyboard;
    MemberHandler.keyboard = keyboard;
    BoardHandler.keyboard = keyboard;
    
    String command;
    do {
      command = prompt();
      switch(command) {
        case "/video/add":
          VideoHandler.addVideo();
          break;
        case "/video/list":
          VideoHandler.listVideo();
          break;
        case "/member/add":
          MemberHandler.addMember();
          break;
        case "/member/list":
          MemberHandler.listMember();
          break;
        case "/board/add":
          BoardHandler.addBoard();
          break;
        case "/board/list":
          BoardHandler.listBoard();
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
}
