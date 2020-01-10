package jeoneunhye.vms.domain;

import java.sql.Date;

public class Video {
  private int no;
  private String subject;
  private String title;
  private Date uploadDate;
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + no;
    result = prime * result + ((playTime == null) ? 0 : playTime.hashCode());
    result = prime * result + ((subject == null) ? 0 : subject.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((uploadDate == null) ? 0 : uploadDate.hashCode());
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
    Video other = (Video) obj;
    if (no != other.no)
      return false;
    if (playTime == null) {
      if (other.playTime != null)
        return false;
    } else if (!playTime.equals(other.playTime))
      return false;
    if (subject == null) {
      if (other.subject != null)
        return false;
    } else if (!subject.equals(other.subject))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (uploadDate == null) {
      if (other.uploadDate != null)
        return false;
    } else if (!uploadDate.equals(other.uploadDate))
      return false;
    return true;
  }
  
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