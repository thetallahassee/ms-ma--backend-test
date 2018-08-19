package app.content.domain;

import app.content.ExceptionManager;
import app.content.service.GeneralServices;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Autowired
    GeneralServices generalServices;
    @Id
    private String userName;

    private String password;

    public User(String userName, String password) throws ExceptionManager{
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
