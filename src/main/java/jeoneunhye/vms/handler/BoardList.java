package jeoneunhye.vms.handler;

import java.util.Arrays;
import jeoneunhye.vms.domain.Board;

public class BoardList {
  private static final int BOARD_SIZE = 100;
  private int boardCount = 0;
  private Board[] boards;

  public BoardList() {
    this.boards = new Board[BOARD_SIZE];
  }

  public BoardList(int capacity) {
    if (capacity > BOARD_SIZE &&
        capacity < 10000) {
      this.boards = new Board[capacity];
    } else {
      this.boards = new Board[BOARD_SIZE];
    }
  }
  public void add(Board board) {
    if(this.boardCount == this.boards.length) {
      int oldCapacity = this.boards.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.boards = Arrays.copyOf(boards, newCapacity);
    }
    this.boards[this.boardCount++] = board;
  }
  
  public Board[] toArray() {
    return Arrays.copyOf(this.boards, this.boardCount);
  }
  
  public Board get(int no) {
    for (int i = 0; i < this.boardCount; i++) {
      if (this.boards[i].getNo() == no) {
        return this.boards[i];
      }
    }
    return null;
  }
}