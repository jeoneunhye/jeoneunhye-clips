package jeoneunhye.vms.domain;

import java.sql.Date;

public class Board {
  private int no;
  private int videoNo;
  private String title;
  private String contents;
  private String writer;
  private Date writeDate;
  private int viewCount;

  // board.csv 파일 데이터 포맷
  // 번호,영상번호,제목,내용,작성자,등록일,조회수
  public static Board valueOf(String csv) {
    String[] data = csv.split(",");

    Board board = new Board();
    board.setNo(Integer.parseInt(data[0]));
    board.setVideoNo(Integer.parseInt(data[1]));
    board.setTitle(data[2]);
    board.setContents(data[3]);
    board.setWriter(data[4]);
    board.setWriteDate(Date.valueOf(data[5]));
    board.setViewCount(Integer.parseInt(data[6]));

    return board;
  }

  public String toCsvString() {
    return String.format("%d,%d,%s,%s,%s,%s,%d",
        this.getNo(), this.getVideoNo(), this.getTitle(), this.getContents(),
        this.getWriter(), this.getWriteDate(), this.getViewCount());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + no;
    result = prime * result + videoNo;
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((contents == null) ? 0 : contents.hashCode());
    result = prime * result + ((writer == null) ? 0 : writer.hashCode());
    result = prime * result + ((writeDate == null) ? 0 : writeDate.hashCode());
    result = prime * result + viewCount;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;

    Board other = (Board) obj;
    if (no != other.no)
      return false;
    if (videoNo != other.videoNo)
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (contents == null) {
      if (other.contents != null)
        return false;
    } else if (!contents.equals(other.contents))
      return false;
    if (writer == null) {
      if (other.writer != null)
        return false;
    } else if (!writer.equals(other.writer))
      return false;
    if (writeDate == null) {
      if (other.writeDate != null)
        return false;
    } else if (!writeDate.equals(other.writeDate))
      return false;
    if (viewCount != other.viewCount)
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getVideoNo() {
    return videoNo;
  }
  public void setVideoNo(int videoNo) {
    this.videoNo = videoNo;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public Date getWriteDate() {
    return writeDate;
  }
  public void setWriteDate(Date writeDate) {
    this.writeDate = writeDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
}