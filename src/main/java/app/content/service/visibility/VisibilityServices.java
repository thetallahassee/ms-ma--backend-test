package app.content.service.visibility;

import app.content.modal.Visibility;
import org.springframework.stereotype.Service;

import static app.StartApp.myApplication;

@Service("visibilityServices")
public class VisibilityServices {
    public Visibility getVisibilityTypeFromCode(String code){
        System.out.println("ENTRA EN SERVICIO "+code);
        Visibility visResp = null;
        for(Visibility visibilityLoop:myApplication.getVisibilityList()){
            System.out.println("FOR EN VISIBILITY"+visibilityLoop);
            if(visibilityLoop.getCode().equals(code)){
                visResp = visibilityLoop;
            }
        }
        return visResp;
    }
}
