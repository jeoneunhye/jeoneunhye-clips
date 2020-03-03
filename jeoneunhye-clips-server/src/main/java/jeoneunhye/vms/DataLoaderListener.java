package jeoneunhye.vms;

import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.dao.json.BoardJsonFileDao;
import jeoneunhye.vms.dao.json.MemberJsonFileDao;
import jeoneunhye.vms.dao.json.VideoJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    VideoJsonFileDao videoDao = new VideoJsonFileDao("data/video.json");
    MemberJsonFileDao memberDao = new MemberJsonFileDao("data/member.json");
    BoardJsonFileDao boardDao = new BoardJsonFileDao("data/board.json");

    context.put("videoDao", videoDao);
    context.put("memberDao", memberDao);
    context.put("boardDao", boardDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}