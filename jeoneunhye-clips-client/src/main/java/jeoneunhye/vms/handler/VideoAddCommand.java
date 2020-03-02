package jeoneunhye.vms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoAddCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public VideoAddCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
    this.prompt = prompt;
    this.out = out;
    this.in = in;
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

    try {
      out.writeUTF("/video/add");
      out.writeObject(video);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("영상을 저장하였습니다.");

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}