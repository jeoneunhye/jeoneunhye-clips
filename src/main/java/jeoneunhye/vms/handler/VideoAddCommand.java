package jeoneunhye.vms.handler;
// "/video/add" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoAddCommand implements Command {
  Prompt prompt;
  List<Video> videoList;

  public VideoAddCommand(Prompt prompt, List<Video> list) {
    this.prompt = prompt;
    this.videoList = list;
  }

  @Override
  public void execute() {
    Video video = new Video();
    video.setNo(prompt.inputInt("번호? "));
    video.setSubject(prompt.inputString("주제? "));
    video.setTitle(prompt.inputString("제목? "));
    video.setUrl(prompt.inputString("주소? "));
    video.setPlayTime(prompt.inputString("재생시간? "));
    video.setWriter(prompt.inputString("업로더? "));
    video.setUploadDate(prompt.inputDate("업로드 날짜? "));

    this.videoList.add(video);
    System.out.println("영상 정보를 저장하였습니다.");
  }
}