package jeoneunhye.vms.handler;
// "/video/detail" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.domain.Video;

public class VideoDetailCommand implements Command {
  Prompt prompt;
  ObjectOutputStream out;
  ObjectInputStream in;

  public VideoDetailCommand(Prompt prompt, ObjectOutputStream out, ObjectInputStream in) {
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

      Video video = (Video) in.readObject();
      System.out.printf("주제: %s\n", video.getSubject());
      System.out.printf("제목: %s\n", video.getTitle());
      System.out.printf("주소: %s\n", video.getUrl());
      System.out.printf("재생시간: %s\n", video.getPlayTime());
      System.out.printf("업로더: %s\n", video.getPlayTime());
      System.out.printf("업로드 날짜: %s\n", video.getUploadDate());

    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}