package jeoneunhye.vms;

import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.handler.BoardHandler;
import jeoneunhye.vms.handler.MemberHandler;
import jeoneunhye.vms.handler.VideoHandler;

public class App {
  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {    
    Prompt prompt = new Prompt(keyboard);
    
    VideoHandler videoHandler = new VideoHandler(prompt);
    MemberHandler memberHandler = new MemberHandler(prompt);
    BoardHandler boardHandler = new BoardHandler(prompt);

    String command;
    do {
      command = prompt();
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