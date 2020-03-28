package jeoneunhye.vms.service;

import java.util.List;
import jeoneunhye.vms.domain.Board;

public interface BoardService {
  void add(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board get(int no) throws Exception;

  void update(Board board) throws Exception;

  void delete(int no) throws Exception;
}