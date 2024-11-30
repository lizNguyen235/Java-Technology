Nguyên Tắc Phát Triển Phần Mềm:
Trong quá trình phát triển, em đã áp dụng các nguyên tắc sau đây:
	- Single Responsibility Principle: Mỗi một hàm chỉ có duy nhất một nhiệm vụ
	- DRY (Don’t Repeat Yourself): Mỗi nghiệp vụ chỉ được định nghĩa một lần trong hệ thống
	- Separation of Concerns (SoC): Em chia nhỏ các thành phần của dự án thành nhiều lớp, module khác nhau
	- KISS (Keep It Simple, Stupid): Trong quá trình viết, em đã cố gắng đơn giản nhất có thể, tránh rườm rà

Kiến Trúc Hệ Thống:
Trong quá trình phát triển hệ thống, em đã áp dụng mô hình MVC để thiết kế hệ thống và áp dụng nó vào thực tế

Cấu Trúc Code:
Cấu trúc code của em được chia làm 7 phần tương ứng với 7 package khác nhau:
1) Config: Ở trong package này chứa những class để cấu hình hệ thống, bao gồm 2 class chính:
	- MySercurityConfig.class: Dùng để cấu hình Serurity ứng dụng của Spring Sercurity vào hệ thống
	- WebConfig: Để cấu hình những phần khác trong ứng dụng web
2) Controller: Đây là package chứa các yêu cầu của người dùng chứa các class là các controller tương ứng với từng nhóm chức năng
3) Service: Đây là package chứa tất cả các class dùng để xử lí nghiệp vụ của các nhóm chức năng
4) Repository: Đây là package chứa các interface có nhiệm vụ giao tiếp với cơ sở dữ liệu, thực hiện truy vấn dữ liệu
5) Model: Đây là package chứa các lớp data, nó là lớp chứa thông tin của dữ liệu tạm thời trước khi được lưu trữ xuống database
6) DTO: tương tự như Model, nhưng package chứa thông tin rút gọn hơn của Model, giúp cho việc xử lí logic trở nên thuận tiện hơn
7) Exception: Đây là package chứa các lớp để xử lí ngoại lệ không mong muốn
Ngoài ra còn một số class khác phục vụ cho việc kiểm thứ đơn vị

Các bước cài đặt để hệ thống chạy trên Local:
Bước 1: Cài đặt Docker destop và cấu hình cơ sở dữ liệu sử dụng postgres
Bước 2: Cài đặt SQL editer ví dụ như DBeaver để thuận tiện cho việc truy vấn dữ liệu
Bước 3: Bật mã nguồn bật file application.properties để cấu hình các thông tin sau:
- spring.datasource.url:
- spring.datasource.username
- spring.datasource.password
Bước 4: Vào file WebConfig chỉnh sửa lại đường dẫn đến mã nguồn:
file:C:\Lit\Programming\java\practice\MidTerm\SpringCommerce\src\main\resources\static\uploads\
Hãy sửa đoạn C:\Lit\Programming\java\practice\ cho phù hợp với máy ở local của thầy
Bước 5: Khởi động docker
Bước 6: chạy chuong trình và truy cập hệ thống ở đường dẫn: localhost:8080

Link Demo: https://drive.google.com/file/d/1CV5ONWsrrb072fDquC58UbdkLqRtnlL0/view?usp=sharing

Tất cả các CURL:
/cart/add/{id}
/cart
/cart/delete/{id}
/detail/{id}
/home/filter
/
/order
/order/add
/detail/order/add/{id}
/odermanagement
/odermanagement/update/{id}
/order/delete/{id}
/product
/product/add
/product/delete/{id}
/product/update/{id}
/register
/register_account