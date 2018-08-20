package app.content.service;

import app.content.modal.Visibility;
import org.springframework.stereotype.Service;

import static app.StartApp.myApplication;

@Service("visibilityServices")
public class VisibilityServices {
    public Visibility getVisibilityTypeFromCode(String code){
        Visibility visResp = null;
        for(Visibility visibilityLoop:myApplication.visibilityList){
            if(visibilityLoop.getCode().equals(code)){
                visResp = visibilityLoop;
            }
        }
        return visResp;
    }
}
