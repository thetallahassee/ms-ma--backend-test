package app.content.modal.user;

import app.content.ExceptionManager;
import app.content.service.GeneralServices;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User extends UserParams {
    @Autowired
    GeneralServices generalServices;
    @Id
    private String userName;

    private String password;
    private Date registerDate;

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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
