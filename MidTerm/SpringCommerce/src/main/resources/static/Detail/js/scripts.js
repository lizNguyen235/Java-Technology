function checkAccess(event) {
    event.preventDefault(); // Ngăn không cho chuyển hướng ngay lập tức

    // Gửi yêu cầu kiểm tra quyền truy cập
    fetch('/product')
        .then(response => {
            if (response.status === 403) {
                Swal.fire({
                    title: 'Lỗi quyền truy cập', // Tiêu đề
                    text: 'Bạn không có quyền truy cập vào trang này.',
                    icon: 'error', // Loại thông báo (có thể là 'warning', 'info', 'success', 'error')
                    confirmButtonText: 'OK'
                });
            } else {
                // Chuyển hướng nếu có quyền truy cập
                window.location.href = '/product';
            }
        })
        .catch(error => console.error('Error:', error));
}



const inputElement = document.getElementById("inputQuantity");
var inputValue = 1;
inputElement.addEventListener("input", function () {
    inputValue = inputElement.value;
});
function addToCart(itemId, maxQuantity) {
    if(inputValue > maxQuantity) {
        Swal.fire({
            title: 'Lỗi', // Tiêu đề
            text: 'Số lượng sản phẩm không đủ.', // Nội dung
            icon: 'error', // Loại thông báo (có thể là 'warning', 'info', 'success', 'error')
            confirmButtonText: 'OK' // Nút "OK"
        });
        return;
    }
// Gửi yêu cầu xóa đến server
fetch(`/cart/add/${itemId}?quantity=${inputValue}`, {
    method: 'POST'
})
    .then(response => {
        if (response.ok) {
            // Hiển thị thông báo thành công
            Swal.fire({
                title: 'Thêm Sản Phẩm Thành Công', // Tiêu đề
                text: 'Vật phẩm đã được thêm thành công vui lòng vào giỏ hàng để kiểm tra lại.',
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
                text: 'Không thể Thêm vật phẩm. Vui lòng thử lại sau.',
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