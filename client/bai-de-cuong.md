```
ỦY BAN NHÂN DÂN
THÀNH PHỐ HỒ CHÍ MINH
```
```
CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM
```
**TRƯỜNG ĐẠI HỌC SÀI GÒN Độc lập – Tự do – Hạnh phúc**

## ĐỀ CƯƠNG ĐỒ ÁN CHUYÊN NGÀNH

```
I – Phần thông tin chung
Họ và tên sinh viên 1: Trần Đức Thanh
MSSV : 3121410451 Lớp: DCT
E-mail : thanhcnttmcpe@gmail.com
Ngành : Công nghệ thông tin
Chuyên ngành : Kỹ thuật phần mềm
```
```
Họ và tên sinh viên 2: Nguyễn Phúc Tứ
MSSV : 3122560088 Lớp:DKP
E-mail : nguyenphuctu.0904@gmail.com
Ngành : Công nghệ thông tin
Chuyên ngành : Kỹ thuật phần mềm
```
```
Họ và tên sinh viên 3: Con Kiến Huy
MSSV : 3121410006 Lớp: DCT
E-mail : kiengianghuy1989@gmail.com
Ngành : Công nghệ thông tin
Chuyên ngành : Kỹ thuật phần mềm
```
```
Họ và tên sinh viên 4: Nguyễn Trí Bảo
MSSV : 3121410076 Lớp: DCT
E-mail : nguyentribao3105@gmail.com
Ngành : Công nghệ thông tin
Chuyên ngành : Hệ thống thông tin
```

# ĐỀ TÀI: XÂY DỰNG ỨNG DỤNG WEB QUẢN LÝ LỊCH TRÌNH

# CÁ NHÂN TÍCH HỢP TÍNH NĂNG XỬ LÝ TIẾNG VIỆT

## II - Nội dung đề cương

**1. Lý do chọn đề tài**
    Trong bối cảnh công nghệ thông tin phát triển mạnh mẽ, các hệ thống phần mềm
ngày càng được tích hợp nhiều công nghệ mới nhằm nâng cao khả năng tự động hóa và
hỗ trợ người dùng hiệu quả hơn. Đặc biệt, các công nghệ liên quan đến trí tuệ nhân tạo
(Artificial Intelligence – AI) và xử lý ngôn ngữ tự nhiên (Natural Language Processing
- NLP) đang được ứng dụng rộng rãi trong nhiều lĩnh vực khác nhau như trợ lý ảo, hệ
thống gợi ý, chatbot và phân tích dữ liệu văn bản.

Đối với sinh viên ngành Công nghệ Thông tin, việc nghiên cứu và tiếp cận các
công nghệ mới là một yếu tố quan trọng nhằm nâng cao kiến thức chuyên môn cũng
như khả năng áp dụng lý thuyết vào thực tiễn. Thông qua việc thực hiện đồ án môn học,
sinh viên có cơ hội tìm hiểu sâu hơn về quy trình thiết kế, xây dựng và triển khai một
hệ thống phần mềm hoàn chỉnh, từ giai đoạn phân tích yêu cầu, thiết kế kiến trúc hệ
thống, phát triển chức năng cho đến kiểm thử và đánh giá kết quả.

## 2. Lịch sử nghiên cứu - Tổng quan

Thư viện NLP tiếng Việt: VnCoreNLP (Thanh Vu et al., 2018) hỗ trợ phân đoạn
từ, POS và NER cho tiếng Việt[1]. Underthesea (Vu Anh, 2018) là một bộ công cụ
Python mã nguồn mở hỗ trợ NLP tiếng Việt[2]. PhoBERT (Nguyễn và Nguyễn, 2020)
là một mô hình BERT lớn được huấn luyện đặc biệt cho tiếng Việt, đạt hiệu suất SOTA
trong nhiều nhiệm vụ[3].

Phân loại ý định và nhận dạng thực thể có tên ( **Intent Classification & NER** ):
Để xác định ý định, các giải pháp AI cho hệ thống lịch thường sử dụng Naive
Bayes/SVM/Logistic hoặc mạng nơ-ron (LSTM, Transformer), và để trích xuất các thực
thể (như tên sự kiện), chúng sử dụng BiLSTM-CRF hoặc BERT.


Xử lý thời gian: SUTime (Stanford) chỉ cung cấp các quy tắc cho tiếng Anh.
HeidelTime (Heidelberg, 2010) là một hệ thống dựa trên quy tắc hỗ trợ nhiều ngôn ngữ,
và tiếng Việt đã được thêm vào. Một nghiên cứu của Ha (2024) đã sử dụng lại
HeidelTime trong văn bản tiếng Việt. Các công cụ này cho phép bạn giải thích thời gian
(TIMEX3) trong dữ liệu liên quan đến ngày và giờ.

Các chương trình lịch: Hiện nay có những hệ thống lịch thông minh tích hợp xử
lý ngôn ngữ tự nhiên/trí tuệ nhân tạo, ví dụ như Calendar.help 2017 và ScheduleMe
2025, nhưng chúng chủ yếu dành cho người dùng tiếng Anh và các công ty lớn. Smart
Scheduler là một hệ thống hướng đến người dùng cá nhân/người Việt Nam, đơn giản

### nhưng hiệu quả.

### 3. Mục đích và nhiệm vụ nghiên cứu

```
3.1. Mục đích
```
Mục đích của đề tài là nghiên cứu và xây dựng một ứng dụng web quản lý lịch
trình cá nhân tích hợp khả năng xử lý ngôn ngữ tự nhiên tiếng Việt nhằm hỗ trợ người
dùng tạo và quản lý công việc một cách thuận tiện hơn. Thay vì phải nhập thông tin
thông qua các biểu mẫu phức tạp, người dùng có thể sử dụng các câu lệnh tiếng Việt tự
nhiên để tạo, chỉnh sửa hoặc xem các công việc trong lịch trình của mình.

Thông qua việc áp dụng các kỹ thuật xử lý ngôn ngữ tự nhiên (Natural Language
Processing – NLP) như phân loại ý định (Intent Classification) và nhận dạng thực thể
có tên (Named Entity Recognition – NER), hệ thống có thể tự động phân tích nội dung
câu lệnh của người dùng, từ đó trích xuất các thông tin quan trọng như nội dung công
việc, thời gian thực hiện và các thuộc tính liên quan.

Ngoài ra, đề tài cũng hướng đến việc xây dựng một hệ thống phần mềm hoàn
chỉnh bao gồm giao diện web, hệ thống backend và cơ sở dữ liệu, qua đó giúp sinh viên
áp dụng các kiến thức đã học trong lĩnh vực kỹ thuật phần mềm, phát triển web và trí
tuệ nhân tạo vào một bài toán thực tiễn.


```
3.2. Nhiệm vụ
```
Để đạt được mục tiêu trên, đề tài cần thực hiện các nhiệm vụ chính sau:

- Nghiên cứu tổng quan về xử lý ngôn ngữ tự nhiên tiếng Việt, các thư viện và mô
    hình hỗ trợ như VnCoreNLP, Underthesea và PhoBERT.
- Tìm hiểu các phương pháp phân loại ý định (Intent Classification) và nhận dạng
    thực thể (Named Entity Recognition) trong bài toán xử lý ngôn ngữ tự nhiên.
- Xây dựng module xử lý câu lệnh tiếng Việt nhằm trích xuất thông tin công việc
    và thời gian từ câu lệnh người dùng.
- Thiết kế và xây dựng hệ thống web quản lý lịch trình, bao gồm giao diện người
    dùng, hệ thống backend và cơ sở dữ liệu.
- Xây dựng các chức năng cơ bản của hệ thống như tạo, chỉnh sửa, xóa và hiển thị
    các sự kiện trong lịch.
- Tích hợp module NLP với hệ thống web để tự động chuyển đổi câu lệnh tiếng
    Việt thành dữ liệu công việc có cấu trúc.
- Thực hiện đánh giá hệ thống thông qua các tiêu chí như độ chính xác của mô
    hình NLP, khả năng nhận dạng thời gian và hiệu năng xử lý của hệ thống.

### 4. Đối tượng và phạm vi nghiên cứu

**4.1. Đối tượng**
Đối tượng nghiên cứu của đề tài là các phương pháp và công nghệ liên quan đến
xử lý ngôn ngữ tự nhiên tiếng Việt và hệ thống quản lý lịch trình cá nhân trên nền tảng
web.

Cụ thể, đề tài tập trung nghiên cứu các kỹ thuật cho phép hệ thống hiểu và xử lý
các câu lệnh tiếng Việt của người dùng nhằm trích xuất thông tin cần thiết để tạo và
quản lý các công việc trong lịch trình. Các thành phần chính của hệ thống bao gồm
module xử lý ngôn ngữ tự nhiên, hệ thống backend, cơ sở dữ liệu và giao diện người
dùng.


Ngoài ra, đề tài cũng nghiên cứu việc ứng dụng các mô hình NLP như PhoBERT
và các thư viện xử lý tiếng Việt như VnCoreNLP và Underthesea trong việc xây dựng
hệ thống quản lý lịch thông minh.

```
4.2. Phạm vi
```
Phạm vi nghiên cứu của đề tài được giới hạn trong việc xây dựng một ứng dụng
web quản lý lịch trình cá nhân có khả năng xử lý các câu lệnh tiếng Việt cơ bản.

Cụ thể, hệ thống tập trung xử lý các loại câu lệnh liên quan đến việc tạo, chỉnh
sửa, xóa và xem các công việc trong lịch trình cá nhân. Các biểu thức thời gian được xử
lý chủ yếu là các dạng phổ biến như ngày, giờ, thứ trong tuần hoặc các biểu thức thời
gian tương đối như “hôm nay”, “ngày mai”, “tuần sau”, “3 giờ chiều”.

Đề tài chỉ tập trung xây dựng phiên bản thử nghiệm (prototype) của hệ thống
nhằm mục đích minh họa khả năng tích hợp giữa xử lý ngôn ngữ tự nhiên và ứng dụng
web. Các tính năng nâng cao như tối ưu hóa lịch trình phức tạp, tích hợp với các hệ
thống lịch bên ngoài (ví dụ Google Calendar) hoặc hỗ trợ đa ngôn ngữ sẽ không nằm
trong phạm vi nghiên cứu của đề tài.

Hệ thống được phát triển trên nền tảng web, sử dụng các công nghệ phổ biến như
ReactJS cho giao diện người dùng, Quarkus Java cho backend và cơ sở dữ liệu
PostgreSQL hoặc SQLite.

### 5. Phương pháp nghiên cứu

### 5.1. Các phương pháp nghiên cứu chính

**_5.1.1. Phương pháp nghiên cứu chủ đạo_**
Phương pháp nghiên cứu chủ đạo của đề tài là Phương pháp thực nghiệm
(Experimental Research):

Đây là phương pháp cốt lõi để kiểm chứng tính hiệu quả của mô hình NPL trong
việc tích hợp vào ứng dụng quản lý lịch trình cá nhân.


Đề tài sẽ bắt đầu phương pháp này bằng việc xây dựng hệ thống phần mềm hoàn
chỉnh, bao gồm frontend (ReactJS), backend (Java, Spring, Quarkus), và cơ sở dữ liệu
(Postgres).

Trên nền tảng của hệ thống đã xây dựng, đề tài tiến hành phát triển module NLP
để phân loại ý định (Intent Classification ) và nhận dạng thực thể (Named Entity
Recognition – NER).

Sau đó, đề tài sẽ xây dựng bộ dữ liệu các câu lệnh lịch trình bằng tiếng Việt nhằm
phục vụ cho quá trình huấn luyện và đánh giá mô hình. Hiệu quả của các mô hình NLP
sẽ được so sánh thông qua các chỉ số đánh giá như Accuracy, Precision, Recall và F1-
score.

```
5.1.2. Phương pháp nghiên cứu bổ trợ
```
Để hỗ trợ cho phương pháp thực nghiệm, đề tài sử dụng thêm các phương pháp sau:

- Phương pháp nghiên cứu và tổng hợp lý thuyết: Nghiên cứu tổng quan về NLP
    tiếng Việt, các mô hình hiện đại (SOTA) như VnCoreNLP, Underthesea và
    PhoBERT và cả kiến trúc hệ thống Web để tạo nền tảng vững chắc cho việc phát
    triển.
- Tìm hiểu các thuật toán lập lịch và kiểm tra xung đột (như cấu trúc dữ liệu
    Interval Tree hoặc thuật toán Greedy) để tối ưu hóa quản lý sự kiện
    **5.2. Phương pháp thu thập thông tin**
    Nghiên cứu tài liệu: Thu thập dữ liệu từ các bộ thư viện mã nguồn mở về NLP
tiếng Việt (Underthesea, VnCoreNLP) và các tiêu chuẩn về biểu thức thời gian
(TIMEX3).

Xây dựng bộ dữ liệu (Dataset): Tự tổng hợp và gắn nhãn (labeling) một tập hợp
các câu lệnh tiếng Việt với ngữ cảnh đa dạng (ví dụ: “Nhắc tôi họp lúc 2h thứ 2 tuần
sau”, “Họp lúc sáng mai”) để làm dữ liệu đầu vào và huấn luyện cho mô hình.


```
5.3. Phương pháp xử lý thông tin
```
Dữ liệu sau khi thu thập sẽ được xử lý qua hai bước:

```
5.3.1. Xử lý định lượng (Quantitative Analysis) :
```
- Thống kê tần suất các loại lỗi trong quá trình thực nghiệm xử lý ngôn ngữ.
- Sử dụng các thước đo lường toán học như: Độ chính xác (Accuracy), Precision,
    Recall, và F1-score để đánh giá độ chính xác của mô hình NPL.
       **_5.3.2. Xử lý định tính (Qualitative Analysis)_** _:_
- Phân tích đặc điểm ngôn ngữ và các khó khăn trong việc xử lý ngôn ngữ tiếng
    Việt (từ ghép, ngữ cảnh, cách diễn đạt thời gian) để điều chỉnh quy tắc (rules)
    cho bộ lọc ngôn ngữ.
- Phân loại ý định của người dùng (tạo, sửa, xóa, xem lịch) để ánh xạ vào các chức
    năng của hệ thống.

### 6. Ý nghĩa khoa học và thực tiễn

Khoa học: Kết hợp các kỹ thuật NLP hiện đại như PhoBERT và NER. Đề tài
cũng xử lý lịch để làm hệ thống cho tiếng Việt. Giúp cung cấp dữ liệu và cho thấy cách
AI giúp quản lý công việc.

Thực tiễn: Ứng dụng này giúp cá nhân và doanh nghiệp Việt. Phiên bản thử có
thể phát triển thành sản phẩm thật. Đồ án có thể phát triển dùng trên web hoặc điện
thoại. Nó có thể để thêm các tính năng như đồng bộ Google Calendar.


### 7. Dự kiến kế hoạch nghiên cứu

**Tuần Công việc dự kiến Ghi chú**
1 Tổng hợp tài liệu, thiết kế kiến trúc hệ thống, chuẩn
bị dữ liệu mẫu

```
Nghiên cứu sâu thư viện
NLP (VnCoreNLP,
Underthesea).
2 Thiết kế Database, phát triển API Backend cơ bản
(Quarkus Java) cho các thực thể người dùng, sự kiện
```
```
Thiết kế bảng todos và users
```
```
3 Xây dựng pipeline tiền xử lý: chuẩn hóa văn bản,
tách từ, loại bỏ nhiễu và gán nhãn dữ liệu huấn luyện.
```
```
Kiểm thử độ chính xác của
các bộ tách từ tiếng Việt trên
vài ví dụ
4 Huấn luyện mô hình phân loại ý định (Intent
Classification). Thực hiện so sánh hiệu năng giữa các
kiến trúc.
```
```
So sánh Logistic Regression
(Baseline) vs PhoBERT
```
```
5 Xây dựng module trích xuất thông tin thời gian, địa
điểm, nội dung công việc dựa trên bộ luật (Rule-
based) và mô hình học máy.
```
```
Tập trung xử lý cách diễn đạt
thời gian đa dạng trong tiếng
Việt.
6 Xây dựng logic kiểm tra xung đột lịch, thuật toán sắp
xếp lịch tối ưu (Greedy/Heuristic) và tích hợp
APScheduler
```
```
Viết chức năng thêm, sửa,
xóa sự kiện và tự động gợi ý
khung giờ trống
7 Xây dựng giao diện Web Demo (Streamlit), kết nối
Backend - AI Model - Database
```
```
Test giao diện và tính năng
end-to-end
8 Đánh giá hệ thống (Accuracy, F1, Latency), phân
tích lỗi (Error Analysis), viết tài liệu và chuẩn bị báo
cáo
```
```
Tổng hợp kết quả thực
nghiệm và hướng phát triển
cho tài liệu, chuẩn bị trình
bày
```

### 8. Dự kiến nội dung của đề tài

## CHƯƠNG 1. GIỚI THIỆU ĐỀ TÀI

### 1.1. Đặt vấn đề

Nhu cầu thực tiễn: Người Việt muốn giao tiếp bằng ngôn ngữ tự nhiên. Ví dụ,
thay vì nhập form thủ công, bạn chỉ cần nói “Nhắc tôi họp lúc 9h sáng mai” để tạo lịch.
Smart Scheduler đáp ứng nhu cầu này. Nó giúp bạn tiết kiệm thời gian nhập liệu và cải
thiện trải nghiệm người dùng.

Khoảng trống nghiên cứu: Hiện nay, ít công cụ AI hỗ trợ tiếng Việt để lập lịch.
Các thư viện NLP tiếng Việt như VnCoreNLP[1], Underthesea[2] và PhoBERT[3] đã
được phát triển. Nhưng chúng ít được dùng trong quản lý lịch. Nhiều hệ thống lập lịch
thông minh chỉ hỗ trợ tiếng Anh. Ví dụ, SUTime chỉ hỗ trợ tiếng Anh[4]. Vậy nên, dự
án này mới khi kết hợp NLP tiếng Việt với quản lý lịch.

### 1.2. Hướng tiếp cận đề tài

Đề tài tập trung nghiên cứu và xây dựng một module xử lý ngôn ngữ tự nhiên
nhằm phân tích các câu lệnh tiếng Việt mà người dùng nhập vào. Module này có nhiệm
vụ trích xuất các thông tin quan trọng từ câu văn như nội dung công việc, thời gian thực
hiện và các thuộc tính liên quan đến công việc. Các kỹ thuật cơ bản của xử lý ngôn ngữ
tự nhiên như tách từ (tokenization), nhận dạng thực thể (Named Entity Recognition) và
phân loại ý định (intent classification) sẽ được nghiên cứu và áp dụng để xây dựng cơ
chế xử lý dữ liệu đầu vào.

Sau khi phân tích câu lệnh từ người dùng, hệ thống sẽ chuyển đổi các thông tin
thu được thành dữ liệu có cấu trúc và tự động tạo các công việc trong hệ thống Todo
List. Cách tiếp cận này giúp kết hợp giữa các thuật toán AI và hệ thống web nhằm xây
dựng một ứng dụng quản lý công việc có tính linh hoạt và thông minh hơn so với các
hệ thống Todo List truyền thống.


### 1.3. Khó khăn của bài toán

Trong quá trình nghiên cứu và xây dựng hệ thống Web Todo List tích hợp AI xử
lý ngôn ngữ tự nhiên tiếng Việt, đề tài có thể gặp một số khó khăn nhất định liên quan
đến cả khía cạnh kỹ thuật và quá trình triển khai hệ thống.

Trước hết, việc xử lý ngôn ngữ tự nhiên tiếng Việt là một bài toán tương đối
phức tạp do đặc điểm của ngôn ngữ này có nhiều cách diễn đạt khác nhau cho cùng một
ý nghĩa. Người dùng có thể nhập công việc với nhiều cấu trúc câu khác nhau, ví dụ như
“Họp khách hàng lúc 9h sáng mai”, “Ngày mai 9h họp khách hàng” hoặc “Nhắc tôi họp
khách hàng vào sáng mai lúc 9 giờ”. Việc nhận dạng và trích xuất chính xác các thông
tin cần thiết từ các câu có cấu trúc đa dạng như vậy là một thách thức đối với hệ thống.

Ngoài ra, dữ liệu tiếng Việt dành cho các bài toán xử lý ngôn ngữ tự nhiên hiện
nay vẫn chưa phong phú và chuẩn hóa như một số ngôn ngữ phổ biến khác. Điều này
có thể gây khó khăn trong quá trình xây dựng hoặc huấn luyện các mô hình học máy
nếu đề tài muốn áp dụng các thuật toán AI nâng cao.

## CHƯƠNG 2. CƠ SỞ LÝ THUYẾT VÀ CÔNG NGHỆ LIÊN QUAN

### 2.1. Tổng quan NLP tiếng Việt (VnCoreNLP, Underthesea, PhoBERT)

Xử lý ngôn ngữ tự nhiên (Natural Language Processing – NLP) là một lĩnh vực
của trí tuệ nhân tạo nhằm giúp máy tính có khả năng hiểu, phân tích và xử lý ngôn ngữ
của con người. NLP được ứng dụng rộng rãi trong nhiều hệ thống như chatbot, trợ lý
ảo, hệ thống tìm kiếm thông minh và phân tích dữ liệu văn bản.

Đối với tiếng Việt, việc xử lý ngôn ngữ tự nhiên gặp nhiều khó khăn do đặc điểm
của ngôn ngữ này có cấu trúc từ ghép phức tạp, nhiều cách diễn đạt khác nhau cho cùng
một ý nghĩa và thiếu các tập dữ liệu chuẩn hóa so với các ngôn ngữ phổ biến như tiếng
Anh.


Trong những năm gần đây, nhiều công cụ và mô hình NLP dành cho tiếng Việt
đã được phát triển nhằm hỗ trợ các bài toán xử lý văn bản. Một số thư viện tiêu biểu
bao gồm VnCoreNLP, Underthesea và mô hình ngôn ngữ PhoBERT, giúp cải thiện
đáng kể hiệu quả của các hệ thống xử lý tiếng Việt.

```
2.1.1. Công cụ và thư viện NLP
```
Một số công cụ phổ biến hỗ trợ xử lý ngôn ngữ tự nhiên tiếng Việt bao gồm:

```
Hình 2.1. Quy trình xử lý ngôn ngữ tự nhiên (NLP Pipeline)
```
- **VnCoreNLP** là một bộ công cụ NLP mã nguồn mở được phát triển bởi nhóm
    nghiên cứu của Thanh Vu và cộng sự. Công cụ này cung cấp nhiều chức năng
    như phân tách từ (word segmentation), gán nhãn từ loại (POS tagging), nhận
    dạng thực thể có tên (Named Entity Recognition – NER) và phân tích cú pháp
    phụ thuộc.
- **Underthesea** là một thư viện NLP tiếng Việt được phát triển bằng Python, cung
    cấp các chức năng như tách câu, tách từ, phân loại văn bản và nhận dạng thực
    thể. Thư viện này có ưu điểm là dễ tích hợp vào các hệ thống xử lý dữ liệu và
    các ứng dụng web.
- **PhoBERT** là một mô hình ngôn ngữ được xây dựng dựa trên kiến trúc BERT và
    được huấn luyện trên một lượng lớn dữ liệu tiếng Việt. PhoBERT đạt hiệu suất
    cao trong nhiều bài toán NLP như phân loại văn bản, nhận dạng thực thể và phân
    tích ngữ nghĩa.


```
2.1.2. Kỹ thuật tiền xử lý
```
Trước khi dữ liệu văn bản được đưa vào các mô hình học máy hoặc học sâu, cần
thực hiện các bước tiền xử lý dữ liệu (preprocessing) nhằm chuẩn hóa dữ liệu đầu vào.

```
Hình 2.2. Sơ đồ quy trình tiền xử lý dữ liệu văn bản (Text Preprocessing)
```
Một số bước tiền xử lý phổ biến trong NLP bao gồm:

- **Tokenization** (tách từ): chia câu văn thành các đơn vị từ hoặc cụm từ có ý nghĩa.
- **Sentence segmentation** : tách văn bản thành các câu riêng biệt.
- **Chuẩn hóa văn bản:** chuyển đổi chữ hoa thành chữ thường, loại bỏ ký tự đặc
    biệt hoặc các dấu câu không cần thiết.
- **Loại bỏ stop words** : loại bỏ các từ ít mang ý nghĩa như “và”, “là”, “của”, v.v.

Các bước tiền xử lý giúp dữ liệu trở nên dễ phân tích hơn và cải thiện hiệu quả
của các mô hình NLP.

### 2.2. Phân loại ý định

Phân loại ý định là một bài toán quan trọng trong các hệ thống xử lý ngôn ngữ
tự nhiên. Mục tiêu của bài toán này là xác định mục đích của người dùng khi nhập một
câu lệnh.


Trong hệ thống quản lý lịch trình, các ý định phổ biến của người dùng có thể bao gồm:

- Tạo công việc mới
- Cập nhật thông tin công việc
- Xóa công việc
- Xem danh sách công việc

Hệ thống cần phân tích nội dung câu lệnh để xác định người dùng muốn thực
hiện hành động nào, từ đó chuyển yêu cầu đó thành các thao tác tương ứng trong hệ
thống.

**_2.2.1. Phương pháp Machine Learning cơ bản_**

Các phương pháp học máy truyền thống thường được sử dụng trong bài toán
phân loại văn bản bao gồm:

- Naive Bayes
- Support Vector Machine (SVM)
- Logistic Regression

Những phương pháp này có ưu điểm là dễ triển khai và yêu cầu tài nguyên tính
toán thấp, tuy nhiên hiệu quả có thể bị hạn chế khi xử lý các câu có cấu trúc phức tạp.

**_2.2.2. Mạng nơ-ron và Transformer_**

Trong những năm gần đây, các mô hình học sâu như LSTM, RNN và
Transformer đã được sử dụng rộng rãi trong NLP.

Đặc biệt, các mô hình dựa trên kiến trúc Transformer như BERT hoặc PhoBERT
có khả năng hiểu ngữ cảnh của câu tốt hơn so với các mô hình truyền thống. Trong đề
tài này, mô hình PhoBERT có thể được sử dụng để cải thiện độ chính xác trong việc
phân loại ý định của người dùng.


**_2.2.3. Các thước đo đánh giá_**

Để đánh giá hiệu quả của mô hình phân loại, một số thước đo phổ biến thường
được sử dụng bao gồm:

- **Accuracy:** tỷ lệ dự đoán đúng trên tổng số mẫu dữ liệu.
- **Precision:** mức độ chính xác của các dự đoán dương tính.
- **Recall:** khả năng phát hiện đầy đủ các mẫu thuộc lớp cần dự đoán.
- **F1-score:** giá trị trung bình điều hòa giữa Precision và Recall.

Các chỉ số này giúp đánh giá mức độ hiệu quả của mô hình NLP trong việc xử
lý câu lệnh của người dùng.

### 2.3. Nhận diện thực tế và xử lý thời gian

Trong các hệ thống xử lý ngôn ngữ tự nhiên, nhận dạng thực thể có tên (Named
Entity Recognition – NER) là một kỹ thuật quan trọng nhằm xác định và trích xuất các
thông tin có ý nghĩa từ văn bản. Các thực thể thường được nhận dạng bao gồm tên
người, địa điểm, tổ chức, thời gian, sự kiện và nhiều loại thông tin khác.

Đối với hệ thống quản lý lịch trình cá nhân, nhiệm vụ chính của NER là trích
xuất các thông tin liên quan đến công việc và thời gian từ câu lệnh của người dùng. Ví
dụ, trong câu lệnh **“Nhắc tôi họp nhóm lúc 9 giờ sáng mai”** , hệ thống cần nhận diện
các thành phần như nội dung công việc (“họp nhóm”) và thời gian thực hiện (“9 giờ
sáng mai”).

Việc nhận dạng chính xác các thực thể này giúp hệ thống chuyển đổi câu lệnh
ngôn ngữ tự nhiên thành dữ liệu có cấu trúc, từ đó có thể lưu trữ và xử lý trong hệ thống
quản lý lịch.


**_2.3.1. Mô hình NER và trích xuất thực thể (CRF, BiLSTM-CRF, BERT
NER)_**

Có nhiều phương pháp khác nhau để thực hiện bài toán nhận dạng thực thể, từ
các phương pháp dựa trên quy tắc cho đến các mô hình học máy và học sâu.

Một số mô hình phổ biến được sử dụng trong NER bao gồm:

```
a. CRF (Conditional Random Fields)
```
CRF là một mô hình thống kê được sử dụng phổ biến trong các bài toán gán nhãn
chuỗi. Phương pháp này có khả năng xem xét mối quan hệ giữa các từ trong câu để dự
đoán nhãn của từng từ.

```
b. BiLSTM-CRF
```
Đây là một mô hình kết hợp giữa mạng nơ-ron hồi tiếp hai chiều (BiLSTM) và
CRF. Mô hình BiLSTM giúp học được ngữ cảnh của từ trong câu, trong khi CRF giúp
tối ưu việc gán nhãn chuỗi.

```
c. BERT-based NER
```
Các mô hình dựa trên kiến trúc Transformer như BERT hoặc PhoBERT có khả
năng hiểu ngữ cảnh sâu hơn nhờ được huấn luyện trên tập dữ liệu lớn. Những mô hình
này thường cho kết quả tốt hơn so với các phương pháp truyền thống trong nhiều bài
toán NLP.

Trong phạm vi của đề tài, hệ thống có thể sử dụng các thư viện NLP tiếng Việt
kết hợp với các mô hình học sâu để trích xuất thông tin như nội dung công việc và thời
gian từ câu lệnh của người dùng.


```
2.3.2. Xử lý biểu thức thời gian (Timeline parsing)
```
Trong hệ thống quản lý lịch trình, việc xử lý các biểu thức thời gian đóng vai trò
rất quan trọng. Người dùng thường nhập thời gian dưới nhiều dạng khác nhau, ví dụ:

- “9 giờ sáng mai”
- “3 giờ chiều thứ hai”
- “tuần sau”
- “hôm nay lúc 7 giờ tối”

Do đó, hệ thống cần có khả năng phân tích và hiểu các biểu thức thời gian này,
sau đó chuyển đổi chúng thành một định dạng thời gian chuẩn để lưu trữ trong cơ sở dữ
liệu.

Một số công cụ hỗ trợ xử lý biểu thức thời gian trong NLP bao gồm:

```
a. SUTime
```
SUTime là một thư viện được phát triển bởi Stanford NLP Group nhằm nhận
diện và chuẩn hóa các biểu thức thời gian trong văn bản. Tuy nhiên, công cụ này chủ
yếu hỗ trợ tiếng Anh.

```
b. HeidelTime
```
HeidelTime là một hệ thống dựa trên quy tắc có khả năng nhận diện biểu thức
thời gian trong nhiều ngôn ngữ khác nhau. Một số nghiên cứu đã mở rộng hệ thống này
để hỗ trợ tiếng Việt.

Ngoài ra, các biểu thức thời gian đơn giản cũng có thể được xử lý bằng các quy
tắc (rule-based) hoặc biểu thức chính quy (regular expression) để nhận dạng các dạng
thời gian phổ biến.


```
2.3.3. Chuẩn hóa thời gian (Time normalization - TIMEX3)
```
Sau khi nhận diện được biểu thức thời gian trong câu lệnh của người dùng, bước
tiếp theo là chuẩn hóa thời gian để chuyển đổi thông tin đó sang một định dạng tiêu
chuẩn.

Một trong những chuẩn phổ biến được sử dụng trong NLP là TIMEX3, cho phép
biểu diễn các thông tin về thời gian theo dạng có cấu trúc. Ví dụ:

- “ngày mai” → 2026- 03 - 16
- “3 giờ chiều” → 15:
- “thứ hai tuần sau” → một ngày cụ thể trong tuần tiếp theo

Việc chuẩn hóa thời gian giúp hệ thống dễ dàng lưu trữ và xử lý các sự kiện trong
cơ sở dữ liệu, đồng thời hỗ trợ các chức năng như kiểm tra trùng lịch và nhắc việc tự
động.

**2.4. Thuật toán lập lịch (Scheduling Algorithms)**

Thuật toán lập lịch là một thành phần quan trọng trong hệ thống quản lý lịch
trình, giúp tổ chức và quản lý các sự kiện theo thời gian. Trong một số trường hợp, hệ
thống cần kiểm tra xem các sự kiện có bị trùng lặp về thời gian hay không, hoặc đề xuất
các khoảng thời gian phù hợp để sắp xếp công việc.

Việc áp dụng các thuật toán lập lịch giúp hệ thống đảm bảo rằng các sự kiện
được quản lý một cách hợp lý và tránh xảy ra xung đột về thời gian.

```
2.4.1. Kiểm tra xung đột lịch
```
Khi người dùng tạo một sự kiện mới, hệ thống cần kiểm tra xem sự kiện đó có
bị trùng với các sự kiện đã tồn tại hay không. Nếu xảy ra trùng lặp, hệ thống có thể
thông báo cho người dùng hoặc đề xuất thời gian khác.


Một số phương pháp phổ biến để kiểm tra xung đột lịch bao gồm:

- So sánh trực tiếp các khoảng thời gian của sự kiện
- Sắp xếp các sự kiện theo thời gian bắt đầu
- Sử dụng cấu trúc dữ liệu Interval Tree để tăng tốc độ tìm kiếm

Interval Tree là một cấu trúc dữ liệu cho phép truy vấn nhanh các khoảng thời
gian chồng lấp nhau, giúp cải thiện hiệu năng của hệ thống khi số lượng sự kiện lớn.

```
2.4.2. Tối ưu sắp xếp lịch
```
Ngoài việc kiểm tra trùng lịch, một số hệ thống còn có khả năng đề xuất các
khoảng thời gian phù hợp để sắp xếp công việc.

Một trong những phương pháp phổ biến là thuật toán Greedy, trong đó hệ thống
lựa chọn các khoảng thời gian khả dụng dựa trên các tiêu chí đơn giản như:

- Khoảng thời gian trống gần nhất
- Khoảng thời gian phù hợp với lịch trình hiện tại
- Thời gian ưu tiên của người dùng

Trong phạm vi đề tài này, hệ thống chủ yếu tập trung vào quản lý và kiểm tra
trùng lịch, trong khi các phương pháp tối ưu hóa lịch trình phức tạp có thể được nghiên
cứu trong các phiên bản phát triển tiếp theo.

### 2.5. Công nghệ triển khai

Để xây dựng hệ thống Smart Scheduler, đề tài sử dụng một số công nghệ phổ
biến trong phát triển ứng dụng web và xử lý dữ liệu. Các công nghệ này được lựa chọn
nhằm đảm bảo hệ thống có khả năng mở rộng, hiệu năng tốt và dễ dàng tích hợp giữa
các thành phần.


```
2.5.1. ReactJS và Vite
```
ReactJS là một thư viện JavaScript phổ biến được sử dụng để xây dựng giao diện
người dùng cho các ứng dụng web. React cho phép phát triển giao diện theo mô hình
component-based, giúp chia nhỏ giao diện thành các thành phần độc lập và dễ tái sử
dụng. Điều này giúp quá trình phát triển và bảo trì hệ thống trở nên thuận tiện hơn.

Trong đề tài này, ReactJS được sử dụng để xây dựng giao diện web cho phép
người dùng nhập câu lệnh tiếng Việt, xem lịch trình và quản lý các công việc trong hệ
thống.

Vite là một công cụ hỗ trợ xây dựng và phát triển ứng dụng frontend hiện đại.
Vite cung cấp dev server tốc độ cao, hỗ trợ Hot Module Replacement (HMR) và giúp
rút ngắn thời gian build ứng dụng. Việc kết hợp ReactJS với Vite giúp quá trình phát
triển giao diện trở nên nhanh chóng và hiệu quả.

```
2.5.2. Quarkus (Java Backend)
```
Quarkus là một framework Java hiện đại được thiết kế để xây dựng các ứng dụng
backend và microservices với hiệu năng cao. Framework này hỗ trợ phát triển các
RESTful API, cho phép các thành phần trong hệ thống giao tiếp với nhau thông qua
giao thức HTTP.

Trong hệ thống Smart Scheduler, Quarkus được sử dụng để triển khai các chức
năng backend như:

- Xử lý các yêu cầu từ phía người dùng
- Thực hiện các thao tác CRUD đối với dữ liệu lịch trình
- Kết nối và thao tác với cơ sở dữ liệu
- Giao tiếp với module xử lý ngôn ngữ tự nhiên

Việc sử dụng Quarkus giúp hệ thống backend có khả năng mở rộng tốt, hiệu
năng cao và phù hợp với các hệ thống web hiện đại.


```
2.5.3. Module xử lý ngôn ngữ tự nhiên (NLP)
```
Module xử lý ngôn ngữ tự nhiên có nhiệm vụ phân tích các câu lệnh tiếng Việt
mà người dùng nhập vào để trích xuất thông tin cần thiết như nội dung công việc và
thời gian thực hiện.

Các thư viện NLP tiếng Việt như Underthesea hoặc VnCoreNLP có thể được sử
dụng để thực hiện các bước tiền xử lý như tách từ, phân tích cú pháp và nhận dạng thực
thể. Ngoài ra, các mô hình ngôn ngữ như PhoBERT có thể được áp dụng để cải thiện
khả năng phân loại ý định và hiểu ngữ cảnh của câu lệnh.

Module NLP có thể được triển khai dưới dạng một dịch vụ riêng (service) và
giao tiếp với hệ thống backend thông qua API.

```
2.5.4. Cơ sở dữ liệu
```
Hệ thống sử dụng cơ sở dữ liệu quan hệ để lưu trữ thông tin người dùng và các
sự kiện trong lịch trình.

Một số hệ quản trị cơ sở dữ liệu có thể được sử dụng trong đề tài bao gồm:

- **PostgreSQL** : phù hợp với các hệ thống web có quy mô lớn và yêu cầu độ ổn
    định cao.
- **SQLite** : phù hợp cho việc phát triển và thử nghiệm trong môi trường nhỏ.

Dữ liệu trong hệ thống có thể bao gồm các bảng chính như users, events và
reminders, dùng để lưu trữ thông tin người dùng và các công việc trong lịch trình.

```
2.5.5. Hệ thống nhắc việc
```
Để hỗ trợ chức năng nhắc việc tự động, hệ thống có thể sử dụng các cơ chế lập
lịch tác vụ (task scheduling). Các công cụ như APScheduler hoặc các cơ chế lập lịch
phía backend có thể được sử dụng để thiết lập các tác vụ chạy theo thời gian định trước.

Khi thời điểm của một sự kiện sắp diễn ra, hệ thống có thể gửi thông báo hoặc
hiển thị nhắc việc cho người dùng, giúp họ quản lý công việc hiệu quả hơn.


## CHƯƠNG 3. THIẾT KẾ VÀ TRIỂN KHAI HỆ THỐNG SMART

## SCHEDULER

### 3.1. Thiết kế kiến trúc tổng thể

Kiến trúc tổng thể của hệ thống Smart Scheduler sẽ được tổ chức theo mô hình
client – server với ba lớp chính:

```
3.1.1. Lớp giao diện người dùng (Frontend)
```
- Được xây dựng bằng ReactJs, sử dụng Vite làm công cụ build, kết hợp Ant
    Design và Styled Components để xây dựng giao diện.
- Người dùng có thể nhập câu lệnh bằng ngôn ngữ tự nhiên, ví dụ: “Nhắc tôi họp
    vào 9h sáng mai”, “Thêm việc nộp báo cáo lúc 17h thứ 6”, “Xóa lịch hẹn với bác
    sĩ tối nay”....
- Phần giao diện cũng sẽ chịu trách nhiệm hiển thị danh sách các công việc, chi
    tiết công việc, trạng thái hoàn thành, ưu tiên, và các thông tin nhắc việc.

**_3.1.2. Lớp backend & cơ sở dữ liệu (Backend + Database)_**
Backend dùng Quarkus (Java) triển khai các RESTful API. Lớp này đảm nhiệm
2 chức năng chính, bao gồm:

- Quản lý người dùng (đăng ký, đăng nhập) với JWT (SmallRye JWT) và phân
    quyền.
- Cung cấp các API CRUD cho Todo, hỗ trợ thêm:
    + Lọc theo trạng thái, độ ưu tiên, hạn chót (due date).
    + Phân trang kết quả.

**_3.1.3. Lớp NLP & Application (NLP + Application)_**
Đây là lớp trung gian xử lý giữa Frontend và Backend:

- Thành phần NLP: tiếp nhận câu lệnh từ UI, thực hiện phân loại intent (ý định:
    tạo lịch, cập nhật, xóa, xem lịch, đặt nhắc việc, ...) và trích xuất thực thể (tên
    công việc, thời gian, mức ưu tiên, ghi chú, ...).
- Thành phần Application: chuyển kết quả NLP thành các request chuẩn REST
    API gửi xuống backend.


### 3.2. Xây dựng mô hình NLP

**3.2.1. Mô hình phân loại intent**
Hệ thống sử dụng các kỹ thuật như Logistic Regression hoặc mô hình ngôn ngữ
PhoBERT để phân tích câu lệnh người dùng thành các ý định cụ thể: Tạo mới, Cập nhật,

### Xóa hoặc Xem danh sách lịch trình.

**3.2.2. Mô hình NER và trích xuất thời gian**
Sử dụng mô hình BiLSTM-CRF hoặc BERT-based NER để nhận diện các thực
thể như tiêu đề công việc và các mốc thời gian.

Tích hợp các quy tắc (Rule-based) hoặc thư viện hỗ trợ tiếng Việt (như
HeidelTime đã tùy chỉnh) để chuẩn hóa các biểu thức thời gian (ví dụ: "9h sáng mai"

### → định dạng ISO).

### 3.3. Triển khai backend và cơ sở dữ liệu

```
3.3.1. Xây dựng API cho các thao tác CRUD lịch
```
API xử lý lịch sẽ bao gồm các phương thức:

- **GET** /todos Lấy danh sách các lịch hẹn
- **GET** /todos/{id} Xem chi tiết một lịch hẹn
- **POST** /todos Tạo mới một lịch hẹn
- **PUT** /todos/{id} Chỉnh sửa thông tin hoặc trạng thái một lịch hẹn
- **DELETE** todos/{id} Xóa một lịch hẹn khỏi hệ thống
    **3.3.2. Thiết kế CSDL**
    Hệ thống sử dụng cơ sở dữ liệu Postgre, được quản lý thông qua Hibernate ORM
và Panache JPA. Các bảng chính bao gồm:


```
Hình 3.1. Sơ đồ thực thể mối quan hệ giữa todos và users
```
**Bảng users**

- id (BIGINT, PK, AUTO_INCREMENT)
- email (VARCHAR, UNIQUE, NOT NULL)
- passwordhash (VARCHAR, NOT NULL)
- active (BOOLEAN, DEFAULT TRUE)
- createdat (TIMESTAMP)

**Bảng todos**

- id (BIGINT, PK, AUTO_INCREMENT)
- title (VARCHAR, NOT NULL)
- description (VARCHAR(500))
- completed (BOOLEAN, DEFAULT FALSE)
- duedate (TIMESTAMP)
- priority (VARCHAR, DEFAULT 'MEDIUM')
- status (VARCHAR, DEFAULT 'PENDING')
- user_id (BIGINT, FK → users.id)
- createdat (TIMESTAMP, NOT NULL)
- updatedat (TIMESTAMP)


**Miêu tả:**

- Mối quan hệ giữa hai bảng: One To Many - Một User có thể tạo và quản lý danh
    sách gồm nhiều công việc khác nhau. Ngược lại, mỗi công việc chỉ thuộc về duy
    nhất một người dùng sở hữu.
- Ràng buộc toàn vẹn:
    + Khóa ngoại user_id trong bảng todos liên kết trực tiếp với id của bảng
       users.
    + Điều này đảm bảo tính nhất quán → không thể tạo một công việc nếu
       không gán cho một người dùng cụ thể đã tồn tại trong hệ thống.
- Cơ chế lưu trữ và quản lý:
    + Các trường **createdat** và **updatedat** được sử dụng để theo dõi dấu vết dữ
       liệu, giúp hệ thống biết chính xác thời điểm một công việc được tạo ra
       hoặc thay đổi trạng thái.
    + Các trường **priority** và **status** giúp phân loại và lọc dữ liệu hiệu quả trong
       quá trình truy vấn thông qua Hibernate và Panache.
    + Bảng users chỉ lưu trữ **passwordhash** thay vì mật khẩu thô để đảm bảo
       an toàn thông tin theo tiêu chuẩn bảo mật hiện đại

### 3.4. Giao diện người dùng và tích hợp tổng thể

```
3.4.1. Giao diện nhập lệnh
```
Hệ thống Smart Scheduler được thiết kế với giao diện web thân thiện nhằm giúp
người dùng dễ dàng tương tác và quản lý các công việc trong lịch trình cá nhân. Một
trong những thành phần quan trọng của giao diện là khu vực nhập lệnh, cho phép người
dùng nhập trực tiếp các câu lệnh tiếng Việt để thực hiện các thao tác quản lý công việc.

Thay vì yêu cầu người dùng phải thao tác thông qua nhiều biểu mẫu hoặc nút
chức năng khác nhau, hệ thống cho phép người dùng nhập nội dung công việc hoặc yêu
cầu tìm kiếm vào một khung nhập liệu trung tâm. Cách tiếp cận này giúp đơn giản hóa
quá trình sử dụng hệ thống, đồng thời tạo tiền đề cho việc tích hợp các kỹ thuật xử lý
ngôn ngữ tự nhiên nhằm hiểu và phân tích các câu lệnh của người dùng.


Trong giai đoạn hiện tại, chức năng nhập liệu chủ yếu được sử dụng để tìm kiếm
các công việc theo tiêu đề hoặc nội dung. Hệ thống hỗ trợ chuẩn hóa văn bản tiếng Việt
bằng cách loại bỏ dấu và chuẩn hóa chuỗi ký tự trước khi so sánh, giúp cải thiện độ
chính xác của quá trình tìm kiếm.

Trong các bước phát triển tiếp theo, hệ thống có thể được mở rộng để hỗ trợ phân
tích các câu lệnh tiếng Việt tự nhiên như “thêm công việc mới”, “xóa công việc”, “đánh
dấu hoàn thành”, hoặc “lọc công việc theo trạng thái”. Khi đó, module xử lý ngôn ngữ
tự nhiên sẽ có nhiệm vụ xác định ý định của người dùng và trích xuất các thông tin cần
thiết, từ đó chuyển đổi câu lệnh thành các thao tác tương ứng trong hệ thống.

```
3.4.2. Tích hợp gọi API và hiển thị lịch
```
Hệ thống Smart Scheduler được xây dựng theo mô hình client–server, trong đó
giao diện người dùng (frontend) giao tiếp với hệ thống backend thông qua các RESTful
API. Kiến trúc này giúp tách biệt giữa phần giao diện và phần xử lý nghiệp vụ, từ đó
giúp hệ thống dễ mở rộng và bảo trì.

Frontend chịu trách nhiệm gửi các yêu cầu đến backend để thực hiện các thao
tác như tạo mới, cập nhật, xóa hoặc truy vấn danh sách công việc. Sau khi nhận dữ liệu
từ backend, hệ thống sẽ hiển thị thông tin công việc lên giao diện dưới dạng danh sách
hoặc các thẻ công việc để người dùng dễ dàng theo dõi.

Backend đóng vai trò xử lý nghiệp vụ chính của hệ thống, bao gồm quản lý dữ
liệu công việc, xử lý các yêu cầu từ phía người dùng và cung cấp các API phục vụ cho
frontend. Hệ thống cũng hỗ trợ các cơ chế tìm kiếm và lọc dữ liệu theo nhiều tiêu chí
khác nhau như trạng thái công việc, mức độ ưu tiên hoặc thời hạn thực hiện.

Để cải thiện trải nghiệm người dùng, hệ thống có thể sử dụng cơ chế lưu trữ tạm
thời ở phía trình duyệt nhằm giảm thời gian tải dữ liệu. Tuy nhiên, toàn bộ dữ liệu chính
vẫn được quản lý tại backend nhằm đảm bảo tính nhất quán và khả năng đồng bộ giữa
nhiều thiết bị khác nhau.


```
3.4.3. Hệ thống nhắc việc
```
Một chức năng quan trọng của ứng dụng quản lý lịch trình là khả năng nhắc nhở
người dùng về các công việc sắp đến hạn. Tính năng này giúp người dùng chủ động hơn
trong việc quản lý thời gian và hạn chế bỏ sót các nhiệm vụ quan trọng.

Trong hệ thống Smart Scheduler, cơ chế nhắc việc có thể được triển khai thông
qua một bộ lập lịch (scheduler) chạy ở phía backend. Bộ lập lịch này có nhiệm vụ kiểm
tra định kỳ các công việc trong cơ sở dữ liệu để xác định những công việc sắp đến thời
hạn thực hiện.

Khi phát hiện một công việc sắp đến hạn, hệ thống có thể gửi thông báo đến giao
diện người dùng. Các thông báo này có thể được hiển thị dưới dạng thông báo trên giao
diện web hoặc danh sách các công việc cần chú ý.

Trong tương lai, hệ thống cũng có thể được mở rộng để hỗ trợ các hình thức nhắc
việc nâng cao như gửi email nhắc việc hoặc thông báo đẩy trên thiết bị di động. Điều

### này giúp tăng tính tiện ích của hệ thống và mang lại trải nghiệm tốt hơn cho người dùng.

### 3.5. Đánh giá và kết quả thử nghiệm

```
3.5.1. Thiết lập thí nghiệm và thu thập dữ liệu test
```
Để kiểm tra khả năng hoạt động của hệ thống Smart Scheduler, nhóm xây dựng
một bộ dữ liệu gồm nhiều câu lệnh tiếng Việt liên quan đến việc quản lý công việc và
lịch trình. Các câu lệnh này được thiết kế với nhiều cách diễn đạt khác nhau để mô
phỏng cách người dùng có thể tương tác với hệ thống trong thực tế.

_Ví dụ:_

- Các câu lệnh: “thêm công việc họp nhóm lúc 9 giờ sáng mai”, “xóa công việc
    báo cáo tuần”, hoặc “hiển thị các công việc chưa hoàn thành”... → Bộ dữ liệu
    này được sử dụng để kiểm tra khả năng hệ thống hiểu và xử lý các câu lệnh tiếng
    Việt của người dùng.


Ngoài ra, hệ thống cũng được thử nghiệm trực tiếp thông qua giao diện web
nhằm kiểm tra khả năng tương tác giữa frontend, backend và module xử lý ngôn ngữ tự
nhiên.

```
3.5.2. Kết quả phân loại intent và NER
```
Sau khi tích hợp module xử lý ngôn ngữ tự nhiên, hệ thống được kiểm tra khả
năng xác định ý định của người dùng và trích xuất các thông tin quan trọng từ câu lệnh,
chẳng hạn như nội dung công việc và thời gian thực hiện.

Việc đánh giá được thực hiện thông qua các chỉ số phổ biến như Accuracy,
Precision, Recall và F1-score. Những chỉ số này giúp xác định mức độ chính xác của
hệ thống khi phân loại các câu lệnh và nhận diện các thực thể trong câu.

Kết quả thử nghiệm ban đầu cho thấy hệ thống có thể xử lý tốt các câu lệnh cơ
bản liên quan đến việc tạo và quản lý công việc, đồng thời trích xuất được các thông tin
cần thiết để lưu trữ và xử lý trong hệ thống.

```
3.5.3. Đánh giá độ trễ phản hồi API và độ bao phủ biểu thức thời gian
```
Bên cạnh việc kiểm tra module xử lý ngôn ngữ tự nhiên, nhóm cũng tiến hành
đánh giá hiệu năng của hệ thống backend thông qua việc đo thời gian phản hồi của các
API. Quá trình kiểm thử được thực hiện bằng **Swagger UI** , công cụ cho phép gửi trực
tiếp các yêu cầu đến các endpoint của hệ thống và quan sát kết quả trả về.

Thông qua Swagger UI, nhóm có thể kiểm tra các chức năng chính của hệ thống
như tạo công việc, cập nhật công việc, xóa công việc và lấy danh sách công việc. Việc
này giúp đánh giá thời gian phản hồi của API cũng như kiểm tra tính ổn định của hệ
thống khi xử lý các yêu cầu từ phía người dùng.

Ngoài ra, hệ thống cũng được kiểm tra khả năng xử lý các biểu thức thời gian
phổ biến trong tiếng Việt như “hôm nay”, “ngày mai”, “tuần sau” hoặc “3 giờ chiều”.
Những biểu thức này sau khi được phân tích sẽ được chuyển đổi thành dữ liệu thời gian
cụ thể để lưu trữ và xử lý trong cơ sở dữ liệu, giúp hệ thống quản lý lịch trình một cách
chính xác hơn.


```
3.5.4. Thảo luận kết quả
```
Qua quá trình thử nghiệm, có thể thấy hệ thống đã bước đầu hoạt động ổn định
khi kết hợp giữa giao diện web, backend và module xử lý ngôn ngữ tự nhiên. Người
dùng có thể tạo và quản lý công việc một cách thuận tiện thông qua giao diện của hệ
thống.

Tuy nhiên, một số tính năng vẫn đang trong quá trình hoàn thiện. Đặc biệt, chức
năng nhắc việc tự động dựa trên thuật toán AI hiện vẫn đang được phát triển nên chưa
thể tiến hành thử nghiệm và đánh giá trong giai đoạn này. Sau khi hoàn thiện chức năng
này, nhóm sẽ tiến hành kiểm tra và đánh giá hiệu quả của cơ chế nhắc việc trong các
bước phát triển tiếp theo.

Ngoài ra, trong tương lai hệ thống cũng có thể được cải thiện bằng cách mở rộng
bộ dữ liệu huấn luyện, tối ưu thuật toán xử lý ngôn ngữ tự nhiên và bổ sung thêm các
tính năng hỗ trợ người dùng quản lý công việc hiệu quả hơn.

## CHƯƠNG 4. KẾT LUẬN VÀ HƯỚNG PHÁT TRIỂN

### 4.1. Kết quả đạt được

Báo cáo các hệ thống đã triển khai: độ chính xác phân loại ý định (thí dụ 90-
95%), độ chính xác trung bình api >200ms, F1 score NER (thí dụ 85-90%) các chức
năng hoàn thiện: CRUD calendar (create, read, update, delete), tự động nhắc việc, ...
Nêu ra việc Smart Scheduler đã thành công trong việc tạo công việc từ lệnh tiếng Việt
(“Smart Scheduler hiểu và tạo được tác vụ mới chỉ với câu nhập tự nhiên”). Nêu ra
hướng ứng dụng trong công việc hàng ngày cho người dùng Việt như: trợ lý ảo cá nhân,
đồng bộ với calendar công ty.

### 4.2. Hạn chế

- Định danh thời gian - Do thiếu quy tắc xử lý, các biểu thức thời gian phức tạp
    hoặc tương đối (ví dụ, 'vào cuối tháng', 'sáu tuần tới') có thể vẫn không chính
    xác.


- Xử lý các tuyên bố đa dạng: Dữ liệu huấn luyện có thể không bao quát tất cả các
    khả năng (câu dài, lỗi chính tả, nhiều ý định). Do đó, hệ thống vẫn bị hạn chế ở
    các mẫu câu phổ biến.
- Dữ liệu huấn luyện - Dữ liệu huấn luyện (dữ liệu lệnh được gán nhãn ý
    định/NER) không đủ lớn và đa dạng, mô hình có thể không tổng quát tốt.
- Năng lực đầu vào giọng nói: Hiện tại hệ thống chỉ hỗ trợ đầu vào văn bản.
    Chuyển đổi giọng nói thành văn bản chưa được tích hợp, do đó tính tiện lợi còn
    hạn chế.

### 4.3. Đề xuất cải tiến

- Quy tắc nâng cấp thời gian: Mở rộng các quy tắc trích xuất và chuẩn hóa thời
    gian (ví dụ: thêm thư viện HeidelTime đã được tùy chỉnh cho tiếng Việt) để cải
    thiện khả năng nhận diện các mẫu thời gian phức tạp hơn.
- Cải tiến giao diện: Tăng cường tính tương tác, ví dụ: hỗ trợ gợi ý văn bản
    (autocomplete), và sử dụng thiết kế responsive.
- Tối ưu hóa thuật toán lập lịch: Nếu có nhiều nhiệm vụ cùng lúc, có thể sử dụng
    các kỹ thuật tối ưu hóa như nhánh-cận (branch and bound) hoặc thuật toán di
    truyền để lập lịch tốt hơn.
- Mở rộng đào tạo mô hình: Thu thập thêm dữ liệu thực tế, đặc biệt là các câu có
    cấu trúc phức tạp hoặc lỗi ngữ pháp, để huấn luyện mô hình mạnh hơn.

### 4.4. Hướng nghiên cứu tiếp theo

- Nhập dữ liệu giọng nói: Việc tích hợp công nghệ nhận diện giọng nói (ví dụ:
    Google Speech API, VOSK) cho phép người dùng điều khiển nó bằng giọng nói.
- Đa người dùng & đồng bộ lịch: Cụ thể cho việc phát triển đa người dùng (multi-
    tenant), người dùng có thể đồng bộ lịch trên các tài khoản (tích hợp với Google
    Calendar, Outlook, v.v.).
- Sử dụng LLMs/Chatbots: Nghiên cứu việc sử dụng Mô hình Ngôn ngữ Lớn
    (GPT, ChatGPT) cho các phản hồi tự động hoặc gợi ý lập lịch thông minh. Ví
    dụ, hệ thống có thể trả lời câu hỏi của người dùng “Tôi có những sự kiện gì vào
    tuần tới ?”.


### 4.5. Khả năng thương mại hóa

Thương mại Smart Scheduler có thể tiềm năng phát triển cho gia đình và doanh
nghiệp nhỏ ứng dụng web/mobile. Nếu phát triển và tích hợp với các hệ sinh thái sẽ hỗ
trợ Smart Scheduler dưới dạng dịch vụ trợ lý quản lý công việc cho người dùng, trong
đó có thể tích hợp trong văn phòng và ứng dụng nhắc việc để hỗ trợ tự động hóa việc
quản lý lịch.

### 9. Danh mục tài liệu tham khảo

**[1]** Vu, T., Nguyen, D. Q., Nguyen, D. Q., Dras, M., & Johnson, M. (2018).
_VnCoreNLP: A Vietnamese Natural Language Processing Toolkit_. NAACL
Demonstrations.
**https://arxiv.org/abs/1801.01331**

**[2]** Vu, A. (2018). _Underthesea – Vietnamese NLP Toolkit_. Tài liệu chính thức.
**https://underthesea.readthedocs.io/en/latest/readme.html**

**[3]** Nguyen, D. Q., & Nguyen, A. T. (2020). _PhoBERT: Pre-trained Language Models
for Vietnamese_. Proceedings of EMNLP 2020.
**https://arxiv.org/abs/2003.00744**

**[4]** Nguyen, D. T. H. (2024). _Research on HeidelTime with Vietnamese Language
Processing_. Vinh University Journal of Science.
**https://vjol.info.vn/index.php/vinhuni/article/download/117535/98065/**

**[5]** Chang, A. X., & Manning, C. D. (2012). _SUTime: A Library for Recognizing and
Normalizing Time Expressions_. Proceedings of LREC 2012.
**https://nlp.stanford.edu/software/sutime.html**

**[6]** Wijerathne, O., et al. (2025). _ScheduleMe: Multi-Agent Calendar Assistant_. arXiv.
**https://arxiv.org/html/2509.25693v1**

**[7]** VinAI Research. _PhoBERT Model Documentation_. HuggingFace.
**https://huggingface.co/vinai/phobert-base-v2**


TP. HCM, ngày ... tháng ... năm ...
Sinh viên thực hiện
(Ký và ghi rõ họ tên)


