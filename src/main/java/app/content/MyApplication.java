package app.content;

import app.content.domain.User;

import java.util.ArrayList;
import java.util.List;

public class MyApplication {
    private List<User> userList;

    public void initUsertList(){
        System.out.println("INICIAR LISTA");
        this.userList = new ArrayList<>();
    }

    public void addUserInUserList(User user){
        this.userList.add(user);
        System.out.println("USUARIO AÑADIDO "+this.userList.size());
    }
}
