package jeoneunhye.vms;
import java.sql.Date;
import java.util.Scanner;

public class App3 {
  static int SIZE = 100;
  static int[] no = new int[SIZE];
  static String[] title = new String[SIZE];
  static String[] contents = new String[SIZE];
  static Date[] writeDate = new Date[SIZE];
  static int count = 0;
  static int[] viewCount = new int[SIZE];
  static public class Board {
    int no;
    String title;
    String contents;
    Date writeDate;
    int viewCount;
  }
  static Board[] boards = new Board[SIZE];

  public static void main(String[] args) {
    inputBoard();
    System.out.println();
    printBoard();
  }

  static void inputBoard() {
    Scanner keyboard = new Scanner(System.in);    

    for (int i = 0; i < SIZE; i++) {
      boards[i] = new Board();
    }
    String response;

    for(int i = 0; i < SIZE; i++) {
      Board board = boards[i];
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

      count++;
      boards[i] = board;

      System.out.println("계속 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();
  }
  static void printBoard() {
    for(int i = 0; i < count; i++) {
      Board board = boards[i];
      System.out.printf("%d, %s, %s, %s\n",
          board.no, board.title, board.contents, board.writeDate, board.viewCount);
    }
  }
}
