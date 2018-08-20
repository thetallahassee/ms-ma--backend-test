package app.content.modal;

import app.content.modal.user.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class UserParams {
    private Visibility visibility;
    private List<User> friendsList = new ArrayList<>();
    private List<User> waitFriendsList = new ArrayList<>();
    private List<User> rejectionFriendsList = new ArrayList<>();
    private int rejections = 0;
    private boolean nobodyLovesYou = false;

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void addFriend(User userFriend){
        this.friendsList.add(userFriend);
        deleteWaitingFriend(userFriend.getUserName());
    }

    public void rejectFriend(User userFriend){
        this.rejectionFriendsList.add(userFriend);
        deleteWaitingFriend(userFriend.getUserName());
    }

    public void deleteWaitingFriend(String username){
        //List<User> filteredList = nums.stream().filter(i -> i >= 3).collect(Collectors.toList());

        Iterator itr = this.waitFriendsList.iterator();

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
        this.rejections ++;
        if(this.rejections >= 3){
            this.nobodyLovesYou = true;
        }
    }
}
