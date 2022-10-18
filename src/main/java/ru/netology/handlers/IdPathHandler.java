package ru.netology.handlers;

import ru.netology.controller.PostController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IdPathHandler implements Handler {
    private final PostController controller;

    public IdPathHandler(PostController postController) {
        this.controller = postController;
    }

    @Override
    public synchronized void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var path = req.getRequestURI();
        var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
        switch (req.getMethod()) {
            case "GET" -> controller.getById(id, resp);
            case "DELETE" -> controller.removeById(id, resp);
            default -> resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }
}
