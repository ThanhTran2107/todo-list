package todo.list.todo.service.todo;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import todo.list.todo.entity.Todo;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
public class TodoReminderService {

    @Inject
    Mailer mailer;

    @Scheduled(every = "1m") // Quét mỗi phút
    @Transactional
    void checkReminders() {
        System.out.println(">>> [DEBUG] DANG QUET DATABASE...");

        Instant now = Instant.now();
        // Quét rộng ra hẳn 12 tiếng trước và sau để "bắt" bằng được task
        Instant start = now.minus(10, ChronoUnit.MINUTES);
        Instant end = now.plus(5, ChronoUnit.MINUTES);

        List<Todo> upcomingTodos = Todo.find(
                "status = 'PENDING' and reminded = false and dueDate BETWEEN ?1 AND ?2",
                start, end).list();

        System.out.println(">>> So luong task tim thay: " + upcomingTodos.size());

        for (Todo todo : upcomingTodos) {
            // Kiểm tra xem User có null không trước khi lấy email
            if (todo.user != null) {
                System.out.println(">>> Dang gui mail cho: " + todo.user.email);
                sendEmail(todo);
                todo.reminded = true;
                todo.persist();
            }
        }
    }

    private void sendEmail(Todo todo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")
                .withZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        String formattedDate = formatter.format(todo.dueDate);
        // Nội dung HTML giúp hiển thị tiếng Việt chuẩn và trình bày đẹp hơn
        String htmlContent = String.format(
                "<html><body>" +
                        "<h3>Chào %s,</h3>" +
                        "<p>Bạn có công việc: <b>%s</b> sắp đến hạn.</p>" +
                        "<p>Thời gian: <i>%s</i></p>" +
                        "<p>Đừng quên hoàn thành nhé!</p>" +
                        "</body></html>",
                todo.user.email, // Bạn có thể thay bằng username nếu có
                todo.title,
                formattedDate);

        mailer.send(Mail.withHtml(todo.user.email, "🔔 Nhắc nhở công việc: " + todo.title, htmlContent));
        System.out.println("Sent reminder for task: " + todo.title + " to " + todo.user.email);
    }
}