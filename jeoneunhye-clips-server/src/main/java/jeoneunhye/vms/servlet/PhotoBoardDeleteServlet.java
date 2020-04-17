package jeoneunhye.vms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import jeoneunhye.vms.service.PhotoBoardService;

@WebServlet("/photoboard/delete")
public class PhotoBoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int videoNo = Integer.parseInt(request.getParameter("videoNo"));
    int no = Integer.parseInt(request.getParameter("no"));

    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      photoBoardService.delete(no);

      response.sendRedirect("list?videoNo=" + videoNo);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?videoNo=" + videoNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}