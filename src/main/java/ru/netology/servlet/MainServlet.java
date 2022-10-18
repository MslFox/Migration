package ru.netology.servlet;

import ru.netology.controller.PostController;
import ru.netology.handlers.*;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class MainServlet extends HttpServlet {
    private final HashMap<String, Handler> handlersMap = new HashMap<>();
    private PostController controller;

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);

        handlersMap.put("/api/posts", new SimplePathHandler(controller));
        handlersMap.put("/api/posts/\\d+", new IdPathHandler(controller));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final var path = req.getRequestURI();
            for (HashMap.Entry<String, Handler> handler : handlersMap.entrySet()) {
                if (path.matches(handler.getKey())) {
                    handler.getValue().handle(req, resp);
                    return;
                }
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

