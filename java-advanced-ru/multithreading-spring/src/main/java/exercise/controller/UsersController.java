package exercise.controller;

import exercise.model.User;
import exercise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @PostMapping
    public Mono<User> create(@RequestBody User user) {
        return userService.create(user);
    }

    @PatchMapping("/{id}")
    public Mono<User> update(@RequestBody User user, @PathVariable("id") long id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") long id) {
        return userService.delete(id);
    }
}
