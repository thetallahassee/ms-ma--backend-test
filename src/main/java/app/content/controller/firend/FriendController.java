package app.content.controller.firend;

import app.content.ExceptionManager;
import app.content.modal.Response;
import app.content.service.friend.FriendServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    FriendServices friendServices;

    @CrossOrigin
    @RequestMapping(value = "/newFriendRequest", method = RequestMethod.POST)
    @ResponseBody
    public Response newFriendRequest(@RequestBody String requestedUserName) throws ExceptionManager {//{"userName":"xxx"}
        return friendServices.addToWaitingList(requestedUserName);
    }

    @CrossOrigin
    @RequestMapping(value = "/declineRquest", method = RequestMethod.POST)//sirve para cargarse amigos o solicitudes
    @ResponseBody
    public Response declineRquest(@RequestBody String requestedUserName) throws ExceptionManager {//{"userName":"xxx"}
        return friendServices.declineFriendResponse(requestedUserName);
    }

    @CrossOrigin
    @RequestMapping(value = "/acceptFriend", method = RequestMethod.POST)
    @ResponseBody
    public Response acceptFriend(@RequestBody String requestedUserName) throws ExceptionManager {//{"userName":"xxx"}
        return friendServices.acceptNewFriend(requestedUserName);
    }

    @CrossOrigin
    @RequestMapping(value = "/myWaitingList", method = RequestMethod.GET)
    @ResponseBody
    public Response myWaitingList() throws ExceptionManager {
        return friendServices.myWaitingList();
    }

    @CrossOrigin
    @RequestMapping(value = "/myFriendsList", method = RequestMethod.GET)
    @ResponseBody
    public Response myFriendsList() throws ExceptionManager {
        return friendServices.myFriendsList();
    }
}
