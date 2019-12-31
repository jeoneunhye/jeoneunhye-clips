package jeoneunhye.vms.handler;

import java.sql.Date;
import java.util.Scanner;
import jeoneunhye.vms.domain.Board;

public class BoardHandler {
  static final int BOARD_SIZE = 100;
  Scanner input;
  int boardCount = 0;
  Board[] boards;


  public BoardHandler(Scanner input) {
    this.input = input;
  }
  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    if (capacity < BOARD_SIZE || capacity < 10000) 
      this.boards = new Board[BOARD_SIZE];
    else
      this.boards = new Board[capacity];
  }

  public void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.setNo(input.nextInt());
    input.nextLine();
    System.out.print("제목? ");
    board.setTitle(input.nextLine());
    System.out.print("내용? ");
    board.setContents(input.nextLine());
    System.out.print("작성일? ");
    board.setWriteDate(Date.valueOf(input.nextLine()));
    board.setViewCount(0);

    this.boards[this.boardCount++] = board;
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    for(int i = 0; i < this.boardCount; i++) {
      Board b = this.boards[i];
      System.out.printf("%d, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getWriteDate(), b.getViewCount());
    }
  }
  public void detailBoard() {
    System.out.print("게시물 번호? ");
    int no = input.nextInt();
    input.nextLine();

    Board board = null;
    for (int i = 0; i < this.boardCount; i++) {
      if (this.boards[i].getNo() == no) {
        board = this.boards[i];
        break;
      }
    }

    if (board == null) {
      System.out.println("게시물 번호가 유효하지 않습니다.");
      return;
    }

    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getWriteDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
}