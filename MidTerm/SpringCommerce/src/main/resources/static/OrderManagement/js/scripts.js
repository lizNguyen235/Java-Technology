function confirmOrder(button) {
    const orderId = button.getAttribute('data-id');
    const status = button.getAttribute('data-status');
    fetch(`/odermanagement/update/${orderId}?status=${status}`, {
        method: 'PUT'
    })
        .then(response => {
            if (response.ok) {
                Swal.fire({
                    title: 'Xác Nhận Thành Công',
                    text: 'Đơn hàng đã được xác nhận thành công.',
                    icon: 'success',
                    confirmButtonText: 'OK'
                })
                    .then(() => window.location.reload());
            } else {
                alert(response.status);
                Swal.fire({
                    title: 'Lỗi',
                })
            }
        });
}