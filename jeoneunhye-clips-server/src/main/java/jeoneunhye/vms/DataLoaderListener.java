package jeoneunhye.vms;

import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.dao.json.BoardJsonFileDao;
import jeoneunhye.vms.dao.json.MemberJsonFileDao;
import jeoneunhye.vms.dao.json.VideoJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    context.put("videoDao", new VideoJsonFileDao("data/video.json"));
    context.put("memberDao", new MemberJsonFileDao("data/member.json"));
    context.put("boardDao", new BoardJsonFileDao("data/board.json"));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}