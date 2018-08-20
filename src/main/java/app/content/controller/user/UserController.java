package app.content.controller.user;

import app.content.ExceptionManager;
import app.content.modal.Response;
import app.content.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @CrossOrigin
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Response createNewUser(@RequestBody String jsonUser) throws ExceptionManager {
        System.out.println("ENTRA A CREAR NEW USER "+jsonUser);
        return userServices.createNewUserService(jsonUser);
    }
}