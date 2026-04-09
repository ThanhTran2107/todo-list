# Prompt Dataset for Smart Scheduler AI/NLP

## Mục đích

Tập hợp các câu lệnh tiếng Việt mẫu để kiểm tra khả năng parse prompt, intent classification và trích xuất thông tin.

## Format

- `prompt`: câu lệnh của người dùng
- `intent`: ý định dự kiến
- `expected title`: tiêu đề công việc
- `expected priority`: mức ưu tiên
- `expected status`: trạng thái
- `expected dueDate`: ngày/giờ dự kiến (mô tả)
- `expected notes`: ghi chú thêm nếu có

## Test cases

### 1. Tạo task cơ bản

- prompt: `Thêm họp nhóm lúc 9h sáng mai, ưu tiên cao`
- intent: `CREATE`
- expected title: `họp nhóm`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 09:00
- expected notes: `ưu tiên cao`

- prompt: `Thêm nhắc gọi điện cho khách hàng vào 2h chiều mai`
- intent: `CREATE`
- expected title: `nhắc gọi điện cho khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 14:00
- expected notes: `task cơ bản`

- prompt: `Thêm soạn email gửi đối tác ngày mai lúc 10h`
- intent: `CREATE`
- expected title: `soạn email gửi đối tác`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 10:00
- expected notes: `task cơ bản`

- prompt: `Thêm đặt lịch họp báo cáo tài chính thứ 6 tới`
- intent: `CREATE`
- expected title: `đặt lịch họp báo cáo tài chính`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6 tuần sau
- expected notes: `task cơ bản`

- prompt: `Thêm mua hoa tặng mẹ vào sáng chủ nhật`
- intent: `CREATE`
- expected title: `mua hoa tặng mẹ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chủ nhật
- expected notes: `task cơ bản`

- prompt: `Thêm nộp đơn nghỉ phép ngày 25/4`
- intent: `CREATE`
- expected title: `nộp đơn nghỉ phép`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 25/04
- expected notes: `task cơ bản`

<<<<<<< HEAD
- prompt: `Thêm họp nhóm vào ngày 20/4 lúc 10h`
- intent: `CREATE`
- expected title: `họp nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/4 lúc 10:00`
- expected notes: `date trước hành động`

- prompt: `Thêm đi khám bệnh ngày 15-8 lúc 14h`
- intent: `CREATE`
- expected title: `đi khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `15/8 lúc 14:00`
- expected notes: `date trước hành động`

- prompt: `Thêm gọi điện khách hàng ngày 13/5/2025`
- intent: `CREATE`
- expected title: `gọi điện khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `13/5/2025`
- expected notes: `date trước hành động`

- prompt: `Thêm nộp báo cáo vào ngày 21-7-2027 lúc 16h`
- intent: `CREATE`
- expected title: `nộp báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/7/2027 lúc 16:00`
- expected notes: `date trước hành động`

- prompt: `Ngày 20/4 lúc 9h, họp nội bộ`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/4 lúc 09:00`
- expected notes: `date trước hành động`

- prompt: `Ngày 15-8, đi du lịch cùng bạn`
- intent: `CREATE`
- expected title: `đi du lịch cùng bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `15/8`
- expected notes: `date trước hành động`

- prompt: `Họp dự án ngày 13/5/2025 lúc 11h`
- intent: `CREATE`
- expected title: `họp dự án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `13/5/2025 lúc 11:00`
- expected notes: `date trước hành động`

- prompt: `Ghé thăm bố mẹ vào ngày 21-7-2027`
- intent: `CREATE`
- expected title: `ghé thăm bố mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/7/2027`
- expected notes: `date trước hành động`

- prompt: `Hẹn gặp đội ngũ, ngày 20/4 lúc 14h`
- intent: `CREATE`
- expected title: `hẹn gặp đội ngũ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/4 lúc 14:00`
- expected notes: `hành động, date time`

- prompt: `Hẹn gặp đội ngũ lúc 14h ngày 20/4`
- intent: `CREATE`
- expected title: `hẹn gặp đội ngũ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/4 lúc 14:00`
- expected notes: `hành động time, date`

- prompt: `Đi mua đồ ngày 15-8, 9h sáng`
- intent: `CREATE`
- expected title: `đi mua đồ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `15/8 lúc 09:00`
- expected notes: `hành động date, time`

- prompt: `Đi mua đồ, 9h sáng ngày 15-8`
- intent: `CREATE`
- expected title: `đi mua đồ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `15/8 lúc 09:00`
- expected notes: `hành động, time date`

- prompt: `Gửi email ngày 13/5/2025 lúc 10h`
- intent: `CREATE`
- expected title: `gửi email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `13/5/2025 lúc 10:00`
- expected notes: `hành động date, time`

- prompt: `Gửi email, 10h ngày 13/5/2025`
- intent: `CREATE`
- expected title: `gửi email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `13/5/2025 lúc 10:00`
- expected notes: `hành động, time date`

- prompt: `Chuẩn bị báo cáo ngày 21-7-2027, 16h`
- intent: `CREATE`
- expected title: `chuẩn bị báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `21/7/2027 lúc 16:00`
- expected notes: `hành động date, time`

- prompt: `Chuẩn bị báo cáo, 16h ngày 21-7-2027`
- intent: `CREATE`
- expected title: `chuẩn bị báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `21/7/2027 lúc 16:00`
- expected notes: `hành động, time date`

- prompt: `Vào ngày 20/4, họp dự án lúc 14h`
- intent: `CREATE`
- expected title: `họp dự án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `20/4 lúc 14:00`
- expected notes: `vào ngày/tháng time`

- prompt: `Gọi cho khách hàng vào ngày 21/4 lúc 10h`
- intent: `CREATE`
- expected title: `gọi cho khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/4 lúc 10:00`
- expected notes: `vào ngày/tháng time`

- prompt: `Nộp đơn nghỉ phép vào ngày 22/4`
- intent: `CREATE`
- expected title: `nộp đơn nghỉ phép`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/4`
- expected notes: `vào ngày/tháng`

- prompt: `Mua đồ đi chợ vào ngày 23/4 lúc 18h`
- intent: `CREATE`
- expected title: `mua đồ đi chợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `23/4 lúc 18:00`
- expected notes: `vào ngày/tháng time`

- prompt: `Đến thăm ông bà vào ngày 24/4`
- intent: `CREATE`
- expected title: `đến thăm ông bà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `24/4`
- expected notes: `vào ngày/tháng`

- prompt: `Soạn email gửi đối tác vào ngày 25/4 lúc 9h`
- intent: `CREATE`
- expected title: `soạn email gửi đối tác`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `25/4 lúc 09:00`
- expected notes: `vào ngày/tháng time`

- prompt: `Thanh toán hoá đơn vào ngày 26/4`
- intent: `CREATE`
- expected title: `thanh toán hoá đơn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `26/4`
- expected notes: `vào ngày/tháng`

- prompt: `Tập gym vào ngày 27/4 lúc 19h`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `27/4 lúc 19:00`
- expected notes: `vào ngày/tháng time`

- prompt: `Về nhà thăm bố mẹ vào ngày 28/4`
- intent: `CREATE`
- expected title: `về nhà thăm bố mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `28/4`
- expected notes: `vào ngày/tháng`

- prompt: `Tham gia buổi workshop vào ngày 29/4 lúc 14h`
- intent: `CREATE`
- expected title: `tham gia buổi workshop`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `29/4 lúc 14:00`
- expected notes: `vào ngày/tháng time`

- prompt: `Chuẩn bị bài thuyết trình vào ngày 1/5`
- intent: `CREATE`
- expected title: `chuẩn bị bài thuyết trình`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `1/5`
- expected notes: `vào ngày/tháng`

- prompt: `Đi khám nha khoa vào ngày 5 tháng 5`
- intent: `CREATE`
- expected title: `đi khám nha khoa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `5/5`
- expected notes: `vào ngày ... tháng`

- prompt: `Gặp đối tác vào ngày 6 tháng 5 lúc 15h`
- intent: `CREATE`
- expected title: `gặp đối tác`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `6/5 lúc 15:00`
- expected notes: `vào ngày ... tháng time`

- prompt: `Mua quà vào ngày 7 tháng 5`
- intent: `CREATE`
- expected title: `mua quà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `7/5`
- expected notes: `vào ngày ... tháng`

- prompt: `Tham dự lớp học vào ngày 8 tháng 5 lúc 17h`
- intent: `CREATE`
- expected title: `tham dự lớp học`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `8/5 lúc 17:00`
- expected notes: `vào ngày ... tháng time`

- prompt: `Nộp báo cáo vào ngày 9 tháng 5`
- intent: `CREATE`
- expected title: `nộp báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `9/5`
- expected notes: `vào ngày ... tháng`

- prompt: `Gửi giấy tờ vào ngày 10 tháng 5 lúc 15h`
- intent: `CREATE`
- expected title: `gửi giấy tờ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `10/5 lúc 15:00`
- expected notes: `vào ngày ... tháng time`

- prompt: `Hẹn gặp sếp vào ngày 11 tháng 5`
- intent: `CREATE`
- expected title: `hẹn gặp sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `11/5`
- expected notes: `vào ngày ... tháng`

- prompt: `Tập yoga vào ngày 12 tháng 5 lúc 18h`
- intent: `CREATE`
- expected title: `tập yoga`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `12/5 lúc 18:00`
- expected notes: `vào ngày ... tháng time`

- prompt: `Vào ngày 1 tháng 6 năm 2025, họp đầu tuần`
- intent: `CREATE`
- expected title: `họp đầu tuần`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `1/6/2025`
- expected notes: `vào ngày ... tháng ... năm`

- prompt: `Gặp nhà cung cấp vào ngày 2 tháng 6 năm 2025 lúc 10h`
- intent: `CREATE`
- expected title: `gặp nhà cung cấp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2/6/2025 lúc 10:00`
- expected notes: `vào ngày ... tháng ... năm time`

- prompt: `Nộp báo cáo dự án vào ngày 3 tháng 6 năm 2025`
- intent: `CREATE`
- expected title: `nộp báo cáo dự án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `3/6/2025`
- expected notes: `vào ngày ... tháng ... năm`

- prompt: `Đi du lịch cùng gia đình vào ngày 4 tháng 6 năm 2025`
- intent: `CREATE`
- expected title: `đi du lịch cùng gia đình`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `4/6/2025`
- expected notes: `vào ngày ... tháng ... năm`

- prompt: `Học trực tuyến vào ngày 5 tháng 6 năm 2025 lúc 20h`
- intent: `CREATE`
- expected title: `học trực tuyến`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `5/6/2025 lúc 20:00`
- expected notes: `vào ngày ... tháng ... năm time`

- prompt: `Gọi điện cho thầy cô vào ngày 6 tháng 6 năm 2025`
- intent: `CREATE`
- expected title: `gọi điện cho thầy cô`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `6/6/2025`
- expected notes: `vào ngày ... tháng ... năm`

- prompt: `Thi tuyển vào ngày 7 tháng 6 năm 2025 lúc 8h`
- intent: `CREATE`
- expected title: `thi tuyển`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `7/6/2025 lúc 08:00`
- expected notes: `vào ngày ... tháng ... năm time`

- prompt: `Thăm bạn cũ vào ngày 8 tháng 6 năm 2025`
- intent: `CREATE`
- expected title: `thăm bạn cũ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `8/6/2025`
- expected notes: `vào ngày ... tháng ... năm`

- prompt: `Vào ngày-15-8, đi khám bệnh`
- intent: `CREATE`
- expected title: `đi khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `15/8`
- expected notes: `vào ngày-tháng`

- prompt: `Gặp bạn vào ngày-16-8 lúc 14h`
- intent: `CREATE`
- expected title: `gặp bạn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `16/8 lúc 14:00`
- expected notes: `vào ngày-tháng time`

- prompt: `Mua sắm vào ngày-17-8`
- intent: `CREATE`
- expected title: `mua sắm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `17/8`
- expected notes: `vào ngày-tháng`

- prompt: `Hẹn cà phê vào ngày-18-8 lúc 10h`
- intent: `CREATE`
- expected title: `hẹn cà phê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `18/8 lúc 10:00`
- expected notes: `vào ngày-tháng time`

- prompt: `Làm bài tập vào ngày-19-8`
- intent: `CREATE`
- expected title: `làm bài tập`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `19/8`
- expected notes: `vào ngày-tháng`

- prompt: `Gửi thư vào ngày-20-8 lúc 11h`
- intent: `CREATE`
- expected title: `gửi thư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/8 lúc 11:00`
- expected notes: `vào ngày-tháng time`

- prompt: `Thanh toán hoá đơn vào ngày-21-8`
- intent: `CREATE`
- expected title: `thanh toán hoá đơn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/8`
- expected notes: `vào ngày-tháng`

- prompt: `Đi tập thể dục vào ngày-22-8 lúc 19h`
- intent: `CREATE`
- expected title: `đi tập thể dục`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/8 lúc 19:00`
- expected notes: `vào ngày-tháng time`

- prompt: `Vào ngày/15/9/2025, họp nhóm lúc 16h`
- intent: `CREATE`
- expected title: `họp nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `15/9/2025 lúc 16:00`
- expected notes: `vào ngày/tháng/năm time`

- prompt: `Mua quà vào ngày/16/9/2025`
- intent: `CREATE`
- expected title: `mua quà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `16/9/2025`
- expected notes: `vào ngày/tháng/năm`

- prompt: `Hẹn bác sĩ vào ngày/17/9/2025 lúc 9h`
- intent: `CREATE`
- expected title: `hẹn bác sĩ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `17/9/2025 lúc 09:00`
- expected notes: `vào ngày/tháng/năm time`

- prompt: `Nộp đơn vào ngày/18/9/2025`
- intent: `CREATE`
- expected title: `nộp đơn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `18/9/2025`
- expected notes: `vào ngày/tháng/năm`

- prompt: `Gọi khách hàng vào ngày/19/9/2025 lúc 10h`
- intent: `CREATE`
- expected title: `gọi khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `19/9/2025 lúc 10:00`
- expected notes: `vào ngày/tháng/năm time`

- prompt: `Tham gia hội thảo vào ngày/20/9/2025`
- intent: `CREATE`
- expected title: `tham gia hội thảo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/9/2025`
- expected notes: `vào ngày/tháng/năm`

- prompt: `Tập gym vào ngày/21/9/2025 lúc 18h`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/9/2025 lúc 18:00`
- expected notes: `vào ngày/tháng/năm time`

- prompt: `Ghé thăm bố mẹ vào ngày/22/9/2025`
- intent: `CREATE`
- expected title: `ghé thăm bố mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/9/2025`
- expected notes: `vào ngày/tháng/năm`

- prompt: `Vào ngày-25-10-2025, soạn báo cáo`
- intent: `CREATE`
- expected title: `soạn báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `25/10/2025`
- expected notes: `vào ngày-tháng-năm`

- prompt: `Hẹn gặp Sếp vào ngày-26-10-2025 lúc 14h`
- intent: `CREATE`
- expected title: `hẹn gặp Sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `26/10/2025 lúc 14:00`
- expected notes: `vào ngày-tháng-năm time`

- prompt: `Gửi quà vào ngày-27-10-2025`
- intent: `CREATE`
- expected title: `gửi quà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `27/10/2025`
- expected notes: `vào ngày-tháng-năm`

- prompt: `Đi tham quan vào ngày-28-10-2025 lúc 9h`
- intent: `CREATE`
- expected title: `đi tham quan`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `28/10/2025 lúc 09:00`
- expected notes: `vào ngày-tháng-năm time`

- prompt: `Làm bài thuyết trình vào ngày-29-10-2025`
- intent: `CREATE`
- expected title: `làm bài thuyết trình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `29/10/2025`
- expected notes: `vào ngày-tháng-năm`

- prompt: `Thanh toán tiền nhà vào ngày-30-10-2025`
- intent: `CREATE`
- expected title: `thanh toán tiền nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `30/10/2025`
- expected notes: `vào ngày-tháng-năm`

- prompt: `Hẹn phỏng vấn vào ngày-31-10-2025 lúc 11h`
- intent: `CREATE`
- expected title: `hẹn phỏng vấn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `31/10/2025 lúc 11:00`
- expected notes: `vào ngày-tháng-năm time`

- prompt: `Mua vé concert vào ngày-1-11-2025`
- intent: `CREATE`
- expected title: `mua vé concert`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `1/11/2025`
- expected notes: `vào ngày-tháng-năm`

=======
>>>>>>> 4a4bae2 (Implement Vietnamese NLP-based AI todo generation)
- prompt: `Thêm chuẩn bị slide cho buổi họp ngày mai`
- intent: `CREATE`
- expected title: `chuẩn bị slide`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `task cơ bản`

- prompt: `Thêm đi họp nhóm dự án lúc 15h chiều mai`
- intent: `CREATE`
- expected title: `đi họp nhóm dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 15:00
- expected notes: `task cơ bản`

- prompt: `Thêm làm bài tập lập trình trong 2 giờ tối nay`
- intent: `CREATE`
- expected title: `làm bài tập lập trình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `task cơ bản`

- prompt: `Thêm đặt vé xem phim tối thứ 7 lúc 19h`
- intent: `CREATE`
- expected title: `đặt vé xem phim`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối thứ 7 lúc 19:00
- expected notes: `task cơ bản`

### 2. Intent VIEW

- prompt: `Xem danh sách công việc chưa hoàn thành`
- intent: `VIEW`
- expected title: `danh sách công việc`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `hiển thị intent VIEW`

- prompt: `Xem công việc hôm nay`
- intent: `VIEW`
- expected title: `công việc hôm nay`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `hôm nay`
- expected notes: `view hôm nay`

- prompt: `Xem những việc cần làm tuần này`
- intent: `VIEW`
- expected title: `việc cần làm tuần này`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `tuần này`
- expected notes: `view tuần này`

- prompt: `Cho tôi thấy các task chưa hoàn thành`
- intent: `VIEW`
- expected title: `task chưa hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view chưa hoàn thành`

- prompt: `Hiển thị danh sách công việc ưu tiên cao`
- intent: `VIEW`
- expected title: `công việc ưu tiên cao`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view ưu tiên`

- prompt: `Xem công việc đã lên lịch ngày mai`
- intent: `VIEW`
- expected title: `công việc ngày mai`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `ngày mai`
- expected notes: `view ngày mai`

- prompt: `Xem các task liên quan đến email`
- intent: `VIEW`
- expected title: `task email`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view email`

- prompt: `Xem công việc cho hôm nay và ngày mai`
- intent: `VIEW`
- expected title: `công việc hôm nay và ngày mai`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `hôm nay và ngày mai`
- expected notes: `view 2 ngày`

- prompt: `Cho tôi xem tất cả công việc trong tuần này`
- intent: `VIEW`
- expected title: `tất cả công việc tuần này`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `tuần này`
- expected notes: `view tuần`

- prompt: `Xem công việc đã hoàn thành từ đầu tuần`
- intent: `VIEW`
- expected title: `công việc đã hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `từ đầu tuần`
- expected notes: `view hoàn thành`

### 3. Update intent

- prompt: `Cập nhật lịch họp sang 10h ngày mai`
- intent: `UPDATE`
- expected title: `lịch họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 10:00
- expected notes: `cập nhật`

- prompt: `Cập nhật thời gian họp dự án sang 14h ngày mai`
- intent: `UPDATE`
- expected title: `họp dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 14:00
- expected notes: `cập nhật`

- prompt: `Cập nhật mức ưu tiên công việc soạn báo cáo thành cao`
- intent: `UPDATE`
- expected title: `soạn báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `cập nhật ưu tiên`

- prompt: `Cập nhật deadline nộp báo cáo sang 12h trưa`
- intent: `UPDATE`
- expected title: `nộp báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `12h trưa`
- expected notes: `cập nhật deadline`

- prompt: `Cập nhật địa điểm cuộc họp thành phòng A`
- intent: `UPDATE`
- expected title: `cuộc họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `cập nhật địa điểm`

- prompt: `Cập nhật trạng thái task này thành đã hoàn thành`
- intent: `UPDATE`
- expected title: `task này`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `cập nhật trạng thái`

- prompt: `Cập nhật nhắc nhở hôm nay thành sáng mai`
- intent: `UPDATE`
- expected title: `nhắc nhở`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `sáng mai`
- expected notes: `cập nhật thời gian`

- prompt: `Cập nhật nội dung công việc sang chuẩn bị tài liệu`
- intent: `UPDATE`
- expected title: `chuẩn bị tài liệu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `cập nhật nội dung`

- prompt: `Cập nhật hẹn khám bác sĩ sang 9h sáng`
- intent: `UPDATE`
- expected title: `hẹn khám bác sĩ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `9h sáng`
- expected notes: `cập nhật`

- prompt: `Cập nhật lịch gặp đối tác sang 15h chiều`
- intent: `UPDATE`
- expected title: `gặp đối tác`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `15h chiều`
- expected notes: `cập nhật`

### 4. Delete intent

- prompt: `Xóa công việc nộp báo cáo ngày mai`
- intent: `DELETE`
- expected title: `nộp báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `xóa`

- prompt: `Xóa nhắc nhở thanh toán hóa đơn`
- intent: `DELETE`
- expected title: `thanh toán hóa đơn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

- prompt: `Hủy cuộc họp với sếp ngày mai`
- intent: `DELETE`
- expected title: `cuộc họp với sếp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `xóa`

- prompt: `Loại bỏ task gửi email cho khách`
- intent: `DELETE`
- expected title: `gửi email cho khách`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

- prompt: `Xóa công việc kiểm tra máy chủ`
- intent: `DELETE`
- expected title: `kiểm tra máy chủ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

- prompt: `Xóa lịch hẹn khám bệnh`
- intent: `DELETE`
- expected title: `lịch hẹn khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

- prompt: `Hủy bỏ công việc sửa xe`
- intent: `DELETE`
- expected title: `sửa xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

- prompt: `Xóa task đi mua sắm`
- intent: `DELETE`
- expected title: `đi mua sắm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

- prompt: `Loại bỏ nhắc nhở họp nhóm`
- intent: `DELETE`
- expected title: `nhắc nhở họp nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

- prompt: `Xóa công việc chuẩn bị báo cáo`
- intent: `DELETE`
- expected title: `chuẩn bị báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `xóa`

### 5. Thời gian explicit

- prompt: `Thêm nộp báo cáo ngày 20/5 lúc 14:30`
- intent: `CREATE`
- expected title: `nộp báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-05-20T14:30:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`

- prompt: `Thêm gửi quà khách hàng vào 22/4 lúc 16h30`
- intent: `CREATE`
- expected title: `gửi quà khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/4 lúc 16:30`
- expected notes: `explicit date`

- prompt: `Thêm làm hợp đồng mới ngày 30/4 lúc 14h`
- intent: `CREATE`
- expected title: `làm hợp đồng mới`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `30/4 lúc 14:00`
- expected notes: `explicit date`

- prompt: `Thêm đi công tác ngày 2/5 lúc 8h sáng`
- intent: `CREATE`
- expected title: `đi công tác`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2/5 lúc 08:00`
- expected notes: `explicit date`

- prompt: `Thêm tham gia buổi đào tạo ngày 14/5 lúc 13h`
- intent: `CREATE`
- expected title: `tham gia buổi đào tạo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `14/5 lúc 13:00`
- expected notes: `explicit date`

- prompt: `Thêm nộp phiếu đăng ký vào 5/5 lúc 15h`
- intent: `CREATE`
- expected title: `nộp phiếu đăng ký`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `5/5 lúc 15:00`
- expected notes: `explicit date`

- prompt: `Thêm tổ chức sinh nhật ngày 10/6 lúc 18h`
- intent: `CREATE`
- expected title: `tổ chức sinh nhật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `10/6 lúc 18:00`
- expected notes: `explicit date`

- prompt: `Thêm trả lời email trước 11h sáng mai`
- intent: `CREATE`
- expected title: `trả lời email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 11:00`
- expected notes: `explicit date`

- prompt: `Thêm họp xét duyệt dự án ngày 19/5 lúc 10h30`
- intent: `CREATE`
- expected title: `họp xét duyệt dự án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `19/5 lúc 10:30`
- expected notes: `explicit date`

- prompt: `Thêm họp offline với team vào 21/4 lúc 16h`
- intent: `CREATE`
- expected title: `họp offline với team`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/4 lúc 16:00`
- expected notes: `explicit date`

<<<<<<< HEAD
- prompt: `20/5 lúc 9h, tôi đi chùa với gia đình`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `20/5, 9h sáng, tôi đi chùa với gia đình`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `20-5-2029, tôi đi chùa với gia đình vào 2h sáng`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5/2029 lúc 02:00`
- expected notes: `explicit date`

- prompt: `20/5/2029 lúc 9h sáng. Tôi đi chùa với gia đình`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5/2029 lúc 09:00`
- expected notes: `explicit date`

- prompt: `20 tháng 5, tôi đi chùa với gia đình lúc 9h`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20 tháng 5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `20 tháng 5 năm 2029, tôi đi chùa với gia đình lúc 9h`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20 tháng 5 năm 2029 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Ngày 20 tháng 5 năm 2029, tôi đi chùa với gia đình lúc 9h sáng`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20 tháng 5 năm 2029 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Ngày 20 tháng 5 năm 2029 lúc 9h sáng, tôi đi chùa với gia đình`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20 tháng 5 năm 2029 lúc 09:00`
- expected notes: `explicit date`

- prompt: `20/5 lúc 9h; tôi đi chùa với gia đình`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Tôi đi chùa với gia đình ngày 20/5 lúc 9h`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Tôi đi chùa với gia đình lúc 9h ngày 20/5`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Tôi đi chùa với gia đình ngày 20-5-2029, lúc 9h sáng`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5/2029 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Tôi đi chùa với gia đình, ngày 20 tháng 5 năm 2029 lúc 9h`
- intent: `CREATE`
- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20 tháng 5 năm 2029 lúc 09:00`
- expected notes: `explicit date`

- prompt: `20/5, 9h sáng, họp nội bộ`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `9h sáng 20/5, họp nội bộ`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Họp nội bộ 9h sáng 20/5`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Họp nội bộ, 20/5 lúc 9h`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

- prompt: `22/6/2029 14h, gọi điện khách hàng`
- intent: `CREATE`
- expected title: `gọi điện khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/6/2029 lúc 14:00`
- expected notes: `explicit date`

- prompt: `Gọi điện khách hàng, 22/6/2029 14h`
- intent: `CREATE`
- expected title: `gọi điện khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/6/2029 lúc 14:00`
- expected notes: `explicit date`

- prompt: `Nhắc tôi gọi điện khách hàng 22/6/2029 14h`
- intent: `CREATE`
- expected title: `gọi điện khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/6/2029 lúc 14:00`
- expected notes: `explicit date`

- prompt: `9h 22/6/2029, nhắc tôi gọi điện khách hàng`
- intent: `CREATE`
- expected title: `gọi điện khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22/6/2029 lúc 09:00`
- expected notes: `explicit date`

- prompt: `9h sáng, 22 tháng 6 năm 2029, nhắc tôi gọi điện khách hàng`
- intent: `CREATE`
- expected title: `gọi điện khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `22 tháng 6 năm 2029 lúc 09:00`
- expected notes: `explicit date`

### 5a. Task dài không có từ xóa hay cập nhật

- expected title: `đi chùa với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/5 lúc 09:00`
- expected notes: `explicit date`

=======
>>>>>>> 4a4bae2 (Implement Vietnamese NLP-based AI todo generation)
### 5a. Task dài không có từ xóa hay cập nhật

- prompt: `Ngày 21 tháng 4, tôi phải lên lớp báo cáo đồ án môn chuyên ngành lúc 1h chiều`
- intent: `CREATE`
- expected title: `lên lớp báo cáo đồ án môn chuyên ngành`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/04/2026 13:00`
- expected notes: `explicit date`, `task học`

### 5b. Task tối mai đi bida

- prompt: `Tối mai tôi đi đánh bida với mấy anh em trong công ty lúc 6h tối`
- intent: `CREATE`
- expected title: `đi đánh bida với mấy anh em trong công ty`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 18:00`
- expected notes: `giải trí`, `task cá nhân`

### 5c. Task làm việc thêm giờ

- prompt: `Hôm nay tôi ngồi lại trên công ty tới 8h mới về`
- intent: `CREATE`
- expected title: `ngồi lại trên công ty`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 20:00`
- expected notes: `task làm thêm giờ`, `create`

### 5d. Task kiểm tra cuối kỳ

- prompt: `Ngày 21 tháng 4, tôi phải làm kiểm tra cuối kỳ lúc 2h chiều`
- intent: `CREATE`
- expected title: `kiểm tra cuối kỳ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/04/2026 14:00`
- expected notes: `task học`, `explicit date`

- prompt: `Mai tôi phải đi làm kiểm tra cuối kỳ lúc 2h chiều`
- intent: `CREATE`
- expected title: `làm kiểm tra cuối kỳ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 14:00`
- expected notes: `task học`, `deadline`

- prompt: `Hôm nay tôi phải làm bài kiểm tra lúc 2h chiều`
- intent: `CREATE`
- expected title: `làm bài kiểm tra`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 14:00`
- expected notes: `task học`

- prompt: `Ngày mai tôi có kỳ thi cuối kỳ môn Toán lúc 2h chiều`
- intent: `CREATE`
- expected title: `kỳ thi cuối kỳ môn Toán`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 14:00`
- expected notes: `task học`, `deadline`

### 5e. Mẫu `cần phải` và `vẫn phải`

- prompt: `Ngày mai tôi cần phải nộp bài tập lúc 9h`
- intent: `CREATE`
- expected title: `nộp bài tập`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 09:00`
- expected notes: `deadline`, `cần phải`

- prompt: `Hôm nay tôi vẫn phải hoàn thành báo cáo trước 6h chiều`
- intent: `CREATE`
- expected title: `hoàn thành báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 18:00`
- expected notes: `deadline`, `vẫn phải`

- prompt: `Cần phải đi khám sức khỏe lúc 8h sáng mai`
- intent: `CREATE`
- expected title: `đi khám sức khỏe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 08:00`
- expected notes: `sức khỏe`, `cần phải`

- prompt: `Tối nay tôi vẫn phải đi họp với nhóm lúc 7h`
- intent: `CREATE`
- expected title: `đi họp với nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tối nay lúc 19:00`
- expected notes: `vẫn phải`, `công việc`

- prompt: `Mai tôi cần phải hoàn thành slide trước 10h`
- intent: `CREATE`
- expected title: `hoàn thành slide`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 10:00`
- expected notes: `deadline`, `cần phải`

- prompt: `Sáng mai tôi vẫn phải đi ngân hàng lúc 9h`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 09:00`
- expected notes: `vẫn phải`, `việc cá nhân`

### 5f. Task thể thao và giải trí

- prompt: `Chiều ngày mai 3h, tôi có hẹn đi đánh cầu lông với các anh em`
- intent: `CREATE`
- expected title: `đi đánh cầu lông với các anh em`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `chiều mai lúc 15:00`
- expected notes: `thể thao`, `đánh cầu lông`

- prompt: `Tối mai tôi đi đá bóng với đồng nghiệp lúc 7h30`
- intent: `CREATE`
- expected title: `đi đá bóng với đồng nghiệp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `tối mai lúc 19:30`
- expected notes: `thể thao`, `đá bóng`

- prompt: `Sáng mai chơi tennis với bạn vào 8h`
- intent: `CREATE`
- expected title: `chơi tennis với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 08:00`
- expected notes: `thể thao`, `tennis`

- prompt: `Cuối tuần này mình có lớp yoga lúc 9h sáng`
- intent: `CREATE`
- expected title: `lớp yoga`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `cuối tuần lúc 09:00`
- expected notes: `thể thao`, `yoga`

- prompt: `Hôm nay tôi sẽ đi bơi lúc 6h chiều`
- intent: `CREATE`
- expected title: `đi bơi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 18:00`
- expected notes: `thể thao`, `bơi`

- prompt: `Sáng mai tôi đi tập gym lúc 6h`
- intent: `CREATE`
- expected title: `đi tập gym`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 06:00`
- expected notes: `thể thao`, `gym`

- prompt: `Chiều mai mình có buổi chạy bộ ở công viên lúc 5h30`
- intent: `CREATE`
- expected title: `chạy bộ ở công viên`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `chiều mai lúc 17:30`
- expected notes: `thể thao`, `chạy bộ`

- prompt: `Tối mai tôi có lịch chơi bóng rổ với bạn lúc 8h`
- intent: `CREATE`
- expected title: `chơi bóng rổ với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `tối mai lúc 20:00`
- expected notes: `thể thao`, `bóng rổ`

- prompt: `Sáng Chủ nhật tôi đi đánh cầu lông với đồng đội lúc 9h`
- intent: `CREATE`
- expected title: `đi đánh cầu lông với đồng đội`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `Chủ nhật lúc 09:00`
- expected notes: `thể thao`, `đánh cầu lông`

- prompt: `Chiều mai tôi có buổi tập võ cùng huấn luyện viên lúc 7h`
- intent: `CREATE`
- expected title: `tập võ cùng huấn luyện viên`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `chiều mai lúc 19:00`
- expected notes: `thể thao`, `võ`

- prompt: `Thứ 7 tuần sau mình có buổi leo núi nhân tạo lúc 10h`
- intent: `CREATE`
- expected title: `leo núi nhân tạo`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `Thứ 7 tuần sau lúc 10:00`
- expected notes: `thể thao`, `leo núi`

- prompt: `Chiều nay mình có hẹn chơi cầu lông với mấy anh lúc 4h`
- intent: `CREATE`
- expected title: `chơi cầu lông với mấy anh`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `chiều nay lúc 16:00`
- expected notes: `thể thao`, `cầu lông`

- prompt: `Mai mình đi đá cầu với mấy đứa bạn lúc 6h`
- intent: `CREATE`
- expected title: `đi đá cầu với mấy đứa bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 18:00`
- expected notes: `thể thao`, `đá cầu`

- prompt: `Sáng mai mình có buổi tập pilates lúc 8h30`
- intent: `CREATE`
- expected title: `buổi tập pilates`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 08:30`
- expected notes: `thể thao`, `pilates`

- prompt: `Tối nay tôi có buổi nhảy zumba lúc 7h30`
- intent: `CREATE`
- expected title: `buổi nhảy zumba`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `tối nay lúc 19:30`
- expected notes: `thể thao`, `zumba`

### 6. Thời gian cuối tuần

- prompt: `Thêm đi chợ cuối tuần, ưu tiên thấp`
- intent: `CREATE`
- expected title: `đi chợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày thứ 7 kế tiếp, giờ mặc định 18:00
- expected notes: `cuối tuần`

- prompt: `Thêm phiếu đăng ký sự kiện vào cuối tuần`
- intent: `CREATE`
- expected title: `phiếu đăng ký sự kiện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm họp nhóm cuối tuần để lên kế hoạch`
- intent: `CREATE`
- expected title: `họp nhóm cuối tuần`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm mua sắm cuối tuần ở siêu thị`
- intent: `CREATE`
- expected title: `mua sắm cuối tuần`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm chăm sóc vườn cuối tuần`
- intent: `CREATE`
- expected title: `chăm sóc vườn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm thăm bố mẹ cuối tuần`
- intent: `CREATE`
- expected title: `thăm bố mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm tập golf cuối tuần với bạn`
- intent: `CREATE`
- expected title: `tập golf`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm sửa chữa nhà cuối tuần`
- intent: `CREATE`
- expected title: `sửa chữa nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm họp lớp cuối tuần nhé`
- intent: `CREATE`
- expected title: `họp lớp cuối tuần`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

- prompt: `Thêm dọn dẹp gara cuối tuần`
- intent: `CREATE`
- expected title: `dọn dẹp gara`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `cuối tuần`

### 7. Tháng sau

- prompt: `Nhắc tôi gửi email tháng sau`
- intent: `CREATE`
- expected title: `gửi email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày 1 tháng sau, giờ mặc định 18:00
- expected notes: `tháng sau`

- prompt: `Nhắc tôi đặt vé máy bay tháng sau`
- intent: `CREATE`
- expected title: `đặt vé máy bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi gửi hóa đơn tháng sau`
- intent: `CREATE`
- expected title: `gửi hóa đơn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi họp với đối tác tháng sau`
- intent: `CREATE`
- expected title: `họp với đối tác`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi đăng ký khóa học tháng sau`
- intent: `CREATE`
- expected title: `đăng ký khóa học`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi đóng tiền nhà tháng sau`
- intent: `CREATE`
- expected title: `đóng tiền nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi kiểm tra vé máy bay tháng sau`
- intent: `CREATE`
- expected title: `kiểm tra vé máy bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi chốt lịch họp tháng sau`
- intent: `CREATE`
- expected title: `chốt lịch họp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi mua quà sinh nhật tháng sau`
- intent: `CREATE`
- expected title: `mua quà sinh nhật`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

- prompt: `Nhắc tôi làm báo cáo tổng kết tháng sau`
- intent: `CREATE`
- expected title: `làm báo cáo tổng kết`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `tháng sau`

### 8. Thời gian buổi chiều

- prompt: `Thêm họp với khách hàng 3 giờ chiều`
- intent: `CREATE`
- expected title: `họp với khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cùng ngày hoặc tiếp theo nếu không rõ, giờ 15:00
- expected notes: `buổi chiều`

- prompt: `Thêm gặp khách hàng lúc 3h chiều`
- intent: `CREATE`
- expected title: `gặp khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 15:00
- expected notes: `buổi chiều`

- prompt: `Thêm xử lý email lúc 4h chiều`
- intent: `CREATE`
- expected title: `xử lý email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 16:00
- expected notes: `buổi chiều`

- prompt: `Thêm đi cafe lúc 5h chiều`
- intent: `CREATE`
- expected title: `đi cafe`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 17:00
- expected notes: `buổi chiều`

- prompt: `Thêm họp nội bộ lúc 2h30 chiều`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 14:30
- expected notes: `buổi chiều`

- prompt: `Thêm gọi điện cho sếp lúc 4h chiều`
- intent: `CREATE`
- expected title: `gọi điện cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 16:00
- expected notes: `buổi chiều`

- prompt: `Thêm học tiếng Anh lúc 3h30 chiều`
- intent: `CREATE`
- expected title: `học tiếng Anh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 15:30
- expected notes: `buổi chiều`

- prompt: `Thêm dọn phòng lúc 5h chiều`
- intent: `CREATE`
- expected title: `dọn phòng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 17:00
- expected notes: `buổi chiều`

- prompt: `Thêm tập thể dục lúc 6h chiều`
- intent: `CREATE`
- expected title: `tập thể dục`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 18:00
- expected notes: `buổi chiều`

- prompt: `Thêm nộp hồ sơ lúc 2h chiều`
- intent: `CREATE`
- expected title: `nộp hồ sơ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 14:00
- expected notes: `buổi chiều`

### 9. Ưu tiên suy luận từ bối cảnh

- prompt: `Hoàn thiện báo cáo gửi sếp trước 10h sáng mai`
- intent: `CREATE`
- expected title: `hoàn thiện báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 10:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Gửi mail cho khách hàng trước 4h chiều`
- intent: `CREATE`
- expected title: `gửi mail cho khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 16:00
- expected notes: `gửi khách`, `deadline`

- prompt: `Chuẩn bị hợp đồng trước 17h`
- intent: `CREATE`
- expected title: `chuẩn bị hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 17:00
- expected notes: `deadline`

- prompt: `Gặp bạn kiểm tra dự án lúc 3h chiều`
- intent: `CREATE`
- expected title: `gặp bạn kiểm tra dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 15:00
- expected notes: `gặp gỡ`

- prompt: `Nộp hồ sơ đăng ký trước ngày mai`
- intent: `CREATE`
- expected title: `nộp hồ sơ đăng ký`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `deadline`

- prompt: `Làm slide thuyết trình xong trước 8h sáng mai`
- intent: `CREATE`
- expected title: `làm slide thuyết trình`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 08:00
- expected notes: `deadline`

- prompt: `Xem lại tài liệu buổi họp sáng mai`
- intent: `CREATE`
- expected title: `xem lại tài liệu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `chuẩn bị`

- prompt: `Gửi thông báo nội bộ trước trưa`
- intent: `CREATE`
- expected title: `gửi thông báo nội bộ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc trưa
- expected notes: `deadline`

- prompt: `Dọn phòng họp trước 16h`
- intent: `CREATE`
- expected title: `dọn phòng họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `chuẩn bị`

- prompt: `Chuẩn bị báo cáo tài chính trước 5h chiều`
- intent: `CREATE`
- expected title: `chuẩn bị báo cáo tài chính`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 17:00
- expected notes: `deadline`

### 10. Câu không rõ intent

- prompt: `asdf hjkl`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không thể hiểu prompt`

- prompt: `Tôi không nhớ phải làm gì`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không thể hiểu prompt`

- prompt: `Rảnh không`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

- prompt: `Có việc gì không`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

- prompt: `Giúp tôi`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

- prompt: `Tối mai thôi`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

- prompt: `Nhắc tôi`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

- prompt: `Buổi chiều`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

- prompt: `Không biết`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

- prompt: `Làm gì đây`
- intent: `CREATE` (default)
- expected title: `Nhiệm vụ AI`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `không rõ intent`

### 11. Xem công việc đã hoàn thành

- prompt: `Xem công việc đã hoàn thành hôm nay`
- intent: `VIEW`
- expected title: `công việc đã hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `hôm nay`
- expected notes: `view completed`

- prompt: `Xem công việc đã hoàn thành tuần này`
- intent: `VIEW`
- expected title: `công việc đã hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `tuần này`
- expected notes: `view completed`

- prompt: `Cho tôi xem các task đã hoàn thành`
- intent: `VIEW`
- expected title: `task đã hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view completed`

- prompt: `Hiển thị tất cả công việc hoàn thành`
- intent: `VIEW`
- expected title: `công việc hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view completed`

- prompt: `Xem công việc hoàn thành tháng này`
- intent: `VIEW`
- expected title: `công việc hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `tháng này`
- expected notes: `view completed`

- prompt: `Xem những task đã xong ngày hôm qua`
- intent: `VIEW`
- expected title: `task đã xong`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `hôm qua`
- expected notes: `view completed`

- prompt: `Cho tôi danh sách hoàn thành`
- intent: `VIEW`
- expected title: `danh sách hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view completed`

- prompt: `Hiển thị job đã hoàn thành`
- intent: `VIEW`
- expected title: `job đã hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view completed`

- prompt: `Xem báo cáo công việc đã hoàn thành`
- intent: `VIEW`
- expected title: `báo cáo công việc đã hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view completed`

- prompt: `Xem những nhiệm vụ đã hoàn thành`
- intent: `VIEW`
- expected title: `nhiệm vụ đã hoàn thành`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view completed`

### 12. Cập nhật trạng thái

- prompt: `Cập nhật nhiệm vụ này thành đã hoàn thành`
- intent: `UPDATE`
- expected title: `nhiệm vụ này`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Cập nhật task này thành đang tiến hành`
- intent: `UPDATE`
- expected title: `task này`
- expected priority: `MEDIUM`
- expected status: `IN_PROGRESS`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Cập nhật trạng thái nhắc nhở này thành hoàn thành`
- intent: `UPDATE`
- expected title: `nhắc nhở này`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Đánh dấu công việc này là đã xong`
- intent: `UPDATE`
- expected title: `công việc này`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Chuyển trạng thái task này sang đang làm`
- intent: `UPDATE`
- expected title: `task này`
- expected priority: `MEDIUM`
- expected status: `IN_PROGRESS`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Đổi trạng thái công việc đọc sách thành hoàn thành`
- intent: `UPDATE`
- expected title: `đọc sách`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Cập nhật trạng thái gửi báo cáo thành đang chờ`
- intent: `UPDATE`
- expected title: `gửi báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Chuyển công việc này sang trạng thái đã hoàn thành`
- intent: `UPDATE`
- expected title: `công việc này`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Cập nhật task dọn dẹp nhà sang đã xong`
- intent: `UPDATE`
- expected title: `dọn dẹp nhà`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `update status`

- prompt: `Đổi trạng thái lịch họp thành hoàn thành`
- intent: `UPDATE`
- expected title: `lịch họp`
- expected priority: `MEDIUM`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `update status`

### 13. Xóa với keyword khác

- prompt: `Loại bỏ sự kiện họp với sếp`
- intent: `DELETE`
- expected title: `sự kiện họp với sếp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Gỡ bỏ sự kiện họp với đối tác`
- intent: `DELETE`
- expected title: `sự kiện họp với đối tác`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Xóa khỏi danh sách dự án`
- intent: `DELETE`
- expected title: `danh sách dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Hủy bỏ nhắc nhở thanh toán`
- intent: `DELETE`
- expected title: `nhắc nhở thanh toán`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Loại bỏ ghi chú họp buổi sáng`
- intent: `DELETE`
- expected title: `ghi chú họp buổi sáng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Xóa lịch hẹn kiểm tra sức khỏe`
- intent: `DELETE`
- expected title: `lịch hẹn kiểm tra sức khỏe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Gỡ công việc dọn dẹp nhà`
- intent: `DELETE`
- expected title: `dọn dẹp nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Hủy nhiệm vụ mua sắm`
- intent: `DELETE`
- expected title: `mua sắm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Loại bỏ task đọc sách`
- intent: `DELETE`
- expected title: `task đọc sách`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

- prompt: `Xóa kế hoạch du lịch`
- intent: `DELETE`
- expected title: `kế hoạch du lịch`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

### 14. Task deadline hôm nay

- prompt: `Hoàn thành báo cáo tài chính trước 6h tối nay`
- intent: `CREATE`
- expected title: `hoàn thành báo cáo tài chính`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 18:00
- expected notes: `deadline`

- prompt: `Nộp báo cáo trước 5h chiều nay`
- intent: `CREATE`
- expected title: `nộp báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 17:00
- expected notes: `deadline`

- prompt: `Hoàn thành bài tập trước 7h tối nay`
- intent: `CREATE`
- expected title: `hoàn thành bài tập`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 19:00
- expected notes: `deadline`

- prompt: `Trả lời email trước trưa nay`
- intent: `CREATE`
- expected title: `trả lời email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 12:00
- expected notes: `deadline`

- prompt: `Gửi hợp đồng trước 4h chiều`
- intent: `CREATE`
- expected title: `gửi hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `deadline`

- prompt: `Xem lại báo cáo trước 3h`
- intent: `CREATE`
- expected title: `xem lại báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 15:00
- expected notes: `deadline`

- prompt: `Hoàn thành việc chuẩn bị tài liệu trước 6h`
- intent: `CREATE`
- expected title: `chuẩn bị tài liệu`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 18:00
- expected notes: `deadline`

- prompt: `Kết thúc buổi họp trước 8h tối`
- intent: `CREATE`
- expected title: `kết thúc buổi họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 20:00
- expected notes: `deadline`

- prompt: `Nộp đơn trước 17h`
- intent: `CREATE`
- expected title: `nộp đơn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 17:00
- expected notes: `deadline`

- prompt: `In tài liệu trước 16h`
- intent: `CREATE`
- expected title: `in tài liệu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `deadline`

### 14a. Task với từ 'nay'

- prompt: `Nay tôi về nhà lúc tối`
- intent: `CREATE`
- expected title: `về nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ngày nay`, `tối`

- prompt: `Nay tôi phải đi họp lúc 3h`
- intent: `CREATE`
- expected title: `đi họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 15:00
- expected notes: `ngày nay`

- prompt: `Nay tôi có việc phải hoàn thành`
- intent: `CREATE`
- expected title: `việc phải hoàn thành`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

- prompt: `Nay tôi sẽ gọi cho khách hàng`
- intent: `CREATE`
- expected title: `gọi cho khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

- prompt: `Nay tôi cần gửi báo cáo`
- intent: `CREATE`
- expected title: `gửi báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

- prompt: `Nay tôi có lịch hẹn bác sĩ`
- intent: `CREATE`
- expected title: `lịch hẹn bác sĩ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

- prompt: `Nay tôi phải tập gym`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

- prompt: `Nay tôi sẽ nộp hồ sơ`
- intent: `CREATE`
- expected title: `nộp hồ sơ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

- prompt: `Nay tôi cần chuẩn bị bài thuyết trình`
- intent: `CREATE`
- expected title: `chuẩn bị bài thuyết trình`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

- prompt: `Nay tôi muốn dọn nhà`
- intent: `CREATE`
- expected title: `dọn nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `ngày nay`

### 15. Task hàng ngày

- prompt: `Nhắc nhở tôi tập gym mỗi sáng`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `mỗi sáng`
- expected notes: `thói quen`

- prompt: `Nhắc tôi uống nước mỗi sáng`
- intent: `CREATE`
- expected title: `uống nước`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `mỗi sáng`
- expected notes: `thói quen`

- prompt: `Nhắc tôi tập yoga mỗi tối`
- intent: `CREATE`
- expected title: `tập yoga`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `mỗi tối`
- expected notes: `thói quen`

- prompt: `Nhắc tôi đi bộ mỗi trưa`
- intent: `CREATE`
- expected title: `đi bộ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `mỗi trưa`
- expected notes: `thói quen`

- prompt: `Nhắc tôi đọc sách mỗi buổi tối`
- intent: `CREATE`
- expected title: `đọc sách`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `mỗi tối`
- expected notes: `thói quen`

- prompt: `Nhắc tôi viết nhật ký mỗi ngày`
- intent: `CREATE`
- expected title: `viết nhật ký`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `mỗi ngày`
- expected notes: `thói quen`

- prompt: `Nhắc tôi kiểm tra email mỗi sáng`
- intent: `CREATE`
- expected title: `kiểm tra email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `mỗi sáng`
- expected notes: `thói quen`

- prompt: `Nhắc tôi uống thuốc mỗi tối`
- intent: `CREATE`
- expected title: `uống thuốc`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `mỗi tối`
- expected notes: `thói quen`

- prompt: `Nhắc tôi học bài mỗi tối`
- intent: `CREATE`
- expected title: `học bài`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `mỗi tối`
- expected notes: `thói quen`

- prompt: `Nhắc tôi dọn bàn làm việc mỗi sáng`
- intent: `CREATE`
- expected title: `dọn bàn làm việc`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `mỗi sáng`
- expected notes: `thói quen`

### 16. Task ưu tiên thấp

- prompt: `Ghi chú mua sữa tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `mua sữa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú đi mua băng keo tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `đi mua băng keo`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Nhắc tôi sắp xếp tủ áo tuần này, ưu tiên thấp`
- intent: `CREATE`
- expected title: `sắp xếp tủ áo`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `ưu tiên thấp`

- prompt: `Ghi nhớ rửa xe cuối tuần, ưu tiên thấp`
- intent: `CREATE`
- expected title: `rửa xe`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú mua hoa ngày mai, ưu tiên thấp`
- intent: `CREATE`
- expected title: `mua hoa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `ưu tiên thấp`

- prompt: `Nhắc tôi dọn tủ lạnh, ưu tiên thấp`
- intent: `CREATE`
- expected title: `dọn tủ lạnh`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú làm vườn cuối tuần, ưu tiên thấp`
- intent: `CREATE`
- expected title: `làm vườn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `ưu tiên thấp`

- prompt: `Nhắc tôi xem phim vào tối mai, ưu tiên thấp`
- intent: `CREATE`
- expected title: `xem phim`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `ưu tiên thấp`

- prompt: `Ghi nhớ mua đồ ăn sáng, ưu tiên thấp`
- intent: `CREATE`
- expected title: `mua đồ ăn sáng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú mua quà sinh nhật, ưu tiên thấp`
- intent: `CREATE`
- expected title: `mua quà sinh nhật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tháng này
- expected notes: `ưu tiên thấp`

### 17. Cập nhật khẩn cấp

- prompt: `Cập nhật gấp thời gian hẹn khám bác sĩ sang 7h sáng`
- intent: `UPDATE`
- expected title: `thời gian hẹn khám bác sĩ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `7h sáng`
- expected notes: `urgent`

- prompt: `Cập nhật gấp hẹn spa sang sáng mai`
- intent: `UPDATE`
- expected title: `hẹn spa`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai`
- expected notes: `urgent`

- prompt: `Cập nhật gấp thời gian họp với nhóm sang 10h`
- intent: `UPDATE`
- expected title: `họp với nhóm`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `10h`
- expected notes: `urgent`

- prompt: `Cập nhật gấp deadline báo cáo sang 15h`
- intent: `UPDATE`
- expected title: `deadline báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `15h`
- expected notes: `urgent`

- prompt: `Cập nhật gấp cuộc họp sales sang chiều nay`
- intent: `UPDATE`
- expected title: `cuộc họp sales`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `chiều nay`
- expected notes: `urgent`

- prompt: `Cập nhật gấp lịch khám sang 8h sáng`
- intent: `UPDATE`
- expected title: `lịch khám`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `8h sáng`
- expected notes: `urgent`

- prompt: `Cập nhật gấp hẹn ăn trưa sang 12h`
- intent: `UPDATE`
- expected title: `hẹn ăn trưa`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `12h`
- expected notes: `urgent`

- prompt: `Cập nhật gấp task sửa máy tính sang chiều nay`
- intent: `UPDATE`
- expected title: `sửa máy tính`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `chiều nay`
- expected notes: `urgent`

- prompt: `Cập nhật gấp deadline đã hoàn thành`
- intent: `UPDATE`
- expected title: `deadline`
- expected priority: `HIGH`
- expected status: `COMPLETED`
- expected dueDate: none
- expected notes: `urgent`

- prompt: `Cập nhật gấp giờ khám nha khoa sang 9h`
- intent: `UPDATE`
- expected title: `giờ khám nha khoa`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `9h`
- expected notes: `urgent`

### 18. Cập nhật gấp cuộc họp khách hàng

- prompt: `Cập nhật gấp cuộc họp với khách hàng sang 15h30 thứ Sáu tuần sau, ưu tiên cao`
- intent: `UPDATE`
- expected title: `cuộc họp với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Sáu tuần sau lúc 15:30`
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp cuộc họp với khách hàng sang 16h thứ sáu`
- intent: `UPDATE`
- expected title: `cuộc họp với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Sáu lúc 16:00`
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp cuộc họp khách hàng sang 10h sáng mai`
- intent: `UPDATE`
- expected title: `cuộc họp khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 10:00`
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp lịch họp khách sang 14h chiều mai`
- intent: `UPDATE`
- expected title: `lịch họp khách`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `chiều mai lúc 14:00`
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp cuộc họp với khách hàng sang thứ hai`
- intent: `UPDATE`
- expected title: `cuộc họp với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Hai`
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp giờ họp khách sang 9h`
- intent: `UPDATE`
- expected title: `giờ họp khách`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `9h`
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp địa điểm họp khách hàng`
- intent: `UPDATE`
- expected title: `địa điểm họp khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp tên cuộc họp khách hàng`
- intent: `UPDATE`
- expected title: `tên cuộc họp khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp trạng thái họp khách hàng`
- intent: `UPDATE`
- expected title: `trạng thái họp khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp ghi chú cuộc họp khách`
- intent: `UPDATE`
- expected title: `ghi chú cuộc họp khách`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `ưu tiên cao`

### 19. Xem tất cả task

- prompt: `Cho tôi xem tất cả những việc cần làm`
- intent: `VIEW`
- expected title: `tất cả công việc`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

- prompt: `Xem tất cả công việc hôm nay`
- intent: `VIEW`
- expected title: `tất cả công việc`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `hôm nay`
- expected notes: `view all`

- prompt: `Cho tôi thấy tất cả task của tuần này`
- intent: `VIEW`
- expected title: `tất cả task`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `tuần này`
- expected notes: `view all`

- prompt: `Hiển thị tất cả nhiệm vụ đang chờ`
- intent: `VIEW`
- expected title: `tất cả nhiệm vụ đang chờ`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

- prompt: `Xem tất cả task đã lên lịch`
- intent: `VIEW`
- expected title: `tất cả task đã lên lịch`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

- prompt: `Cho tôi tất cả công việc quan trọng`
- intent: `VIEW`
- expected title: `tất cả công việc quan trọng`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

- prompt: `Xem tất cả task ưu tiên cao`
- intent: `VIEW`
- expected title: `tất cả task ưu tiên cao`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

- prompt: `Hiển thị tất cả công việc chưa xong`
- intent: `VIEW`
- expected title: `tất cả công việc chưa xong`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

- prompt: `Xem tất cả task`
- intent: `VIEW`
- expected title: `tất cả task`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

- prompt: `Xem tất cả công việc đã hẹn`
- intent: `VIEW`
- expected title: `tất cả công việc đã hẹn`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view all`

### 19. Task với ngày cụ thể và giờ

- prompt: `Tạo hẹn nộp hồ sơ ngày 15.6 lúc 16:00`
- intent: `CREATE`
- expected title: `nộp hồ sơ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-06-15T16:00:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`

- prompt: `Tạo lịch họp ngày 18/5 lúc 14h`
- intent: `CREATE`
- expected title: `lịch họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `18/05/2026 lúc 14:00`
- expected notes: `explicit date`

- prompt: `Tạo công việc nộp hồ sơ ngày 12/6 lúc 9h`
- intent: `CREATE`
- expected title: `nộp hồ sơ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `12/06/2026 lúc 09:00`
- expected notes: `explicit date`

- prompt: `Thêm event ngày 20/7 lúc 16h`
- intent: `CREATE`
- expected title: `event`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `20/07/2026 lúc 16:00`
- expected notes: `explicit date`

- prompt: `Tạo nhắc nhở ngày 03/05 lúc 10h sáng`
- intent: `CREATE`
- expected title: `nhắc nhở`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `03/05/2026 lúc 10:00`
- expected notes: `explicit date`

- prompt: `Ghi nhớ deadline 15/5 lúc 18h`
- intent: `CREATE`
- expected title: `deadline`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `15/05/2026 lúc 18:00`
- expected notes: `explicit date`

- prompt: `Thêm lịch tập gym ngày 25/5 lúc 6h`
- intent: `CREATE`
- expected title: `lịch tập gym`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: `25/05/2026 lúc 06:00`
- expected notes: `explicit date`

- prompt: `Thêm đi khám mắt ngày 30/5 lúc 8h`
- intent: `CREATE`
- expected title: `đi khám mắt`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `30/05/2026 lúc 08:00`
- expected notes: `explicit date`

- prompt: `Thêm nộp báo cáo ngày 22/5 lúc 14h`
- intent: `CREATE`
- expected title: `nộp báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `22/05/2026 lúc 14:00`
- expected notes: `explicit date`

- prompt: `Tạo hẹn sửa xe ngày 27/5 lúc 9h`
- intent: `CREATE`
- expected title: `hẹn sửa xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `27/05/2026 lúc 09:00`
- expected notes: `explicit date`

### 20. Thông báo không thêm

- prompt: `Tôi muốn xem lại những việc đã ghi`
- intent: `VIEW`
- expected title: `xem lại những việc đã ghi`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Cho tôi xem lại công việc đã ghi`
- intent: `VIEW`
- expected title: `xem lại công việc đã ghi`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Nhắc lại những việc cần làm`
- intent: `VIEW`
- expected title: `những việc cần làm`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Xem lại lịch trình`
- intent: `VIEW`
- expected title: `lịch trình`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Duyệt lại các ghi chú`
- intent: `VIEW`
- expected title: `các ghi chú`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Xem lịch của tôi`
- intent: `VIEW`
- expected title: `lịch của tôi`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Cho tôi thấy những việc chưa xong`
- intent: `VIEW`
- expected title: `những việc chưa xong`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Kiểm tra các task đã lưu`
- intent: `VIEW`
- expected title: `các task đã lưu`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Xem lại danh sách đã ghi`
- intent: `VIEW`
- expected title: `danh sách đã ghi`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

- prompt: `Xem lại những công việc`
- intent: `VIEW`
- expected title: `những công việc`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `review`

### 21. Gửi sếp khẩn cấp

- prompt: `Nhắc tôi gửi báo cáo cho sếp trước 10h sáng mai`
- intent: `CREATE`
- expected title: `gửi báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 10:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi proposal cho sếp trước 9h sáng`
- intent: `CREATE`
- expected title: `gửi proposal cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 09:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi bảng tiến độ cho sếp chiều nay`
- intent: `CREATE`
- expected title: `gửi bảng tiến độ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi file KPI cho sếp tối nay`
- intent: `CREATE`
- expected title: `gửi file KPI cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi báo cáo tổng kết cho sếp trước 5h`
- intent: `CREATE`
- expected title: `gửi báo cáo tổng kết cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 17:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi ngân sách cho sếp trước trưa mai`
- intent: `CREATE`
- expected title: `gửi ngân sách cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 12:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi báo cáo dự án cho sếp trước 4h`
- intent: `CREATE`
- expected title: `gửi báo cáo dự án cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi kế hoạch tháng này cho sếp`
- intent: `CREATE`
- expected title: `gửi kế hoạch tháng này cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi báo cáo tài chính cho sếp sáng mai`
- intent: `CREATE`
- expected title: `gửi báo cáo tài chính cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 09:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nhắc tôi gửi biên bản họp cho sếp tối nay`
- intent: `CREATE`
- expected title: `gửi biên bản họp cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gửi sếp`, `deadline`

- prompt: `Gửi bản thảo vào 10h tối nay`
- intent: `CREATE`
- expected title: `gửi bản thảo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 22:00
- expected notes: `gửi bản thảo`, `task sáng tạo`

- prompt: `Hôm nay gửi bản thảo lúc 10h tối`
- intent: `CREATE`
- expected title: `gửi bản thảo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 22:00
- expected notes: `gửi bản thảo`, `task sáng tạo`

- prompt: `Sáng mốt đi mua quà sinh nhật lúc 10h sáng`
- intent: `CREATE`
- expected title: `đi mua quà sinh nhật`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mốt lúc 10:00
- expected notes: `quà sinh nhật`, `task cá nhân`

- prompt: `Nhắc tôi đi tập gym vào 6h chiều ngày mai`
- intent: `CREATE`
- expected title: `đi tập gym`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 18:00
- expected notes: `thể thao`, `nhắc`

- prompt: `Lên lịch cho tôi đi tập gym vào 6h chiều ngày mai`
- intent: `CREATE`
- expected title: `đi tập gym`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 18:00
- expected notes: `thể thao`, `lên lịch`

### 22. Cuộc họp tuần sau

- prompt: `Tạo lịch họp cấp bách với khách hàng thứ sáu tuần sau`
- intent: `CREATE`
- expected title: `lịch họp cấp bách với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ Sáu tuần sau
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Thêm cuộc họp với team tuần sau`
- intent: `CREATE`
- expected title: `cuộc họp với team`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Tạo cuộc họp chiến lược tuần sau`
- intent: `CREATE`
- expected title: `cuộc họp chiến lược`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Thêm họp đánh giá tuần sau`
- intent: `CREATE`
- expected title: `họp đánh giá`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Lên lịch họp dự án tuần sau`
- intent: `CREATE`
- expected title: `họp dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Thêm họp sales tuần sau`
- intent: `CREATE`
- expected title: `họp sales`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Tạo họp training tuần sau`
- intent: `CREATE`
- expected title: `họp training`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Lên lịch họp phản hồi khách hàng tuần sau`
- intent: `CREATE`
- expected title: `họp phản hồi khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Tạo họp nội bộ tuần sau`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

- prompt: `Đặt lịch họp demo tuần sau`
- intent: `CREATE`
- expected title: `họp demo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

### 23. Cập nhật gấp cuộc hẹn

- prompt: `Cập nhật gấp cuộc họp với khách hàng sang 15h30 thứ sáu tuần sau, ưu tiên cao`
- intent: `UPDATE`
- expected title: `cuộc họp với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ Sáu tuần sau lúc 15:30
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp cuộc hẹn với đối tác sang 9h sáng mai`
- intent: `UPDATE`
- expected title: `cuộc hẹn với đối tác`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp hẹn gặp sếp sang chiều mai`
- intent: `UPDATE`
- expected title: `hẹn gặp sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp lịch họp sang 3h chiều mai`
- intent: `UPDATE`
- expected title: `lịch họp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 15:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp hẹn thi sang 16h`
- intent: `UPDATE`
- expected title: `hẹn thi`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp lịch review sang 11h`
- intent: `UPDATE`
- expected title: `lịch review`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 11:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp lịch hướng dẫn sang 14h`
- intent: `UPDATE`
- expected title: `lịch hướng dẫn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 14:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp lịch thảo luận sang 15h`
- intent: `UPDATE`
- expected title: `lịch thảo luận`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 15:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp lịch training sang 1h chiều`
- intent: `UPDATE`
- expected title: `lịch training`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 13:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Cập nhật gấp cuộc họp sang 2h chiều`
- intent: `UPDATE`
- expected title: `cuộc họp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 14:00
- expected notes: `gấp`, `ưu tiên cao`

### 24. Kiểm tra lịch với sếp

- prompt: `Xem lịch họp với sếp tuần sau`
- intent: `VIEW`
- expected title: `lịch họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `view`

- prompt: `Xem lịch họp với sếp hôm nay`
- intent: `VIEW`
- expected title: `lịch họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `view`

- prompt: `Xem lịch họp với sếp trong tuần này`
- intent: `VIEW`
- expected title: `lịch họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `view`

- prompt: `Kiểm tra lịch họp với sếp`
- intent: `VIEW`
- expected title: `lịch họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view`

- prompt: `Cho tôi xem lịch họp với sếp`
- intent: `VIEW`
- expected title: `lịch họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view`

- prompt: `Hiển thị cuộc họp với sếp`
- intent: `VIEW`
- expected title: `cuộc họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view`

- prompt: `Xem lịch gặp sếp`
- intent: `VIEW`
- expected title: `lịch gặp sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view`

- prompt: `Xem lịch họp sếp vào tuần sau`
- intent: `VIEW`
- expected title: `lịch họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `view`

- prompt: `Xem các cuộc họp với sếp`
- intent: `VIEW`
- expected title: `các cuộc họp với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view`

- prompt: `Xem lịch họp với sếp và khách hàng`
- intent: `VIEW`
- expected title: `lịch họp với sếp và khách hàng`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `view`

### 25. Ưu tiên cao vì deadline

- prompt: `Hoàn thiện đề cương trước 17h ngày mai`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 17:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 8h sáng mai`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 08:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước trưa mai`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 12:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 3h chiều hôm nay`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 15:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 18h hôm nay`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 18:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 7h tối nay`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 19:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 5h chiều mai`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 17:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 10h sáng mai`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 11h trưa mai`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 11:00
- expected notes: `deadline`

- prompt: `Hoàn thiện đề cương trước 16h hôm nay`
- intent: `CREATE`
- expected title: `hoàn thiện đề cương`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `deadline`

### 26. Nhắc gọi sếp

- prompt: `Nhắc tôi gọi điện cho sếp chiều mai`
- intent: `CREATE`
- expected title: `gọi điện cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi điện cho sếp sáng mai`
- intent: `CREATE`
- expected title: `gọi điện cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi điện cho sếp trước trưa mai`
- intent: `CREATE`
- expected title: `gọi điện cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 12:00
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi điện cho sếp giờ nghỉ trưa`
- intent: `CREATE`
- expected title: `gọi điện cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 12:00
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi điện cho sếp trước 6h tối`
- intent: `CREATE`
- expected title: `gọi điện cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 18:00
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi điện báo cáo cho sếp`
- intent: `CREATE`
- expected title: `gọi điện báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi sếp để xin ý kiến`
- intent: `CREATE`
- expected title: `gọi sếp để xin ý kiến`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi sếp ngay khi rảnh`
- intent: `CREATE`
- expected title: `gọi sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngay khi rảnh
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi sếp để báo cáo tiến độ`
- intent: `CREATE`
- expected title: `gọi sếp báo cáo tiến độ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gửi sếp`

- prompt: `Nhắc tôi gọi sếp vào chiều nay`
- intent: `CREATE`
- expected title: `gọi sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `gửi sếp`

### 27. Gấp và ưu tiên thấp

- prompt: `Ghi chú mua sữa tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `mua sữa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú mua bánh mì tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `mua bánh mì`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú dọn phòng tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `dọn phòng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú tưới cây tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `tưới cây`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú mua giấy in tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `mua giấy in`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú trả lời tin nhắn tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `trả lời tin nhắn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú đặt nước hoa tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `đặt nước hoa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú xếp giày tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `xếp giày`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú sắp xếp tài liệu tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `sắp xếp tài liệu`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

- prompt: `Ghi chú rửa bát tối nay, ưu tiên thấp`
- intent: `CREATE`
- expected title: `rửa bát`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `ưu tiên thấp`

### 28. Buổi sáng tuần sau

- prompt: `Thêm cuộc họp trực tuyến thứ tư tuần sau lúc 14h`
- intent: `CREATE`
- expected title: `cuộc họp trực tuyến`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ Tư tuần sau lúc 14:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp thứ hai tuần sau lúc 9h`
- intent: `CREATE`
- expected title: `họp sáng thứ hai`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ Hai tuần sau lúc 09:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp thứ ba tuần sau lúc 10h`
- intent: `CREATE`
- expected title: `họp sáng thứ ba`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ Ba tuần sau lúc 10:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp thứ tư tuần sau lúc 8h30`
- intent: `CREATE`
- expected title: `họp sáng thứ tư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ Tư tuần sau lúc 08:30
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp thứ năm tuần sau lúc 9h`
- intent: `CREATE`
- expected title: `họp sáng thứ năm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ Năm tuần sau lúc 09:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp thứ sáu tuần sau lúc 8h`
- intent: `CREATE`
- expected title: `họp sáng thứ sáu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ Sáu tuần sau lúc 08:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp thứ bảy tuần sau lúc 9h`
- intent: `CREATE`
- expected title: `họp sáng thứ bảy`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ Bảy tuần sau lúc 09:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp tuần sau với đối tác lúc 9h`
- intent: `CREATE`
- expected title: `họp sáng với đối tác`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau lúc 09:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp tuần sau với nhóm lúc 10h`
- intent: `CREATE`
- expected title: `họp sáng với nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau lúc 10:00
- expected notes: `tuần sau`

- prompt: `Thêm buổi sáng họp tuần sau`
- intent: `CREATE`
- expected title: `buổi sáng họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `tuần sau`

### 29. Nộp hồ sơ trưởng phòng

- prompt: `Nộp hồ sơ cho trưởng phòng vào ngày mai`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gửi sếp`, `ưu tiên cao`

- prompt: `Nộp hồ sơ cho trưởng phòng hôm nay`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gửi sếp`, `ưu tiên cao`

- prompt: `Nộp hồ sơ cho trưởng phòng trước chiều mai`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `gửi sếp`, `ưu tiên cao`

- prompt: `Nộp hồ sơ cho trưởng phòng trước 5h`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 17:00
- expected notes: `gửi sếp`, `ưu tiên cao`

- prompt: `Nộp hồ sơ cho trưởng phòng trước trưa mai`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 12:00
- expected notes: `gửi sếp`, `ưu tiên cao`

- prompt: `Nộp hồ sơ cho trưởng phòng vào thứ hai tuần sau`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ Hai tuần sau
- expected notes: `gửi sếp`, `ưu tiên cao`

- prompt: `Nộp hồ sơ cho trưởng phòng hôm 15/5`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: 15/05/2026
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho trưởng phòng trước 4h chiều`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nộp hồ sơ cho trưởng phòng vào ngày mai 9h`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 09:00
- expected notes: `gửi sếp`, `deadline`

- prompt: `Nộp hồ sơ cho trưởng phòng trước 8h sáng mai`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho trưởng phòng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 08:00
- expected notes: `gửi sếp`, `deadline`

### 30. Thông báo gấp giờ

- prompt: `Ưu tiên gấp hoàn thiện bài tập nhóm trước 8h sáng mai`
- intent: `CREATE`
- expected title: `hoàn thiện bài tập nhóm`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 08:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Ưu tiên gấp làm báo cáo trước 9h sáng mai`
- intent: `CREATE`
- expected title: `làm báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `gấp`, `ưu tiên cao`

- prompt: `Ưu tiên gấp nộp hồ sơ trước 10h sáng mai`
- intent: `CREATE`
- expected title: `nộp hồ sơ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp hoàn thành slide trước 11h trưa mai`
- intent: `CREATE`
- expected title: `hoàn thành slide`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 11:00
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp chuẩn bị tài liệu trước 3h chiều mai`
- intent: `CREATE`
- expected title: `chuẩn bị tài liệu`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 15:00
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp gửi email quan trọng trước trưa mai`
- intent: `CREATE`
- expected title: `gửi email quan trọng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 12:00
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp chuẩn bị cuộc họp trước 4h chiều`
- intent: `CREATE`
- expected title: `chuẩn bị cuộc họp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp hoàn thành báo cáo trước 5h chiều`
- intent: `CREATE`
- expected title: `hoàn thành báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 17:00
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp xử lý công việc trước 6h tối`
- intent: `CREATE`
- expected title: `xử lý công việc`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 18:00
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp chuẩn bị thư mời trước 2h chiều`
- intent: `CREATE`
- expected title: `chuẩn bị thư mời`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 14:00
- expected notes: `gấp`, `deadline`

### 31. Ngày cụ thể tháng 4

- prompt: `Thêm nộp báo cáo ngày 20 tháng 4 lúc 7h sáng`
- intent: `CREATE`
- expected title: `nộp báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-04-20T07:00:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm họp dự án ngày 22 tháng 4 lúc 9h`
- intent: `CREATE`
- expected title: `họp dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-04-22T09:00:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm nộp đơn xin nghỉ phép ngày 25 tháng 4`
- intent: `CREATE`
- expected title: `nộp đơn xin nghỉ phép`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-04-25`
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm tham dự hội thảo ngày 28 tháng 4 lúc 14h`
- intent: `CREATE`
- expected title: `tham dự hội thảo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-04-28T14:00:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm báo cáo thuế ngày 30 tháng 4`
- intent: `CREATE`
- expected title: `báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-30`
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm đặt vé máy bay ngày 24 tháng 4 lúc 18h`
- intent: `CREATE`
- expected title: `đặt vé máy bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-04-24T18:00:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm đóng tiền điện ngày 26 tháng 4`
- intent: `CREATE`
- expected title: `đóng tiền điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-04-26`
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm kiểm tra tiến độ dự án ngày 23 tháng 4 lúc 16h`
- intent: `CREATE`
- expected title: `kiểm tra tiến độ dự án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-23T16:00:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm họp báo cáo ngân sách ngày 27 tháng 4 lúc 10h`
- intent: `CREATE`
- expected title: `họp báo cáo ngân sách`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-27T10:00:00Z` hoặc ISO tương ứng
- expected notes: `explicit date`, `tháng 4`

- prompt: `Thêm gửi báo cáo khách hàng ngày 29 tháng 4`
- intent: `CREATE`
- expected title: `gửi báo cáo khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `2026-04-29`
- expected notes: `explicit date`, `tháng 4`

### 32. Gặp sếp tuần sau

- prompt: `Tạo lịch họp với sếp thứ sáu tuần sau, ưu tiên cao`
- intent: `CREATE`
- expected title: `lịch họp với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Sáu tuần sau`
- expected notes: `gửi sếp`, `tuần sau`, `ưu tiên cao`

- prompt: `Thêm cuộc họp với sếp tuần sau`
- intent: `CREATE`
- expected title: `cuộc họp với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Gặp sếp tuần sau để báo cáo tiến độ`
- intent: `CREATE`
- expected title: `gặp sếp để báo cáo tiến độ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Lên lịch gặp sếp tuần sau`
- intent: `CREATE`
- expected title: `gặp sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Gặp sếp tuần sau để duyệt tài liệu`
- intent: `CREATE`
- expected title: `gặp sếp để duyệt tài liệu`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Tạo lịch gặp sếp tuần sau lúc 10h`
- intent: `CREATE`
- expected title: `lịch gặp sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau lúc 10:00`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Thêm buổi trao đổi với sếp tuần sau`
- intent: `CREATE`
- expected title: `trao đổi với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Lên lịch gọi sếp tuần sau`
- intent: `CREATE`
- expected title: `gọi sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Xem lịch gặp sếp tuần sau`
- intent: `VIEW`
- expected title: `lịch gặp sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

- prompt: `Thêm trao đổi nhanh với sếp tuần sau`
- intent: `CREATE`
- expected title: `trao đổi nhanh với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gặp sếp`, `tuần sau`

### 33. Nộp báo cáo cho sếp ngày 20 tháng 4

- prompt: `Nộp báo cáo cho sếp ngày 20 tháng 4 lúc 7h sáng`
- intent: `CREATE`
- expected title: `nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20T07:00:00Z` hoặc ISO tương ứng
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Nộp báo cáo cho sếp ngày 20 tháng 4 lúc 9h`
- intent: `CREATE`
- expected title: `nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20T09:00:00Z` hoặc ISO tương ứng
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Gửi báo cáo cho sếp ngày 20/4`
- intent: `CREATE`
- expected title: `gửi báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Nộp báo cáo cho sếp ngày 20/4 lúc 8h30`
- intent: `CREATE`
- expected title: `nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20T08:30:00Z` hoặc ISO tương ứng
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Nộp báo cáo cho sếp ngày 20 tháng 4 buổi sáng`
- intent: `CREATE`
- expected title: `nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Nộp báo cáo cho sếp ngày 20 tháng 4 buổi chiều`
- intent: `CREATE`
- expected title: `nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Chuẩn bị và nộp báo cáo cho sếp ngày 20 tháng 4`
- intent: `CREATE`
- expected title: `chuẩn bị và nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Nộp báo cáo gửi sếp ngày 20 tháng 4`
- intent: `CREATE`
- expected title: `nộp báo cáo gửi sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Nộp báo cáo cho sếp ngày 20 tháng 4 vào buổi sáng`
- intent: `CREATE`
- expected title: `nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

- prompt: `Nộp báo cáo cho sếp ngày 20 tháng 4 trước 10h`
- intent: `CREATE`
- expected title: `nộp báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20T10:00:00Z` hoặc ISO tương ứng
- expected notes: `gửi sếp`, `explicit date`, `tháng 4`

### 34. Họp khẩn với sếp tuần sau

- prompt: `Thêm cuộc họp khẩn với sếp thứ ba tuần sau lúc 10h30`
- intent: `CREATE`
- expected title: `cuộc họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Ba tuần sau lúc 10:30`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Thêm cuộc họp khẩn với sếp tuần sau`
- intent: `CREATE`
- expected title: `cuộc họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Lên lịch họp khẩn với sếp tuần sau`
- intent: `CREATE`
- expected title: `họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Họp khẩn với sếp tuần sau vào 9h sáng`
- intent: `CREATE`
- expected title: `họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau lúc 09:00`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Họp khẩn với sếp tuần sau lúc 14h`
- intent: `CREATE`
- expected title: `họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau lúc 14:00`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Tổ chức họp khẩn với sếp tuần sau`
- intent: `CREATE`
- expected title: `tổ chức họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Sắp xếp họp khẩn với sếp tuần sau`
- intent: `CREATE`
- expected title: `sắp xếp họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Gặp sếp khẩn tuần sau`
- intent: `CREATE`
- expected title: `gặp sếp khẩn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Xem lịch họp khẩn với sếp tuần sau`
- intent: `VIEW`
- expected title: `lịch họp khẩn với sếp`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

- prompt: `Chuẩn bị họp khẩn với sếp tuần sau`
- intent: `CREATE`
- expected title: `chuẩn bị họp khẩn với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `gửi sếp`, `tuần sau`

### 35. Cập nhật gấp hợp đồng

- prompt: `Cập nhật gấp hợp đồng với khách hàng vào thứ năm tuần sau`
- intent: `UPDATE`
- expected title: `hợp đồng với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Năm tuần sau`
- expected notes: `gấp`, `tuần sau`

- prompt: `Cập nhật gấp điều khoản hợp đồng`
- intent: `UPDATE`
- expected title: `điều khoản hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp ngày ký hợp đồng sang tuần sau`
- intent: `UPDATE`
- expected title: `ngày ký hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp tên hợp đồng`
- intent: `UPDATE`
- expected title: `tên hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp đối tác hợp đồng`
- intent: `UPDATE`
- expected title: `đối tác hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp giá trị hợp đồng`
- intent: `UPDATE`
- expected title: `giá trị hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp thời hạn hợp đồng`
- intent: `UPDATE`
- expected title: `thời hạn hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp ngày bắt đầu hợp đồng sang thứ hai`
- intent: `UPDATE`
- expected title: `ngày bắt đầu hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Hai`
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp ngày kết thúc hợp đồng`
- intent: `UPDATE`
- expected title: `ngày kết thúc hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `hợp đồng`

- prompt: `Cập nhật gấp ký kết hợp đồng`
- intent: `UPDATE`
- expected title: `ký kết hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `hợp đồng`

### 36. Gửi email đầu tuần sau

- prompt: `Gửi email cho sếp đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gửi sếp`, `tuần sau`

- prompt: `Gửi email báo cáo đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `gửi sếp`, `tuần sau`

- prompt: `Gửi email xác nhận đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email xác nhận`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

- prompt: `Gửi email mời họp đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email mời họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

- prompt: `Gửi email hợp đồng đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email hợp đồng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

- prompt: `Gửi email phê duyệt đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email phê duyệt`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

- prompt: `Gửi email yêu cầu thông tin đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email yêu cầu thông tin`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

- prompt: `Gửi email nhắc deadline đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email nhắc deadline`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

- prompt: `Gửi email lịch họp đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email lịch họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

- prompt: `Gửi email đính kèm tài liệu đầu tuần sau`
- intent: `CREATE`
- expected title: `gửi email đính kèm tài liệu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `tuần sau`

### 37. Ưu tiên gấp báo cáo thuế

- prompt: `Nhắc tôi gửi báo cáo thuế gấp sáng mai`
- intent: `CREATE`
- expected title: `gửi báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai`
- expected notes: `gấp`, `sáng mai`

- prompt: `Gửi báo cáo thuế gấp chiều nay`
- intent: `CREATE`
- expected title: `gửi báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `chiều nay`
- expected notes: `gấp`, `deadline`

- prompt: `Nộp báo cáo thuế gấp trước trưa mai`
- intent: `CREATE`
- expected title: `nộp báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 12:00`
- expected notes: `gấp`, `deadline`

- prompt: `Hoàn thiện báo cáo thuế gấp sáng mai`
- intent: `CREATE`
- expected title: `hoàn thiện báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai`
- expected notes: `gấp`, `deadline`

- prompt: `Gửi báo cáo thuế gấp ngày mai`
- intent: `CREATE`
- expected title: `gửi báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai`
- expected notes: `gấp`, `deadline`

- prompt: `Nhắc gấp nộp báo cáo thuế trước 5h`
- intent: `CREATE`
- expected title: `nộp báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 17:00`
- expected notes: `gấp`, `deadline`

- prompt: `Nhắc gấp gửi báo cáo thuế khách hàng`
- intent: `CREATE`
- expected title: `gửi báo cáo thuế khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai`
- expected notes: `gấp`, `deadline`

- prompt: `Nộp báo cáo thuế gấp vào sáng mai`
- intent: `CREATE`
- expected title: `nộp báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai`
- expected notes: `gấp`, `deadline`

- prompt: `Cập nhật gấp báo cáo thuế`
- intent: `UPDATE`
- expected title: `báo cáo thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `deadline`

- prompt: `Xem lại báo cáo thuế gấp`
- intent: `VIEW`
- expected title: `báo cáo thuế`
- expected priority: none
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `gấp`, `deadline`

### 38. Nộp hồ sơ sếp tháng 4

- prompt: `Nộp hồ sơ cho sếp vào ngày 20 tháng 4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp vào ngày 21 tháng 4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-21`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp vào 22/4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-22`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp vào 23/4 lúc 9h`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-23T09:00:00Z` hoặc ISO tương ứng
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp ngày 24 tháng 4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-24`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp ngày 25/4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-25`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp vào 26/4 lúc 10h`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-26T10:00:00Z` hoặc ISO tương ứng
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp ngày 27 tháng 4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-27`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp ngày 28 tháng 4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-28`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Nộp hồ sơ cho sếp ngày 29 tháng 4`
- intent: `CREATE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-29`
- expected notes: `gửi sếp`, `explicit date`

### 39. Thêm deadline gấp

- prompt: `Ưu tiên gấp hoàn thiện báo cáo trước 16h thứ sáu`
- intent: `CREATE`
- expected title: `hoàn thiện báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `thứ Sáu lúc 16:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp gửi hồ sơ trước 10h sáng mai`
- intent: `CREATE`
- expected title: `gửi hồ sơ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 10:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp hoàn thành báo cáo trước trưa mai`
- intent: `CREATE`
- expected title: `hoàn thành báo cáo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 12:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp hoàn thành việc trước 5h chiều`
- intent: `CREATE`
- expected title: `hoàn thành việc`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 17:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp chuẩn bị tài liệu trước 4h chiều`
- intent: `CREATE`
- expected title: `chuẩn bị tài liệu`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 16:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp hoàn thiện slide trước 11h trưa mai`
- intent: `CREATE`
- expected title: `hoàn thiện slide`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 11:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp trả lời email trước 3h`
- intent: `CREATE`
- expected title: `trả lời email`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 15:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp họp online trước 2h chiều`
- intent: `CREATE`
- expected title: `họp online`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 14:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp nộp báo cáo dự án trước 9h sáng mai`
- intent: `CREATE`
- expected title: `nộp báo cáo dự án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 09:00`
- expected notes: `gấp`, `deadline`

- prompt: `Ưu tiên gấp hoàn thành đề án trước 5h chiều`
- intent: `CREATE`
- expected title: `hoàn thành đề án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 17:00`
- expected notes: `gấp`, `deadline`

### 40. Chuẩn bị slide cho sếp

- prompt: `Chuẩn bị slide gửi sếp vào ngày 20 tháng 4`
- intent: `CREATE`
- expected title: `chuẩn bị slide gửi sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `2026-04-20`
- expected notes: `gửi sếp`, `explicit date`

- prompt: `Chuẩn bị slide cho sếp trước 9h sáng mai`
- intent: `CREATE`
- expected title: `chuẩn bị slide cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 09:00`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide cho sếp trước trưa mai`
- intent: `CREATE`
- expected title: `chuẩn bị slide cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 12:00`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide cho sếp trước 3h chiều mai`
- intent: `CREATE`
- expected title: `chuẩn bị slide cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `chiều mai lúc 15:00`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide báo cáo cho sếp trước 10h sáng`
- intent: `CREATE`
- expected title: `chuẩn bị slide báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `sáng mai lúc 10:00`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide thuyết trình cho sếp`
- intent: `CREATE`
- expected title: `chuẩn bị slide thuyết trình cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide cho sếp trong ngày mai`
- intent: `CREATE`
- expected title: `chuẩn bị slide cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide cho sếp trước 11h trưa mai`
- intent: `CREATE`
- expected title: `chuẩn bị slide cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `ngày mai lúc 11:00`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide thuyết trình sếp trước 4h chiều`
- intent: `CREATE`
- expected title: `chuẩn bị slide thuyết trình sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 16:00`
- expected notes: `gửi sếp`, `deadline`

- prompt: `Chuẩn bị slide cho sếp trước 2h chiều`
- intent: `CREATE`
- expected title: `chuẩn bị slide cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: `hôm nay lúc 14:00`
- expected notes: `gửi sếp`, `deadline`

### 41. Thứ bảy đi chơi

- prompt: `Thứ 7 tuần này tôi qua nhà anh hai chơi lúc tối`
- intent: `CREATE`
- expected title: `qua nhà anh hai chơi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `thứ 7 tuần này lúc tối`
- expected notes: `câu tạo task cá nhân`

### 42. Xóa cuộc họp để giải phóng lịch

- prompt: `Xóa cuộc họp với sếp vào thứ sáu tuần sau`
- intent: `DELETE`
- expected title: `cuộc họp với sếp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `thứ Sáu tuần sau`
- expected notes: `delete`

### 43. Loại bỏ nhắc nhở

- prompt: `Loại bỏ nhắc nhở báo cáo tài chính ngay hôm nay`
- intent: `DELETE`
- expected title: `nhắc nhở báo cáo tài chính`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `hôm nay`
- expected notes: `delete`

### 44. Hủy hẹn

- prompt: `Hủy cuộc hẹn tối nay với anh hai`
- intent: `DELETE`
- expected title: `cuộc hẹn với anh hai`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tối nay`
- expected notes: `delete`

### 45. Xóa task mua quà cho sếp

- prompt: `Xóa task mua quà cho sếp`
- intent: `DELETE`
- expected title: `mua quà cho sếp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

### 46. Xóa nhắc nhở họp nhóm

- prompt: `Xóa nhắc nhở họp nhóm tuần sau`
- intent: `DELETE`
- expected title: `nhắc nhở họp nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `tuần sau`
- expected notes: `delete`

### 47. Gỡ công việc cũ

- prompt: `Gỡ bỏ công việc nộp hồ sơ cho sếp ngày mai`
- intent: `DELETE`
- expected title: `nộp hồ sơ cho sếp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `ngày mai`
- expected notes: `delete`

### 48. Task cá nhân ở nhà anh hai

- prompt: `Cần ghi nhớ tôi qua nhà anh hai chơi tối thứ bảy`
- intent: `CREATE`
- expected title: `qua nhà anh hai chơi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `thứ bảy tối`
- expected notes: `task cá nhân`

### 49. Xóa nhiệm vụ không cần thiết

- prompt: `Xóa nhiệm vụ không cần nữa`
- intent: `DELETE`
- expected title: `nhiệm vụ không cần nữa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `delete`

### 50. Ghi nhớ cuối tuần

- prompt: `Ghi nhớ cuối tuần qua nhà anh hai chơi`
- intent: `CREATE`
- expected title: `qua nhà anh hai chơi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `cuối tuần`
- expected notes: `task cá nhân`

### 41. Việc cá nhân đi ra ngoài

- prompt: `Thứ 2 tuần sau tôi có việc phải đi ra ngoài lúc 3h chiều`
- intent: `CREATE`
- expected title: `đi ra ngoài`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2 tuần sau lúc 15:00
- expected notes: `việc cá nhân`

- prompt: `Thứ 3 tôi cần đi ra ngoài mua đồ`
- intent: `CREATE`
- expected title: `đi ra ngoài mua đồ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3
- expected notes: `việc cá nhân`

- prompt: `Sáng mai tôi cần ra ngoài lấy thuốc`
- intent: `CREATE`
- expected title: `ra ngoài lấy thuốc`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc cá nhân`

- prompt: `Chiều nay tôi có việc ra ngoài ngân hàng`
- intent: `CREATE`
- expected title: `ra ngoài ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc cá nhân`

- prompt: `Thứ 7 này tôi phải ra ngoài làm giấy tờ`
- intent: `CREATE`
- expected title: `ra ngoài làm giấy tờ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `việc cá nhân`

- prompt: `Tối mai tôi có việc ngoài đường phải làm`
- intent: `CREATE`
- expected title: `việc ngoài đường`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `việc cá nhân`

- prompt: `Mai tôi phải đi ra ngoài gửi thư`
- intent: `CREATE`
- expected title: `đi ra ngoài gửi thư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Sáng thứ 4 tôi cần chạy ra ngoài công ty`
- intent: `CREATE`
- expected title: `chạy ra ngoài công ty`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4
- expected notes: `việc cá nhân`

- prompt: `Tuần tới tôi có việc phải ra ngoài đột xuất`
- intent: `CREATE`
- expected title: `việc phải ra ngoài đột xuất`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần tới
- expected notes: `việc cá nhân`

- prompt: `Cuối tuần này tôi có việc đi ra ngoài`
- intent: `CREATE`
- expected title: `đi ra ngoài`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

### 42. Việc gia đình

- prompt: `Chiều mai tôi có việc gia đình phải về sớm`
- intent: `CREATE`
- expected title: `việc gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `việc cá nhân`

- prompt: `Tối mai tôi có việc gia đình sang nhà ngoại`
- intent: `CREATE`
- expected title: `việc gia đình sang nhà ngoại`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `việc gia đình`

- prompt: `Sáng chủ nhật tôi có việc gia đình`
- intent: `CREATE`
- expected title: `việc gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chủ nhật
- expected notes: `việc gia đình`

- prompt: `Thứ 7 này tôi có việc gia đình phải về quê`
- intent: `CREATE`
- expected title: `việc gia đình về quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `việc gia đình`

- prompt: `Ngày mai tôi phải giúp việc gia đình`
- intent: `CREATE`
- expected title: `giúp việc gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc gia đình`

- prompt: `Cuối tuần này tôi có việc gia đình cần xử lý`
- intent: `CREATE`
- expected title: `việc gia đình cần xử lý`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc gia đình`

- prompt: `Tôi cần sắp xếp việc gia đình hôm nay`
- intent: `CREATE`
- expected title: `sắp xếp việc gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc gia đình`

- prompt: `Tôi có lịch việc gia đình tuần sau`
- intent: `CREATE`
- expected title: `lịch việc gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `việc gia đình`

- prompt: `Tôi có việc gia đình phải giải quyết ngay`
- intent: `CREATE`
- expected title: `việc gia đình phải giải quyết`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc gia đình`

- prompt: `Tôi có việc gia đình cần làm buổi chiều`
- intent: `CREATE`
- expected title: `việc gia đình cần làm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc gia đình`

### 43. Đi khám bệnh

- prompt: `Thứ 3 tuần tới tôi đi khám bệnh lúc 10h sáng`
- intent: `CREATE`
- expected title: `khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3 tuần tới lúc 10:00
- expected notes: `việc cá nhân`

- prompt: `Thêm lịch khám bệnh ngày 5/5 lúc 9h`
- intent: `CREATE`
- expected title: `lịch khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 05/05/2026 lúc 09:00
- expected notes: `sức khỏe`

- prompt: `Tối nay tôi phải đi khám bệnh`
- intent: `CREATE`
- expected title: `đi khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `sức khỏe`

- prompt: `Sáng mai tôi đi khám sức khỏe`
- intent: `CREATE`
- expected title: `đi khám sức khỏe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tuần sau tôi có lịch khám bệnh`
- intent: `CREATE`
- expected title: `lịch khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `sức khỏe`

- prompt: `Ngày mai đi khám mắt lúc 10h`
- intent: `CREATE`
- expected title: `đi khám mắt`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `sức khỏe`

- prompt: `Thứ 5 này khám nha khoa lúc 14h`
- intent: `CREATE`
- expected title: `khám nha khoa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5
- expected notes: `sức khỏe`

- prompt: `Thứ 6 tuần sau tôi khám tổng quát`
- intent: `CREATE`
- expected title: `khám tổng quát`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6 tuần sau
- expected notes: `sức khỏe`

- prompt: `Thêm lịch khám bệnh định kỳ`
- intent: `CREATE`
- expected title: `lịch khám bệnh định kỳ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `sức khỏe`

### 44. Gặp luật sư

- prompt: `Tuần tới tôi phải đi gặp luật sư lúc 2h chiều`
- intent: `CREATE`
- expected title: `gặp luật sư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần tới lúc 14:00
- expected notes: `việc cá nhân`

- prompt: `Mai tôi có hẹn gặp luật sư`
- intent: `CREATE`
- expected title: `gặp luật sư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Thứ 4 tôi gặp luật sư tại văn phòng`
- intent: `CREATE`
- expected title: `gặp luật sư tại văn phòng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4
- expected notes: `việc cá nhân`

- prompt: `Cuối tuần này gặp luật sư`
- intent: `CREATE`
- expected title: `gặp luật sư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

- prompt: `Thứ 5 gặp luật sư giải quyết hợp đồng`
- intent: `CREATE`
- expected title: `gặp luật sư giải quyết hợp đồng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5
- expected notes: `việc cá nhân`

- prompt: `Hẹn luật sư tuần sau lúc 10h`
- intent: `CREATE`
- expected title: `hẹn luật sư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `việc cá nhân`

- prompt: `Đi gặp luật sư vào 15h chiều mai`
- intent: `CREATE`
- expected title: `đi gặp luật sư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 15:00
- expected notes: `việc cá nhân`

- prompt: `Thêm lịch gặp luật sư vào thứ hai tới`
- intent: `CREATE`
- expected title: `lịch gặp luật sư`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ hai tới
- expected notes: `việc cá nhân`

- prompt: `Tôi cần gặp luật sư để tư vấn`
- intent: `CREATE`
- expected title: `gặp luật sư để tư vấn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `việc cá nhân`

### 45. Đám cưới ở quê

- prompt: `Thứ 7 tuần sau có đám cưới ở quê`
- intent: `CREATE`
- expected title: `đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7 tuần sau
- expected notes: `việc cá nhân`

- prompt: `Cuối tuần này tôi có đám cưới ở quê`
- intent: `CREATE`
- expected title: `đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

- prompt: `Tham dự đám cưới ở quê vào thứ bảy`
- intent: `CREATE`
- expected title: `tham dự đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ bảy
- expected notes: `việc cá nhân`

- prompt: `Đi đám cưới ở quê tuần sau`
- intent: `CREATE`
- expected title: `đi đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `việc cá nhân`

- prompt: `Thứ 7 này tham dự đám cưới ở quê`
- intent: `CREATE`
- expected title: `tham dự đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `việc cá nhân`

- prompt: `Chuẩn bị hành lý đi đám cưới ở quê`
- intent: `CREATE`
- expected title: `chuẩn bị hành lý đi đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: vài ngày nữa
- expected notes: `việc cá nhân`

- prompt: `Đặt vé đi đám cưới ở quê`
- intent: `CREATE`
- expected title: `đặt vé đi đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi chuẩn bị quà đám cưới ở quê`
- intent: `CREATE`
- expected title: `chuẩn bị quà đám cưới ở quê`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: vài ngày nữa
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi gặp người thân trước đám cưới ở quê`
- intent: `CREATE`
- expected title: `gặp người thân trước đám cưới`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: vài ngày nữa
- expected notes: `việc cá nhân`

- prompt: `Thông báo đám cưới ở quê vào cuối tuần`
- intent: `CREATE`
- expected title: `đám cưới ở quê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

### 46. Đi ngân hàng

- prompt: `Sáng mai tôi phải đi ngân hàng lúc 9h`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `việc cá nhân`

- prompt: `Chiều mai tôi phải đi ngân hàng lúc 3h`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 15:00
- expected notes: `việc cá nhân`

- prompt: `Thứ 6 tuần sau đi ngân hàng`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6 tuần sau
- expected notes: `việc cá nhân`

- prompt: `Tối nay tôi phải ra ngân hàng`
- intent: `CREATE`
- expected title: `ra ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `việc cá nhân`

- prompt: `Thứ 3 tôi đi ngân hàng lấy tiền`
- intent: `CREATE`
- expected title: `đi ngân hàng lấy tiền`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3
- expected notes: `việc cá nhân`

- prompt: `Sáng thứ 4 đi ngân hàng gửi hồ sơ`
- intent: `CREATE`
- expected title: `đi ngân hàng gửi hồ sơ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng thứ 4
- expected notes: `việc cá nhân`

- prompt: `Đi ngân hàng lấy sổ tiết kiệm vào 10h sáng`
- intent: `CREATE`
- expected title: `đi ngân hàng lấy sổ tiết kiệm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi đi ngân hàng đóng tiền gửi`
- intent: `CREATE`
- expected title: `đi ngân hàng đóng tiền gửi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi đi ngân hàng kiểm tra thẻ`
- intent: `CREATE`
- expected title: `đi ngân hàng kiểm tra thẻ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Tôi cần đi ngân hàng tuần này`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc cá nhân`

### 47. Việc nhà

- prompt: `Chiều thứ 5 tôi có việc nhà phải về sớm lúc 4h`
- intent: `CREATE`
- expected title: `việc nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5 lúc 16:00
- expected notes: `việc cá nhân`

- prompt: `Thứ 7 tôi phải làm việc nhà cả ngày`
- intent: `CREATE`
- expected title: `làm việc nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `việc nhà`

- prompt: `Tối mai tôi có việc nhà cần giải quyết`
- intent: `CREATE`
- expected title: `việc nhà cần giải quyết`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `việc nhà`

- prompt: `Thứ 4 tôi phải dọn dẹp nhà`
- intent: `CREATE`
- expected title: `dọn dẹp nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 4
- expected notes: `việc nhà`

- prompt: `Sáng mai tôi có việc nhà`
- intent: `CREATE`
- expected title: `việc nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc nhà`

- prompt: `Cuối tuần này tôi có việc nhà`
- intent: `CREATE`
- expected title: `việc nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc nhà`

- prompt: `Tháng này tôi có nhiều việc nhà`
- intent: `CREATE`
- expected title: `nhiều việc nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tầng tháng này
- expected notes: `việc nhà`

- prompt: `Nhắc tôi dọn dẹp nhà bếp`
- intent: `CREATE`
- expected title: `dọn dẹp nhà bếp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc nhà`

- prompt: `Nhắc tôi giặt đồ hôm nay`
- intent: `CREATE`
- expected title: `giặt đồ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc nhà`

- prompt: `Nhắc tôi lau nhà vào tối mai`
- intent: `CREATE`
- expected title: `lau nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `việc nhà`

### 48. Đón người thân

- prompt: `Thứ 6 tuần sau đi đón người thân ở sân bay lúc 5h chiều`
- intent: `CREATE`
- expected title: `đón người thân ở sân bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6 tuần sau lúc 17:00
- expected notes: `việc cá nhân`

- prompt: `Mai tôi đi đón người thân`
- intent: `CREATE`
- expected title: `đi đón người thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Chiều nay tôi có lịch đón người thân`
- intent: `CREATE`
- expected title: `đón người thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc cá nhân`

- prompt: `Thứ 7 tôi đi đón người thân ở sân bay`
- intent: `CREATE`
- expected title: `đón người thân ở sân bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `việc cá nhân`

- prompt: `Sáng mai đi đón người thân`
- intent: `CREATE`
- expected title: `đi đón người thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc cá nhân`

- prompt: `Đón người thân vào buổi tối`
- intent: `CREATE`
- expected title: `đón người thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi đón người thân ở sân bay`
- intent: `CREATE`
- expected title: `đón người thân ở sân bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `việc cá nhân`

- prompt: `Đón người thân lúc 6h chiều`
- intent: `CREATE`
- expected title: `đón người thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 18:00
- expected notes: `việc cá nhân`

- prompt: `Đón người thân tuần sau`
- intent: `CREATE`
- expected title: `đón người thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `việc cá nhân`

- prompt: `Đi đón người thân với gia đình`
- intent: `CREATE`
- expected title: `đi đón người thân với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

### 49. Sửa chữa nhà cửa

- prompt: `Sửa ống nước nhà tôi vào chủ nhật tuần này lúc 8h sáng`
- intent: `CREATE`
- expected title: `sửa ống nước nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chủ nhật tuần này lúc 08:00
- expected notes: `việc cá nhân`

- prompt: `Sửa mái nhà vào thứ 7 tới`
- intent: `CREATE`
- expected title: `sửa mái nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7 tới
- expected notes: `việc cá nhân`

- prompt: `Sửa cửa sổ phòng khách tuần sau`
- intent: `CREATE`
- expected title: `sửa cửa sổ phòng khách`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `việc cá nhân`

- prompt: `Sửa điện trong nhà hôm nay`
- intent: `CREATE`
- expected title: `sửa điện trong nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc cá nhân`

- prompt: `Sửa bồn cầu sáng mai`
- intent: `CREATE`
- expected title: `sửa bồn cầu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc cá nhân`

- prompt: `Sửa chữa nhà cửa vào chiều mai`
- intent: `CREATE`
- expected title: `sửa chữa nhà cửa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `việc cá nhân`

- prompt: `Sửa chữa nhà cửa vào cuối tuần`
- intent: `CREATE`
- expected title: `sửa chữa nhà cửa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

- prompt: `Sửa ống nước và điện nhà tôi`
- intent: `CREATE`
- expected title: `sửa ống nước và điện nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: none
- expected notes: `việc cá nhân`

- prompt: `Thay bóng đèn nhà bếp hôm nay`
- intent: `CREATE`
- expected title: `thay bóng đèn nhà bếp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc cá nhân`

- prompt: `Sửa chữa nhà cửa trước cuối tuần`
- intent: `CREATE`
- expected title: `sửa chữa nhà cửa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

### 50. Học thêm

- prompt: `Tôi có lớp học thêm tiếng Anh thứ 4 hàng tuần lúc 7h tối`
- intent: `CREATE`
- expected title: `học thêm tiếng Anh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4 hàng tuần lúc 19:00
- expected notes: `học tập`

- prompt: `Thêm lớp học thêm Toán tối thứ 3`
- intent: `CREATE`
- expected title: `lớp học thêm Toán`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3
- expected notes: `học tập`

- prompt: `Tôi có lớp học thêm Tiếng Việt tối mai`
- intent: `CREATE`
- expected title: `lớp học thêm Tiếng Việt`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `học tập`

- prompt: `Học thêm Lịch sử thứ 5 tuần này`
- intent: `CREATE`
- expected title: `học thêm Lịch sử`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5 tuần này
- expected notes: `học tập`

- prompt: `Thêm lớp học thêm Vật lý sáng thứ 7`
- intent: `CREATE`
- expected title: `lớp học thêm Vật lý`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `học tập`

- prompt: `Tôi có lớp học thêm tiếng Nhật vào tối mai`
- intent: `CREATE`
- expected title: `lớp học thêm tiếng Nhật`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `học tập`

- prompt: `Học thêm tiếng Anh cuối tuần`
- intent: `CREATE`
- expected title: `học thêm tiếng Anh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `học tập`

- prompt: `Học thêm Toán trước kỳ thi`
- intent: `CREATE`
- expected title: `học thêm Toán`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: trước kỳ thi
- expected notes: `học tập`

- prompt: `Lên lịch học thêm Văn vào thứ 3`
- intent: `CREATE`
- expected title: `học thêm Văn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3
- expected notes: `học tập`

- prompt: `Nhắc tôi học thêm tiếng Anh tối nay`
- intent: `CREATE`
- expected title: `học thêm tiếng Anh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `học tập`

### 51. Ra ngoài mất tiêu

- prompt: `Thứ 3 tuần tới tôi có việc phải ra ngoài mất tiêu lúc 3h chiều`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3 tuần tới lúc 15:00
- expected notes: `việc cá nhân`

- prompt: `Cuối tuần này tôi phải ra ngoài mất tiêu`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

- prompt: `Tối nay tôi ra ngoài mất tiêu`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `việc cá nhân`

- prompt: `Sáng mai đi ra ngoài mất tiêu`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc cá nhân`

- prompt: `Mai tôi sẽ ra ngoài mất tiêu`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi ra ngoài mất tiêu buổi chiều`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc cá nhân`

- prompt: `Đi ra ngoài mất tiêu với bạn`
- intent: `CREATE`
- expected title: `đi ra ngoài mất tiêu với bạn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Ra ngoài mất tiêu rồi về trễ`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu rồi về trễ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `việc cá nhân`

- prompt: `Đặt lịch ra ngoài mất tiêu`
- intent: `CREATE`
- expected title: `đặt lịch ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc cá nhân`

- prompt: `Ra ngoài mất tiêu cuối tháng`
- intent: `CREATE`
- expected title: `ra ngoài mất tiêu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tháng
- expected notes: `việc cá nhân`

### 52. Đi làm giấy tờ

- prompt: `Ngày mai tôi đi làm giấy tờ tùy thân lúc 10h sáng`
- intent: `CREATE`
- expected title: `làm giấy tờ tùy thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 10:00
- expected notes: `việc cá nhân`

- prompt: `Thứ 5 tôi đi làm giấy tờ căn cước`
- intent: `CREATE`
- expected title: `làm giấy tờ căn cước`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5
- expected notes: `việc cá nhân`

- prompt: `Tuần sau đi làm giấy tờ xe`
- intent: `CREATE`
- expected title: `làm giấy tờ xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `việc cá nhân`

- prompt: `Đi làm giấy tờ trong giờ hành chính`
- intent: `CREATE`
- expected title: `đi làm giấy tờ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi đi làm giấy tờ`
- intent: `CREATE`
- expected title: `đi làm giấy tờ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Làm giấy tờ thuế vào sáng thứ 3`
- intent: `CREATE`
- expected title: `làm giấy tờ thuế`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng thứ 3
- expected notes: `việc cá nhân`

- prompt: `Đi làm giấy tờ bảo hiểm`
- intent: `CREATE`
- expected title: `đi làm giấy tờ bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Đến cơ quan làm giấy tờ`
- intent: `CREATE`
- expected title: `đến cơ quan làm giấy tờ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc cá nhân`

- prompt: `Làm giấy tờ hộ chiếu vào thứ 6`
- intent: `CREATE`
- expected title: `làm giấy tờ hộ chiếu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6
- expected notes: `việc cá nhân`

- prompt: `Làm giấy tờ đăng ký kinh doanh`
- intent: `CREATE`
- expected title: `làm giấy tờ đăng ký kinh doanh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc cá nhân`

### 53. Việc riêng urgent

- prompt: `Tôi có việc riêng gấp phải giải quyết sáng mai lúc 9h`
- intent: `CREATE`
- expected title: `giải quyết việc riêng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `việc riêng, khẩn`

- prompt: `Nhiệm vụ riêng cấp bách hôm nay`
- intent: `CREATE`
- expected title: `nhiệm vụ riêng cấp bách`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc riêng, khẩn`

- prompt: `Giải quyết việc riêng khẩn cấp`
- intent: `CREATE`
- expected title: `giải quyết việc riêng khẩn cấp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc riêng, khẩn`

- prompt: `Việc riêng gấp phải làm trước 10h`
- intent: `CREATE`
- expected title: `việc riêng gấp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 10:00
- expected notes: `việc riêng, khẩn`

- prompt: `Việc riêng quan trọng sáng nay`
- intent: `CREATE`
- expected title: `việc riêng quan trọng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng nay
- expected notes: `việc riêng, khẩn`

- prompt: `Việc riêng gấp tuần này`
- intent: `CREATE`
- expected title: `việc riêng gấp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc riêng, khẩn`

- prompt: `Nhắc tôi hoàn thành việc riêng urgent`
- intent: `CREATE`
- expected title: `hoàn thành việc riêng urgent`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc riêng, khẩn`

- prompt: `Giải quyết việc riêng tối nay`
- intent: `CREATE`
- expected title: `giải quyết việc riêng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `việc riêng, khẩn`

- prompt: `Việc riêng khẩn vào thứ 2`
- intent: `CREATE`
- expected title: `việc riêng khẩn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 2
- expected notes: `việc riêng, khẩn`

- prompt: `Giải quyết việc riêng trước trưa`
- intent: `CREATE`
- expected title: `giải quyết việc riêng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: trước trưa
- expected notes: `việc riêng, khẩn`

### 54. Đi chợ

- prompt: `Chiều nay đi chợ mua đồ ăn lúc 5h`
- intent: `CREATE`
- expected title: `đi chợ mua đồ ăn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 17:00
- expected notes: `việc cá nhân`

- prompt: `Sáng mai đi chợ mua rau củ`
- intent: `CREATE`
- expected title: `đi chợ mua rau củ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc cá nhân`

- prompt: `Cuối tuần này đi chợ`
- intent: `CREATE`
- expected title: `đi chợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc cá nhân`

- prompt: `Đi chợ mua quà Tết`
- intent: `CREATE`
- expected title: `đi chợ mua quà Tết`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc cá nhân`

- prompt: `Đi chợ mua đồ tươi`
- intent: `CREATE`
- expected title: `đi chợ mua đồ tươi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Nhắc tôi đi chợ`
- intent: `CREATE`
- expected title: `đi chợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc cá nhân`

- prompt: `Đi chợ mua đồ nấu bữa tối`
- intent: `CREATE`
- expected title: `đi chợ mua đồ nấu bữa tối`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc cá nhân`

- prompt: `Đi chợ cùng mẹ`
- intent: `CREATE`
- expected title: `đi chợ cùng mẹ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

- prompt: `Sáng thứ 7 đi chợ`
- intent: `CREATE`
- expected title: `sáng thứ 7 đi chợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7 sáng
- expected notes: `việc cá nhân`

- prompt: `Mua trái cây ở chợ`
- intent: `CREATE`
- expected title: `mua trái cây ở chợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc cá nhân`

### 55. Tập thể dục

- prompt: `Mỗi sáng thứ 2 4 6 tôi tập gym lúc 6h sáng`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2 4 6 lúc 06:00
- expected notes: `sức khỏe`

- prompt: `Tập yoga vào tối nay`
- intent: `CREATE`
- expected title: `tập yoga`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `sức khỏe`

- prompt: `Chạy bộ buổi sáng`
- intent: `CREATE`
- expected title: `chạy bộ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tập thể dục mỗi tối`
- intent: `CREATE`
- expected title: `tập thể dục mỗi tối`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `sức khỏe`

- prompt: `Lên lịch tập gym thứ 4`
- intent: `CREATE`
- expected title: `tập gym thứ 4`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4
- expected notes: `sức khỏe`

- prompt: `Tập bơi vào cuối tuần`
- intent: `CREATE`
- expected title: `tập bơi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `sức khỏe`

- prompt: `Nhắc tôi tập thể dục hôm nay`
- intent: `CREATE`
- expected title: `tập thể dục`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `sức khỏe`

- prompt: `Tập thể dục với bạn`
- intent: `CREATE`
- expected title: `tập thể dục với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

- prompt: `Tập cardio sáng mai`
- intent: `CREATE`
- expected title: `tập cardio`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tập thể dục trước 7h`
- intent: `CREATE`
- expected title: `tập thể dục trước 7h`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 07:00
- expected notes: `sức khỏe`

### 56. Đi bảo hiểm

- prompt: `Thứ 2 tuần sau đi đóng bảo hiểm xã hội lúc 8h sáng`
- intent: `CREATE`
- expected title: `đóng bảo hiểm xã hội`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2 tuần sau lúc 08:00
- expected notes: `việc hành chính`

- prompt: `Mai tôi đi bảo hiểm y tế`
- intent: `CREATE`
- expected title: `đi bảo hiểm y tế`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc hành chính`

- prompt: `Nộp hồ sơ bảo hiểm vào chiều mai`
- intent: `CREATE`
- expected title: `nộp hồ sơ bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `việc hành chính`

- prompt: `Thanh toán bảo hiểm xe vào thứ 4`
- intent: `CREATE`
- expected title: `thanh toán bảo hiểm xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4
- expected notes: `việc hành chính`

- prompt: `Đến công ty bảo hiểm xem hợp đồng`
- intent: `CREATE`
- expected title: `đến công ty bảo hiểm xem hợp đồng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc hành chính`

- prompt: `Kiểm tra bảo hiểm nhân thọ`
- intent: `CREATE`
- expected title: `kiểm tra bảo hiểm nhân thọ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc hành chính`

- prompt: `Nhắc tôi kiểm tra bảo hiểm`
- intent: `CREATE`
- expected title: `kiểm tra bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc hành chính`

- prompt: `Đi bảo hiểm sáng mai`
- intent: `CREATE`
- expected title: `đi bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc hành chính`

- prompt: `Tư vấn bảo hiểm chiều nay`
- intent: `CREATE`
- expected title: `tư vấn bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc hành chính`

- prompt: `Gặp tư vấn viên bảo hiểm`
- intent: `CREATE`
- expected title: `gặp tư vấn viên bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc hành chính`

### 57. Gặp bạn bè

- prompt: `Cuối tuần này đi cafe với bạn lúc 3h chiều thứ 7`
- intent: `CREATE`
- expected title: `cafe với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7 lúc 15:00
- expected notes: `gặp gỡ`

- prompt: `Gặp bạn bè đi ăn trưa`
- intent: `CREATE`
- expected title: `gặp bạn bè đi ăn trưa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gặp gỡ`

- prompt: `Hẹn gặp bạn chiều mai`
- intent: `CREATE`
- expected title: `hẹn gặp bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `gặp gỡ`

- prompt: `Cả nhóm bạn gặp nhau`
- intent: `CREATE`
- expected title: `cả nhóm bạn gặp nhau`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `gặp gỡ`

- prompt: `Gặp bạn cũ tối nay`
- intent: `CREATE`
- expected title: `gặp bạn cũ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gặp gỡ`

- prompt: `Nhắc tôi gặp bạn`
- intent: `CREATE`
- expected title: `gặp bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gặp gỡ`

- prompt: `Gặp bạn uống trà sáng thứ 7`
- intent: `CREATE`
- expected title: `gặp bạn uống trà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7 sáng
- expected notes: `gặp gỡ`

- prompt: `Gặp bạn bàn chuyện công việc`
- intent: `CREATE`
- expected title: `gặp bạn bàn chuyện công việc`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gặp gỡ`

- prompt: `Đi chơi với bạn chiều nay`
- intent: `CREATE`
- expected title: `đi chơi với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `gặp gỡ`

- prompt: `Gặp bạn tại nhà hàng`
- intent: `CREATE`
- expected title: `gặp bạn tại nhà hàng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gặp gỡ`

### 58. Đưa con đi học

- prompt: `Sáng mai đưa con đi học lúc 7h30`
- intent: `CREATE`
- expected title: `đưa con đi học`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 07:30
- expected notes: `việc gia đình`

- prompt: `Thứ 2 tôi đưa con đi học`
- intent: `CREATE`
- expected title: `đưa con đi học`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2
- expected notes: `việc gia đình`

- prompt: `Nhắc tôi đưa con đi học`
- intent: `CREATE`
- expected title: `đưa con đi học`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc gia đình`

- prompt: `Đưa con đến trường học thêm`
- intent: `CREATE`
- expected title: `đưa con đến trường học thêm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc gia đình`

- prompt: `Đưa con đi học sớm`
- intent: `CREATE`
- expected title: `đưa con đi học sớm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc gia đình`

- prompt: `Sáng thứ 3 đưa con đi học`
- intent: `CREATE`
- expected title: `đưa con đi học`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3 sáng
- expected notes: `việc gia đình`

- prompt: `Đưa con đi học trước 8h`
- intent: `CREATE`
- expected title: `đưa con đi học trước 8h`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 08:00
- expected notes: `việc gia đình`

- prompt: `Đưa con đi học bằng xe hơi`
- intent: `CREATE`
- expected title: `đưa con đi học bằng xe hơi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc gia đình`

- prompt: `Đưa con đến trường mới`
- intent: `CREATE`
- expected title: `đưa con đến trường mới`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2
- expected notes: `việc gia đình`

- prompt: `Đưa con đi học buổi sáng`
- intent: `CREATE`
- expected title: `đưa con đi học buổi sáng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc gia đình`

### 59. Đi bưu điện

- prompt: `Chiều thứ 4 ra bưu điện gửi hàng lúc 2h`
- intent: `CREATE`
- expected title: `gửi hàng ở bưu điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4 lúc 14:00
- expected notes: `việc hành chính`

- prompt: `Gửi thư tại bưu điện`
- intent: `CREATE`
- expected title: `gửi thư tại bưu điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc hành chính`

- prompt: `Đến bưu điện mua tem`
- intent: `CREATE`
- expected title: `đến bưu điện mua tem`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc hành chính`

- prompt: `Nộp hồ sơ ở bưu điện`
- intent: `CREATE`
- expected title: `nộp hồ sơ ở bưu điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc hành chính`

- prompt: `Ra bưu điện nhận hàng`
- intent: `CREATE`
- expected title: `ra bưu điện nhận hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc hành chính`

- prompt: `Gửi bưu phẩm vào sáng mai`
- intent: `CREATE`
- expected title: `gửi bưu phẩm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc hành chính`

- prompt: `Gửi hàng COD`
- intent: `CREATE`
- expected title: `gửi hàng COD`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc hành chính`

- prompt: `Ra bưu điện chiều nay`
- intent: `CREATE`
- expected title: `ra bưu điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc hành chính`

- prompt: `Mua bưu điện gửi quà`
- intent: `CREATE`
- expected title: `mua bưu điện gửi quà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc hành chính`

- prompt: `Đi bưu điện trước 5h`
- intent: `CREATE`
- expected title: `đi bưu điện trước 5h`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay trước 17:00
- expected notes: `việc hành chính`

### 60. Kiểm tra sức khỏe

- prompt: `Thứ 5 tuần sau đi kiểm tra sức khỏe định kỳ lúc 9h sáng`
- intent: `CREATE`
- expected title: `kiểm tra sức khỏe định kỳ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5 tuần sau lúc 09:00
- expected notes: `sức khỏe`

- prompt: `Khám sức khỏe tổng quát`
- intent: `CREATE`
- expected title: `khám sức khỏe tổng quát`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

- prompt: `Kiểm tra sức khỏe buổi sáng`
- intent: `CREATE`
- expected title: `kiểm tra sức khỏe buổi sáng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Khám nha khoa`
- intent: `CREATE`
- expected title: `khám nha khoa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `sức khỏe`

- prompt: `Khám mắt chiều nay`
- intent: `CREATE`
- expected title: `khám mắt`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `sức khỏe`

- prompt: `Nhắc tôi kiểm tra sức khỏe`
- intent: `CREATE`
- expected title: `kiểm tra sức khỏe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

- prompt: `Kiểm tra sức khỏe vì công ty`
- intent: `CREATE`
- expected title: `kiểm tra sức khỏe vì công ty`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `sức khỏe`

- prompt: `Đặt lịch khám sức khỏe`
- intent: `CREATE`
- expected title: `đặt lịch khám sức khỏe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

- prompt: `Khám sức khỏe trước kỳ nghỉ`
- intent: `CREATE`
- expected title: `khám sức khỏe trước kỳ nghỉ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: trước kỳ nghỉ
- expected notes: `sức khỏe`

- prompt: `Kiểm tra sức khỏe cho gia đình`
- intent: `CREATE`
- expected title: `kiểm tra sức khỏe cho gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `sức khỏe`

### 61. Sửa xe

- prompt: `Sáng chủ nhật đem xe đi bảo dưỡng lúc 8h`
- intent: `CREATE`
- expected title: `bảo dưỡng xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chủ nhật lúc 08:00
- expected notes: `việc cá nhân`

- prompt: `Thứ 4 đem xe đi sửa máy lúc 14h`
- intent: `CREATE`
- expected title: `sửa máy xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4 lúc 14:00
- expected notes: `việc cá nhân`

- prompt: `Hẹn thợ sửa xe ngày mai lúc 9h`
- intent: `CREATE`
- expected title: `hẹn thợ sửa xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 09:00
- expected notes: `việc cá nhân`

- prompt: `Mang xe đi kiểm tra lốp`
- intent: `CREATE`
- expected title: `kiểm tra lốp xe`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sửa xe`

- prompt: `Đổi nhớt xe vào chiều mai`
- intent: `CREATE`
- expected title: `đổi nhớt xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sửa xe`

- prompt: `Sửa phanh xe tuần này`
- intent: `CREATE`
- expected title: `sửa phanh xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `sửa xe`

- prompt: `Kiểm tra bình ắc quy sáng mai`
- intent: `CREATE`
- expected title: `kiểm tra bình ắc quy`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sửa xe`

- prompt: `Rửa xe hôm nay`
- intent: `CREATE`
- expected title: `rửa xe`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `sửa xe`

- prompt: `Nhắc mua phụ tùng xe`
- intent: `CREATE`
- expected title: `mua phụ tùng xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sửa xe`

- prompt: `Đặt lịch bảo dưỡng ô tô tháng này`
- intent: `CREATE`
- expected title: `đặt lịch bảo dưỡng ô tô`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng này
- expected notes: `sửa xe`

### 62. Dọn dẹp nhà cửa

- prompt: `Cuối tuần này tổng vệ sinh nhà cửa lúc 9h sáng thứ 7`
- intent: `CREATE`
- expected title: `tổng vệ sinh nhà cửa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7 lúc 09:00
- expected notes: `việc nhà`

- prompt: `Dọn phòng ngủ sáng mai`
- intent: `CREATE`
- expected title: `dọn phòng ngủ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc nhà`

- prompt: `Lau nhà bếp chiều nay`
- intent: `CREATE`
- expected title: `lau nhà bếp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc nhà`

- prompt: `Hút bụi phòng khách chủ nhật`
- intent: `CREATE`
- expected title: `hút bụi phòng khách`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chủ nhật
- expected notes: `việc nhà`

- prompt: `Giặt rèm cửa trong tuần`
- intent: `CREATE`
- expected title: `giặt rèm cửa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: trong tuần
- expected notes: `việc nhà`

- prompt: `Dọn tủ lạnh cuối tuần`
- intent: `CREATE`
- expected title: `dọn tủ lạnh`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc nhà`

- prompt: `Lau bụi giá sách`
- intent: `CREATE`
- expected title: `lau bụi giá sách`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc nhà`

- prompt: `Dọn phòng làm việc`
- intent: `CREATE`
- expected title: `dọn phòng làm việc`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `việc nhà`

- prompt: `Sắp xếp tủ quần áo`
- intent: `CREATE`
- expected title: `sắp xếp tủ quần áo`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc nhà`

- prompt: `Dọn dẹp sân trước`
- intent: `CREATE`
- expected title: `dọn dẹp sân trước`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc nhà`

### 63. Thanh toán hóa đơn

- prompt: `Ngày 15 hàng tháng đi đóng tiền điện nước lúc 10h sáng`
- intent: `CREATE`
- expected title: `đóng tiền điện nước`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày 15 hàng tuần lúc 10:00
- expected notes: `tài chính`

- prompt: `Thanh toán internet ngày 20`
- intent: `CREATE`
- expected title: `thanh toán internet`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày 20
- expected notes: `tài chính`

- prompt: `Đóng tiền nước tháng này`
- intent: `CREATE`
- expected title: `đóng tiền nước`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng này
- expected notes: `tài chính`

- prompt: `Trả tiền thuê nhà`
- intent: `CREATE`
- expected title: `trả tiền thuê nhà`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Nộp hóa đơn gas`
- intent: `CREATE`
- expected title: `nộp hóa đơn gas`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

- prompt: `Thanh toán thẻ tín dụng`
- intent: `CREATE`
- expected title: `thanh toán thẻ tín dụng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Đóng tiền truyền hình cáp`
- intent: `CREATE`
- expected title: `đóng tiền truyền hình cáp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Đóng cước điện thoại`
- intent: `CREATE`
- expected title: `đóng cước điện thoại`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán dịch vụ vệ sinh`
- intent: `CREATE`
- expected title: `thanh toán dịch vụ vệ sinh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `tài chính`

- prompt: `Nhắc thanh toán bảo hiểm`
- intent: `CREATE`
- expected title: `thanh toán bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

### 64. Đi siêu thị

- prompt: `Chiều mai đi siêu thị mua sắm lúc 4h`
- intent: `CREATE`
- expected title: `đi siêu thị mua sắm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 16:00
- expected notes: `mua sắm`

- prompt: `Đi siêu thị mua đồ tươi`
- intent: `CREATE`
- expected title: `đi siêu thị mua đồ tươi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Mua thực phẩm cuối tuần`
- intent: `CREATE`
- expected title: `mua thực phẩm cuối tuần`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `mua sắm`

- prompt: `Mua đồ dùng nhà bếp`
- intent: `CREATE`
- expected title: `mua đồ dùng nhà bếp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Đi siêu thị tối nay`
- intent: `CREATE`
- expected title: `đi siêu thị tối nay`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `mua sắm`

- prompt: `Mua quà sinh nhật`
- intent: `CREATE`
- expected title: `mua quà sinh nhật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Đi siêu thị với vợ`
- intent: `CREATE`
- expected title: `đi siêu thị với vợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `mua sắm`

- prompt: `Mua sắm tại siêu thị`
- intent: `CREATE`
- expected title: `mua sắm tại siêu thị`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Mua đồ chuẩn bị tiệc`
- intent: `CREATE`
- expected title: `mua đồ chuẩn bị tiệc`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `mua sắm`

- prompt: `Mua sắm cho tuần tới`
- intent: `CREATE`
- expected title: `mua sắm cho tuần tới`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần tới
- expected notes: `mua sắm`

### 65. Gọi điện cho người thân

- prompt: `Tối nay gọi điện cho bố mẹ lúc 8h tối`
- intent: `CREATE`
- expected title: `gọi điện cho bố mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 20:00
- expected notes: `gia đình`

- prompt: `Gọi điện cho chị gái`
- intent: `CREATE`
- expected title: `gọi điện cho chị gái`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Gọi điện cho ông bà`
- intent: `CREATE`
- expected title: `gọi điện cho ông bà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Gọi điện mời gia đình ăn tối`
- intent: `CREATE`
- expected title: `gọi điện mời gia đình ăn tối`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `gia đình`

- prompt: `Gọi điện xác nhận chuyến đi`
- intent: `CREATE`
- expected title: `gọi điện xác nhận chuyến đi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Gọi cho vợ hỏi lịch`
- intent: `CREATE`
- expected title: `gọi cho vợ hỏi lịch`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Gọi bạn cũ trò chuyện`
- intent: `CREATE`
- expected title: `gọi bạn cũ trao đổi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `gặp gỡ`

- prompt: `Nhắc gọi điện thoại`
- intent: `CREATE`
- expected title: `nhắc gọi điện thoại`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Nói chuyện với em gái`
- intent: `CREATE`
- expected title: `nói chuyện với em gái`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gia đình`

- prompt: `Gọi hỏi thăm sức khỏe`
- intent: `CREATE`
- expected title: `gọi hỏi thăm sức khỏe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

### 66. Đọc sách

- prompt: `Mỗi tối đọc sách trước khi ngủ lúc 10h`
- intent: `CREATE`
- expected title: `đọc sách`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: mỗi tối lúc 22:00
- expected notes: `học tập`

- prompt: `Đọc sách kỹ năng lãnh đạo`
- intent: `CREATE`
- expected title: `đọc sách kỹ năng lãnh đạo`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `học tập`

- prompt: `Đọc chương mới trong tiểu thuyết`
- intent: `CREATE`
- expected title: `đọc chương mới trong tiểu thuyết`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Đọc tài liệu học tiếng Anh`
- intent: `CREATE`
- expected title: `đọc tài liệu học tiếng Anh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `học tập`

- prompt: `Đọc sách trước khi đi làm`
- intent: `CREATE`
- expected title: `đọc sách trước khi đi làm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `học tập`

- prompt: `Đọc báo buổi sáng`
- intent: `CREATE`
- expected title: `đọc báo buổi sáng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `tin tức`

- prompt: `Đọc sách tâm lý vào cuối tuần`
- intent: `CREATE`
- expected title: `đọc sách tâm lý`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `học tập`

- prompt: `Đọc sách nấu ăn`
- intent: `CREATE`
- expected title: `đọc sách nấu ăn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Đọc sách kỹ thuật buổi tối`
- intent: `CREATE`
- expected title: `đọc sách kỹ thuật`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `học tập`

- prompt: `Nhắc tôi đọc sách lúc 9h tối`
- intent: `CREATE`
- expected title: `đọc sách`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 21:00
- expected notes: `học tập`

### 67. Nấu ăn

- prompt: `Chiều nay nấu cơm cho cả nhà lúc 5h30`
- intent: `CREATE`
- expected title: `nấu cơm cho cả nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 17:30
- expected notes: `gia đình`

- prompt: `Nấu bún sáng mai`
- intent: `CREATE`
- expected title: `nấu bún sáng mai`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `gia đình`

- prompt: `Chuẩn bị bữa tối cho 4 người`
- intent: `CREATE`
- expected title: `chuẩn bị bữa tối cho 4 người`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Nấu ăn cho buổi tiệc nhỏ`
- intent: `CREATE`
- expected title: `nấu ăn cho buổi tiệc nhỏ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `gia đình`

- prompt: `Học nấu món mới`
- intent: `CREATE`
- expected title: `học nấu món mới`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Nấu ăn cho người ốm`
- intent: `CREATE`
- expected title: `nấu ăn cho người ốm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Nấu cơm trưa lúc 11h`
- intent: `CREATE`
- expected title: `nấu cơm trưa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 11:00
- expected notes: `gia đình`

- prompt: `Nấu bánh mì cho sáng mai`
- intent: `CREATE`
- expected title: `nấu bánh mì cho sáng mai`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `gia đình`

- prompt: `Nấu ăn với mẹ lúc 5h`
- intent: `CREATE`
- expected title: `nấu ăn với mẹ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `gia đình`

- prompt: `Lên thực đơn cho tuần tới`
- intent: `CREATE`
- expected title: `lên thực đơn cho tuần tới`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần tới
- expected notes: `gia đình`

### 68. Việc bất ngờ

- prompt: `Mai có việc đột xuất phải giải quyết lúc 2h chiều`
- intent: `CREATE`
- expected title: `giải quyết việc đột xuất`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: mai lúc 14:00
- expected notes: `việc khẩn`

- prompt: `Đột nhiên có cuộc họp gấp`
- intent: `CREATE`
- expected title: `cuộc họp gấp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc khẩn`

- prompt: `Việc khẩn bất ngờ tối nay`
- intent: `CREATE`
- expected title: `việc khẩn bất ngờ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `việc khẩn`

- prompt: `Đột xuất cần đi làm thủ tục`
- intent: `CREATE`
- expected title: `đi làm thủ tục đột xuất`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc khẩn`

- prompt: `Có việc bất ngờ phải hoãn họp`
- intent: `CREATE`
- expected title: `hoãn họp do việc bất ngờ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc khẩn`

- prompt: `Nhắc tôi xử lý vấn đề đột xuất`
- intent: `CREATE`
- expected title: `xử lý vấn đề đột xuất`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc khẩn`

- prompt: `Việc bất ngờ xuất hiện giữa giờ`
- intent: `CREATE`
- expected title: `việc bất ngờ xuất hiện`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc khẩn`

- prompt: `Giải quyết sự cố bất ngờ`
- intent: `CREATE`
- expected title: `giải quyết sự cố bất ngờ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc khẩn`

- prompt: `Đột xuất cần liên hệ khách hàng`
- intent: `CREATE`
- expected title: `liên hệ khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc khẩn`

- prompt: `Cần đi giải quyết việc gấp`
- intent: `CREATE`
- expected title: `giải quyết việc gấp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc khẩn`

### 69. Đi rút tiền

- prompt: `Thứ 4 tuần này đi rút tiền ATM lúc 11h trưa`
- intent: `CREATE`
- expected title: `rút tiền ATM`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4 tuần này lúc 11:00
- expected notes: `tài chính`

- prompt: `Đi rút tiền mặt sáng mai`
- intent: `CREATE`
- expected title: `rút tiền mặt`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `tài chính`

- prompt: `Rút tiền ở ATM gần nhà`
- intent: `CREATE`
- expected title: `rút tiền ở ATM gần nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

- prompt: `Rút tiền cho chuyến đi`
- intent: `CREATE`
- expected title: `rút tiền cho chuyến đi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Rút tiền trả tiền thuê`
- intent: `CREATE`
- expected title: `rút tiền trả tiền thuê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Rút tiền cuối tháng`
- intent: `CREATE`
- expected title: `rút tiền cuối tháng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tháng
- expected notes: `tài chính`

- prompt: `Rút tiền cấp bách`
- intent: `CREATE`
- expected title: `rút tiền cấp bách`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

- prompt: `Rút tiền ATM trước 5h`
- intent: `CREATE`
- expected title: `rút tiền ATM trước 5h`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay trước 17:00
- expected notes: `tài chính`

- prompt: `Rút tiền để mua quà`
- intent: `CREATE`
- expected title: `rút tiền để mua quà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Rút tiền mặt tại ngân hàng`
- intent: `CREATE`
- expected title: `rút tiền mặt tại ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

### 70. Tiệc sinh nhật

- prompt: `Tối thứ 7 dự tiệc sinh nhật bạn lúc 7h tối`
- intent: `CREATE`
- expected title: `tiệc sinh nhật bạn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7 lúc 19:00
- expected notes: `gặp gỡ`

- prompt: `Chuẩn bị quà sinh nhật cho bạn`
- intent: `CREATE`
- expected title: `chuẩn bị quà sinh nhật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Đặt bánh kem cho sinh nhật`
- intent: `CREATE`
- expected title: `đặt bánh kem`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Gọi mời bạn đến dự tiệc`
- intent: `CREATE`
- expected title: `gọi mời bạn đến dự tiệc`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gặp gỡ`

- prompt: `Trang trí phòng cho sinh nhật`
- intent: `CREATE`
- expected title: `trang trí phòng cho sinh nhật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Mua quà sinh nhật cho em gái`
- intent: `CREATE`
- expected title: `mua quà sinh nhật cho em gái`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Đặt nhà hàng cho tiệc sinh nhật`
- intent: `CREATE`
- expected title: `đặt nhà hàng cho tiệc sinh nhật`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `giải trí`

- prompt: `Chuẩn bị thiệp mời`
- intent: `CREATE`
- expected title: `chuẩn bị thiệp mời`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Tải nhạc cho tiệc sinh nhật`
- intent: `CREATE`
- expected title: `tải nhạc cho tiệc sinh nhật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Đặt bóng bay và trang trí`
- intent: `CREATE`
- expected title: `đặt bóng bay và trang trí`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `giải trí`

### 71. Câu trần thuật - Hội thảo (Declarative)

- prompt: `Thứ 3 tuần sau mình sẽ có một buổi hội thảo cần tham gia với các đồng nghiệp lúc 3h chiều`
- intent: `CREATE`
- expected title: `tham gia hội thảo với đồng nghiệp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3 tuần sau lúc 15:00
- expected notes: `hội thảo`

- prompt: `Sắp xếp buổi hội thảo trong tháng sau`
- intent: `CREATE`
- expected title: `sắp xếp buổi hội thảo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng sau
- expected notes: `hội thảo`

- prompt: `Mình có tham dự hội thảo công nghệ`
- intent: `CREATE`
- expected title: `tham dự hội thảo công nghệ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `hội thảo`

- prompt: `Buổi hội thảo chiều nay cần chuẩn bị`
- intent: `CREATE`
- expected title: `chuẩn bị buổi hội thảo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `hội thảo`

- prompt: `Hội thảo khách hàng diễn ra sáng mai`
- intent: `CREATE`
- expected title: `hội thảo khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `hội thảo`

- prompt: `Tham gia hội thảo với cấp trên`
- intent: `CREATE`
- expected title: `tham gia hội thảo với cấp trên`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4
- expected notes: `hội thảo`

- prompt: `Chuẩn bị slide cho hội thảo`
- intent: `CREATE`
- expected title: `chuẩn bị slide cho hội thảo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `hội thảo`

- prompt: `Tham dự hội thảo marketing`
- intent: `CREATE`
- expected title: `tham dự hội thảo marketing`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `hội thảo`

- prompt: `Buổi hội thảo nội bộ hôm nay`
- intent: `CREATE`
- expected title: `buổi hội thảo nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `hội thảo`

- prompt: `Sắp xếp lịch hội thảo tháng này`
- intent: `CREATE`
- expected title: `sắp xếp lịch hội thảo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng này
- expected notes: `hội thảo`

### 72. Câu trần thuật - Lịch họp

- prompt: `Sáng mai tôi có cuộc họp định kỳ với team marketing lúc 9h`
- intent: `CREATE`
- expected title: `họp định kỳ với team marketing`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `họp`

- prompt: `Tối nay có cuộc họp ban lãnh đạo`
- intent: `CREATE`
- expected title: `cuộc họp ban lãnh đạo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `họp`

- prompt: `Tuần sau họp đánh giá dự án`
- intent: `CREATE`
- expected title: `họp đánh giá dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `họp`

- prompt: `Cuộc họp nhóm diễn ra chiều mai`
- intent: `CREATE`
- expected title: `cuộc họp nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `họp`

- prompt: `Sáng thứ 2 họp triển khai kế hoạch`
- intent: `CREATE`
- expected title: `họp triển khai kế hoạch`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2 sáng
- expected notes: `họp`

- prompt: `Buổi họp online lúc 10h sáng mai`
- intent: `CREATE`
- expected title: `họp online`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `họp`

- prompt: `Họp với khách hàng vào chiều thứ 4`
- intent: `CREATE`
- expected title: `họp với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều thứ 4
- expected notes: `họp`

- prompt: `Họp bàn kế hoạch tài chính`
- intent: `CREATE`
- expected title: `họp bàn kế hoạch tài chính`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `họp`

- prompt: `Họp nội bộ cuối tuần`
- intent: `CREATE`
- expected title: `họp nội bộ cuối tuần`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `họp`

- prompt: `Họp gấp buổi chiều`
- intent: `CREATE`
- expected title: `họp gấp buổi chiều`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `họp`

### 73. Câu trần thuật - Gặp gỡ

- prompt: `Chiều nay mình có lịch gặp gỡ đối tác tại quán cafe lúc 4h`
- intent: `CREATE`
- expected title: `gặp gỡ đối tác tại quán cafe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 16:00
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ khách hàng sáng mai`
- intent: `CREATE`
- expected title: `gặp gỡ khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ bạn bè tối nay`
- intent: `CREATE`
- expected title: `gặp gỡ bạn bè`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ đồng nghiệp để bàn dự án`
- intent: `CREATE`
- expected title: `gặp gỡ đồng nghiệp để bàn dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ nhóm sáng thứ 7`
- intent: `CREATE`
- expected title: `gặp gỡ nhóm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7 sáng
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ đối tác tại văn phòng`
- intent: `CREATE`
- expected title: `gặp gỡ đối tác tại văn phòng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ khách hàng mới`
- intent: `CREATE`
- expected title: `gặp gỡ khách hàng mới`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ thầy cô học tập`
- intent: `CREATE`
- expected title: `gặp gỡ thầy cô`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `học tập`

- prompt: `Gặp gỡ hội đồng xét tuyển`
- intent: `CREATE`
- expected title: `gặp gỡ hội đồng xét tuyển`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `gặp gỡ`

- prompt: `Gặp gỡ lễ tân khách sạn`
- intent: `CREATE`
- expected title: `gặp gỡ lễ tân khách sạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gặp gỡ`

### 74. Câu trần thuật - Sự kiện

- prompt: `Tuần sau công ty có tổ chức tiệc tất niên vào thứ 6 lúc 6h tối`
- intent: `CREATE`
- expected title: `tiệc tất niên công ty`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6 tuần sau lúc 18:00
- expected notes: `tiệc`

- prompt: `Sự kiện khai trương cửa hàng vào thứ 3`
- intent: `CREATE`
- expected title: `sự kiện khai trương cửa hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3
- expected notes: `sự kiện`

- prompt: `Buổi họp báo sự kiện chiều mai`
- intent: `CREATE`
- expected title: `họp báo sự kiện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sự kiện`

- prompt: `Sự kiện ra mắt sản phẩm ngày mai`
- intent: `CREATE`
- expected title: `sự kiện ra mắt sản phẩm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sự kiện`

- prompt: `Tham gia sự kiện văn hóa cuối tuần`
- intent: `CREATE`
- expected title: `tham gia sự kiện văn hóa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `sự kiện`

- prompt: `Sự kiện gặp gỡ startup`
- intent: `CREATE`
- expected title: `sự kiện gặp gỡ startup`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `sự kiện`

- prompt: `Sự kiện thể thao chiều mai`
- intent: `CREATE`
- expected title: `sự kiện thể thao`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sự kiện`

- prompt: `Tham gia sự kiện từ thiện`
- intent: `CREATE`
- expected title: `tham gia sự kiện từ thiện`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sự kiện`

- prompt: `Sự kiện hội nghị khách hàng`
- intent: `CREATE`
- expected title: `sự kiện hội nghị khách hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `sự kiện`

- prompt: `Lên lịch sự kiện offline`
- intent: `CREATE`
- expected title: `lên lịch sự kiện offline`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sự kiện`

### 75. Câu trần thuật - Khám bệnh

- prompt: `Sáng thứ 4 tôi có lịch tái khám răng tại phòng khám lúc 10h`
- intent: `CREATE`
- expected title: `tái khám răng tại phòng khám`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4 lúc 10:00
- expected notes: `sức khỏe`

- prompt: `Buổi sáng khám tổng quát`
- intent: `CREATE`
- expected title: `khám tổng quát`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Hẹn khám nhãn khoa chiều mai`
- intent: `CREATE`
- expected title: `hẹn khám nhãn khoa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sức khỏe`

- prompt: `Khám da liễu vào thứ 6`
- intent: `CREATE`
- expected title: `khám da liễu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6
- expected notes: `sức khỏe`

- prompt: `Khám sàng lọc ung thư tuần này`
- intent: `CREATE`
- expected title: `khám sàng lọc ung thư`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `sức khỏe`

- prompt: `Khám tim mạch sáng mai`
- intent: `CREATE`
- expected title: `khám tim mạch`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Khám răng vào thứ 3 tuần sau`
- intent: `CREATE`
- expected title: `khám răng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3 tuần sau
- expected notes: `sức khỏe`

- prompt: `Khám sức khỏe công ty`
- intent: `CREATE`
- expected title: `khám sức khỏe công ty`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần tới
- expected notes: `sức khỏe`

- prompt: `Khám nha khoa hôm nay`
- intent: `CREATE`
- expected title: `khám nha khoa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `sức khỏe`

- prompt: `Khám sức khỏe trước kỳ nghỉ`
- intent: `CREATE`
- expected title: `khám sức khỏe trước kỳ nghỉ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: trước kỳ nghỉ
- expected notes: `sức khỏe`

### 76. Câu trần thuật - Nộp bài

- prompt: `Ngày mai em sẽ nộp bài tập lớn cho thầy vào lúc 8h sáng`
- intent: `CREATE`
- expected title: `nộp bài tập lớn cho thầy`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 08:00
- expected notes: `học tập`

- prompt: `Nộp bài luận cuối kỳ vào thứ 3`
- intent: `CREATE`
- expected title: `nộp bài luận cuối kỳ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 3
- expected notes: `học tập`

- prompt: `Nộp báo cáo thực tập vào ngày 18/4`
- intent: `CREATE`
- expected title: `nộp báo cáo thực tập`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: 18/04/2026
- expected notes: `học tập`

- prompt: `Nộp bài tập lập trình trước 10h`
- intent: `CREATE`
- expected title: `nộp bài tập lập trình`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: trước 10h
- expected notes: `học tập`

- prompt: `Nộp đơn xin học bổng`
- intent: `CREATE`
- expected title: `nộp đơn xin học bổng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `học tập`

- prompt: `Nộp bài tập nhóm`
- intent: `CREATE`
- expected title: `nộp bài tập nhóm`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `học tập`

- prompt: `Nộp đề cương đồ án`
- intent: `CREATE`
- expected title: `nộp đề cương đồ án`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `học tập`

- prompt: `Nộp bài tập văn tự học`
- intent: `CREATE`
- expected title: `nộp bài tập văn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `học tập`

- prompt: `Nộp bài tập lịch sử`
- intent: `CREATE`
- expected title: `nộp bài tập lịch sử`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `học tập`

- prompt: `Nộp bài nhóm vào cuối tuần`
- intent: `CREATE`
- expected title: `nộp bài nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `học tập`

### 77. Câu trần thuật - Đón người thân

- prompt: `Chiều thứ 7 mình phải đi đón người thân ở sân bay lúc 5h`
- intent: `CREATE`
- expected title: `đón người thân ở sân bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7 lúc 17:00
- expected notes: `gia đình`

- prompt: `Đón người thân từ sân bay vào sáng mai`
- intent: `CREATE`
- expected title: `đón người thân từ sân bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `gia đình`

- prompt: `Đón người thân tại bến xe chiều nay`
- intent: `CREATE`
- expected title: `đón người thân tại bến xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `gia đình`

- prompt: `Đón chị gái từ bệnh viện`
- intent: `CREATE`
- expected title: `đón chị gái từ bệnh viện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Đón người thân buổi tối`
- intent: `CREATE`
- expected title: `đón người thân buổi tối`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gia đình`

- prompt: `Đón bố mẹ từ ga`
- intent: `CREATE`
- expected title: `đón bố mẹ từ ga`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Đón người thân về nhà`
- intent: `CREATE`
- expected title: `đón người thân về nhà`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `gia đình`

- prompt: `Đón bạn thân từ sân bay`
- intent: `CREATE`
- expected title: `đón bạn thân từ sân bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `gia đình`

- prompt: `Đón mẹ từ bệnh viện`
- intent: `CREATE`
- expected title: `đón mẹ từ bệnh viện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Nhắc đón người thân lúc 6h`
- intent: `CREATE`
- expected title: `đón người thân`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: lúc 18:00
- expected notes: `gia đình`

### 78. Câu trần thuật - Bảo trì

- prompt: `Sáng chủ nhật nhà mình có lịch bảo trì máy lạnh lúc 9h`
- intent: `CREATE`
- expected title: `bảo trì máy lạnh`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chủ nhật lúc 09:00
- expected notes: `việc nhà`

- prompt: `Bảo trì máy nước chiều mai`
- intent: `CREATE`
- expected title: `bảo trì máy nước`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `việc nhà`

- prompt: `Bảo trì điều hòa vào thứ 6`
- intent: `CREATE`
- expected title: `bảo trì điều hòa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 6
- expected notes: `việc nhà`

- prompt: `Bảo trì máy tính văn phòng`
- intent: `CREATE`
- expected title: `bảo trì máy tính văn phòng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc công việc`

- prompt: `Bảo trì xe máy tuần này`
- intent: `CREATE`
- expected title: `bảo trì xe máy`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc nhà`

- prompt: `Bảo trì hệ thống âm thanh`
- intent: `CREATE`
- expected title: `bảo trì hệ thống âm thanh`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc nhà`

- prompt: `Bảo trì cửa cuốn sáng mai`
- intent: `CREATE`
- expected title: `bảo trì cửa cuốn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc nhà`

- prompt: `Bảo trì nhà tắm vào chiều mai`
- intent: `CREATE`
- expected title: `bảo trì nhà tắm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `việc nhà`

- prompt: `Bảo trì hệ thống nóng lạnh`
- intent: `CREATE`
- expected title: `bảo trì hệ thống nóng lạnh`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `việc nhà`

- prompt: `Bảo trì máy giặt cuối tuần`
- intent: `CREATE`
- expected title: `bảo trì máy giặt`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `việc nhà`

### 79. Câu trần thuật - Thanh toán

- prompt: `Thứ 2 tuần tới tôi có lịch thanh toán hóa đơn tiền điện lúc 10h`
- intent: `CREATE`
- expected title: `thanh toán hóa đơn tiền điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2 tuần tới lúc 10:00
- expected notes: `tài chính`

- prompt: `Nhắc tôi thanh toán tiền nước`
- intent: `CREATE`
- expected title: `thanh toán tiền nước`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán tiền internet sau giờ làm`
- intent: `CREATE`
- expected title: `thanh toán tiền internet`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `tài chính`

- prompt: `Thanh toán phí dịch vụ vào ngày 10`
- intent: `CREATE`
- expected title: `thanh toán phí dịch vụ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày 10
- expected notes: `tài chính`

- prompt: `Thanh toán tiền học phí`
- intent: `CREATE`
- expected title: `thanh toán tiền học phí`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `tài chính`

- prompt: `Thanh toán hóa đơn gas`
- intent: `CREATE`
- expected title: `thanh toán hóa đơn gas`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán số dư thẻ`
- intent: `CREATE`
- expected title: `thanh toán số dư thẻ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

- prompt: `Thanh toán trước hạn`
- intent: `CREATE`
- expected title: `thanh toán trước hạn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

- prompt: `Thanh toán hóa đơn điện thoại`
- intent: `CREATE`
- expected title: `thanh toán hóa đơn điện thoại`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán hóa đơn bảo hiểm`
- intent: `CREATE`
- expected title: `thanh toán hóa đơn bảo hiểm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `tài chính`

### 80. Câu trần thuật - Tập thể dục

- prompt: `Mỗi sáng tôi đều chạy bộ ở công viên lúc 6h sáng`
- intent: `CREATE`
- expected title: `chạy bộ ở công viên`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: mỗi sáng lúc 06:00
- expected notes: `sức khỏe`

- prompt: `Tập yoga buổi tối`
- intent: `CREATE`
- expected title: `tập yoga buổi tối`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `sức khỏe`

- prompt: `Chạy bộ quanh hồ vào sáng mai`
- intent: `CREATE`
- expected title: `chạy bộ quanh hồ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tập gym chiều mai`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sức khỏe`

- prompt: `Bơi lội buổi sáng`
- intent: `CREATE`
- expected title: `bơi lội buổi sáng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tập gym trước khi đi làm`
- intent: `CREATE`
- expected title: `tập gym trước khi đi làm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tập thể dục với bạn`
- intent: `CREATE`
- expected title: `tập thể dục với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

- prompt: `Tập thể dục ở công viên`
- intent: `CREATE`
- expected title: `tập thể dục ở công viên`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sức khỏe`

- prompt: `Tập cardio buổi tối`
- intent: `CREATE`
- expected title: `tập cardio buổi tối`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `sức khỏe`

- prompt: `Nhắc mình tập thể dục lúc 7h sáng`
- intent: `CREATE`
- expected title: `tập thể dục`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 07:00
- expected notes: `sức khỏe`

### 81. Câu hỏi tạo lịch

- prompt: `Chiều mai rảnh không, mình có việc cần bàn`
- intent: `CREATE`
- expected title: `việc cần bàn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `hỏi đáp`

- prompt: `Mai mình có thể gặp nhau không`
- intent: `CREATE`
- expected title: `gặp nhau`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `hỏi đáp`

- prompt: `Bạn có rảnh tối nay không`
- intent: `CREATE`
- expected title: `kiểm tra lịch tối nay`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `hỏi đáp`

- prompt: `Sáng mai có họp không`
- intent: `CREATE`
- expected title: `kiểm tra lịch sáng mai`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `hỏi đáp`

- prompt: `Chiều mai có giờ trống không`
- intent: `CREATE`
- expected title: `kiểm tra giờ trống`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `hỏi đáp`

- prompt: `Có thể xếp lịch họp hôm nay không`
- intent: `CREATE`
- expected title: `xếp lịch họp hôm nay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `hỏi đáp`

- prompt: `Hôm nay có thời gian không`
- intent: `CREATE`
- expected title: `kiểm tra thời gian hôm nay`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `hỏi đáp`

- prompt: `Rảnh vào sáng mai chứ`
- intent: `CREATE`
- expected title: `kiểm tra lịch sáng mai`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `hỏi đáp`

- prompt: `Tối nay có lịch hẹn nào không`
- intent: `CREATE`
- expected title: `kiểm tra lịch tối nay`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `hỏi đáp`

- prompt: `Tuần sau mình có slot nào không`
- intent: `CREATE`
- expected title: `kiểm tra slot tuần sau`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `hỏi đáp`

### 82. Công việc công nghệ

- prompt: `Lúc rảnh fix bug khẩn cấp trên app mobile`
- intent: `CREATE`
- expected title: `fix bug trên app mobile`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: lúc rảnh
- expected notes: `công việc`

- prompt: `Cập nhật phiên bản app vào sáng mai`
- intent: `CREATE`
- expected title: `cập nhật phiên bản app`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `công việc`

- prompt: `Kiểm tra lỗi backend`
- intent: `CREATE`
- expected title: `kiểm tra lỗi backend`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `công việc`

- prompt: `Phân tích yêu cầu tính năng mới`
- intent: `CREATE`
- expected title: `phân tích yêu cầu tính năng mới`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `công việc`

- prompt: `Triển khai bản vá bảo mật`
- intent: `CREATE`
- expected title: `triển khai bản vá bảo mật`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `công việc`

- prompt: `Debug sự cố mạng nội bộ`
- intent: `CREATE`
- expected title: `debug sự cố mạng nội bộ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `công việc`

- prompt: `Cài đặt máy chủ dev`
- intent: `CREATE`
- expected title: `cài đặt máy chủ dev`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `công việc`

- prompt: `Kiểm tra CI/CD`
- intent: `CREATE`
- expected title: `kiểm tra CI/CD`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `công việc`

- prompt: `Cập nhật tài liệu kỹ thuật`
- intent: `CREATE`
- expected title: `cập nhật tài liệu kỹ thuật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `công việc`

- prompt: `Sweep log lỗi vào cuối tuần`
- intent: `CREATE`
- expected title: `sweep log lỗi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `công việc`

### 83. Học tập

- prompt: `Thứ 4 tuần sau thi cuối kỳ môn Toán lúc 7h sáng`
- intent: `CREATE`
- expected title: `thi cuối kỳ môn Toán`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 4 tuần sau lúc 07:00
- expected notes: `học tập`

- prompt: `Học nhóm tiếng Anh chiều mai`
- intent: `CREATE`
- expected title: `học nhóm tiếng Anh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `học tập`

- prompt: `Ôn thi cuối kỳ sáng mai`
- intent: `CREATE`
- expected title: `ôn thi cuối kỳ`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `học tập`

- prompt: `Nộp bài tập về nhà hôm nay`
- intent: `CREATE`
- expected title: `nộp bài tập về nhà`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `học tập`

- prompt: `Đọc lại đề cương môn Lịch sử`
- intent: `CREATE`
- expected title: `đọc lại đề cương môn Lịch sử`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `học tập`

- prompt: `Học online buổi tối`
- intent: `CREATE`
- expected title: `học online buổi tối`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `học tập`

- prompt: `Làm bài tập cuối tuần`
- intent: `CREATE`
- expected title: `làm bài tập cuối tuần`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `học tập`

- prompt: `Học nhóm buổi chiều`
- intent: `CREATE`
- expected title: `học nhóm buổi chiều`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `học tập`

- prompt: `Ôn tập môn Vật lý`
- intent: `CREATE`
- expected title: `ôn tập môn Vật lý`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `học tập`

- prompt: `Chuẩn bị bài thuyết trình môn Văn`
- intent: `CREATE`
- expected title: `chuẩn bị bài thuyết trình môn Văn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `học tập`

### 84. Gia đình

- prompt: `Đưa con đi tiêm phòng lúc 9h sáng thứ 4`
- intent: `CREATE`
- expected title: `đưa con đi tiêm phòng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 4 lúc 09:00
- expected notes: `gia đình`

- prompt: `Đưa mẹ đi khám bệnh`
- intent: `CREATE`
- expected title: `đưa mẹ đi khám bệnh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Mời cả nhà ăn tối`
- intent: `CREATE`
- expected title: `mời cả nhà ăn tối`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Chuẩn bị đồ cho chuyến đi cùng gia đình`
- intent: `CREATE`
- expected title: `chuẩn bị đồ cho chuyến đi cùng gia đình`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Gặp họ hàng vào cuối tuần`
- intent: `CREATE`
- expected title: `gặp họ hàng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `gia đình`

- prompt: `Giúp con làm bài tập`
- intent: `CREATE`
- expected title: `giúp con làm bài tập`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `gia đình`

- prompt: `Gọi thăm bố mẹ lúc 8h`
- intent: `CREATE`
- expected title: `gọi thăm bố mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `gia đình`

- prompt: `Chuẩn bị bữa sáng cho gia đình`
- intent: `CREATE`
- expected title: `chuẩn bị bữa sáng cho gia đình`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `gia đình`

- prompt: `Dọn phòng cho khách đến chơi`
- intent: `CREATE`
- expected title: `dọn phòng cho khách đến chơi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `gia đình`

- prompt: `Lên lịch họp gia đình`
- intent: `CREATE`
- expected title: `lên lịch họp gia đình`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `gia đình`

### 85. Giải trí

- prompt: `Tối thứ 7 đi xem phim rạp lúc 8h`
- intent: `CREATE`
- expected title: `xem phim rạp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7 lúc 20:00
- expected notes: `giải trí`

- prompt: `Đi xem ca nhạc cuối tuần`
- intent: `CREATE`
- expected title: `đi xem ca nhạc`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `giải trí`

- prompt: `Chơi bowling với bạn bè`
- intent: `CREATE`
- expected title: `chơi bowling với bạn bè`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Đi xem triển lãm nghệ thuật`
- intent: `CREATE`
- expected title: `đi xem triển lãm nghệ thuật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `giải trí`

- prompt: `Nhìn phim tại nhà tối nay`
- intent: `CREATE`
- expected title: `xem phim tại nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `giải trí`

- prompt: `Đặt vé xem hài kịch`
- intent: `CREATE`
- expected title: `đặt vé xem hài kịch`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Đi uống cà phê với bạn`
- intent: `CREATE`
- expected title: `đi uống cà phê với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `giải trí`

- prompt: `Chơi game cuối tuần`
- intent: `CREATE`
- expected title: `chơi game cuối tuần`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `giải trí`

- prompt: `Đọc truyện tranh vào tối nay`
- intent: `CREATE`
- expected title: `đọc truyện tranh`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `giải trí`

- prompt: `Xem ca nhạc trực tuyến`
- intent: `CREATE`
- expected title: `xem ca nhạc trực tuyến`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

### 86. Thể thao

- prompt: `Sáng mai chạy bộ quanh hồ lúc 6h`
- intent: `CREATE`
- expected title: `chạy bộ quanh hồ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 06:00
- expected notes: `sức khỏe`

- prompt: `Chơi bóng rổ chiều mai`
- intent: `CREATE`
- expected title: `chơi bóng rổ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sức khỏe`

- prompt: `Tập gym buổi sáng`
- intent: `CREATE`
- expected title: `tập gym buổi sáng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Chơi cầu lông tối nay`
- intent: `CREATE`
- expected title: `chơi cầu lông`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `sức khỏe`

- prompt: `Tập bơi vào cuối tuần`
- intent: `CREATE`
- expected title: `tập bơi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `sức khỏe`

- prompt: `Chạy xe đạp buổi sáng`
- intent: `CREATE`
- expected title: `chạy xe đạp buổi sáng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tập võ chiều nay`
- intent: `CREATE`
- expected title: `tập võ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `sức khỏe`

- prompt: `Đá bóng cuối tuần`
- intent: `CREATE`
- expected title: `đá bóng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `sức khỏe`

- prompt: `Tập pilates sáng mai`
- intent: `CREATE`
- expected title: `tập pilates`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Tập thể thao cùng bạn`
- intent: `CREATE`
- expected title: `tập thể thao cùng bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

### 87. Mua sắm

- prompt: `Chiều nay đi siêu thị mua đồ ăn lúc 5h`
- intent: `CREATE`
- expected title: `đi siêu thị mua đồ ăn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 17:00
- expected notes: `mua sắm`

- prompt: `Mua quần áo mới vào ngày mai`
- intent: `CREATE`
- expected title: `mua quần áo mới`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Mua giày thể thao cuối tuần`
- intent: `CREATE`
- expected title: `mua giày thể thao`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `mua sắm`

- prompt: `Mua quà tặng bạn bè`
- intent: `CREATE`
- expected title: `mua quà tặng bạn bè`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Mua đồ dùng văn phòng`
- intent: `CREATE`
- expected title: `mua đồ dùng văn phòng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Mua đồ trang trí nhà cửa`
- intent: `CREATE`
- expected title: `mua đồ trang trí nhà cửa`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `mua sắm`

- prompt: `Mua đồ dùng học tập`
- intent: `CREATE`
- expected title: `mua đồ dùng học tập`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `mua sắm`

- prompt: `Mua thức ăn cho thú cưng`
- intent: `CREATE`
- expected title: `mua thức ăn cho thú cưng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `mua sắm`

- prompt: `Mua nước hoa cho vợ`
- intent: `CREATE`
- expected title: `mua nước hoa cho vợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `mua sắm`

- prompt: `Mua đồ tặng khách tới chơi`
- intent: `CREATE`
- expected title: `mua đồ tặng khách tới chơi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `mua sắm`

### 88. Thanh toán

- prompt: `Nhắc mình thanh toán hóa đơn tiền điện ngày 10 hàng tháng`
- intent: `CREATE`
- expected title: `thanh toán hóa đơn tiền điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày 10 hàng tháng
- expected notes: `tài chính`

- prompt: `Thanh toán tiền internet vào ngày mai`
- intent: `CREATE`
- expected title: `thanh toán tiền internet`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán tiền nước trước 5h chiều`
- intent: `CREATE`
- expected title: `thanh toán tiền nước`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay trước 17:00
- expected notes: `tài chính`

- prompt: `Thanh toán tiềns điện thoại`
- intent: `CREATE`
- expected title: `thanh toán tiền điện thoại`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán tiền gas vào chiều mai`
- intent: `CREATE`
- expected title: `thanh toán tiền gas`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `tài chính`

- prompt: `Thanh toán hóa đơn truyền hình`
- intent: `CREATE`
- expected title: `thanh toán hóa đơn truyền hình`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán đóng góp hội nhóm`
- intent: `CREATE`
- expected title: `thanh toán đóng góp hội nhóm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `tài chính`

- prompt: `Thanh toán trễ phí công ty`
- intent: `CREATE`
- expected title: `thanh toán trễ phí công ty`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

- prompt: `Thanh toán lại tiền thuê nhà`
- intent: `CREATE`
- expected title: `thanh toán tiền thuê nhà`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán phí bảo trì`
- intent: `CREATE`
- expected title: `thanh toán phí bảo trì`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `tài chính`

### 89. Du lịch

- prompt: `Tháng 5 đi Đà Lạt nghỉ dưỡng 3 ngày`
- intent: `CREATE`
- expected title: `đi Đà Lạt nghỉ dưỡng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tháng 5
- expected notes: `du lịch`

- prompt: `Chuẩn bị hành lý cho chuyến đi`
- intent: `CREATE`
- expected title: `chuẩn bị hành lý cho chuyến đi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Đặt vé máy bay cho chuyến du lịch`
- intent: `CREATE`
- expected title: `đặt vé máy bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `du lịch`

- prompt: `Lên kế hoạch du lịch 2 ngày`
- intent: `CREATE`
- expected title: `lên kế hoạch du lịch 2 ngày`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Đi Vũng Tàu cuối tuần`
- intent: `CREATE`
- expected title: `đi Vũng Tàu`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `du lịch`

- prompt: `Đặt phòng khách sạn cho kỳ nghỉ`
- intent: `CREATE`
- expected title: `đặt phòng khách sạn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Thuê xe đi du lịch miền núi`
- intent: `CREATE`
- expected title: `thuê xe đi du lịch miền núi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `du lịch`

- prompt: `Chuẩn bị đồ dùng cá nhân cho chuyến đi`
- intent: `CREATE`
- expected title: `chuẩn bị đồ dùng cá nhân cho chuyến đi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Đặt tour tham quan`
- intent: `CREATE`
- expected title: `đặt tour tham quan`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `du lịch`

- prompt: `Tìm khách sạn cho kỳ nghỉ`
- intent: `CREATE`
- expected title: `tìm khách sạn cho kỳ nghỉ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

### 90. Công việc văn phòng

- prompt: `Họp giao ban đầu tuần lúc 8h30 thứ 2`
- intent: `CREATE`
- expected title: `họp giao ban đầu tuần`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 2 lúc 08:30
- expected notes: `công việc`

- prompt: `Báo cáo tài chính cho sếp vào chiều mai`
- intent: `CREATE`
- expected title: `báo cáo tài chính cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `công việc`

- prompt: `Họp nội bộ phòng nhân sự`
- intent: `CREATE`
- expected title: `họp nội bộ phòng nhân sự`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `công việc`

- prompt: `Chuẩn bị slide thuyết trình`
- intent: `CREATE`
- expected title: `chuẩn bị slide thuyết trình`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `công việc`

- prompt: `Kiểm tra email công việc`
- intent: `CREATE`
- expected title: `kiểm tra email công việc`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `công việc`

- prompt: `Lên kế hoạch công việc tuần tới`
- intent: `CREATE`
- expected title: `lên kế hoạch công việc tuần tới`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần tới
- expected notes: `công việc`

- prompt: `Gặp trưởng phòng lúc 10h`
- intent: `CREATE`
- expected title: `gặp trưởng phòng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `công việc`

- prompt: `Nộp báo cáo nhân sự`
- intent: `CREATE`
- expected title: `nộp báo cáo nhân sự`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `công việc`

- prompt: `Đi họp báo cáo sáng mai`
- intent: `CREATE`
- expected title: `đi họp báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `công việc`

- prompt: `Làm việc giấy tờ văn phòng`
- intent: `CREATE`
- expected title: `làm việc giấy tờ văn phòng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `công việc`

### 91. Khám bệnh

- prompt: `Sáng thứ 5 đi khám sức khỏe định kỳ lúc 8h`
- intent: `CREATE`
- expected title: `khám sức khỏe định kỳ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5 lúc 08:00
- expected notes: `sức khỏe`

- prompt: `Khám nha khoa chiều mai`
- intent: `CREATE`
- expected title: `khám nha khoa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sức khỏe`

- prompt: `Khám mắt sáng mai`
- intent: `CREATE`
- expected title: `khám mắt`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Khám tim mạch tuần sau`
- intent: `CREATE`
- expected title: `khám tim mạch`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần sau
- expected notes: `sức khỏe`

- prompt: `Khám bệnh công ty`
- intent: `CREATE`
- expected title: `khám bệnh công ty`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `sức khỏe`

- prompt: `Khám sức khỏe cho gia đình`
- intent: `CREATE`
- expected title: `khám sức khỏe cho gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `sức khỏe`

- prompt: `Đặt lịch khám thai`
- intent: `CREATE`
- expected title: `đặt lịch khám thai`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

- prompt: `Khám mỡ máu vào thứ 3`
- intent: `CREATE`
- expected title: `khám mỡ máu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3
- expected notes: `sức khỏe`

- prompt: `Khám sức khỏe định kỳ lúc 9h`
- intent: `CREATE`
- expected title: `khám sức khỏe định kỳ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 09:00
- expected notes: `sức khỏe`

- prompt: `Khám răng buổi sáng`
- intent: `CREATE`
- expected title: `khám răng buổi sáng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

### 92. Sự kiện xã hội

- prompt: `Tối nay đi karaoke với nhóm bạn lúc 9h`
- intent: `CREATE`
- expected title: `đi karaoke với nhóm bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 21:00
- expected notes: `giải trí`

- prompt: `Tham gia đêm nhạc cuối tuần`
- intent: `CREATE`
- expected title: `tham gia đêm nhạc`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `giải trí`

- prompt: `Dự tiệc chào mừng đồng nghiệp`
- intent: `CREATE`
- expected title: `dự tiệc chào mừng đồng nghiệp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Tham dự lễ trao giải`
- intent: `CREATE`
- expected title: `tham dự lễ trao giải`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `giải trí`

- prompt: `Tham gia buổi hoà nhạc`
- intent: `CREATE`
- expected title: `tham gia buổi hoà nhạc`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Gặp mặt nhóm cuối tuần`
- intent: `CREATE`
- expected title: `gặp mặt nhóm`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `giải trí`

- prompt: `Dự tiệc sinh nhật bạn`
- intent: `CREATE`
- expected title: `dự tiệc sinh nhật bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `giải trí`

- prompt: `Xem triển lãm nghệ thuật`
- intent: `CREATE`
- expected title: `xem triển lãm nghệ thuật`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Tham gia buổi tụ họp cộng đồng`
- intent: `CREATE`
- expected title: `tham gia buổi tụ họp cộng đồng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `giải trí`

- prompt: `Đi xem hài kịch tối mai`
- intent: `CREATE`
- expected title: `đi xem hài kịch`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối mai
- expected notes: `giải trí`

### 93. Việc nhà

- prompt: `Chủ nhật này dọn nhà đón tết`
- intent: `CREATE`
- expected title: `dọn nhà đón tết`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chủ nhật này
- expected notes: `việc nhà`

- prompt: `Dọn dẹp phòng khách sáng mai`
- intent: `CREATE`
- expected title: `dọn dẹp phòng khách`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc nhà`

- prompt: `Giặt áo quần chiều nay`
- intent: `CREATE`
- expected title: `giặt áo quần`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay
- expected notes: `việc nhà`

- prompt: `Sắp xếp lại tủ bếp`
- intent: `CREATE`
- expected title: `sắp xếp lại tủ bếp`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc nhà`

- prompt: `Hút bụi cầu thang`
- intent: `CREATE`
- expected title: `hút bụi cầu thang`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc nhà`

- prompt: `Lau kính cửa sổ`
- intent: `CREATE`
- expected title: `lau kính cửa sổ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc nhà`

- prompt: `Thay drap giường`
- intent: `CREATE`
- expected title: `thay drap giường`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `việc nhà`

- prompt: `Rửa bát sau bữa tối`
- intent: `CREATE`
- expected title: `rửa bát`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `việc nhà`

- prompt: `Lau sàn nhà buổi sáng`
- intent: `CREATE`
- expected title: `lau sàn nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `việc nhà`

- prompt: `Sắp xếp đồ dùng trong phòng ngủ`
- intent: `CREATE`
- expected title: `sắp xếp đồ dùng trong phòng ngủ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `việc nhà`

### 94. Câu trần thuật bắt đầu bằng ngày (Date-First Declarative)

- prompt: `2/2/2027, mình có buổi đi Đà Lạt với gia đình, khởi hành lúc 3h sáng`
- intent: `CREATE`
- expected title: `đi Đà Lạt với gia đình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 2/2/2027 lúc 03:00
- expected notes: `du lịch`

- prompt: `15/5/2026, gia đình tổ chức sinh nhật cho bà nội`
- intent: `CREATE`
- expected title: `tổ chức sinh nhật cho bà nội`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: 15/5/2026
- expected notes: `gia đình`

- prompt: `Ngày 10 tháng 10, nộp hồ sơ xin visa du học`
- intent: `CREATE`
- expected title: `nộp hồ sơ xin visa du học`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: 10/10
- expected notes: `học tập`

- prompt: `Ngày 21 tháng 4, tôi phải lên lớp báo cáo đồ án môn chuyên ngành lúc 1h chiều`
- intent: `CREATE`
- expected title: `lên lớp báo cáo đồ án môn chuyên ngành`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/04/2026 13:00`
- expected notes: `học tập`, `task học`

- prompt: `Ngày 21 tháng 4 tôi có buổi báo cáo đồ án môn chuyên ngành lúc 13h`
- intent: `CREATE`
- expected title: `buổi báo cáo đồ án môn chuyên ngành`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/04/2026 13:00`
- expected notes: `học tập`

- prompt: `Ngày 21 tháng 4 này tôi phải lên lớp báo cáo đồ án chuyên ngành lúc 1h chiều`
- intent: `CREATE`
- expected title: `lên lớp báo cáo đồ án chuyên ngành`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: `21/04/2026 13:00`
- expected notes: `học tập`

- prompt: `20-11-2026, đi khám sức khỏe định kỳ tại bệnh viện`
- intent: `CREATE`
- expected title: `khám sức khỏe định kỳ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 20-11-2026
- expected notes: `sức khỏe`

- prompt: `Thứ 6 tuần sau, họp tổng kết quý 3 với phòng kinh doanh`
- intent: `CREATE`
- expected title: `họp tổng kết quý 3`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: Thứ 6 tuần sau
- expected notes: `công việc`

### 95. More morning/evening create examples

- prompt: `Mai tôi phải đi ngân hàng lúc 9h sáng`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `task cá nhân`

- prompt: `Tối mai tôi phải đi ngân hàng lúc 9h tối`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối mai lúc 21:00
- expected notes: `task cá nhân`

- prompt: `Sáng mai tôi phải đi ngân hàng lúc 9h`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `task cá nhân`

- prompt: `Mai tôi phải đi ngân hàng vào 9h tối`
- intent: `CREATE`
- expected title: `đi ngân hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối mai lúc 21:00
- expected notes: `task cá nhân`

- prompt: `Hôm nay tôi có họp muộn lúc 8h tối`
- intent: `CREATE`
- expected title: `họp muộn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 20:00
- expected notes: `họp`

- prompt: `Ngày mai tôi họp với sếp lúc 10h`
- intent: `CREATE`
- expected title: `họp với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 10:00
- expected notes: `gửi sếp`

- prompt: `Chiều mai đi siêu thị lúc 4h`
- intent: `CREATE`
- expected title: `đi siêu thị`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều mai lúc 16:00
- expected notes: `mua sắm`

- prompt: `Sáng thứ ba tuần sau đi gặp đối tác lúc 9h`
- intent: `CREATE`
- expected title: `gặp đối tác`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ ba tuần sau lúc 09:00
- expected notes: `công việc`

- prompt: `Chủ nhật tuần này đi chợ lúc 7h sáng`
- intent: `CREATE`
- expected title: `đi chợ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chủ nhật tuần này lúc 07:00
- expected notes: `việc gia đình`

- prompt: `Tối thứ sáu tôi đi xem phim lúc 8h30`
- intent: `CREATE`
- expected title: `đi xem phim`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối thứ sáu lúc 20:30
- expected notes: `giải trí`

- prompt: `20/4 tôi có bài thuyết trình lúc 14h`
- intent: `CREATE`
- expected title: `bài thuyết trình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 20/04/2026 lúc 14:00
- expected notes: `học tập`

- prompt: `Ngày 21 tháng 4 họp báo cáo đồ án lúc 13h`
- intent: `CREATE`
- expected title: `họp báo cáo đồ án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 21/04/2026 lúc 13:00
- expected notes: `học tập`

- prompt: `Thứ tư tuần sau kiểm tra y tế lúc 9h`
- intent: `CREATE`
- expected title: `kiểm tra y tế`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ tư tuần sau lúc 09:00
- expected notes: `sức khỏe`

- prompt: `Thứ hai tôi phải đi gặp khách hàng lúc 11h`
- intent: `CREATE`
- expected title: `gặp khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ hai lúc 11:00
- expected notes: `công việc`

- prompt: `Xem lại tài liệu trước 10h sáng mai`
- intent: `CREATE`
- expected title: `xem lại tài liệu`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `task chuẩn bị`

- prompt: `Gọi điện cho bố mẹ lúc 6h tối`
- intent: `CREATE`
- expected title: `gọi điện cho bố mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 18:00
- expected notes: `gia đình`

- prompt: `Thanh toán hóa đơn điện vào ngày mai`
- intent: `CREATE`
- expected title: `thanh toán hóa đơn điện`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Dọn phòng lúc 5h chiều hôm nay`
- intent: `CREATE`
- expected title: `dọn phòng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 17:00
- expected notes: `việc nhà`

- prompt: `Đưa con đi học lúc 7h30 sáng`
- intent: `CREATE`
- expected title: `đưa con đi học`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 07:30
- expected notes: `gia đình`

- prompt: `Đi bể bơi lúc 6h chiều`
- intent: `CREATE`
- expected title: `đi bể bơi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 18:00
- expected notes: `sức khỏe`

- prompt: `Chạy bộ lúc 5h30 sáng`
- intent: `CREATE`
- expected title: `chạy bộ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 05:30
- expected notes: `sức khỏe`

- prompt: `Đi ăn tối với bạn lúc 8h tối ngày mai`
- intent: `CREATE`
- expected title: `đi ăn tối với bạn`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối mai lúc 20:00
- expected notes: `giải trí`

- prompt: `Họp đội phát triển lúc 9h sáng thứ sáu`
- intent: `CREATE`
- expected title: `họp đội phát triển`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ sáu lúc 09:00
- expected notes: `công việc`

- prompt: `Nộp hồ sơ trước 15h ngày 18/4`
- intent: `CREATE`
- expected title: `nộp hồ sơ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 18/04/2026 lúc 15:00
- expected notes: `deadline`

- prompt: `Làm báo cáo cho sếp trước 9h sáng mai`
- intent: `CREATE`
- expected title: `làm báo cáo cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `deadline`

- prompt: `Nhớ mua quà cho mẹ vào ngày 20 tháng 5`
- intent: `CREATE`
- expected title: `mua quà cho mẹ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 20/05/2026
- expected notes: `gia đình`

- prompt: `Viết email cho khách lúc 10h trưa`
- intent: `CREATE`
- expected title: `viết email cho khách`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 10:00
- expected notes: `công việc`

- prompt: `Hẹn gặp bác sĩ lúc 11h sáng thứ tư`
- intent: `CREATE`
- expected title: `hẹn gặp bác sĩ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ tư lúc 11:00
- expected notes: `sức khỏe`

- prompt: `Đặt phòng khách sạn ngày 2/5`
- intent: `CREATE`
- expected title: `đặt phòng khách sạn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 02/05/2026
- expected notes: `du lịch`

- prompt: `Gặp đối tác tại quán cà phê lúc 3h chiều`
- intent: `CREATE`
- expected title: `gặp đối tác tại quán cà phê`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 15:00
- expected notes: `công việc`

- prompt: `Tham gia cuộc họp online lúc 14h`
- intent: `CREATE`
- expected title: `tham gia cuộc họp online`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 14:00
- expected notes: `công việc`

- prompt: `Họp trực tuyến lúc 16h thứ tư`
- intent: `CREATE`
- expected title: `họp trực tuyến`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ tư lúc 16:00
- expected notes: `công việc`

- prompt: `Họp trực tiếp lúc 10h sáng mai`
- intent: `CREATE`
- expected title: `họp trực tiếp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `công việc`

- prompt: `Đi lấy thuốc lúc 7h tối`
- intent: `CREATE`
- expected title: `đi lấy thuốc`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 19:00
- expected notes: `sức khỏe`

- prompt: `Lên kế hoạch tuần sau vào thứ hai`
- intent: `CREATE`
- expected title: `lên kế hoạch`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ hai tuần sau
- expected notes: `công việc`

- prompt: `Chuẩn bị báo cáo cho buổi họp ngày mai`
- intent: `CREATE`
- expected title: `chuẩn bị báo cáo cho buổi họp`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `công việc`

- prompt: `Gửi email cho sếp lúc 9h sáng mai`
- intent: `CREATE`
- expected title: `gửi email cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `gửi sếp`

- prompt: `Tập gym lúc 6h sáng`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 06:00
- expected notes: `sức khỏe`

- prompt: `Hẹn phỏng vấn lúc 14h ngày mai`
- intent: `CREATE`
- expected title: `hẹn phỏng vấn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 14:00
- expected notes: `công việc`

- prompt: `Đi họp nhóm lúc 3h chiều thứ sáu`
- intent: `CREATE`
- expected title: `đi họp nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ sáu lúc 15:00
- expected notes: `công việc`

- prompt: `Xem phim tối nay lúc 8h`
- intent: `CREATE`
- expected title: `xem phim`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 20:00
- expected notes: `giải trí`

- prompt: `Làm slide báo cáo lúc 2h chiều`
- intent: `CREATE`
- expected title: `làm slide báo cáo`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 14:00
- expected notes: `công việc`

- prompt: `Có buổi học tiếng Anh lúc 7h tối`
- intent: `CREATE`
- expected title: `học tiếng Anh`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 19:00
- expected notes: `học tập`

- prompt: `Nộp bài tập lúc 5h chiều thứ bảy`
- intent: `CREATE`
- expected title: `nộp bài tập`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ bảy lúc 17:00
- expected notes: `học tập`

- prompt: `Đi họp với sếp vào 10h sáng mai`
- intent: `CREATE`
- expected title: `đi họp với sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `gửi sếp`

- prompt: `Chuẩn bị báo cáo tài chính vào ngày 25/4`
- intent: `CREATE`
- expected title: `chuẩn bị báo cáo tài chính`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: 25/04/2026
- expected notes: `công việc`

- prompt: `Đón khách đến lúc 6h chiều`
- intent: `CREATE`
- expected title: `đón khách`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 18:00
- expected notes: `công việc`

- prompt: `Họp nội bộ lúc 9h sáng mai`
- intent: `CREATE`
- expected title: `họp nội bộ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `công việc`

- prompt: `Viết email xác nhận vào 11h trưa`
- intent: `CREATE`
- expected title: `viết email xác nhận`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 11:00
- expected notes: `công việc`

- prompt: `Đặt lịch sửa xe vào ngày 15 tháng 4 lúc 8h sáng`
- intent: `CREATE`
- expected title: `sửa xe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 15/04/2026 lúc 08:00
- expected notes: `việc nhà`

### 96. More diverse time/title/priority examples

- prompt: `Sáng mai tôi có buổi họp quan trọng với khách hàng lúc 8h`
- intent: `CREATE`
- expected title: `buổi họp quan trọng với khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 08:00
- expected notes: `gặp khách hàng`, `quan trọng`

- prompt: `Chiều nay nhận hồ sơ tại văn phòng lúc 3h`
- intent: `CREATE`
- expected title: `nhận hồ sơ tại văn phòng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 15:00
- expected notes: `hành chính`

- prompt: `Tối nay đi ăn uống với đối tác lúc 7h30`
- intent: `CREATE`
- expected title: `đi ăn uống với đối tác`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 19:30
- expected notes: `giải trí`, `công việc`

- prompt: `Ngày 30/4 tôi phải hoàn thiện slide`
- intent: `CREATE`
- expected title: `hoàn thiện slide`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: 30/04/2026
- expected notes: `deadline`

- prompt: `Tuần tới làm báo cáo ngân sách`
- intent: `CREATE`
- expected title: `làm báo cáo ngân sách`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần tới
- expected notes: `công việc`

- prompt: `Nhắc tôi đặt vé máy bay vào ngày 5/5`
- intent: `CREATE`
- expected title: `đặt vé máy bay`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 05/05/2026
- expected notes: `du lịch`

- prompt: `Thứ tư tuần này họp online lúc 14h30`
- intent: `CREATE`
- expected title: `họp online`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ tư tuần này lúc 14:30
- expected notes: `công việc`

- prompt: `Ghi chú xếp lịch gặp khách lúc 15h`
- intent: `CREATE`
- expected title: `xếp lịch gặp khách`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 15:00
- expected notes: `công việc`

- prompt: `Nhớ gửi thư cho sếp lúc 17h`
- intent: `CREATE`
- expected title: `gửi thư cho sếp`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 17:00
- expected notes: `gửi sếp`

- prompt: `Đi họp nhóm lúc 10h sáng`
- intent: `CREATE`
- expected title: `đi họp nhóm`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 10:00
- expected notes: `công việc`

- prompt: `Tối nay tập gym lúc 8h`
- intent: `CREATE`
- expected title: `tập gym`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối nay lúc 20:00
- expected notes: `sức khỏe`

- prompt: `Chiều mai kiểm tra hợp đồng với luật sư`
- intent: `CREATE`
- expected title: `kiểm tra hợp đồng với luật sư`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `công việc`

- prompt: `Ngày 1/5 đi lễ hội với gia đình`
- intent: `CREATE`
- expected title: `đi lễ hội với gia đình`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: 01/05/2026
- expected notes: `gia đình`

- prompt: `Sáng mai họp giao ban lúc 9h chính xác`
- intent: `CREATE`
- expected title: `họp giao ban`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `công việc`

- prompt: `Thứ sáu tuần sau phỏng vấn ứng viên lúc 11h`
- intent: `CREATE`
- expected title: `phỏng vấn ứng viên`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ sáu tuần sau lúc 11:00
- expected notes: `công việc`

- prompt: `Thứ bảy tới họp cùng team lúc 8h`
- intent: `CREATE`
- expected title: `họp cùng team`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ bảy tới lúc 08:00
- expected notes: `công việc`

- prompt: `Ngày mai tôi đi nhận hàng lúc 2h`
- intent: `CREATE`
- expected title: `đi nhận hàng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 14:00
- expected notes: `hành chính`

- prompt: `Nhắc tôi kiểm tra email lúc 11h trưa`
- intent: `CREATE`
- expected title: `kiểm tra email`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 11:00
- expected notes: `công việc`

- prompt: `Gặp khách hàng vào chiều thứ 4`
- intent: `CREATE`
- expected title: `gặp khách hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: chiều thứ 4
- expected notes: `công việc`

- prompt: `Nộp thuế trước 17h ngày mai`
- intent: `CREATE`
- expected title: `nộp thuế`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 17:00
- expected notes: `tài chính`

- prompt: `Tối nay kiểm tra báo cáo ngân hàng`
- intent: `CREATE`
- expected title: `kiểm tra báo cáo ngân hàng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tối nay
- expected notes: `tài chính`

- prompt: `Thứ 2 tuần sau tổ chức hội thảo lúc 14h`
- intent: `CREATE`
- expected title: `tổ chức hội thảo`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 2 tuần sau lúc 14:00
- expected notes: `công việc`

- prompt: `Buổi sáng mai mình có hẹn khám sức khỏe lúc 8h30`
- intent: `CREATE`
- expected title: `hẹn khám sức khỏe`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 08:30
- expected notes: `sức khỏe`

- prompt: `Tối mai mình có bữa ăn cùng đối tác lúc 7h`
- intent: `CREATE`
- expected title: `bữa ăn cùng đối tác`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: tối mai lúc 19:00
- expected notes: `công việc`

- prompt: `Nhắc tôi làm việc nhà lúc 4h chiều`
- intent: `CREATE`
- expected title: `làm việc nhà`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 16:00
- expected notes: `việc nhà`

- prompt: `Thứ 3 tuần tới tôi cần gặp luật sư lúc 2h chiều`
- intent: `CREATE`
- expected title: `gặp luật sư`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 3 tuần tới lúc 14:00
- expected notes: `công việc`

- prompt: `Chiều mai sửa bài thuyết trình trong 30 phút`
- intent: `CREATE`
- expected title: `sửa bài thuyết trình`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `công việc`

- prompt: `Sáng mai đi gửi hồ sơ lúc 7h30`
- intent: `CREATE`
- expected title: `đi gửi hồ sơ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 07:30
- expected notes: `hành chính`

### 97. Hẹn gặp bác sĩ

- prompt: `Hôm nay tôi có hẹn gặp bác sĩ lúc 4h chiều`
- intent: `CREATE`
- expected title: `gặp bác sĩ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 16:00
- expected notes: `sức khỏe`

- prompt: `Mai tôi có lịch khám mắt lúc 9h`
- intent: `CREATE`
- expected title: `khám mắt`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 09:00
- expected notes: `sức khỏe`

- prompt: `Thứ 5 tuần sau khám nha khoa lúc 10h`
- intent: `CREATE`
- expected title: `khám nha khoa`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 5 tuần sau lúc 10:00
- expected notes: `sức khỏe`

- prompt: `Nhắc tôi đặt lịch khám tim mạch`
- intent: `CREATE`
- expected title: `đặt lịch khám tim mạch`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `sức khỏe`

- prompt: `Khám thai sản vào sáng thứ 3`
- intent: `CREATE`
- expected title: `khám thai sản`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 3 sáng
- expected notes: `sức khỏe`

- prompt: `Buổi trưa đi khám da liễu`
- intent: `CREATE`
- expected title: `khám da liễu`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 12:00
- expected notes: `sức khỏe`

- prompt: `Thứ 6 tôi có hẹn khám răng lúc 8h`
- intent: `CREATE`
- expected title: `khám răng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 6 lúc 08:00
- expected notes: `sức khỏe`

- prompt: `Gọi bác sĩ để hẹn khám sáng mai`
- intent: `CREATE`
- expected title: `gọi bác sĩ hẹn khám`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `sức khỏe`

- prompt: `Hẹn bác sĩ tâm lý vào chiều mai`
- intent: `CREATE`
- expected title: `hẹn bác sĩ tâm lý`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều mai
- expected notes: `sức khỏe`

- prompt: `Đặt khám tổng quát vào sáng thứ 7`
- intent: `CREATE`
- expected title: `đặt khám tổng quát`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ 7 sáng
- expected notes: `sức khỏe`

### 98. Hẹn phỏng vấn

- prompt: `Thứ 4 tuần tới tôi có phỏng vấn lúc 10h`
- intent: `CREATE`
- expected title: `phỏng vấn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 4 tuần tới lúc 10:00
- expected notes: `công việc`

- prompt: `Mai tôi có phỏng vấn lúc 2h chiều`
- intent: `CREATE`
- expected title: `phỏng vấn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: ngày mai lúc 14:00
- expected notes: `công việc`

- prompt: `Hẹn phỏng vấn với HR lúc 9h sáng`
- intent: `CREATE`
- expected title: `hẹn phỏng vấn với HR`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai lúc 09:00
- expected notes: `công việc`

- prompt: `Phỏng vấn trực tuyến hôm nay lúc 3h`
- intent: `CREATE`
- expected title: `phỏng vấn trực tuyến`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay lúc 15:00
- expected notes: `công việc`

- prompt: `Phỏng vấn xin việc vào thứ 2 tới`
- intent: `CREATE`
- expected title: `phỏng vấn xin việc`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 2 tới
- expected notes: `công việc`

- prompt: `Thứ sáu tôi có lịch phỏng vấn đối tác`
- intent: `CREATE`
- expected title: `phỏng vấn đối tác`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: thứ sáu
- expected notes: `gặp gỡ`

- prompt: `Nhắc tôi chuẩn bị phỏng vấn sáng mai`
- intent: `CREATE`
- expected title: `chuẩn bị phỏng vấn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `công việc`

- prompt: `Phỏng vấn ứng viên lúc 11h thứ 5`
- intent: `CREATE`
- expected title: `phỏng vấn ứng viên`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: thứ 5 lúc 11:00
- expected notes: `công việc`

- prompt: `Gặp khách hàng để phỏng vấn dự án`
- intent: `CREATE`
- expected title: `gặp khách hàng để phỏng vấn dự án`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `công việc`

- prompt: `Xác nhận lịch phỏng vấn lúc 4h chiều`
- intent: `CREATE`
- expected title: `xác nhận lịch phỏng vấn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: chiều nay lúc 16:00
- expected notes: `công việc`

### 99. Thanh toán thẻ tín dụng

- prompt: `Thanh toán thẻ tín dụng trước 12h hôm nay`
- intent: `CREATE`
- expected title: `thanh toán thẻ tín dụng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay trước 12:00
- expected notes: `tài chính`

- prompt: `Nộp tiền thẻ tín dụng ngày 20`
- intent: `CREATE`
- expected title: `nộp tiền thẻ tín dụng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày 20
- expected notes: `tài chính`

- prompt: `Nhắc tôi thanh toán thẻ tín dụng`
- intent: `CREATE`
- expected title: `thanh toán thẻ tín dụng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Thanh toán thẻ trước kỳ hạn`
- intent: `CREATE`
- expected title: `thanh toán thẻ trước kỳ hạn`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `tài chính`

- prompt: `Mua vé máy bay và thanh toán thẻ`
- intent: `CREATE`
- expected title: `mua vé máy bay và thanh toán thẻ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `tài chính`

- prompt: `Kiểm tra sao kê thẻ tín dụng`
- intent: `CREATE`
- expected title: `kiểm tra sao kê thẻ tín dụng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: hôm nay
- expected notes: `tài chính`

- prompt: `Đóng thẻ tín dụng cũ vào thứ 6`
- intent: `CREATE`
- expected title: `đóng thẻ tín dụng cũ`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 6
- expected notes: `tài chính`

- prompt: `Chuẩn bị tiền để thanh toán thẻ`
- intent: `CREATE`
- expected title: `chuẩn bị tiền thanh toán thẻ`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: sáng mai
- expected notes: `tài chính`

- prompt: `Thanh toán thẻ tín dụng vào ngày 5/5`
- intent: `CREATE`
- expected title: `thanh toán thẻ tín dụng`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: 05/05/2026
- expected notes: `tài chính`

- prompt: `Nhắc tôi đóng tiền thẻ tín dụng trước 17h`
- intent: `CREATE`
- expected title: `đóng tiền thẻ tín dụng`
- expected priority: `HIGH`
- expected status: `PENDING`
- expected dueDate: hôm nay trước 17:00
- expected notes: `tài chính`

### 100. Du lịch ngắn hạn

- prompt: `Cuối tuần này đi Vũng Tàu nghỉ dưỡng`
- intent: `CREATE`
- expected title: `đi Vũng Tàu nghỉ dưỡng`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `du lịch`

- prompt: `Mai đi Đà Lạt 2 ngày`
- intent: `CREATE`
- expected title: `đi Đà Lạt 2 ngày`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Đặt vé xe đi Nha Trang`
- intent: `CREATE`
- expected title: `đặt vé xe đi Nha Trang`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: tuần này
- expected notes: `du lịch`

- prompt: `Chuẩn bị hành lý cho chuyến du lịch`
- intent: `CREATE`
- expected title: `chuẩn bị hành lý cho chuyến du lịch`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Đi du lịch cùng gia đình ngày 30/4`
- intent: `CREATE`
- expected title: `đi du lịch cùng gia đình`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: 30/04/2026
- expected notes: `du lịch`

- prompt: `Đặt phòng khách sạn cho kỳ nghỉ ngắn`
- intent: `CREATE`
- expected title: `đặt phòng khách sạn`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Thuê xe đi du lịch miền núi`
- intent: `CREATE`
- expected title: `thuê xe đi du lịch miền núi`
- expected priority: `MEDIUM`
- expected status: `PENDING`
- expected dueDate: cuối tuần
- expected notes: `du lịch`

- prompt: `Lên kế hoạch đi du lịch 2 ngày`
- intent: `CREATE`
- expected title: `lên kế hoạch đi du lịch 2 ngày`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Chuẩn bị đồ dùng cá nhân cho chuyến đi`
- intent: `CREATE`
- expected title: `chuẩn bị đồ dùng cá nhân cho chuyến đi`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: ngày mai
- expected notes: `du lịch`

- prompt: `Đi du lịch ngắn ngày vào thứ 7`
- intent: `CREATE`
- expected title: `đi du lịch ngắn ngày`
- expected priority: `LOW`
- expected status: `PENDING`
- expected dueDate: thứ 7
- expected notes: `du lịch`
