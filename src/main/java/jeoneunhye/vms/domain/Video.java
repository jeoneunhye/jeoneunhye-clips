package jeoneunhye.vms.domain;

import java.sql.Date;

public class Video {
  private int no;
  private String subject;
  private String title;
  private String url;
  private String playTime;
  private String writer;
  private Date uploadDate;

  // video.csv 파일 데이터 포맷
  // 번호,주제,제목,주소,재생시간,업로더,업로드날짜
  public static Video valueOf(String csv) {
    String[] data = csv.split(",");

    Video video = new Video();
    video.setNo(Integer.parseInt(data[0]));
    video.setSubject(data[1]);
    video.setTitle(data[2]);
    video.setUrl(data[3]);
    video.setPlayTime(data[4]);
    video.setWriter(data[5]);
    video.setUploadDate(Date.valueOf(data[6]));

    return video;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s",
        this.getNo(), this.getSubject(), this.getTitle(), this.getUrl(),
        this.getPlayTime(), this.getWriter(), this.getUploadDate());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + no;
    result = prime * result + ((subject == null) ? 0 : subject.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((writer == null) ? 0 : writer.hashCode());
    result = prime * result + ((url == null) ? 0 : url.hashCode());
    result = prime * result + ((playTime == null) ? 0 : playTime.hashCode());
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
    if (url == null) {
      if (other.url != null)
        return false;
    } else if (!url.equals(other.url))
      return false;
    if (playTime == null) {
      if (other.playTime != null)
        return false;
    } else if (!playTime.equals(other.playTime))
      return false;
    if (writer == null) {
      if (other.writer != null)
        return false;
    } else if (!writer.equals(other.writer))
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
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getPlayTime() {
    return playTime;
  }
  public void setPlayTime(String playTime) {
    this.playTime = playTime;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public Date getUploadDate() {
    return uploadDate;
  }
  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }
}