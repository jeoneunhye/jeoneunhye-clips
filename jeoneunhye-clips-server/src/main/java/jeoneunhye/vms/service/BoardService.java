package jeoneunhye.vms.service;

import java.util.List;
import jeoneunhye.vms.domain.Board;

public interface BoardService {
  int add(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board get(int no) throws Exception;

  int update(Board board) throws Exception;

  int delete(int no) throws Exception;
}