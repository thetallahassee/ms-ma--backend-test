package app.content.service.user;

import app.content.MyApplication;
import app.content.domain.Response;
import app.content.domain.Staff;
import app.content.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.oracle.tools.packager.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static app.StartApp.myApplication;

@Service("userServices")
public class UserServices {
    public Response createNewUserService(String jsonUser){
        System.out.println("SERVICIO CREAR USUARIO "+jsonUser);
        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);
        myApplication.addUserInUserList(user);

        return new Response(200, "User created ok");
    }
}

