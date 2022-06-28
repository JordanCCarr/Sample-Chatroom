package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    /**
     * Authenticates a user
     * @param username the username to authenticate
     * @return true if the user is authenticated, false otherwise
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth")
    public boolean authenticate(@RequestBody final String username) {
        return service.authenticateUser(username);
    }

    /**
     * Creates a new user
     * @param username the username to create
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void create(@RequestBody final String username) {
        service.createNewUser(username);
    }
}