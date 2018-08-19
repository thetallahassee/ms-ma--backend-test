package app.content.controller;

import app.content.ExceptionManager;
import app.content.domain.Response;
import app.content.service.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visibility")
public class VisibilityController {


    /*@CrossOrigin
    @RequestMapping(value = "/{}", method = RequestMethod.POST)
    @ResponseBody
    public Response createNewUser(@RequestBody String jsonUser) throws ExceptionManager {
        System.out.println("ENTRA A CREAR NEW USER "+jsonUser);
        return userServices.createNewUserService(jsonUser);
    }*/
}
