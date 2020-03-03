package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.json.VideoJsonFileDao;
import jeoneunhye.vms.domain.Video;

public class VideoDetailServlet implements Servlet {
  VideoJsonFileDao videoDao;

  public VideoDetailServlet(VideoJsonFileDao videoDao) {
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