package jeoneunhye.vms.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoDaoProxy implements VideoDao {
  ObjectInputStream in;
  ObjectOutputStream out;

  public VideoDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Video video) throws Exception {
    out.writeUTF("/video/add");
    out.writeObject(video);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Video> findAll() throws Exception {
    out.writeUTF("/video/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      System.out.println(in.readUTF());
    }

    return (List<Video>) in.readObject();
  }

  @Override
  public Video findByNo(int no) throws Exception {
    out.writeUTF("/video/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return (Video) in.readObject();
  }

  @Override
  public int update(Video video) throws Exception {
    out.writeUTF("/video/update");
    out.writeObject(video);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/video/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
  }
}