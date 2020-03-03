package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public class VideoUpdateServlet implements Servlet {
  List<Video> videos;

  public VideoUpdateServlet(List<Video> videos) {
    this.videos = videos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Video video = (Video) in.readObject();

    int index = -1;
    for (int i = 0; i < videos.size(); i++) {
      if (videos.get(i).getNo() == video.getNo()) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      videos.set(index, video);
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영상이 없습니다.");
    }
  }
}