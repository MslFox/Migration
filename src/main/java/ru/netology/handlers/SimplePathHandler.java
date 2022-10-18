package ru.netology.handlers;

import ru.netology.controller.PostController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimplePathHandler implements Handler {
    private final PostController controller;

    public SimplePathHandler(PostController controller) {
        this.controller = controller;
    }

    @Override
    public synchronized void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (req.getMethod()) {
            case "GET" -> controller.all(resp);
            case "POST" -> controller.save(req.getReader(), resp);
            default -> resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }
}
