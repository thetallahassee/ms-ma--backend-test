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

    /**
     *Create a new friend request action, return a Response with the result
     * @param requestedUserName
     * @return response
     * @throws ExceptionManager
     */
    @CrossOrigin
    @RequestMapping(value = "/newFriendRequest", method = RequestMethod.POST)
    @ResponseBody
    public Response newFriendRequest(@RequestBody String requestedUserName) throws ExceptionManager {//{"userName":"xxx"}
        return friendServices.addToWaitingList(requestedUserName);
    }

    /**
     *
     Create a new request rejection action, return a Response with the result
     * @param requestedUserName
     * @return response
     * @throws ExceptionManager
     */
    @CrossOrigin
    @RequestMapping(value = "/declineRquest", method = RequestMethod.POST)//sirve para cargarse amigos o solicitudes
    @ResponseBody
    public Response declineRquest(@RequestBody String requestedUserName) throws ExceptionManager {//{"userName":"xxx"}
        return friendServices.declineFriendResponse(requestedUserName);
    }

    /**
     * Create a new action to add the requesting friend, return a Response with the result
     * @param requestedUserName
     * @return response
     * @throws ExceptionManager
     */
    @CrossOrigin
    @RequestMapping(value = "/acceptFriend", method = RequestMethod.POST)
    @ResponseBody
    public Response acceptFriend(@RequestBody String requestedUserName) throws ExceptionManager {//{"userName":"xxx"}
        return friendServices.acceptNewFriend(requestedUserName);
    }

    /**
     * Returns the list of pending requests of the registered user
     * @return response
     * @throws ExceptionManager
     */
    @CrossOrigin
    @RequestMapping(value = "/myWaitingList", method = RequestMethod.GET)
    @ResponseBody
    public Response myWaitingList() throws ExceptionManager {
        return friendServices.myWaitingList();
    }

    /**
     * Returns the list of friends of the registered user
     * @return response
     * @throws ExceptionManager
     */
    @CrossOrigin
    @RequestMapping(value = "/myFriendsList", method = RequestMethod.GET)
    @ResponseBody
    public Response myFriendsList() throws ExceptionManager {
        return friendServices.myFriendsList();
    }
}
