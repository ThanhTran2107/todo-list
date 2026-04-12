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
        // Quét ra hẳn 10 phút trước và 5 phút sau để "bắt" được task
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
        String htmlContent = """
                    <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 10px; padding: 20px;">
                        <h2 style="color: #2d89ef;">🔔 Nhắc nhở công việc</h2>
                        <p>Chào bạn, bạn có một công việc sắp đến hạn:</p>
                        <div style="background-color: #f9f9f9; padding: 15px; border-left: 5px solid #2d89ef; margin: 20px 0;">
                            <strong style="font-size: 18px;">%s</strong><br/>
                            <span style="color: #666;">Hạn chót: %s</span>
                        </div>
                        <p>Hãy nhấn vào nút bên dưới để xem chi tiết:</p>
                        <a href="http://localhost:3000/todos" style="background-color: #2d89ef; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;">Mở danh sách công việc</a>
                        <hr style="border: 0; border-top: 1px solid #eee; margin-top: 20px;">
                        <small style="color: #999;">Đây là email tự động, vui lòng không phản hồi.</small>
                    </div>
                """
                .formatted(todo.title, formattedDate);

        mailer.send(Mail.withHtml(todo.user.email, "🔔 Nhắc nhở công việc: " + todo.title, htmlContent));
        System.out.println("Sent reminder for task: " + todo.title + " to " + todo.user.email);
    }
}