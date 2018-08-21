package app.content;

import app.content.modal.Visibility;
import app.content.modal.login.Login;
import app.content.modal.user.User;

import java.util.*;

/**
 *
 This class simulates the data that we would extract from a database
 */
public class MyApplication {
    private List<User> userList;
    public List<Visibility> visibilityList;

    private Map<String,List<String>> waitingLists;
    private Map<String,List<String>> friendsLists;

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
        this.setUserLoggedNow(null);
        this.waitingLists = new HashMap<>();
        this.friendsLists = new HashMap<>();
        //initLoginList();
    }

    public Map<String, List<String>> getWaitingLists() {
        return waitingLists;
    }

    public Map<String, List<String>> getFriendsLists() {
        return friendsLists;
    }

    public void addOneUserInMyFriendList(String userLogged, String userToAdd){

        List<String> list = this.friendsLists.get(userLogged);
        list.add(userToAdd);
        this.friendsLists.put(userLogged, list);
        //Remove from waiting
        removeElementFromWaitingList(userLogged, userToAdd);
    }

    private void removeElementFromWaitingList(String userToFind,String userDelete){
        List<String> list = this.waitingLists.get(userToFind);
        deleteElementFriendFromList(list, userDelete);
    }

    public void deleteElementFriendFromList(List<String> list, String userDelete){
        System.out.println("DELETE ELEMENT");
        Iterator itr = list.iterator();

        while (itr.hasNext()){
            String x = (String)itr.next();
            if(x.equals(userDelete)){
                itr.remove();
            }
        }
    }

    public void addIntoWaitingFriendList(String userLogged, String userToFind){
        List<String> list = this.waitingLists.get(userToFind);
        list.add(userLogged);
        this.waitingLists.put(userToFind, list);
    }

    public List<Visibility> getVisibilityList() {
        return visibilityList;
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
        this.visibilityList.add(v1);
        this.visibilityList.add(v2);
    }
}
