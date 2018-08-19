package app.content.service.user;

import app.content.ExceptionManager;
import app.content.MyApplication;
import app.content.domain.Response;
import app.content.domain.Staff;
import app.content.domain.User;
import app.content.service.GeneralServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.oracle.tools.packager.Log;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
                    myApplication.addUserInUserList(user);
                    code = 200;
                    message = "User"+user.getUserName()+" created OK";
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
}

