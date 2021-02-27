package es.urjc.etsii.dad.campfire.service;

/**
 * Represents the status of a login or register request.
 */
public enum LoginResponse {
    /**
     * The provided username is already in use by other user.
     */
    USERNAME_NOT_AVAILABLE,
    /**
     * The provided username is not registered in the database.
     */
    USERNAME_NOT_FOUND,
    /**
     * The provided password does not match with the password stored in the
     * database.
     */
    CREDENTIALS_NOT_VALID,
    /**
     * The request has been correctly fulfilled.
     */
    SUCCESS
}
