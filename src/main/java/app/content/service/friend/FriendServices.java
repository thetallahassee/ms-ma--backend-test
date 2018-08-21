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

import java.util.ArrayList;
import java.util.List;

import static app.StartApp.myApplication;

@Service("friendServices")
public class FriendServices {
    //User myUser = myApplication.getUserLoggedNow().getUserLogged();
    Response response = new Response(600, "empty");
    @Autowired
    UserServices userServices;

    public void returnFriendsWaitingFromUser(){

    }
    public void returnFriendsFromUser(){

    }
    public void returnFriendsRejectedFromUser(){

    }

    public Response acceptNewFriend(String userNameToAdd){
        //Response response = null;
        try{
            System.out.println("ACCEPT NEW FRIEND");
            User myUser = myApplication.getUserLoggedNow().getUserLogged();
            UserToFind userTemp = new Gson().fromJson(userNameToAdd, UserToFind.class);
            boolean isFriend = checkIsFriend(myUser.getUserName(),userTemp.getUserName());
            boolean isWaiting = checkIsWaiting(userTemp.getUserName(), myUser.getUserName());
           // User userReject = userServices.getUserFromListByUserName(userTemp.getUserName());
            if(isWaiting){
                //myUser.addFriend(userReject.getUserName());
                System.out.println("GO TO ADD FRIEND");
                myApplication.addOneUserInMyFriendList(myUser.getUserName(), userTemp.getUserName());
            }else if(isFriend){
                response.setCode(400);
                response.setMessage("this user is already your friend");
            }else{
                response.setCode(400);
                response.setMessage("User not found");
            }
        }catch (Exception e){
            response.setCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response declineFriendResponse(String userNameToDecline){
        try{
            User myUser = myApplication.getUserLoggedNow().getUserLogged();
            //Response response = null;
            UserToFind userTemp = new Gson().fromJson(userNameToDecline, UserToFind.class);
            boolean isFriend = checkIsFriend(myUser.getUserName(),userTemp.getUserName());
            boolean isWaiting = checkIsWaiting(userTemp.getUserName(), myUser.getUserName());
            User userReject = userServices.getUserFromListByUserName(userTemp.getUserName());
            if(isWaiting){
                System.out.println("IS WAITING AND DECLINE");
                userReject.setRejections();
                //myUser.deleteWaitingFriend(userReject.getUserName());
                List<String>myWaitingFriends = myApplication.getWaitingLists().get(myUser.getUserName());
                myApplication.deleteElementFriendFromList(myWaitingFriends, userReject.getUserName());
                response.setCode(200);
                response.setMessage("Declined friend: "+userReject.getUserName());
            }else if(isFriend){
                userReject.setRejections();
                List<String>myFriends = myApplication.getWaitingLists().get(myUser.getUserName());
                myApplication.deleteElementFriendFromList(myFriends, userReject.getUserName());
                //myUser.deleteFriend(userReject.getUserName());
                response.setCode(200);
                response.setMessage("Deleted friend: "+userReject.getUserName());
            }else{
                response.setCode(400);
                response.setMessage("User not found");
            }
        }catch (Exception e){
            response.setCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    public Response myFriendsList(){
        //Response response = null;
        try {
            User myUser = myApplication.getUserLoggedNow().getUserLogged();
            response.setCode(200);
            response.setMessage("return waiting list");
            Gson gson = new Gson();
            List<String>myFriendsList = myApplication.getFriendsLists().get(myUser.getUserName());
            response.setContent(gson.toJson(myFriendsList));
        }catch (Exception e){
            response.setCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response myWaitingList(){
        //Response response = null;
        try {
            User myUser = myApplication.getUserLoggedNow().getUserLogged();

            Gson gson = new Gson();
            System.out.println("MY USER NAME "+myUser.getUserName());
            //System.out.println("LISTA DE ESPERA "+myUser.getWaitFriendsList());

            List<String>myWaitingList = myApplication.getWaitingLists().get(myUser.getUserName());

            response.setCode(200);
            response.setMessage("return waiting list");
            response.setContent(gson.toJson(myWaitingList));
        }catch (Exception e){
            response.setCode(400);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response addToWaitingList(String requestedUser){
        //Response response = null;
        try{
            UserToFind userTemp = new Gson().fromJson(requestedUser, UserToFind.class);
            User userReq = userServices.getUserFromListByUserName(userTemp.getUserName());
            User myUser = myApplication.getUserLoggedNow().getUserLogged();
            System.out.println("MY USER STATE "+myUser.getRejections());
            System.out.println("MY USER STATE NOBODY"+myUser.isNobodyLovesYou());
            if(myUser.isNobodyLovesYou()){
                response.setCode(450);
                response.setMessage("NOBODY LOVES YOU");
            }else{
                response = checkIntoLists(myUser, userReq);
            }
        }catch (Exception e){
            response.setCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    private Response checkIntoLists(User myUser, User userReq){
        //Response response = null;
        System.out.println("CHECK INTO LISTS");
        /*if(userReq.getFriendsList() == null){
            System.out.println("INIT LIST");
            userReq.setFriendsList(new ArrayList<>());
        }
        if(userReq.getWaitFriendsList() == null){
            System.out.println("INIT LIST");
            userReq.setWaitFriendsList(new ArrayList<>());
        }*/
        //boolean isFriend = loopListsFriend(myUser.getUserName(), userReq.getFriendsList());
        //boolean isWaiting = loopListsFriend(myUser.getUserName(), userReq.getWaitFriendsList());
        boolean isFriend = checkIsFriend(myUser.getUserName(),userReq.getUserName());
        boolean isWaiting = checkIsWaiting(myUser.getUserName(),userReq.getUserName());
        System.out.println("CHECKED");
        if(!myUser.getUserName().equals(userReq.getUserName())){
            if(!isFriend && !isWaiting){
                //userReq.addWaitingFriend(myUser.getUserName());
                myApplication.addIntoWaitingFriendList(myUser.getUserName(), userReq.getUserName());
                response.setCode(200);
                response.setMessage("Request sent");
            }else if(isFriend){
                response.setCode(400);
                response.setMessage("You are now friends");
            }else if(isWaiting){
                response.setCode(400);
                response.setMessage("You are waiting");
            }
        }else{
            response.setCode(400);
            response.setMessage("you are requesting yourself");
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

    private boolean checkIsFriend(String userLogged, String userToFind){
        List<String>listFr = myApplication.getFriendsLists().get(userLogged);
        return loopListsFriend(userToFind, listFr);
    }
    private boolean checkIsWaiting(String userLogged, String userToFind){
        List<String>listWait = myApplication.getWaitingLists().get(userToFind);
        return loopListsFriend(userLogged, listWait);

    }

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
