package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public class VideoDetailServlet implements Servlet {
  List<Video> videos;

  public VideoDetailServlet(List<Video> videos) {
    this.videos = videos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Video video = null;
    for (Video v : videos) {
      if (v.getNo() == no) {
        video = v;
        break;
      }
    }

    if (video != null) {
      out.writeUTF("OK");
      out.writeObject(video);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영상이 없습니다.");
    }
  }
}