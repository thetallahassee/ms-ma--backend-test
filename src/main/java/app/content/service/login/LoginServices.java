package app.content.service.login;

import app.content.modal.Response;
import app.content.modal.login.Login;
import app.content.modal.user.User;
import app.content.service.GeneralServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static app.StartApp.myApplication;

@Service("loginServices")
public class LoginServices {
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

    public void logOut() {
        myApplication.setUserLoggedNow(null);
    }
}
