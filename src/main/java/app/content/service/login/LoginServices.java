package app.content.service.login;

import app.content.modal.Response;
import app.content.modal.friendly.UserToFind;
import app.content.modal.login.Login;
import app.content.modal.user.User;
import app.content.service.GeneralServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static app.StartApp.myApplication;

@Service("loginServices")
public class LoginServices {
    User myUser = myApplication.getUserLoggedNow().getUserLogged();
    @Autowired
    GeneralServices generalServices;

    public Response createLogin(String jsonUserParams){
        int code;
        String message;
        User user = generalServices.mappingUser(jsonUserParams);

        if(myApplication.getUserLoggedNow() == null){
            loginToApp(user);
            code = 200;
            message = "User logged OK";
        }else{
            code = 400;
            message = "Another user is logged";
        }
        return new Response(code,message);
    }

    private void loginToApp(User user) {
        Login login = new Login(user);
        myApplication.setUserLoggedNow(login);
    }

    private boolean checkUserPasswd(User userToLogin){
        return generalServices.isUserAndPasswd(userToLogin);
    }
    private boolean checkUserExistance(String username){
        return generalServices.isExistUser(username);
    }

    public Response logOut(String username) {
        Response response = null;
        UserToFind userTemp = new Gson().fromJson(username, UserToFind.class);
        if(myUser.getUserName().equals(userTemp)){
            myApplication.setUserLoggedNow(null);
            response.setCode(200);
            response.setMessage("LogOut OK");
        }else{
            response.setCode(400);
            response.setMessage("This user not logged");
        }
        return response;
    }
}
