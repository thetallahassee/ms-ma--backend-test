package app.content.service.friend;

import app.content.modal.Response;
import app.content.modal.friendly.UserToFind;
import app.content.modal.user.User;
import app.content.service.user.UserServices;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;

import java.util.List;

import static app.StartApp.myApplication;

@Service("friendServices")
public class FriendServices {
    User myUser = myApplication.getUserLoggedNow().getUserLogged();

    @Autowired
    UserServices userServices;

    public void returnFriendsWaitingFromUser(){

    }
    public void returnFriendsFromUser(){

    }
    public void returnFriendsRejectedFromUser(){

    }

    public Response acceptNewFriend(String userNameToAdd){
        Response response = null;

        UserToFind userTemp = new Gson().fromJson(userNameToAdd, UserToFind.class);
        boolean isInWaitList = loopListsFriend(userTemp.getUserName(),myUser.getWaitFriendsList());
        boolean isInMyFriendsList = loopListsFriend(userTemp.getUserName(),myUser.getFriendsList());
        User userReject = userServices.getUserFromListByUserName(userTemp.getUserName());
        if(isInWaitList){
            myUser.addFriend(userReject.getUserName());
        }else if(isInMyFriendsList){
            response.setCode(400);
            response.setMessage("this user is already your friend");
        }else{
            response.setCode(400);
            response.setMessage("User not found");
        }

        return response;
    }

    public Response declineFriendResponse(String userNameToDecline){
        //User myUser = myApplication.getUserLoggedNow().getUserLogged();
        Response response = null;
        UserToFind userTemp = new Gson().fromJson(userNameToDecline, UserToFind.class);
        boolean isInWaitList = loopListsFriend(userTemp.getUserName(),myUser.getWaitFriendsList());
        boolean isInMyFriendsList = loopListsFriend(userTemp.getUserName(),myUser.getFriendsList());
        User userReject = userServices.getUserFromListByUserName(userTemp.getUserName());
        if(isInWaitList){
            userReject.setRejections();
            myUser.deleteWaitingFriend(userReject.getUserName());
        }else if(isInMyFriendsList){
            userReject.setRejections();
            myUser.deleteFriend(userReject.getUserName());
        }else{
            response.setCode(400);
            response.setMessage("User not found");
        }
        return response;
    }

    public Response myFriendsList(){
        //User myUser = myApplication.getUserLoggedNow().getUserLogged();
        Response response = new Response(200,"return friends list");
        Gson gson = new Gson();
        response.setContent(gson.toJson(myUser.getFriendsList()));

        return response;
    }

    public Response myWaitingList(){
        //User myUser = myApplication.getUserLoggedNow().getUserLogged();
        Response response = new Response(200,"return waiting list");
        Gson gson = new Gson();
        response.setContent(gson.toJson(myUser.getWaitFriendsList()));

        return response;
    }

    public Response addToWaitingList(String requestedUser){
        Response response = null;
        UserToFind userTemp = new Gson().fromJson(requestedUser, UserToFind.class);

        User userReq = userServices.getUserFromListByUserName(userTemp.getUserName());
        //User myUser = myApplication.getUserLoggedNow().getUserLogged();

        if(myUser.isNobodyLovesYou()){
            response.setCode(450);
            response.setMessage("NOBODY LOVES YOU");
        }else{
            response = checkIntoLists(myUser, userReq);
        }
        return response;
    }

    private Response checkIntoLists(User myUser, User userReq){
        Response response = null;
        boolean isFriend = loopListsFriend(myUser.getUserName(), userReq.getFriendsList());
        boolean isWaiting = loopListsFriend(myUser.getUserName(), userReq.getWaitFriendsList());
        if(!isFriend && !isWaiting){
            userReq.addWaitingFriend(myUser);
            response.setCode(200);
            response.setMessage("Request sent");
        }else if(isFriend){
            response.setCode(400);
            response.setMessage("You are now friends");
        }else if(isWaiting){
            response.setCode(400);
            response.setMessage("You are waiting");
        }
        return response;
    }

    public void addToFriendsList(){

    }

    public void addToRejectedList(){

    }

    /*private boolean myUserIsAFriend(String requestedUser){
        boolean isFriend = false;
        User myUser = myApplication.getUserLoggedNow().getUserLogged();
        User userReq = userServices.getUserFromListByUserName(requestedUser);

        for(String userName : userReq.getFriendsList()){
            if(userName.equals(myUser.getUserName())){
                isFriend = true;
                break;
            }
        }
        return isFriend;
    }

    private boolean myUserIsWaiting(String requestedUser){

    }*/

    private boolean loopListsFriend(String userToFind, List<String>list){
        boolean coincidence = false;
        for(String userName : list){
            if(userName.equals(userToFind)){
                coincidence = true;
                break;
            }
        }
        return coincidence;
    }
}
