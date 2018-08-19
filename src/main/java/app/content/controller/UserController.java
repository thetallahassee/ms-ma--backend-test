package app.content.controller;

import app.content.service.UserServices;
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
    public void createNewUser(@RequestBody String jsonUser){
        System.out.println("ENTRA A CREAR NEW USER "+jsonUser);
        //userServices.createNewUserService(jsonUser);
    }
}