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
//    User myUser = myApplication.getUserLoggedNow().getUserLogged();
    Response response = new Response(600, "not message");
    @Autowired
    GeneralServices generalServices;

    /**
     * Returns a Response object. Create a Login object checking that there are no conflicts
     * @param jsonUserParams
     * @return
     */
    public Response createLogin(String jsonUserParams){
        int code;
        String message;
        User user = generalServices.mappingUser(jsonUserParams);

        if(myApplication.getUserLoggedNow() == null && myApplication.userExists(user.getUserName())){
            loginToApp(user);
            code = 200;
            message = "User logged OK "+myApplication.getUserLoggedNow().getUserLogged().getUserName();
        }else{
            code = 400;
            message = "Another user is logged or not exist";
        }
        return new Response(code,message);
    }

    /**
     * Assign login to the generic variable
     * @param user
     */
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

    /**
     * Returns a Response object. Assign the null value to the generic variable and
     * check that the requestor is the same one that is logged in
     * @param username
     * @return
     */
    public Response logOut(String username) {
        //Response response = null;
        try{
            User myUser = myApplication.getUserLoggedNow().getUserLogged();
            UserToFind userTemp = new Gson().fromJson(username, UserToFind.class);

            System.out.println("USER LOGGED "+myUser.getUserName());
            System.out.println("USER TO LOGOUT "+userTemp.getUserName());

            if(myUser.getUserName().equals(userTemp.getUserName())){
                myApplication.setUserLoggedNow(null);
                response.setCode(200);
                response.setMessage("LogOut OK "+myUser.getUserName());
            }else{
                response.setCode(400);
                response.setMessage("This user not logged");
            }
        }catch (Exception e){
            response.setCode(400);
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
