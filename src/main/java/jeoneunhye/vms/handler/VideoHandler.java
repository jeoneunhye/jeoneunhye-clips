package jeoneunhye.vms.handler;

import jeoneunhye.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoHandler {
  Prompt prompt;
  List<Video> videoList;

  public VideoHandler(Prompt prompt, List<Video> list) {
    this.prompt = prompt;
    this.videoList = list;
  }

  public void addVideo() {
    Video video = new Video();

    video.setNo(prompt.inputInt("번호? "));
    video.setSubject(prompt.inputString("주제? "));
    video.setTitle(prompt.inputString("제목? "));
    video.setUploadDate(prompt.inputDate("업로드 날짜? "));
    video.setPlayTime(prompt.inputString("재생시간? "));

    this.videoList.add(video);

    System.out.println("저장하였습니다.");
  }

  public void listVideo() {
    Video[] arr = new Video[this.videoList.size()];
    this.videoList.toArray(arr);
    for (Video v : arr) {
      System.out.printf("%d, %s, %s, %s, %s\n",
          v.getNo(), v.getSubject(), v.getTitle(), v.getUploadDate(), v.getPlayTime());
    }
  }

  public void detailVideo() {
    int index = indexOfVideo(prompt.inputInt("영상 번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 영상을 찾을 수 없습니다.");
    }

    Video video = this.videoList.get(index);

    System.out.printf("주제: %s\n", video.getSubject());
    System.out.printf("제목: %s\n", video.getTitle());
    System.out.printf("업로드 날짜: %s\n", video.getUploadDate());
    System.out.printf("재생시간: %s\n", video.getPlayTime());
  }

  public void updateVideo() {
    int index = indexOfVideo(prompt.inputInt("영상 번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 영상을 찾을 수 없습니다.");
    }

    Video oldVideo = this.videoList.get(index);
    Video newVideo = new Video();

    newVideo.setNo(oldVideo.getNo());
    newVideo.setSubject(prompt.inputString(
        String.format("주제(%s)? ", oldVideo.getSubject()), oldVideo.getSubject()));
    newVideo.setTitle(prompt.inputString(
        String.format("제목(%s)? ", oldVideo.getTitle()), oldVideo.getTitle()));
    newVideo.setUploadDate(prompt.inputDate(
        String.format("업로드 날짜(%s)? ", oldVideo.getUploadDate()), oldVideo.getUploadDate()));
    newVideo.setPlayTime(prompt.inputString(
        String.format("재생시간(%s)? ", oldVideo.getPlayTime()), oldVideo.getPlayTime()));

    if (newVideo.equals(oldVideo)) {
      System.out.println("영상 정보 변경을 취소하였습니다.");
      return;
    }

    this.videoList.set(index, newVideo);

    System.out.println("영상 정보를 변경하였습니다.");
  }

  public void deleteVideo() {
    int index = indexOfVideo(prompt.inputInt("영상 번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 영상을 찾을 수 없습니다.");
    }

    this.videoList.remove(index);

    System.out.println("영상 정보를 삭제하였습니다.");
  }

  private int indexOfVideo(int no) {
    for (int i = 0; i < this.videoList.size(); i++) {
      if (this.videoList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}