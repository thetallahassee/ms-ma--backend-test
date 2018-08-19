package app.content.controller.login;

import app.content.ExceptionManager;
import app.content.domain.Response;
import app.content.service.login.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    LoginServices loginServices;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response loginUser(@RequestBody String jsonUserParams) throws ExceptionManager {
        //loginServices.checkUserExistance(jsonUserParams);
        return loginServices.createLogin(jsonUserParams);
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Response logOut(@RequestBody String userName) throws ExceptionManager {
        //loginServices.checkUserExistance(jsonUserParams);
        //return loginServices.createLogin(jsonUserParams);

        //loginServices.logOut(userName);
        return new Response(000,"empty");
    }
}
