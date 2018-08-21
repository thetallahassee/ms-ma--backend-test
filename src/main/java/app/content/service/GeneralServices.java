package app.content.service;

import app.content.modal.user.User;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static app.StartApp.myApplication;

@Service("generalServices")
public class GeneralServices {

    /**
     * Returns if the user exists
     * @param username
     * @return
     */
    public boolean isExistUser(String username){
        return myApplication.userExists(username);
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean isUserAndPasswd(User user){
        return myApplication.isUserAndPasswd(user);
    }

    /**
     * Mapping from string to object user
     * @param jsonContent
     * @return
     */
    public User mappingUser(String jsonContent){
        System.out.println("MAPPING "+jsonContent);
        Gson gson = new Gson();
        User user = null;
        try{
            user = gson.fromJson(jsonContent, User.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    /**
     * String pattern
     * @param param
     * @return
     */
    public boolean patternOnlyLetterAndNumber(String param){
        System.out.println("ENTRA AL PATTERN");
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(param);
        return matcher.matches();
    }
}
