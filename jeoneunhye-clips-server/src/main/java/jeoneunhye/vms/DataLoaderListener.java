package jeoneunhye.vms;

import java.util.Map;
import jeoneunhye.context.ApplicationContextListener;
import jeoneunhye.vms.dao.BoardObjectFileDao;
import jeoneunhye.vms.dao.MemberObjectFileDao;
import jeoneunhye.vms.dao.VideoObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String, Object> context) {
    VideoObjectFileDao videoDao = new VideoObjectFileDao("data/video.ser2");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("data/member.ser2");
    BoardObjectFileDao boardDao = new BoardObjectFileDao("data/board.ser2");

    context.put("videoDao", videoDao);
    context.put("memberDao", memberDao);
    context.put("boardDao", boardDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}