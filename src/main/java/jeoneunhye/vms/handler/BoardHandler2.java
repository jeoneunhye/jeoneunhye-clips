package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.domain.Board;

public class BoardHandler2 {

  static final int BOARD_SIZE = 100;
  static int boardCount = 0;
  static Board[] boards = new Board[BOARD_SIZE];
  public static Scanner keyboard;
  
  public static void addBoard() {
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

  public static void listBoard() {
    for(int i = 0; i < boardCount; i++) {
      Board b = boards[i];
      System.out.printf("%d, %s, %s, %d\n",
          b.no, b.title, b.writeDate, b.viewCount);
    }
  }
  public static void detailBoard() {
    System.out.print("게시물 번호? ");
    int no = keyboard.nextInt();
    keyboard.nextLine();

    Board board = null;
    for (int i = 0; i < boardCount; i++) {
      if (boards[i].no == no) {
        board = boards[i];
        break;
      }
    }
    
    if (board == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }
    
    System.out.printf("번호: %d\n", board.no);
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.contents);
    System.out.printf("작성일: %s\n", board.writeDate);
    System.out.printf("조회수: %d\n", board.viewCount);
  }
}
