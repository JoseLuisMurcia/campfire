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

    public LoginResponse checkCredentials(String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);

        if (!user.isPresent())
            return LoginResponse.USERNAME_NOT_FOUND;

        User validUser = user.get();
        if (validUser.getPassword().equals(password)) {
            return LoginResponse.LOGGED_IN;
        } else {
            return LoginResponse.CREDENTIALS_NOT_VALID;
        }
    }

    public LoginResponse registerUser(User user) {
        if (!isUsernameAvailable(user.getUsername()))
            return LoginResponse.USERNAME_NOT_AVAILABLE;

        userRepo.save(user);
        return LoginResponse.REGISTERED;
    }

    public LoginResponse loginUser(User user) {
        return checkCredentials(user.getUsername(), user.getPassword());
    }
}
