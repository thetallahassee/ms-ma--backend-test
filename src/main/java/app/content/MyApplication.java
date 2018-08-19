package app.content;

import app.content.domain.Visibility;
import app.content.domain.login.Login;
import app.content.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class MyApplication {
    private List<User> userList;
    private List<Visibility> visibilityList;

    private List<Login> usersLogged;

    public void addUserLogged(Login userLoggin){
        usersLogged.add(userLoggin);
    }

    public void removeUserLogged(String username){}

    public void initDefaultParams(){
        initUsertList();
        initDefaultOptionsVisibility();
        initLoginList();
    }

    private void initLoginList(){
        this.usersLogged = new ArrayList<>();
    }

    private void initUsertList(){
        System.out.println("INICIAR LISTA");
        this.userList = new ArrayList<>();
    }

    public boolean isUserLogged(String userName){
        boolean isExists=false;
        for(Login userLoop:this.usersLogged){
            if(userLoop.getUserLogged().getUserName().equals(userName)){
                isExists = true;
                break;
            }
        }
        return isExists;
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

    public Visibility getVisibilityTypeFromCode(String code){
        Visibility visResp = null;
        for(Visibility visibilityLoop:this.visibilityList){
            if(visibilityLoop.getCode().equals(code)){
                visResp = visibilityLoop;
            }
        }
        return visResp;
    }

    private void initDefaultOptionsVisibility() {
        Visibility v1 = new Visibility("001","Hidden", true, false);
        Visibility v2 = new Visibility("002", "Public",true,true);
        this.visibilityList = new ArrayList<>();
        visibilityList.add(v1);
        visibilityList.add(v2);
    }
}
