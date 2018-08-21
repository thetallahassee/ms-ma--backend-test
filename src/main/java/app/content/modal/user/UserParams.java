package app.content.modal.user;

import app.content.modal.Visibility;
import app.content.modal.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class UserParams {

    private Visibility visibility;
    private List<String> friendsList = new ArrayList<>();
    private List<String> waitFriendsList = new ArrayList<>();
    //private List<String> rejectionFriendsList = new ArrayList<>();
    private int rejections = 0;
    private boolean nobodyLovesYou = false;

    public UserParams() {
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void addFriend(String userFriend){
        /*if(this.friendsList.size()==0){
            this.friendsList = new ArrayList<>();
        }*/
        this.friendsList.add(userFriend);
        deleteWaitingFriend(userFriend);
    }
    public void addWaitingFriend(String userFriend){
        System.out.println("ENTRA EN ADD WAITING FRIEND");
        /*if(this.waitFriendsList.size()==0){
            this.waitFriendsList = new ArrayList<>();
        }*/

        this.waitFriendsList.add(userFriend);
        System.out.println("LISTADO "+ this.waitFriendsList);

        //deleteWaitingFriend(userFriend);
    }

    public void setFriendsList(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    public void setWaitFriendsList(List<String> waitFriendsList) {
        this.waitFriendsList = waitFriendsList;
    }

    /*public void rejectFriend(User userFriend){
        this.rejectionFriendsList.add(userFriend.getUserName());
        //deleteWaitingFriend(userFriend.getUserName());
    }*/

    public void deleteWaitingFriend(String username){ ;
        Iterator itr = this.waitFriendsList.iterator();

        while (itr.hasNext()){
            User x = (User)itr.next();
            if(x.getUserName().equals(username)){
                itr.remove();
            }
        }
    }

    public void deleteFriend(String username){
        //List<User> filteredList = nums.stream().filter(i -> i >= 3).collect(Collectors.toList());

        Iterator itr = this.friendsList.iterator();

        while (itr.hasNext()){
            User x = (User)itr.next();
            if(x.getUserName().equals(username)){
                itr.remove();
            }
        }
    }

    public int getRejections() {

        return rejections;
    }

    public void setRejections() {
        System.out.println("NEW REJECTION, TOTAL: "+this.rejections);
        this.rejections ++;
        if(this.rejections >= 3){
            this.nobodyLovesYou = true;
        }
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    public List<String> getWaitFriendsList() {
        return waitFriendsList;
    }

    public boolean isNobodyLovesYou() {
        return nobodyLovesYou;
    }
}
