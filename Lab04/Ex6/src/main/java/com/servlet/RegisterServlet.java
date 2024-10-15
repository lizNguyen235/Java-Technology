package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] ide = request.getParameterValues("favorite_ide[]");
        String toeic = request.getParameter("toeic");
        String introduction = request.getParameter("introduction");

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("birthday", birthday);
        request.setAttribute("birthtime", birthtime);
        request.setAttribute("gender", gender);
        request.setAttribute("country", country);
        request.setAttribute("ide", ide);
        request.setAttribute("toeic", toeic);
        request.setAttribute("introduction", introduction);

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
