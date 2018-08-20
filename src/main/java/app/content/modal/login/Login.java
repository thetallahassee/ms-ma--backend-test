package app.content.modal.login;

import app.content.modal.user.User;

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
