package todo.list.todo.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import todo.list.todo.dto.request.CreateTodoRequest;
import todo.list.todo.dto.request.UpdateTodoRequest;
import todo.list.todo.dto.response.TodoResponse;
import todo.list.todo.entity.Todo;
import todo.list.todo.entity.enums.PriorityEnum;
import todo.list.todo.entity.enums.StatusEnum;
import todo.list.todo.repository.TodoRepository;
import todo.list.user.entity.User;
import todo.list.user.service.UserService;

@ApplicationScoped
public class TodoService {
    @Inject
    TodoRepository todoRepository;

    @Inject
    UserService userService;

    @Inject
    TodoSupportService todoSupportService;

    // REPOSITORY METHODS FOR REUSING
    public Optional<Todo> findById(long id) {
        return Optional.ofNullable(todoRepository.findById(id));
    }

    public List<Todo> findByUserWithFilters(User user, String searchText, String priority, Instant dueDateBefore,
            Boolean completed) {
        return todoRepository.findByUserWithFilters(user, searchText, priority, dueDateBefore, completed);
    }

    public Todo saveTodo(Todo todo) {
        return todoRepository.saveTodo(todo);
    }

    public Todo deleteTodo(Todo todo) {
        return todoRepository.deleteTodo(todo);
    }

    public void deleteAllByUser(User user) {
        todoRepository.deleteAllByUser(user);
    }

    // SERVICE METHODS
    public List<TodoResponse> getTodos(String userEmail, Boolean completed, String searchText, String priority,
            Instant dueDateBefore, Integer limit, Integer offset)
            throws Exception {
        User user = todoSupportService.findUserByEmail(userEmail);

        List<Todo> todosOfUser = findByUserWithFilters(user, searchText, priority, dueDateBefore,
                completed);

        // Order by createdAt desc (newest first)
        todosOfUser.sort((a, b) -> b.createdAt.compareTo(a.createdAt));

        // Apply pagination if provided
        if (limit != null && offset != null) {
            int start = Math.min(offset, todosOfUser.size());
            int end = Math.min(start + limit, todosOfUser.size());

            todosOfUser = todosOfUser.subList(start, end);
        }

        List<TodoResponse> todoList = new ArrayList<>();
        for (Todo todo : todosOfUser)
            todoList.add(todoSupportService.mapTodoResponse(todo));

        return todoList;
    }

    public TodoResponse getTodoById(String userEmail, Long id) throws Exception {
        Todo todo = todoSupportService.findTodoByIdAndUser(userEmail, id);

        return todoSupportService.mapTodoResponse(todo);
    }

    @Transactional
    public TodoResponse createTodo(String userEmail, CreateTodoRequest request) throws Exception {
        todoSupportService.validateAndPrepareTodo(request.getTitle(), request.getPriority(), request.getStatus());

        User user = todoSupportService.findUserByEmail(userEmail);

        Todo todo = new Todo();
        todo.user = user;

        PriorityEnum priorityEnum = request.getPriority() != null ? request.getPriority() : PriorityEnum.MEDIUM;
        StatusEnum statusEnum = request.getStatus() != null ? request.getStatus() : StatusEnum.PENDING;

        todoSupportService.setTodoFields(todo, request.getTitle(), request.getDescription(), request.getDueDate(),
                priorityEnum, statusEnum);

        saveTodo(todo);

        return todoSupportService.mapTodoResponse(todo);
    }

    @Transactional
    public TodoResponse updateTodo(String userEmail, Long id, UpdateTodoRequest request) throws Exception {
        todoSupportService.validateAndPrepareTodo(request.getTitle(), request.getPriority(), request.getStatus());

        Todo todo = todoSupportService.findTodoByIdAndUser(userEmail, id);
        todo.updatedAt = Instant.now();

        todoSupportService.setTodoFields(todo, request.getTitle(), request.getDescription(), request.getDueDate(),
                request.getPriority(),
                request.getStatus());

        if (request.getCompleted() != null)
            todo.completed = request.getCompleted();

        return todoSupportService.mapTodoResponse(todo);
    }

    @Transactional
    public void deleteTodo(String userEmail, Long id) throws Exception {
        Todo todo = todoSupportService.findTodoByIdAndUser(userEmail, id);

        deleteTodo(todo);
    }

    @Transactional
    public void deleteAllTodos(String userEmail) throws Exception {
        User user = userService.findByEmail(userEmail).orElseThrow(() -> new Exception("User not found"));

        deleteAllByUser(user);
    }
}
