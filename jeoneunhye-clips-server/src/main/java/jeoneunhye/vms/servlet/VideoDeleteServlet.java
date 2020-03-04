package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.VideoDao;

public class VideoDeleteServlet implements Servlet {
  VideoDao videoDao;

  public VideoDeleteServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (videoDao.delete(no) > 0) {
      out.writeUTF("OK");

    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 영상이 없습니다.");
    }
  }
}