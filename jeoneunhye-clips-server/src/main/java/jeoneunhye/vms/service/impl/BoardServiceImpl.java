package jeoneunhye.vms.service.impl;

import java.util.List;
import jeoneunhye.vms.dao.BoardDao;
import jeoneunhye.vms.domain.Board;
import jeoneunhye.vms.service.BoardService;

public class BoardServiceImpl implements BoardService {
  BoardDao boardDao;

  public BoardServiceImpl(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void add(Board board) throws Exception {
    boardDao.insert(board);
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.findAll();
  }

  @Override
  public Board get(int no) throws Exception {
    return boardDao.findByNo(no);
  }

  @Override
  public void update(Board board) throws Exception {
    boardDao.update(board);
  }

  @Override
  public void delete(int no) throws Exception {
    boardDao.delete(no);
  }
}