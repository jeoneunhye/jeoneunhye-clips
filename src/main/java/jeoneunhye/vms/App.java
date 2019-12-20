package jeoneunhye.vms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    final int VIDEO_SIZE = 100;
    final int MEMBER_SIZE = 100;
    final int BOARD_SIZE = 100;
    int videoCount = 0;
    int memberCount = 0;
    int boardCount = 0;

    class Video {
      int no;
      String subject;
      String title;
      Date uploadDate;
      String playTime;
    }
    class Member {
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
    class Board {
      int no;
      String title;
      String contents;
      Date writeDate;
      int viewCount;
    }
    Video[] videos = new Video[VIDEO_SIZE];
    Member[] members = new Member[MEMBER_SIZE];
    Board[] boards = new Board[BOARD_SIZE];

    String command;
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      switch(command) {
        case "/video/add":
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
          break;
        case "/video/list":
          for (int i = 0; i < videoCount; i++) {
            Video v = videos[i];
            System.out.printf("%d, %s, %s, %s, %s\n",
                v.no, v.subject, v.title, v.uploadDate, v.playTime);
          }
          break;
        case "/member/add":
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
          break;
        case "/member/list":
          for (int i = 0; i < memberCount; i++) {
            Member m = members[i];
            System.out.printf("%d, %s, %s, %s, 글 %d개, 댓글 %d개, %d, %s\n",
                m.no, m.name, m.email, m.grade,
                m.writeCount, m.commentCount, m.visitDateCount, m.registeredDate);
          }
          break;
        case "/board/add":
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
          break;
        case "/board/list":
          for(int i = 0; i < boardCount; i++) {
            Board b = boards[i];
            System.out.printf("%d, %s, %s, %s, %d\n",
                b.no, b.title, b.contents, b.writeDate, b.viewCount);
          }
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
}
