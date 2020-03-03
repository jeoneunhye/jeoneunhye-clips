package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jeoneunhye.vms.domain.Video;

public class VideoDeleteServlet implements Servlet {
  List<Video> videos;

  public VideoDeleteServlet(List<Video> videos) {
    this.videos = videos;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    int index = -1;
    for (int i = 0; i < videos.size(); i++) {
      if (videos.get(i).getNo() == no) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      videos.remove(index);
      out.writeUTF("OK");
      out.flush();

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영상이 없습니다.");
    }
  }
}