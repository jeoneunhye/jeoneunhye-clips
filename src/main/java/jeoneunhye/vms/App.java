package jeoneunhye.vms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import com.google.gson.Gson;
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

  static List<Video> videoList = new ArrayList<>();
  static List<Member> memberList = new ArrayList<>();
  static List<Board> boardList = new LinkedList<>();

  public static void main(String[] args) {
    loadVideoData();
    loadMemberData();
    loadBoardData();

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/video/add", new VideoAddCommand(prompt, videoList));
    commandMap.put("/video/list", new VideoListCommand(videoList));
    commandMap.put("/video/detail", new VideoDetailCommand(prompt, videoList));
    commandMap.put("/video/update", new VideoUpdateCommand(prompt, videoList));
    commandMap.put("/video/delete", new VideoDeleteCommand(prompt, videoList));

    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));

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

    saveVideoData();
    saveMemberData();
    saveBoardData();
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

  private static void loadVideoData() {
    // File file = new File("data/video.csv");
    File file = new File("data/video.json");

    /*
    try (FileReader in = new FileReader(file);
        Scanner dataScan = new Scanner(in)) {

      while (true) {
        try {
          videoList.add(Video.valueOf(dataScan.nextLine()));

        } catch (Exception e) {
          break;
        }

        System.out.printf("총 %d개의 영상 데이터를 로딩했습니다.\n", videoList.size());
      }
     */

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      videoList.addAll(Arrays.asList(new Gson().fromJson(in, Video[].class)));

      System.out.printf("총 %d개의 영상 데이터를 로딩했습니다.\n", videoList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveVideoData() {
    // File file = new File("data/video.csv");
    File file = new File("data/video.json");

    /*
    try (FileWriter out = new FileWriter(file)) {

      for (Video video : videoList) {
        out.write(video.toCsvString() + "\n");
      }
     */

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {

      out.write(new Gson().toJson(videoList));

      System.out.printf("총 %d개의 영상 데이터를 저장했습니다.\n", videoList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadMemberData() {
    // File file = new File("data/member.csv");
    File file = new File("data/member.json");

    /*
    try (FileReader in = new FileReader(file);
        Scanner dataScan = new Scanner(in)) {

      while (true) {
        try {
          memberList.add(Member.valueOf(dataScan.nextLine()));

        } catch (Exception e) {
          break;
        }

        System.out.printf("총 %d개의 회원 데이터를 로딩했습니다.\n", memberList.size());
      }
     */

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      memberList.addAll(Arrays.asList(new Gson().fromJson(in, Member[].class)));

      System.out.printf("총 %d개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveMemberData() {
    // File file = new File("data/member.csv");
    File file = new File("data/member.json");

    /*
    try (FileWriter out = new FileWriter(file)) {

      for (Member member : memberList) {
        out.write(member.toCsvString() + "\n");
      }
     */

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {

      out.write(new Gson().toJson(memberList));

      System.out.printf("총 %d개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void loadBoardData() {
    // File file = new File("data/board.csv");
    File file = new File("data/board.json");

    /*
    try (FileReader in = new FileReader(file);
        Scanner dataScan = new Scanner(in)) {

      while (true) {
        try {
          boardList.add(Board.valueOf(dataScan.nextLine()));

        } catch (Exception e) {
          break;
        }

        System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", boardList.size());
      }
     */

    try (BufferedReader in = new BufferedReader(new FileReader(file))) {

      boardList.addAll(Arrays.asList(new Gson().fromJson(in, Board[].class)));

      System.out.printf("총 %d개의 게시글 데이터를 로딩했습니다.\n", boardList.size());

    } catch (IOException e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private static void saveBoardData() {
    // File file = new File("data/board.csv");
    File file = new File("data/board.json");

    /*
    try (FileWriter out = new FileWriter(file)) {

      for (Board board : boardList) {
        out.write(board.toCsvString() + "\n");
      }
     */

    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {

      out.write(new Gson().toJson(boardList));

      System.out.printf("총 %d개의 게시글 데이터를 저장했습니다.\n", boardList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }
}