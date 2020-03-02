package jeoneunhye.vms.handler;
// "/video/update" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoUpdateCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public VideoUpdateCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      out.writeUTF("/video/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Video oldVideo = (Video) in.readObject();

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

      out.writeUTF("/video/update");
      out.writeObject(newVideo);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("영상을 변경하였습니다.");

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}