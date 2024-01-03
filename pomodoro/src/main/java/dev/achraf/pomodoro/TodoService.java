package dev.achraf.pomodoro;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    public List<Todo> allTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> todoByImdbId(String imdbId) {
        return todoRepository.findTodoByImdbId(imdbId);
    }

    public Todo saveTodo(Todo newTodo) {
        return todoRepository.save(newTodo);
    }
}
