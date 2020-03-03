package jeoneunhye.vms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import jeoneunhye.vms.dao.json.BoardJsonFileDao;

public class BoardListServlet implements Servlet {
  BoardJsonFileDao boardDao;

  public BoardListServlet(BoardJsonFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(boardDao.findAll());
  }
}