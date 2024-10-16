package Servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

import java.io.IOException;
import java.util.List;

public class AddProductServlet extends HttpServlet {
    private final ProductDAO productDAO;

    public AddProductServlet() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        try {
            Product product = new Product(name, price);
            productDAO.save(product);
            resp.sendRedirect("/products.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override protected  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDAO.findAll();
        System.out.println("Số lượng sản phẩm: " + (products != null ? products.size() : 0));
        req.setAttribute("products", products);
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}
