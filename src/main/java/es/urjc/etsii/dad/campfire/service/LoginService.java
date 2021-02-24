package es.urjc.etsii.dad.campfire.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.dad.campfire.model.User;
import es.urjc.etsii.dad.campfire.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepo;

    public boolean isUsernameAvailable(String username) {
        return userRepo.findByUsername(username).isEmpty();
    }

    public boolean checkCredentials(String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);

        if (!user.isPresent())
            return false;

        User validUser = user.get();
        return validUser.getPassword().equals(password);
    }

    public boolean registerUser(User user) {
        if (!isUsernameAvailable(user.getUsername()))
            return false;

        userRepo.save(user);
        return true;
    }

    public boolean loginUser(User user) {
        return checkCredentials(user.getUsername(), user.getPassword());
    }
}
