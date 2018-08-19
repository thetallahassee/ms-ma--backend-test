package app.content.service.user;

import app.content.domain.Response;
import app.content.domain.user.User;
import app.content.service.GeneralServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static app.StartApp.myApplication;

@Service("userServices")
public class UserServices {
    @Autowired
    GeneralServices generalServices;
    public Response createNewUserService(String jsonUser){
        int code = 0;
        String message = "";
        User user = generalServices.mappingUser(jsonUser);
            if(user != null){
                if(generalServices.patternOnlyLetterAndNumber(user.getUserName()) &&
                        generalServices.patternOnlyLetterAndNumber(user.getPassword())){
                    if(userNameIsUnique(user.getUserName())){
                        user.setRegisterDate(new Date());
                        user.setVisibility(myApplication.getVisibilityTypeFromCode("001"));
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
}

