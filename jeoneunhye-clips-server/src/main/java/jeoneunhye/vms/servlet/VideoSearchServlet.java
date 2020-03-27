package jeoneunhye.vms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import jeoneunhye.util.Prompt;
import jeoneunhye.vms.dao.VideoDao;
import jeoneunhye.vms.domain.Video;

public class VideoSearchServlet implements Servlet {
  VideoDao videoDao;

  public VideoSearchServlet(VideoDao videoDao) {
    this.videoDao = videoDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    HashMap<String, Object> params = new HashMap<>();

    String keyword = Prompt.getString(in, out, "주제 검색: ");
    if (keyword.length() > 0) {
      params.put("subject", keyword);
    }

    keyword = Prompt.getString(in, out, "제목 검색: ");
    if (keyword.length() > 0) {
      params.put("title", keyword);
    }

    int value = Prompt.getInt(in, out, "재생시간 검색: ");
    if (value > 0) {
      params.put("playTime", value);
    }

    keyword = Prompt.getString(in, out, "업로더 검색: ");
    if (keyword.length() > 0) {
      params.put("writer", keyword);
    }

    Date date = Prompt.getDate(in, out, "업로드일 검색: ");
    if (date != null) {
      params.put("uploadDate", date);
    }

    out.println("------------------------------");
    out.println("[검색 결과]");
    out.println();

    List<Video> videos = videoDao.findByKeyword(params);
    for (Video v : videos) {
      out.printf("%d, %s, %s, %s, %s, %s\n", v.getNo(), v.getSubject(), v.getTitle(),
          v.getPlayTime(), v.getWriter(), v.getUploadDate());
    }
  }
}