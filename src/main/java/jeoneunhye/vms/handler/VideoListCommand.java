package jeoneunhye.vms.handler;
// "video/list" 명령어 처리
import java.util.Iterator;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public class VideoListCommand implements Command {
  List<Video> videoList;

  public VideoListCommand(List<Video> list) {
    this.videoList = list;
  }

  @Override
  public void execute() {
    Iterator<Video> iterator = videoList.iterator();

    while (iterator.hasNext()) {
      Video v = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s\n",
          v.getNo(), v.getSubject(), v.getTitle(), v.getPlayTime(), v.getWriter());
    }
  }
}