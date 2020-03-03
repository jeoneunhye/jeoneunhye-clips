package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.VideoObjectFileDao;
import jeoneunhye.vms.domain.Video;

public class VideoDetailServlet implements Servlet {
  VideoObjectFileDao videoDao;

  public VideoDetailServlet(VideoObjectFileDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Video video = videoDao.findByNo(no);
    if (video != null) {
      out.writeUTF("OK");
      out.writeObject(video);

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영상이 없습니다.");
    }
  }
}