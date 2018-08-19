package app;

import app.content.MyApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApp {
    public static MyApplication myApplication = new MyApplication();


    public static void main(String[] args) {

        SpringApplication.run(StartApp.class, args);
        //myApplication.initUsertList();
        myApplication.initDefaultParams();
    }
}
