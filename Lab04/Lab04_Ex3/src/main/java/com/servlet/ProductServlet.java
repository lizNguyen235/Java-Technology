package com.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class ProductServlet extends HttpServlet {
    Response res;
    List<Product> list = new ArrayList<>();
    Gson gson = new Gson();

    public void init() {
        list.add(new Product(1, "phone", 1000000));
        list.add(new Product(2, "laptop", 15000000));
        list.add(new Product(3, "tablet", 8000000));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url_id = request.getParameter("id");
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        if(url_id==null || url_id.isEmpty()) {
            res = new Response(0, "Đọc tất cả sản phẩm thành công!", list);
            writer.write(gson.toJson(res));
        }
        else {
            for (Product product : list) {
                if (Integer.parseInt(url_id)==(product.getId())) {
                    res = new Response(0, "Đọc sản phẩm thành công!", product);
                    writer.write(gson.toJson(res));
                    return;
                }
            }
            res = new Response(1, "Không tìm thấy sản phẩm!", null);
            writer.write(gson.toJson(res));
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String post_id = request.getParameter("id");
        String post_name = request.getParameter("name");
        int post_price = Integer.parseInt(request.getParameter("price"));
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        for (Product product : list) {
            if (Integer.parseInt(post_id)==(product.getId())) {
                res = new Response(1, "Id sản phẩm đã tồn tại!", null);
                writer.write(gson.toJson(res));
                return;
            }
        }
        Product product = new Product(Integer.parseInt(post_id), post_name, post_price);
        list.add(product);
        res = new Response(0, "Thêm sản phẩm thành công!", null);
        writer.write(gson.toJson(res));
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String put_id = request.getParameter("id");
        String put_name = request.getParameter("name");
        int put_price = Integer.parseInt(request.getParameter("price"));
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        for (Product product : list) {
            if (Integer.parseInt(put_id)==(product.getId())) {
                product.setName(put_name);
                product.setPrice(put_price);
                res = new Response(0, "Cập nhật sản phẩm thành công!", null);
                writer.write(gson.toJson(res));
                return;
            }
        }
        res = new Response(1, "Id sản phẩm không tồn tại!", null);
        writer.write(gson.toJson(res));
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String post_id = request.getParameter("id");
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        for (Product product : list) {
            if (Integer.parseInt(post_id)==(product.getId())) {
                list.remove(product);
                res = new Response(1, "Xóa sản phẩm thành công!", null);
                writer.write(gson.toJson(res));
                return;
            }
        }
        res = new Response(1, "Id sản phẩm không tồn tại!", null);
        writer.write(gson.toJson(res));
    }

}
