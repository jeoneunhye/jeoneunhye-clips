package jeoneunhye.vms.domain;

import java.sql.Date;

public class Video {
  private int no;
  private String subject;
  private String title;
  private Date uploadDate;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Date getUploadDate() {
    return uploadDate;
  }
  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }
  public String getPlayTime() {
    return playTime;
  }
  public void setPlayTime(String playTime) {
    this.playTime = playTime;
  }
  private String playTime;
}