package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(long id) {
        service.removeById(id);
    }
}

//
//@Controller
//public class PostController {
//    public static final String APPLICATION_JSON = "application/json";
//    private final PostService service;
//    public PostController(PostService service) {
//        this.service = service;
//    }
//
//    public void all(HttpServletResponse response) throws IOException {
//        response.setContentType(APPLICATION_JSON);
//        final var data = service.all();
//        final var gson = new Gson();
//        response.getWriter().print(gson.toJson(data));
//    }
//
//    public void getById(long id, HttpServletResponse response) throws IOException {
//        response.getWriter().print(new Gson().toJson(service.getById(id)));
//    }
//
//    public void save(Reader body, HttpServletResponse response) throws IOException {
//        response.setContentType(APPLICATION_JSON);
//        final var gson = new Gson();
//        final var post = gson.fromJson(body, Post.class);
//        final var data = service.save(post);
//        response.getWriter().print(gson.toJson(data));
//    }
//
//    public void removeById(long id, HttpServletResponse response) throws IOException {
//        getById(id, response);
//        service.removeById(id);
//        response.setStatus(HttpServletResponse.SC_ACCEPTED);
//    }
//}
