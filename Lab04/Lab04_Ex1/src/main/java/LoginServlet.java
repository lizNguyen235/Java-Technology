import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private final HashMap<String, String> accounts = new HashMap<>();

    public LoginServlet() {
        accounts.put("admin", "admin");
        accounts.put("user", "user");
    }

    public void init() {
        System.out.println("LoginServlet is initialized");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            req.setAttribute("notify", "Đăng nhập thành công");
            req.setAttribute("color", "green");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } else {
            req.setAttribute("notify", "Tài khoản hoặc mật khẩu không chính xác");
            req.setAttribute("color", "red");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}
