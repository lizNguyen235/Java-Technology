import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class DownloadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");

        if (fileName == null || fileName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không tìm thầy file");
            return;
        }

        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName+"\"");

        try (InputStream imageStream = getClass().getResourceAsStream("/" + fileName)) {
            if (imageStream != null) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = imageStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }


    }
}
