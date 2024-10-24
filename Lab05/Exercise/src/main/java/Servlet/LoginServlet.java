package Servlet;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.User;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    public LoginServlet() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("rememberPassword");

        try {
            User user = userDAO.findById(username);
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("userID", user.getId());
                session.setAttribute("username", user.getUsername());
                if (remember != null) {
                    Cookie userIDcookie = new Cookie("userID", user.getId() + "");
                    userIDcookie.setMaxAge(30 * 60 * 60 * 24);
                    resp.addCookie(userIDcookie);
                }
                resp.sendRedirect("/add");
            } else {
                resp.sendRedirect("/register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
