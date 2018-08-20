package app.content;

import app.content.modal.Visibility;
import app.content.modal.friendly.State;
import app.content.modal.login.Login;
import app.content.modal.user.User;

import java.util.ArrayList;
import java.util.List;

public class MyApplication {
    private List<User> userList;
    public List<Visibility> visibilityList;
    private List<State>friendStatesOptions;

    private Login userLoggedNow;

    public Login getUserLoggedNow() {
        return userLoggedNow;
    }

    public void setUserLoggedNow(Login userLoggedNow) {
        this.userLoggedNow = userLoggedNow;
    }

    public void initDefaultParams(){
        initUsertList();
        initDefaultOptionsVisibility();
        initDefaultOptionsState();
        this.setUserLoggedNow(null);
        //initLoginList();
    }

    private void initDefaultOptionsState() {
        friendStatesOptions = new ArrayList<>();
    }

    private void initUsertList(){
        System.out.println("INICIAR LISTA");
        this.userList = new ArrayList<>();
    }

    public boolean userExists(String userName){
        boolean isExists=false;
        for(User userLoop:this.userList){
            if(userLoop.getUserName().equals(userName)){
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    public boolean isUserAndPasswd(User user){
        boolean permission=false;
        for(User userLoop:this.userList){
            if(userLoop.getUserName().equals(user.getUserName()) &&
                    userLoop.getPassword().equals(user.getPassword())){
                permission = true;
                break;
            }
        }
        return permission;
    }


    public void addUserInUserList(User user){
        this.userList.add(user);
        System.out.println("USUARIO AÑADIDO "+this.userList.size());
        for(User userLoop : this.userList){
            System.out.println("*****************************************");
            System.out.println("USER LOOP NAME: "+userLoop.getUserName());
            System.out.println("USER LOOP IS VISIBLE: "+userLoop.getVisibility().getCode()+"-"+userLoop.getVisibility().getName());
            System.out.println("USER LOOP DATE: "+userLoop.getRegisterDate());
        }
    }

    public List<User> getUserList() {
        return userList;
    }



    private void initDefaultOptionsVisibility() {
        Visibility v1 = new Visibility("001","Hidden", true, false);
        Visibility v2 = new Visibility("002", "Public",true,true);
        this.visibilityList = new ArrayList<>();
        visibilityList.add(v1);
        visibilityList.add(v2);
    }
}
