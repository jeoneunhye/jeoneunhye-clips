package jeoneunhye.vms;

import java.sql.Date;
import java.util.Scanner;

public class BoardHandler {
  static class Board {
    int no;
    String title;
    String contents;
    Date writeDate;
    int viewCount;
  }
  static final int BOARD_SIZE = 100;
  static int boardCount = 0;
  static Board[] boards = new Board[BOARD_SIZE];
  static Scanner keyboard;
  
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
