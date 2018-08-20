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
    public Response resquestFriend(@RequestBody String requestedUser) throws ExceptionManager {
        //return userServices.createNewUserService(jsonUser);
        return new Response(000,"-");
    }
}
