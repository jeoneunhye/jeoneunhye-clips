package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public class VideoAddServlet implements Servlet {
  List<Video> videos;

  public VideoAddServlet(List<Video> videos) {
    this.videos = videos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Video video = (Video) in.readObject();

    int i = 0;
    for (; i < videos.size(); i++) {
      if (videos.get(i).getNo() == video.getNo()) {
        break;
      }
    }

    if (i == videos.size()) {
      videos.add(video);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 영상이 있습니다.");
    }
  }
}