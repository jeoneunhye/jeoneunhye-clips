package jeoneunhye.vms.dao;

import java.util.List;
import jeoneunhye.vms.domain.Board;

public interface BoardDao {
  public int insert(Board board) throws Exception;

  public List<Board> findAll() throws Exception;

  public Board findByNo(int no) throws Exception;

  public int update(Board board) throws Exception;

  public int delete(int no) throws Exception;
}