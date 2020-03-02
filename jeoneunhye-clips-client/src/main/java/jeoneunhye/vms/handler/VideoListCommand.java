package jeoneunhye.vms.handler;
// "video/list" 명령어 처리
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public class VideoListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;

  public VideoListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      out.writeUTF("/video/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Video> videos = (List<Video>) in.readObject();

      for (Video v : videos) {
        System.out.printf("%d, %s, %s, %s, %s\n",
            v.getNo(), v.getSubject(), v.getTitle(), v.getPlayTime(), v.getWriter());
      }

    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}