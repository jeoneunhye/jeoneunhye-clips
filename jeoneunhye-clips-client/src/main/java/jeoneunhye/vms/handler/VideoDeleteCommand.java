package jeoneunhye.vms.handler;
// "/video/delete" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.VideoDao;

public class VideoDeleteCommand implements Command {
  Prompt prompt;
  VideoDao videoDao;

  public VideoDeleteCommand(Prompt prompt, VideoDao videoDao) {
    this.prompt = prompt;
    this.videoDao = videoDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      if (videoDao.delete(no) > 0) {
        System.out.println("영상을 삭제하였습니다.");

      } else {
        System.out.println("해당 번호의 영상이 없습니다.");
      }

    } catch (Exception e) {
      System.out.println("영상을 삭제할 수 없습니다.");
    }
  }
}