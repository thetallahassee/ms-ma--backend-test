package app.content.service;

import app.content.domain.User;
import com.oracle.tools.packager.Log;
import org.springframework.stereotype.Service;

@Service("userServices")
public class UserServices {
    //MyAppContent myAppContent;

    public void createNewUserService(User jsonUser){
        Log.debug("create new user and add to list");
        //User user = new Gson().fromJson(jsonUser, User.class);
        //myAppContent.addUserInUserList(jsonUser);
    }
}

