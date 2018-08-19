package app.content.service;

import app.content.domain.User;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("generalServices")
public class GeneralServices {
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

    public boolean patternOnlyLetterAndNumber(String param){
        System.out.println("ENTRA AL PATTERN");
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(param);
        return matcher.matches();
    }
}
