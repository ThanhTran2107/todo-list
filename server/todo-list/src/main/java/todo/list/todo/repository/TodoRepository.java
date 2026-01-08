package todo.list.todo.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.List;
import todo.list.todo.entity.Todo;
import todo.list.user.entity.User;

@ApplicationScoped
public class TodoRepository implements PanacheRepository<Todo> {
    public List<Todo> findByUserWithFilters(User user, String searchText, String priority, Instant dueDateBefore,
            Boolean completed) {
        StringBuilder query = new StringBuilder("user = ?1");
        List<Object> params = new java.util.ArrayList<>();
        params.add(user);

        if (searchText != null && !searchText.trim().isEmpty()) {
            query.append(" and lower(title) like ?").append(params.size() + 1);
            params.add("%" + searchText.toLowerCase() + "%");
        }
        if (priority != null && !priority.trim().isEmpty()) {
            query.append(" and priority = ?").append(params.size() + 1);
            params.add(priority.toLowerCase());
        }
        if (dueDateBefore != null) {
            query.append(" and dueDate < ?").append(params.size() + 1);
            params.add(dueDateBefore);
        }
        if (completed != null) {
            query.append(" and completed = ?").append(params.size() + 1);
            params.add(completed);
        }

        return list(query.toString(), params.toArray());
    }

    public Todo findById(Long id) {
        return find("id", id).firstResult();
    }

    public Todo saveTodo(Todo todo) {
        persist(todo);

        return todo;
    }

    public Todo deleteTodo(Todo todo) {
        delete(todo);

        return todo;
    }

    public void deleteAllByUser(User user) {
        delete("user", user);
    }
}
