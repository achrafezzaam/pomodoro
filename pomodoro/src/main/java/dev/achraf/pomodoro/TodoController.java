package dev.achraf.pomodoro;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<List<Todo>>(todoService.allTodos(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Todo>> getTodo(@PathVariable String imdbId) {
        return new ResponseEntity<Optional<Todo>>(todoService.todoByImdbId(imdbId), HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo newTodo) {
        Todo todo = todoService.saveTodo(newTodo);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PutMapping("/{imdbId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String imdbId, @RequestBody Todo newInfo) {
        Todo todo = todoService.todoByImdbId(imdbId)
                .orElseThrow(() -> new IllegalArgumentException("This Todo doesn't exist"));
        todo.setTitle(newInfo.getTitle());
        todo.setBody(newInfo.getBody());
        todoService.saveTodo(todo);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @DeleteMapping("/{imdbId}")
    public  ResponseEntity<String> deleteTodo(@PathVariable String imdbId) {
        Todo todo = todoService.todoByImdbId(imdbId)
                .orElseThrow(() -> new IllegalArgumentException("This Todo doesn't exist"));
        return new ResponseEntity<String>(todoService.deleteTodo(todo), HttpStatus.OK);
    }
}
