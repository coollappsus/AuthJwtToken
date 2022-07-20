package main.service;

import main.model.User;
import main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Получить пользователя по логину
    public User getByLogin(String login) {
        return userRepository.findUserByLogin(login)
                .orElseThrow(() -> new RuntimeException("Пользователя с логином = " + login + " не существует!"));
    }
}
