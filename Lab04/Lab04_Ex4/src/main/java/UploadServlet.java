
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import java.util.*;

@MultipartConfig
public class UploadServlet extends HttpServlet {

    private final String[] list = {"txt", "doc" ,"docx", "img", "pdf", "rar", "zip"};
    private final ArrayList<String> extensions = new ArrayList<String>(Arrays.asList(list));

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");





        String createFileName = request.getParameter("name");
        Part PartFile = request.getPart("document");
        String uploadedFileName = PartFile.getSubmittedFileName();
        boolean isOverride = "on".equals(request.getParameter("override"));




        PrintWriter writer = response.getWriter();

        //Tao folder uploads.
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }






        String[] tmp = uploadedFileName.split("\\.");
        if(!extensions.contains(tmp[tmp.length-1])) {
            writer.write("<html><body><h4>Unsupported file extension<h4></body></html>");
            return;
        }





        //Lay file tu request
        for (Part part : request.getParts()) {
            if (part.getContentType() != null) {
                String oldFileName = part.getSubmittedFileName();

                if (oldFileName != null && !oldFileName.isEmpty()) {
                    File file = new File(uploadDir, createFileName + "." + tmp[tmp.length-1]);

                    if (!isOverride && file.exists()) {
                        writer.write("<html><body><h4>File already exists</h4></body></html>");
                        return;
                    } else {
                        part.write(file.getAbsolutePath());
                        writer.write("<html><body><h3>Tải file lên thành công</h3></body></html>");
                    }
                } else {
                    writer.write("<html><body><h3>Không file nào được chọn</h3></body></html>");
                }
            }
        }
    }
}
