package todo.list.todo.service.todo;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import todo.list.todo.entity.Todo;
import todo.list.todo.repository.TodoRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
public class TodoReminderService {

    private static final Logger LOG = Logger.getLogger(TodoReminderService.class);

    @Inject
    TodoRepository todoRepo;

    @Inject
    TodoTaskProcessor todoTaskProcessor;

    @Scheduled(every = "7m")
    void checkReminders() {
        LOG.info(">>> [DEBUG] Scanning database for upcoming tasks...");
        Instant now = Instant.now();
        // Start: 5 phút trước (để phòng hờ sót)
        // End: 30 phút tới (chỉ bắt những task sắp đến hạn trong vòng 30 phút)
        Instant start = now.minus(5, ChronoUnit.MINUTES);
        Instant end = now.plus(30, ChronoUnit.MINUTES);

        // gửi email mỗi 10p nếu còn trong hạn và quá hạn dưới 5p
        List<Todo> upcomingTodos = todoRepo.findUpcomingTasks(start, end);

        if (!upcomingTodos.isEmpty()) {
            LOG.infof("Found %d task(s) due within the 30-minute window", upcomingTodos.size());
            for (Todo todo : upcomingTodos) {
                // Truyền ID (Long) vào hàm xử lý
                todoTaskProcessor.processTodoReminder(todo.id);
            }
        } else {
            LOG.debug("No upcoming tasks found in this scan.");
        }
    }

}