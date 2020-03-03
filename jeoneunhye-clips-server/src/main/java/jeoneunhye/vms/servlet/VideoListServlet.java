package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.json.VideoJsonFileDao;

public class VideoListServlet implements Servlet {
  VideoJsonFileDao videoDao;

  public VideoListServlet(VideoJsonFileDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(videoDao.findAll());
  }
}