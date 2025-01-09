package service;

import model.User;
import storage.Storage;

import java.util.Optional;

public class UserService {
    private final Storage<String, User> userStorage;

    public UserService(Storage<String, User> userStorage) {
        this.userStorage = userStorage;
    }

    public Optional<User> authenticate(String login, String password) {
        return userStorage.find(login).filter(user -> user.getPassword().equals(password));
    }
}
