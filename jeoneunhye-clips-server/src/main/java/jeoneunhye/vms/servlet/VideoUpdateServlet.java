package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoUpdateServlet implements Servlet {
  VideoDao videoDao;

  public VideoUpdateServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Video video = (Video) in.readObject();

    if (videoDao.update(video) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영상이 없습니다.");
    }
  }
}