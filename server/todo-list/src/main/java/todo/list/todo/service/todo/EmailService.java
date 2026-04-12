package todo.list.todo.service.todo;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import todo.list.todo.entity.Todo;

@ApplicationScoped
public class EmailService {

    @Inject
    Mailer mailer;

    @ConfigProperty(name = "app.frontend.url", defaultValue = "http://localhost:3000")
    String frontendUrl;

    public void sendEmail(Todo todo) throws Exception {
        String htmlTemplate;
        try (InputStream is = getClass().getResourceAsStream("/templates/reminder-email.html")) {
            if (is == null)
                throw new RuntimeException("Template not found");
            
            htmlTemplate = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")
                .withZone(ZoneId.of("Asia/Ho_Chi_Minh"));
        String formattedDate = formatter.format(todo.dueDate);

        // TRUYỀN DỮ LIỆU VÀO TEMPLATE (Thứ tự phải khớp với các dấu %s trong HTML)
        // 1: Email, 2: Title, 3: Date
        htmlTemplate = htmlTemplate
                .replace("{email}", todo.user.email)
                .replace("{title}", todo.title)
                .replace("{dueDate}", formattedDate)
                .replace("{appUrl}", frontendUrl);

        // 4. Gửi email với nội dung đã được thay thế dữ liệu
        mailer.send(Mail.withHtml(
                todo.user.email,
                "Reminder: " + todo.title,
                htmlTemplate));
    }

}
