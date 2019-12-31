package jeoneunhye.vms.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String title;
  private String contents;
  private Date writeDate;
  private int viewCount;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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