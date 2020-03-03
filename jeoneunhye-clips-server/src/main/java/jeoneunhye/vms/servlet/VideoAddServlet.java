package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.VideoObjectFileDao;
import jeoneunhye.vms.domain.Video;

public class VideoAddServlet implements Servlet {
  VideoObjectFileDao videoDao;

  public VideoAddServlet(VideoObjectFileDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Video video = (Video) in.readObject();

    if (videoDao.insert(video) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 영상이 있습니다.");
    }
  }
}