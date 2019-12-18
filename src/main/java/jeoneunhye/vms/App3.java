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
  
  public static void main(String[] args) {
    inputBoard();
    System.out.println();
    printBoard();
    }
  
  static void inputBoard() {
    Scanner keyboard = new Scanner(System.in);
    String response;
    for(int i = 0; i < SIZE; i++) {
    System.out.print("번호? ");
    no[i] = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("제목? ");
    title[i] = keyboard.nextLine();
    System.out.print("내용? ");
    contents[i] = keyboard.nextLine();
    System.out.print("작성일? ");
    writeDate[i] = Date.valueOf(keyboard.nextLine());
    viewCount[i] = 0;
    
    count++;
    
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
  System.out.printf("%d, %s, %s, %s\n",
      no[i], title[i], contents[i], writeDate[i], viewCount[i]);
  }
}
}
