package jeoneunhye.vms.handler;

import java.sql.Date;
import jeoneunhye.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Board;

public class BoardHandler {
  List<Board> boardList;

  Prompt prompt;

  public BoardHandler(Prompt prompt, List<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  public void addBoard() {
    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("제목? "));
    board.setContents(prompt.inputString("내용? "));
    board.setWriteDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    this.boardList.add(board);

    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Board[] arr = new Board[this.boardList.size()];

    this.boardList.toArray(arr);

    for (Board b : arr) {
      System.out.printf("%d, %s, %s, %d\n",
          b.getNo(), b.getTitle(), b.getWriteDate(), b.getViewCount());
    }
  }

  public void detailBoard() {
    int index = indexOfBoard(prompt.inputInt("글번호? "));

    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }

    Board board = this.boardList.get(index);
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getWriteDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }

  public void updateBoard() {
    int index = indexOfBoard(prompt.inputInt("글번호? "));

    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }

    Board oldBoard = this.boardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(oldBoard.getNo());
    newBoard.setTitle(prompt.inputString(String.format("제목? ", oldBoard.getTitle()),
        oldBoard.getTitle()));
    newBoard.setContents(prompt.inputString(String.format("내용? ", oldBoard.getContents()),
        oldBoard.getContents()));
    newBoard.setWriteDate(oldBoard.getWriteDate());
    newBoard.setViewCount(oldBoard.getViewCount());

    if (oldBoard.equals(newBoard)) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    this.boardList.set(index, newBoard);
    System.out.println("게시글을 변경하였습니다.");
  }

  public void deleteBoard() {
    int index = indexOfBoard(prompt.inputInt("글번호? "));

    if (index == -1) {
      System.out.println("해당 게시글을 찾을 수 없습니다.");
      return;
    }

    this.boardList.remove(index);

    System.out.println("게시글을 삭제하였습니다.");
  }

  private int indexOfBoard(int no) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}