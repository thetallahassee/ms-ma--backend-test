package app.content.service.user;

import app.content.modal.Response;
import app.content.modal.user.User;
import app.content.service.GeneralServices;
import app.content.service.visibility.VisibilityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static app.StartApp.myApplication;

@Service("userServices")
public class UserServices {
    @Autowired
    GeneralServices generalServices;
    @Autowired
    VisibilityServices visibilityServices;

    /**
     * Returns a Response object. Create a new user and assign it to the list. Check that the name and password meet the pattern
     * @param jsonUser
     * @return
     */
    public Response createNewUserService(String jsonUser){
        int code = 0;
        String message = "";
        User user = generalServices.mappingUser(jsonUser);
            if(user != null){
                if(generalServices.patternOnlyLetterAndNumber(user.getUserName()) &&
                        generalServices.patternOnlyLetterAndNumber(user.getPassword())){
                    if(userNameIsUnique(user.getUserName())){
                        user.setRegisterDate(new Date());
                        user.setVisibility(visibilityServices.getVisibilityTypeFromCode("001"));

                        Map<String,List<String>> listFriends = myApplication.getFriendsLists();
                        listFriends.put(user.getUserName(), new ArrayList<>());

                        Map<String,List<String>> listsWaiting = myApplication.getWaitingLists();
                        listsWaiting.put(user.getUserName(), new ArrayList<>());
                        /*user.setWaitFriendsList(new ArrayList<>());
                        user.setFriendsList(new ArrayList<>());*/
                        myApplication.addUserInUserList(user);
                        code = 200;
                        message = "User"+user.getUserName()+" created OK";
                    }else{
                        code = 400;
                        message = "User Name is not unique";
                    }
                }else{
                    code = 400;
                    message = "the pattern is not met";
                }
            }else{
                code = 400;
                message = "it is not possible to create the userUser";
            }
        return new Response(code, message);
    }

    /**
     * Returns if there are matches with the name
     * @param username
     * @return
     */
    private boolean userNameIsUnique(String username){
        boolean unique = true;
        for(User userLoop : myApplication.getUserList()){
            if(userLoop.getUserName().equals(username)){
                unique = false;
                break;
            }
        }
        return unique;
    }

    /*public User modifyParamsFromUser(String userParams){
        User userPar = generalServices.mappingUser(userParams);
        if(userPar.getUserName().equals(myApplication.getUserLoggedNow().getUserLogged().getUserName())){

        }
    }*/

    /**
     * Update the user's visibility type parameter
     * @param userParStr
     * @return
     */
    public Response updateUserParamsVisibility(String userParStr){
        User userNewParams = generalServices.mappingUser(userParStr);
        Response response = new Response(400, "Error to modify visibility or user not found");
        if(userNewParams.getUserName().equals(myApplication.getUserLoggedNow().getUserLogged().getUserName())) {
            for (User user : myApplication.getUserList()) {
                if (user.getUserName().equals(userNewParams.getUserName())) {
                    user.setVisibility(visibilityServices.getVisibilityTypeFromCode(userNewParams.getVisibility().getCode()));
                    response.setCode(200);
                    response.setMessage("Visibility updated OK");
                }
            }
        }
        return response;
    }

    /**
     * Returns the corresponding user to the name
     * @param userNameReq
     * @return
     */
    public User getUserFromListByUserName(String userNameReq){
        User userReq = null;
        for(User user:myApplication.getUserList()){
            if(user.getUserName().equals(userNameReq)){
                userReq = user;
            }
        }
        return userReq;
    }
}

