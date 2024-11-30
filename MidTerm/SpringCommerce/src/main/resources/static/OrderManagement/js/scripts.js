function confirmOrder(orderId) {
    alert(orderId);
    fetch(`/odermanagement/update/${orderId}`, {
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