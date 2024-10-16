package Servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RemoveProductServlet extends HttpServlet {
    private final ProductDAO productDAO;

    public RemoveProductServlet() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void service (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            productDAO.delete(id);
            resp.sendRedirect("/products.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
