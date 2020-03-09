package jeoneunhye.vms.handler;
// "/video/add" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoAddCommand implements Command {
  Prompt prompt;
  VideoDao videoDao;

  public VideoAddCommand(Prompt prompt, VideoDao videoDao) {
    this.prompt = prompt;
    this.videoDao = videoDao;
  }

  @Override
  public void execute() {
    Video video = new Video();
    video.setSubject(prompt.inputString("주제? "));
    video.setTitle(prompt.inputString("제목? "));
    video.setUrl(prompt.inputString("주소? "));
    video.setPlayTime(prompt.inputString("재생시간? "));
    video.setWriter(prompt.inputString("업로더? "));

    try {
      videoDao.insert(video);

      System.out.println("영상을 저장하였습니다.");

    } catch (Exception e) {
      System.out.println("영상을 저장할 수 없습니다.");
    }
  }
}