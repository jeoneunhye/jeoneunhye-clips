package jeoneunhye.vms;

import java.sql.Date;
import java.util.Scanner;

public class App {
  static Scanner keyboard = new Scanner(System.in);
  static int SIZE = 100;
  static int count = 0;
  static int[] no = new int[SIZE];
  static String[] subject = new String[SIZE];
  static String[] title = new String[SIZE];
  static Date[] uploadDate = new Date[SIZE];
  static String[] playTime = new String[SIZE];

  public static void main(String[] args) {
    inputVideo();
    System.out.println();
    printVideo();
  }

  static void inputVideo() {
    String response;
    for (int i = 0; i < SIZE; i++) {
      System.out.print("번호? ");
      no[i] = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("주제? ");
      subject[i] = keyboard.nextLine();
      System.out.print("제목? ");
      title[i] = keyboard.nextLine();
      System.out.print("업로드날짜? ");
      uploadDate[i] = Date.valueOf(keyboard.nextLine());
      System.out.print("재생시간? ");
      playTime[i] = keyboard.nextLine();

      count++;
      
      System.out.println("더 입력하시겠습니까?(Y/n)");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
    }
    keyboard.close();

  }

  static void printVideo() {
    for (int i = 0; i < count; i++) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          no[i], subject[i], title[i], uploadDate[i], playTime[i]);
    }
  }
}
