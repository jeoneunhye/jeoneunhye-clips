package jeoneunhye.vms.handler;
// "/video/update" 명령어 처리
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoUpdateCommand implements Command {
  Prompt prompt;
  VideoDao videoDao;

  public VideoUpdateCommand(Prompt prompt, VideoDao videoDao) {
    this.prompt = prompt;
    this.videoDao = videoDao;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Video oldVideo = null;
      try {
        oldVideo = videoDao.findByNo(no);

      } catch (Exception e) {
        System.out.println("해당 번호의 영상을 찾을 수 없습니다.");
        return;
      }

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

      if (oldVideo.equals(newVideo)) {
        System.out.println("영상 변경을 취소하였습니다.");
        return;
      }

      videoDao.update(newVideo);

      System.out.println("영상을 변경하였습니다.");

    } catch (Exception e) {
      System.out.println("영상을 변경할 수 없습니다.");
    }
  }
}