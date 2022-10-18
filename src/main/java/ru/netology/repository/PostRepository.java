package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Stub
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
                Post newPost = new Post(idCounter, "Zero content Post");
                while (repository.putIfAbsent(idCounter, newPost) != null) {
                    idCounter++;
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
