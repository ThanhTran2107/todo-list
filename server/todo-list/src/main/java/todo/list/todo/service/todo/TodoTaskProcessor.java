package todo.list.todo.service.todo;

import org.jboss.logging.Logger;

import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import todo.list.todo.entity.Todo;
import todo.list.todo.repository.TodoRepository;

@ApplicationScoped
public class TodoTaskProcessor {

    private static final Logger LOG = Logger.getLogger(TodoReminderService.class);

    @Inject
    Mailer mailer;

    @Inject
    TodoRepository todoRepo;

    @Inject
    EmailService emailService;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void processTodoReminder(Long id) {
        Todo todo = todoRepo.findById(id);
        if (todo == null || todo.user == null || todo.user.email == null || todo.user.email.isBlank())
            return;

        try {
            emailService.sendEmail(todo);
            // Đánh dấu true để lần sau (dù có lọt vào window) cũng không gửi nữa
            todo.reminded = true;
            todo.persist();
            LOG.infof("Successfully sent 30-minute reminder for task: %s", todo.title);
        } catch (Exception e) {
            // Đổi: "Loi gui mail..."
            LOG.errorf("Failed to send email for task ID %d: %s", todo.id, e.getMessage());
        }
    }

}
