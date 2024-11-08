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