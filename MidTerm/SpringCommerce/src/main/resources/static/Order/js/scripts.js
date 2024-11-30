function checkDelete(Button) {
    let id = Button.dataset.id;
    let productId = Button.dataset.product;
    let statusOrder = Button.dataset.status;
    if(statusOrder !== 'Pending'){
        Swal.fire({
            title: 'Lỗi',
            text: 'Không thể xóa đơn hàng đã được xác nhận hoặc đã giao hàng.',
            icon: 'error',
            confirmButtonText: 'OK'
        });
        return;
    }
        Swal.fire({
            title: 'Xác Nhận Xóa Đơn Hàng',
            text: 'Bạn có chắc chắn muốn xóa đơn hàng này không?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Có',
            cancelButtonText: 'Không'
        }).then((result) => {
            if (result.isConfirmed) {
                fetch(`/order/delete/${id}?productId=${productId}`, {
                    method: 'DELETE'
                })
                    .then(response => {
                        if (response.ok) {
                            Swal.fire({
                                title: 'Xóa Thành Công',
                                text: 'Đơn hàng đã được xóa thành công.',
                                icon: 'success',
                                confirmButtonText: 'OK'
                            })
                            .then(() => window.location.reload());
                        } else {
                            alert(response.status);
                            Swal.fire({
                                title: 'Lỗi',
                                text: 'Không thể xóa đơn hàng. Vui lòng thử lại sau.',
                                icon: 'error',
                                confirmButtonText: 'OK'
                            });
                        }
                    })
                    .catch(error => {
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