function checkDelete(itemId) {
    Swal.fire({
        title: 'Xác Nhận Xóa Vật Phẩm', // Tiêu đề
        text: 'Bạn có chắc chắn muốn xóa vật phẩm này không?',
        icon: 'warning', // Loại thông báo
        showCancelButton: true, // Hiển thị nút Hủy
        confirmButtonText: 'Có',
        cancelButtonText: 'Không'
    }).then((result) => {
        if (result.isConfirmed) {
            // Gửi yêu cầu xóa đến server
            fetch(`product/delete/${itemId}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) {
                        // Hiển thị thông báo thành công
                        Swal.fire({
                            title: 'Xóa Thành Công', // Tiêu đề
                            text: 'Vật phẩm đã được xóa thành công.',
                            icon: 'success',
                            confirmButtonText: 'OK'
                        })
                        // Tải lại trang sau khi xóa
                        .then(() => window.location.reload());
                    } else {
                        // Hiển thị lỗi nếu có
                        alert(response.status);
                        Swal.fire({
                            title: 'Lỗi',
                            text: 'Không thể xóa vật phẩm. Vui lòng thử lại sau.',
                            icon: 'error',
                            confirmButtonText: 'OK'
                        });
                    }
                })
                .catch(error => {
                    // Xử lý lỗi kết nối
                    Swal.fire({
                        title: 'Lỗi',
                        text: 'Đã xảy ra lỗi kết nối. Vui lòng thử lại sau.',
                        icon: 'error',
                        confirmButtonText: 'OK'
                    });
                });
        }
    });
}

// Lấy phần tử HTML
const button = document.getElementById("edit-button");

// Lấy dữ liệu từ thuộc tính data-product
let productId = button.dataset.product;

document.getElementById("edit-form").addEventListener("submit", function(event) {
    event.preventDefault(); // Ngăn form gửi dữ liệu theo cách mặc định
    const data = new FormData(event.target);
    // Gửi dữ liệu qua Fetch API hoặc bất kỳ phương thức nào
    fetch(`/product/update/${productId}`, {
        method: 'POST',
        body: data
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        })
        .then(data => {
            console.log('Success:', data);
            Swal.fire({
                title: 'Cập Nhật Thành Công', // Tiêu đề
                text: 'Vật phẩm đã được cập nhật thành công.',
                icon: 'success', // Loại thông báo
                confirmButtonText: 'OK'
            })
            .then(() => window.location.reload());
        })
        .catch(error => {
            console.error('Error:', error);
            alert(error);
        });
});
