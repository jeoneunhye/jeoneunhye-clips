package jeoneunhye.vms.domain;

import java.sql.Date;

public class Member {
  private int no;
  private String name;
  private String email;
  private String password;
  private String grade;
  private int writeCount;
  private int commentCount;
  private int visitDateCount;
  private Date registeredDate;
  private int count;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getGrade() {
    return grade;
  }
  public void setGrade(String grade) {
    this.grade = grade;
  }
  public int getWriteCount() {
    return writeCount;
  }
  public void setWriteCount(int writeCount) {
    this.writeCount = writeCount;
  }
  public int getCommentCount() {
    return commentCount;
  }
  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
  }
  public int getVisitDateCount() {
    return visitDateCount;
  }
  public void setVisitDateCount(int visitDateCount) {
    this.visitDateCount = visitDateCount;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
}