
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

        String newFileName = request.getParameter("name");
        Part filePart = request.getPart("document");
        String uploadedFileName = filePart.getSubmittedFileName();
        boolean override = "on".equals(request.getParameter("override"));

        PrintWriter writer = response.getWriter();

        //Tao folder uploads.
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        //Kiem tra duoi tap tin
        String[] x = uploadedFileName.split("\\.");
        if(!extensions.contains(x[x.length-1])) {
            writer.write("<html><body><h4>Unsupported file extension<h4></body></html>");
            return;
        }
        //Lay file tu request
        for (Part part : request.getParts()) {
            if (part.getContentType() != null) {
                String oldFileName = part.getSubmittedFileName();

                if (oldFileName != null && !oldFileName.isEmpty()) {
                    File file = new File(uploadDir, newFileName + "." + x[x.length-1]);

                    if (!override && file.exists()) {
                        writer.write("<html><body><h4>File already exists.</h4></body></html>");
                        return;
                    } else {
                        part.write(file.getAbsolutePath());
                        writer.write("<html><body><h4>File uploaded successfully.</h4></body></html>");
                    }
                } else {
                    writer.write("<html><body><h4>No file selected.</h4></body></html>");
                }
            }
        }
    }
}
