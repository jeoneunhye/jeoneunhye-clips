package jeoneunhye.vms.handler;
// "/video/update" 명령어 처리
import java.util.List;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoUpdateCommand implements Command {
  Prompt prompt;
  List<Video> videoList;

  public VideoUpdateCommand(Prompt prompt, List<Video> list) {
    this.prompt = prompt;
    this.videoList = list;
  }

  @Override
  public void execute() {
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
    newVideo.setUrl(prompt.inputString(
        String.format("주소(%s)? ", oldVideo.getUrl()), oldVideo.getUrl()));
    newVideo.setPlayTime(prompt.inputString(
        String.format("재생시간(%s)? ", oldVideo.getPlayTime()), oldVideo.getPlayTime()));
    newVideo.setWriter(prompt.inputString(
        String.format("업로더(%s)?", oldVideo.getWriter()), oldVideo.getWriter()));
    newVideo.setUploadDate(prompt.inputDate(
        String.format("업로드 날짜(%s)? ", oldVideo.getUploadDate()), oldVideo.getUploadDate()));

    if (newVideo.equals(oldVideo)) {
      System.out.println("영상 변경을 취소하였습니다.");
      return;
    }

    this.videoList.set(index, newVideo);
    System.out.println("영상을 변경하였습니다.");
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