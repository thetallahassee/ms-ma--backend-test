package app.content.domain;

import app.content.domain.user.User;

import java.util.Iterator;
import java.util.List;

public abstract class UserParams {
    private Visibility visibility;
    private List<User> friendsList;
    private List<User> waitFriendsList;
    private List<User> rejectionFriendsList;

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
}
