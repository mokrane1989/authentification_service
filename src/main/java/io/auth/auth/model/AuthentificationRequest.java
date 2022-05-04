package io.auth.auth.model;

/**
 * Created by k.kezzar on 26/02/2020.
 */
public class AuthentificationRequest {

    public AuthentificationRequest() {
    }

    public AuthentificationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
