package com.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import java.util.*;
import java.io.*;

public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = request.getParameter("page");

        PrintWriter writer = response.getWriter();
        if (page == null) {
            writer.write("<html><body><h1>Welcome to our website</h1></body></html>");
        } else {
            switch (page) {
                case "about":
                    RequestDispatcher rd1 = request.getRequestDispatcher("about.jsp");
                    rd1.forward(request, response);
                    break;
                case "contact":
                    RequestDispatcher rd2 = request.getRequestDispatcher("contact.jsp");
                    rd2.forward(request, response);
                    break;
                case "help":
                    RequestDispatcher rd3 = request.getRequestDispatcher("help.jsp");
                    rd3.forward(request, response);
                    break;
                default:
                    writer.write("<html><body><h1>Welcome to our website</h1></body></html>");
                    break;
            }
        }
    }
}
