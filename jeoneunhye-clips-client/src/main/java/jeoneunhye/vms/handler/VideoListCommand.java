package jeoneunhye.vms.handler;
// "video/list" 명령어 처리
import java.util.List;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoListCommand implements Command {
  VideoDao videoDao;

  public VideoListCommand(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void execute() {
    try {
      List<Video> videos = videoDao.findAll();

      for (Video v : videos) {
        System.out.printf("%d, %s, %s, %s, %s\n",
            v.getNo(), v.getSubject(), v.getTitle(), v.getPlayTime(), v.getWriter());
      }

    } catch (Exception e) {
      System.out.println("영상 목록을 조회할 수 없습니다.");
    }
  }
}