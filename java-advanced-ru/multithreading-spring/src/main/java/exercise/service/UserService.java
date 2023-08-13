package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Mono<User> update(User user, long id) {
        User updated = userRepository.findById(id).toFuture()
                .whenComplete((futureUser, e) -> mapNewUserToOld(futureUser, user))
                .join();
        return userRepository.save(updated);
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<Void> delete(long id) {
        return userRepository.deleteById(id);
    }

    private User mapNewUserToOld(User old, User updated) {
        old.setEmail(updated.getEmail());
        old.setFirstName(updated.getFirstName());
        old.setLastName(updated.getLastName());
        return old;
    }
}
