package jeoneunhye.vms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import jeoneunhye.vms.domain.PhotoBoard;
import jeoneunhye.vms.domain.PhotoFile;
import jeoneunhye.vms.service.PhotoBoardService;

@WebServlet("/photoboard/update")
@MultipartConfig(maxFileSize = 5000000)
public class PhotoBoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    int videoNo = 0;

    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));

      PhotoBoard photoBoard = photoBoardService.get(no);
      photoBoard.setTitle(request.getParameter("title"));
      photoBoard.setContent(request.getParameter("content"));

      List<PhotoFile> photoFiles = new ArrayList<>();

      Collection<Part> parts = request.getParts();
      String dirPath = getServletContext().getRealPath("/upload/photoboard");
      for (Part part : parts) {
        if (!part.getName().equals("photo") || part.getSize() <= 0) {
          continue;
        }

        String filename = UUID.randomUUID().toString();
        part.write(dirPath + "/" + filename);
        photoFiles.add(new PhotoFile().setFilepath(filename));
      }

      if (photoFiles.size() > 0) {
        photoBoard.setFiles(photoFiles);

      } else {
        photoBoard.setFiles(null);
      }

      videoNo = photoBoard.getVideo().getNo();

      photoBoardService.update(photoBoard);

      response.sendRedirect("list?videoNo=" + videoNo);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?videoNo=" + videoNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}