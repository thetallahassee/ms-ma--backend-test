package app.content.controller.firend;

import app.content.ExceptionManager;
import app.content.modal.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @CrossOrigin
    @RequestMapping(value = "/newFriendRequest", method = RequestMethod.POST)
    @ResponseBody
    public Response resquestFriendShip(@RequestBody String objectFriendWait) throws ExceptionManager {
        System.out.println("ENTRA A CREAR NEW USER "+objectFriendWait);
        //return userServices.createNewUserService(jsonUser);
        return new Response(000,"-");
    }
}
