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

    /**
     * Create new user
     * @param jsonUser
     * @return
     * @throws ExceptionManager
     */
    @CrossOrigin
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Response createNewUser(@RequestBody String jsonUser) throws ExceptionManager {
        System.out.println("ENTRA A CREAR NEW USER "+jsonUser);
        return userServices.createNewUserService(jsonUser);
    }

    /**
     * Update user params
     * @param jsonUser
     * @return
     * @throws ExceptionManager
     */
    @CrossOrigin
    @RequestMapping(value = "/new", method = RequestMethod.PUT)
    @ResponseBody
    public Response modifyUser(@RequestBody String jsonUser) throws ExceptionManager {
        System.out.println("ENTRA A CREAR NEW USER "+jsonUser);
        return userServices.updateUserParamsVisibility(jsonUser);
    }
}