package jeoneunhye.vms.handler;
// "/video/detail" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoDetailCommand implements Command {
  Prompt prompt;
  List<Video> videoList;

  public VideoDetailCommand(Prompt prompt, List<Video> list) {
    this.prompt = prompt;
    this.videoList = list;
  }

  @Override
  public void execute() {
    int index = indexOfVideo(prompt.inputInt("영상 번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 영상을 찾을 수 없습니다.");
    }

    Video video = this.videoList.get(index);
    System.out.printf("주제: %s\n", video.getSubject());
    System.out.printf("제목: %s\n", video.getTitle());
    System.out.printf("주소: %s\n", video.getUrl());
    System.out.printf("재생시간: %s\n", video.getPlayTime());
    System.out.printf("업로더: %s\n", video.getPlayTime());
    System.out.printf("업로드 날짜: %s\n", video.getUploadDate());
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