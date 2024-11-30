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
            fetch(`cart/delete/${itemId}`, {
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

const addressElement = document.getElementById("address");
let address = addressElement.value;
addressElement.addEventListener("input", function () {
    address = addressElement.value;
});
function checkOrder(cartID) {
    Swal.fire({
        title: 'Xác Nhận Đặt Hàng', // Tiêu đề
        text: 'Bạn có chắc chắn muốn đặt hàng không?',
        icon: 'warning', // Loại thông báo
        showCancelButton: true, // Hiển thị nút Hủy
        confirmButtonText: 'Có',
        cancelButtonText: 'Không'
    }).then((result) => {
        if (result.isConfirmed) {
            // Gửi yêu cầu xóa đến server
            fetch(`/order/add?cartId=${cartID}&address=${address}`, {
                method: 'POST'
            })
                .then(response => {
                    if (response.ok) {
                        // Hiển thị thông báo thành công
                        Swal.fire({
                            title: 'Đặt Hàng Thành Công', // Tiêu đề
                            text: 'Đơn hàng đã được đặt thành công.',
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
                            text: 'Không thể đặt hàng. Vui lòng thử lại sau.',
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