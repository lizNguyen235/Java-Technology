import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private final HashMap<String, String> accounts = new HashMap<>();

    public LoginServlet() {}

    public void init() {
        accounts.put("user", "123");
        accounts.put("mio", "0701");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            req.setAttribute("message", "Username and Password are correct");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } else {
            req.setAttribute("message", "Username or Password is incorrect");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}
