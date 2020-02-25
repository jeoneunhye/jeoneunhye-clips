package jeoneunhye.vms.handler;
// "/video/delete" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoDeleteCommand implements Command {
  Prompt prompt;
  List<Video> videoList;

  public VideoDeleteCommand(Prompt prompt, List<Video> list) {
    this.prompt = prompt;
    this.videoList = list;
  }

  @Override
  public void execute() {
    int index = indexOfVideo(prompt.inputInt("영상 번호? "));
    if (index == -1) {
      System.out.println("해당 번호의 영상을 찾을 수 없습니다.");
    }

    this.videoList.remove(index);
    System.out.println("영상을 삭제하였습니다.");
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