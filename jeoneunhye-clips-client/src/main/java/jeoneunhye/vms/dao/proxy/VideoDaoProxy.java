package jeoneunhye.vms.dao.proxy;

import java.util.List;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoDaoProxy implements VideoDao {
  DaoProxyHelper daoProxyHelper;

  public VideoDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Video video) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
    out.writeUTF("/video/add");
    out.writeObject(video);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Video> findAll() throws Exception {
    return (List<Video>) daoProxyHelper.request((in, out) -> {
    out.writeUTF("/video/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      System.out.println(in.readUTF());
    }

    return (List<Video>) in.readObject();
    });
  }

  @Override
  public Video findByNo(int no) throws Exception {
    return (Video) daoProxyHelper.request((in, out) -> {
    out.writeUTF("/video/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return (Video) in.readObject();
    });
  }

  @Override
  public int update(Video video) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
    out.writeUTF("/video/update");
    out.writeObject(video);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
    out.writeUTF("/video/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }

    return 1;
    });
  }
}