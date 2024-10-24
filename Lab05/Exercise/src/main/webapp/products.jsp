<%@ page import="model.Product" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Kiểm tra xem có yêu cầu đăng xuất không
    String action = request.getParameter("action");
    if ("logout".equals(action)) {
        // Xóa session
        session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Hủy session
        }
        // Chuyển hướng về trang đăng nhập
        response.sendRedirect("login.jsp");
        return; // Dừng việc thực hiện mã JSP sau khi chuyển hướng
    }

%>



<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            <%
                String username = (String) session.getAttribute("username"); // Lấy tên người dùng từ session
            %>
            Xin chào <span class="text-danger"><%=username%></span> | <a href="?action=logout">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" action="add">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="price">
                </div>
                <div class="form-group">
                    <button class="btn btn-success mr-2">Thêm sản phẩm</button>
                </div>

                <div class="alert alert-danger">
                    Vui lòng nhập tên sản phẩm
                </div>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
<%--                <tr>--%>
<%--                    <td>1</td>--%>
<%--                    <td><a href="#">Macbook Air M1</a></td>--%>
<%--                    <td>$1,100</td>--%>
<%--                    <td>--%>
<%--                        <a href="#">Chỉnh sửa</a> |--%>
<%--                        <a href="#">Xóa</a>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>2</td>--%>
<%--                    <td><a href="#">Macbook Pro 2020</a></td>--%>
<%--                    <td>$2,400</td>--%>
<%--                    <td>--%>
<%--                        <a href="#">Chỉnh sửa</a> |--%>
<%--                        <a href="#">Xóa</a>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                    <%--%>
<%--                        ProductDAO productDAO = new ProductDAO();--%>
<%--                        List<Product> productList = productDAO.findAll();--%>
<%--                        if (productList != null) {--%>
<%--                            for (Product product : productList) {--%>
<%--                    %>--%>
<%--                    <tr>--%>
<%--                        <td><%= product.getId() %></td>--%>
<%--                        <td><a href="#"><%= product.getName() %></a></td>--%>
<%--                        <td>$<%= product.getPrice() %></td>--%>
<%--                        <td>--%>
<%--                            <a href="delete?id=<%= product.getId() %>" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');">Xóa</a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <%--%>
<%--                        }--%>
<%--                    } else {--%>
<%--                    %>--%>
<%--                    <tr>--%>
<%--                        <td colspan="4">Không có sản phẩm nào.</td>--%>
<%--                    </tr>--%>
<%--                    <%--%>
<%--                        }--%>
<%--                    %>--%>
<!-- Đặt danh sách sản phẩm vào một biến JSTL -->
<%--&lt;%&ndash;                                ProductDAO productDAO = new ProductDAO();&ndash;%&gt;--%>
<%--&lt;%&ndash;                                List<Product> productList = productDAO.findAll();&ndash;%&gt;--%>
<%--&lt;%&ndash;                                request.setAttribute("productList", productList);&ndash;%&gt;--%>
<%--&lt;%&ndash;                                System.out.println(productList);&ndash;%&gt;--%>
<%--&lt;%&ndash;                            %>&ndash;%&gt;--%>

<%--&lt;%&ndash;                            <!-- Kiểm tra nếu danh sách sản phẩm không rỗng -->&ndash;%&gt;--%>
                            <c:if test="${not empty products}">
                                <!-- Lặp qua danh sách sản phẩm -->
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td><c:out value="${product.id}"/></td>
                                        <td><c:out value="${product.name}"/></td>
                                        <td><c:out value="${product.price}"/></td>
                                        <td>
                                            <a href="delete?id=${product.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm ${product.name} không?');">Xóa</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            <!-- Trường hợp không có sản phẩm nào -->
                            <c:if test="${empty products}">
                                <tr>
                                    <td colspan="4">Không có sản phẩm nào.</td>
                                </tr>
                            </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
