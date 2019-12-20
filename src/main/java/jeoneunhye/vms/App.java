package jeoneunhye.vms;

import java.sql.Date;
import java.util.Scanner;

public class App {
  static Scanner keyboard = new Scanner(System.in);

  static class Video {
    int no;
    String subject;
    String title;
    Date uploadDate;
    String playTime;
  }

  static class Member {
    int no;
    String name;
    String email;
    String password;
    String grade;
    int writeCount;
    int commentCount;
    int visitDateCount;
    Date registeredDate;
    int count;
  }

  static class Board {
    int no;
    String title;
    String contents;
    Date writeDate;
    int viewCount;
  }

  static final int VIDEO_SIZE = 100;
  static final int MEMBER_SIZE = 100;
  static final int BOARD_SIZE = 100;
  static int videoCount = 0;
  static int memberCount = 0;
  static int boardCount = 0;
  static Video[] videos = new Video[VIDEO_SIZE];
  static Member[] members = new Member[MEMBER_SIZE];
  static Board[] boards = new Board[BOARD_SIZE];

  public static void main(String[] args) {
    String command;
    do {
      command = prompt();
      switch(command) {
        case "/video/add":
          addVideo();
          break;
        case "/video/list":
          listVideo();
          break;
        case "/member/add":
          addMember();
          break;
        case "/member/list":
          listMember();
          break;
        case "/board/add":
          addBoard();
          break;
        case "/board/list":
          listBoard();
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

  static void addVideo() {
    Video video = new Video();
    System.out.print("번호? ");
    video.no = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("주제? ");
    video.subject = keyboard.nextLine();
    System.out.print("제목? ");
    video.title = keyboard.nextLine();
    System.out.print("업로드날짜? ");
    video.uploadDate = Date.valueOf(keyboard.nextLine());
    System.out.print("재생시간? ");
    video.playTime = keyboard.nextLine();
    videos[videoCount++] = video;
    System.out.println("저장하였습니다.");
  }

  static void listVideo() {
    for (int i = 0; i < videoCount; i++) {
      Video v = videos[i];
      System.out.printf("%d, %s, %s, %s, %s\n",
          v.no, v.subject, v.title, v.uploadDate, v.playTime);
    }
  }

  static void addMember() {
    Member member = new Member();

    System.out.print("번호? ");
    member.no = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("이름? ");
    member.name = keyboard.nextLine();
    System.out.print("이메일주소? ");
    member.email = keyboard.nextLine();
    System.out.print("암호? ");
    member.password = keyboard.nextLine();
    System.out.print("등급? ");
    member.grade = keyboard.nextLine();
    System.out.print("작성글 수? ");
    member.writeCount = keyboard.nextInt();
    System.out.print("작성댓글 수? ");
    member.commentCount = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("방문일? ");
    member.visitDateCount = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("가입일? ");
    member.registeredDate = Date.valueOf(keyboard.nextLine());

    members[memberCount++] = member;
    System.out.println("저장하였습니다.");
  }

  static void listMember() {
    for (int i = 0; i < memberCount; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
          m.no, m.name, m.email, m.grade,
          m.writeCount, m.commentCount, m.visitDateCount, m.registeredDate);
    }
  }

  static void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.no = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("제목? ");
    board.title = keyboard.nextLine();
    System.out.print("내용? ");
    board.contents = keyboard.nextLine();
    System.out.print("작성일? ");
    board.writeDate = Date.valueOf(keyboard.nextLine());
    board.viewCount = 0;

    boards[boardCount++] = board;
    System.out.println("저장하였습니다.");
  }

  static void listBoard() {
    for(int i = 0; i < boardCount; i++) {
      Board b = boards[i];
      System.out.printf("%d, %s, %s, %s, %d\n",
          b.no, b.title, b.contents, b.writeDate, b.viewCount);
    }
  }
}
