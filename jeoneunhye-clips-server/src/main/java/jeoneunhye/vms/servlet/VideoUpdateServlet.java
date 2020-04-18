package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.domain.Video;
import jeoneunhye.vms.service.VideoService;

@WebServlet("/video/update")
@MultipartConfig(maxFileSize = 10000000)
public class VideoUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      VideoService videoService = iocContainer.getBean(VideoService.class);

      Video video = new Video();
      video.setNo(Integer.parseInt(request.getParameter("no")));
      video.setSubject(request.getParameter("subject"));
      video.setTitle(request.getParameter("title"));
      video.setUrl(request.getParameter("url"));
      video.setPlayTime(request.getParameter("playTime"));
      video.setWriter(request.getParameter("uploader"));
      video.setUploadDate(Date.valueOf(request.getParameter("uploadDate")));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String dirPath = getServletContext().getRealPath("/upload/video");
        String filename = UUID.randomUUID().toString();
        photoPart.write(dirPath + "/" + filename);
        video.setPhoto(filename);
      }

      if (videoService.update(video) > 0) {
        response.sendRedirect("list");

      } else {
        throw new Exception("영상 번호가 유효하지 않습니다.");
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}