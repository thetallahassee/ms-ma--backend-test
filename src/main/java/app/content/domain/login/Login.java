package app.content.domain.login;

import app.content.domain.user.User;

import java.util.Date;

public class Login {
    private User userLogged;
    private Date registerLogin = new Date();



    public Login(User userLogged) {
        this.userLogged = userLogged;
    }

    public User getUserLogged() {
        return userLogged;
    }
}
