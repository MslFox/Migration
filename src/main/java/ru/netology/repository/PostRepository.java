package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


// Stub
@Repository
public class PostRepository {
    private final ConcurrentHashMap<Long, Post> repository = new ConcurrentHashMap<>();
    private Long idCounter = 0L;

    public List<Post> all() {
        return new ArrayList<>(repository.values());
    }

    public Optional<Post> getById(long id) {
        return repository.values().stream().filter(x -> x.getId() == id).findFirst();
    }

    public synchronized Post save(Post post) {
        if (post == null) {
            synchronized (idCounter) {
                idCounter++;
                    var newPost= new Post(idCounter, "Null post ");
                while (repository.putIfAbsent(idCounter, newPost) != null) {
                    idCounter++;
                    newPost.setId(idCounter);
                }
                return newPost;
            }
        }
        repository.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        repository.remove(id);
    }
}
