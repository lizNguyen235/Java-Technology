<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Information Form</title>
    <style>
        table {
            border-collapse: collapse;
            width: 60%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: left;
        }
        td:nth-child(2) {
            color: green;
        }
        .note {
            color: red;
            font-style: italic;
        }
    </style>
</head>
<body>
    <%
        String name = (String) request.getAttribute("name");
        String email = (String) request.getAttribute("email");
        String birthday = (String) request.getAttribute("birthday");
        String birthtime = (String) request.getAttribute("birthtime");
        String gender = (String) request.getAttribute("gender");
        String country = (String) request.getAttribute("country");
        String[] ide = (String[]) request.getAttribute("ide");
        String toeic = (String) request.getAttribute("toeic");
        String introduction = (String) request.getAttribute("introduction");
    %>
        <table>
            <tr>
                <th>Full name</th>
                <% out.println("<td>" + name + "</td>"); %>
            </tr>
            <tr>
                <th>Email</th>
                <% out.println("<td>" + email + "</td>"); %>
            </tr>
            <tr>
                <th>Birthday</th>
                <% out.println("<td>" + birthday + "</td>"); %>
            </tr>
            <tr>
                <th>Birthtime</th>
                <% out.println("<td>" + birthtime + "</td>"); %>
            </tr>
            <tr>
                <th>Gender</th>
                <% out.println("<td>" + gender + "</td>"); %>
            </tr>
            <tr>
                <th>Country</th>
                <% out.println("<td>" + country + "</td>"); %>
            </tr>
            <tr>
                <th>IDE</th>
                <td><ul>
                    <%
                        for (int i=0; i<ide.length; i++) {
                            out.println("<li>" + ide[i] + "</li>");
                        }
                    %>
                </ul></td>
            </tr>
            <tr>
                <th>TOEIC</th>
                <% out.println("<td>" + toeic + "</td>"); %>
            </tr>
            <tr>
                <th>Introduction</th>
                <% out.println("<td>" + introduction + "</td>"); %>
            </tr>
        </table>
</body>
</html>