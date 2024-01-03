package dev.achraf.pomodoro;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<Todo, ObjectId> {
    Optional<Todo> findTodoByImdbId(String imdbId);
}
