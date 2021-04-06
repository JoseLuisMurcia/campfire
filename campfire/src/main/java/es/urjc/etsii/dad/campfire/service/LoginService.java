package es.urjc.etsii.dad.campfire.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.dad.campfire.entity.User;
import es.urjc.etsii.dad.campfire.repository.UserRepository;

/**
 * Provides login and register services.
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepo;

    /**
     * Checks if the provided username is available.
     * 
     * @param username The username to perform the check with.
     * @return <code>true</code> if the username is available, <code>false</code>
     *         otherwise.
     */
    public boolean isUsernameAvailable(String username) {
        return userRepo.findByUsername(username).isEmpty();
    }

    /**
     * Performs the register request.
     * 
     * @param user the user to perform the request with.
     * @return One of the following <code>LoginResponse</code>:
     *         <ul>
     *         <li>{@link LoginResponse#USERNAME_NOT_AVAILABLE} if the provided
     *         username is already in use by other user.
     *         <li>{@link LoginResponse#SUCCESS} if the register request is
     *         successful.
     *         </ul>
     * @see LoginResponse
     */
    public LoginResponse registerUser(User user) {
        if (!isUsernameAvailable(user.getUsername()))
            return LoginResponse.USERNAME_NOT_AVAILABLE;

        userRepo.save(user);
        return LoginResponse.SUCCESS;
    }

    /**
     * Performs the login request.
     * <p>
     * First, checks if the username is registered. Then, checks if the provided
     * password is valid.
     * 
     * @param user the user to perform the request with.
     * @return One of the following <code>LoginResponse</code>:
     *         <ul>
     *         <li>{@link LoginResponse#USERNAME_NOT_FOUND} if the provided username
     *         is not registered in the database.
     *         <li>{@link LoginResponse#CREDENTIALS_NOT_VALID} if the provided
     *         password does not match with the password stored in the database for
     *         this user.
     *         <li>{@link LoginResponse#SUCCESS} if the login request is successful.
     *         </ul>
     * @see LoginResponse
     */
    public LoginResponse loginUser(User user) {
        Optional<User> requestUser = userRepo.findByUsername(user.getUsername());

        if (!requestUser.isPresent())
            return LoginResponse.USERNAME_NOT_FOUND;

        if (requestUser.get().getPassword().equals(user.getPassword())) {
            return LoginResponse.SUCCESS;
        } else {
            return LoginResponse.CREDENTIALS_NOT_VALID;
        }
    }
}
