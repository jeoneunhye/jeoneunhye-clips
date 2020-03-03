package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.VideoObjectFileDao;

public class VideoListServlet implements Servlet {
  VideoObjectFileDao videoDao;

  public VideoListServlet(VideoObjectFileDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(videoDao.findAll());
  }
}