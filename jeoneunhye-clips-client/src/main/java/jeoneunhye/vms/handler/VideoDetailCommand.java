package jeoneunhye.vms.handler;
// "/video/detail" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoDetailCommand implements Command {
  Prompt prompt;
  VideoDao videoDao;

  public VideoDetailCommand(Prompt prompt, VideoDao videoDao) {
    this.prompt = prompt;
    this.videoDao = videoDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Video video = videoDao.findByNo(no);
      System.out.printf("주제: %s\n", video.getSubject());
      System.out.printf("제목: %s\n", video.getTitle());
      System.out.printf("주소: %s\n", video.getUrl());
      System.out.printf("재생시간: %s\n", video.getPlayTime());
      System.out.printf("업로더: %s\n", video.getPlayTime());
      System.out.printf("업로드 날짜: %s\n", video.getUploadDate());

    } catch (Exception e) {
      System.out.println("해당 영상을 조회할 수 없습니다.");
    }
  }
}