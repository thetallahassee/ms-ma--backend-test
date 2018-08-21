import app.content.ExceptionManager;
import app.content.modal.Response;
import app.content.modal.friendly.UserToFind;
import app.content.modal.user.User;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


class Test1 {
    List<Response> errorList = new ArrayList<>();
    Response response = new Response(600, "empty");
    Gson gson = new Gson();
    List<User> userList = new ArrayList<>();
    User userToLog;
    String nameGen = "user1";
    String passwdGen = "abc";
    @Test
    void runAllTest(){
        /*insertUser();
        insertFourUsers();
        userToLog = userList.get(0);
        login();
        logOut();
        login();


        errorsListPrint();*/
    }

    /*@Test
    void testInsertUser(){
        insertUser();
        errorsListPrint();
    }*/

    /**
     * BLOCK CREATE USERS
     */
    @Test
    void testInsertFourUsers(){
        insertFourUsers();
        //userToLog = userList.get(0);
        errorsListPrint();
    }

    /**
     * BLOCK LOGIN USER
     */
    @Test
    void testLoginUser1(){
        loginWithUser("user1", passwdGen);
        errorsListPrint();
    }
    @Test
    void testLoginUser2(){
        loginWithUser("user2", passwdGen);
        errorsListPrint();
    }
    @Test
    void testLoginUser3(){
        loginWithUser("user3", passwdGen);
        errorsListPrint();
    }
    @Test
    void testLoginUser4(){
        loginWithUser("user4", passwdGen);
        errorsListPrint();
    }


    /**
     * BLOCK LOGOUT USER
     */
    @Test
    void testLogOutUser1(){
        logOutWithUser("user1");
        errorsListPrint();
    }

    @Test
    void testLogOutUser2(){
        logOutWithUser("user2");
        errorsListPrint();
    }

    @Test
    void testLogOutUser3(){
        logOutWithUser("user3");
        errorsListPrint();
    }
    @Test
    void testLogOutUser4(){
        logOutWithUser("user4");
        errorsListPrint();
    }

    /**
     * BLOCK GET MY WAITING LIST
     */
    @Test
    void testMyWaitingList(){
        myWaitingFriendsList();
        errorsListPrint();
    }

    /**
     * BLOCK GET MY FRIENDS
     */
    @Test
    void testMyFriends(){
        myFriendsList();
        errorsListPrint();
    }

    /**
     * BLOCK FRIEND REQUEST
     */
    @Test
    void user1NewFriendRequest(){
        friendRequest("user1");
        errorsListPrint();
    }

    @Test
    void user2NewFriendRequest(){
        friendRequest("user2");
        errorsListPrint();
    }

    @Test
    void user3NewFriendRequest(){
        friendRequest("user3");
        errorsListPrint();
    }

    @Test
    void user4NewFriendRequest(){
        friendRequest("user4");
        errorsListPrint();
    }

    /**
     * BLOCK DECLINE FRIEND REQUEST
     */
    @Test
    void user1DeclineRequest(){
        declineRequest("user1");
        errorsListPrint();
    }
    @Test
    void user2DeclineRequest(){
        declineRequest("user2");
        errorsListPrint();
    }
    @Test
    void user3DeclineRequest(){
        declineRequest("user3");
        errorsListPrint();
    }
    @Test
    void user4DeclineRequest(){
        declineRequest("user4");
        errorsListPrint();
    }

    /**
     * BLOCK ACCEPT NEW FRIEND
     */
    @Test
    void user1AcceptFriend(){
        acceptFriend("user1");
        errorsListPrint();
    }
    @Test
    void user2AcceptFriend(){
        acceptFriend("user2");
        errorsListPrint();
    }
    @Test
    void user3AcceptFriend(){
        acceptFriend("user3");
        errorsListPrint();
    }
    @Test
    void user4AcceptFriend(){
        acceptFriend("user4");
        errorsListPrint();
    }


    /**
     *
     * FUNCTIONS
     */
    void acceptFriend(String username){
        String path = "http://localhost:8080/friend/acceptFriend";
        try{
            UserToFind userToFind = new UserToFind(username);
            String paramSend = gson.toJson(userToFind);
            response = sendPost(paramSend, path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }


    void declineRequest(String username){
        String path = "http://localhost:8080/friend/declineRquest";
        try{
            UserToFind userToFind = new UserToFind(username);
            String paramSend = gson.toJson(userToFind);
            response = sendPost(paramSend, path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }


    void friendRequest(String username){
        String path = "http://localhost:8080/friend/newFriendRequest";
        try{
            UserToFind userToFind = new UserToFind(username);
            String paramSend = gson.toJson(userToFind);
            response = sendPost(paramSend, path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }

    private void myFriendsList() {
        String path = "http://localhost:8080/friend/myFriendsList";
        try {
            response = sendGet(path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
                System.out.println("CONTENT: "+response.getContent());
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }


    void myWaitingFriendsList(){
        String path = "http://localhost:8080/friend/myWaitingList";
        try {
            response = sendGet(path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
                System.out.println("CONTENT: "+response.getContent());
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }



    void loginWithUser(String userName, String passwd){

        String path = "http://localhost:8080/auth/login";
        try {
            String userToLogin = gson.toJson(new User(userName,passwd));
            response = sendPost(userToLogin, path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }

    void login(){

        String path = "http://localhost:8080/auth/login";
        try {
            String userToLogin = gson.toJson(new User(nameGen,passwdGen));
            response = sendPost(userToLogin, path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }


    void logOut(){
        //String userToLogOut = gson.toJson(userToLog);
        String path = "http://localhost:8080/auth/logout";
        try {
            String userToLogOut = gson.toJson(new UserToFind("user1"));
            response = sendPost(userToLogOut, path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
                userToLog = null;
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }

    private void logOutWithUser(String userName) {
        String path = "http://localhost:8080/auth/logout";
        try {
            String userToLogOut = gson.toJson(new UserToFind(userName));
            response = sendPost(userToLogOut, path);
            if(response.getCode()==200){
                System.out.println("OK: "+response.getMessage());
                userToLog = null;
            }else{
                errorList.add(response);
                System.err.println("BAD: "+response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(410);
            response.setMessage(e.getMessage());
            errorList.add(response);
        }
    }


    void insertUser(){
        System.out.println("****INSERT USER TEST****");
        Response resp = null;
        String path = "http://localhost:8080/user/new";

        //int random = (int)(Math.random()*100);
            try {
                User user = new User(nameGen,passwdGen);
                String userSenParam = gson.toJson(user);
                resp = sendPost(userSenParam, path);
                if(resp.getCode()==200){
                    System.out.println("User insert OK: "+user.getUserName());
                    userList.add(user);
                }else{
                    errorList.add(resp);
                    System.err.println("Error to insert"+user.getUserName());
                }
            } catch (ExceptionManager exceptionManager) {
                exceptionManager.printStackTrace();
                response.setCode(410);
                response.setMessage(exceptionManager.getMessage());
                errorList.add(response);
            } catch (Exception e) {
                e.printStackTrace();
                response.setCode(410);
                response.setMessage(e.getMessage());
                errorList.add(response);
            }
    }

    private void insertFourUsers(){
        for(int i=1; i<5; i++){
            Response resp = null;
            String path = "http://localhost:8080/user/new";

            int random = (int)(Math.random()*100);
            try {
                User user = new User("user"+i,"abc");
                String userSenParam = gson.toJson(user);
                resp = sendPost(userSenParam, path);
                if(resp.getCode()==200){
                    System.out.println("User insert OK: "+user.getUserName());
                    userList.add(user);
                }else{
                    errorList.add(resp);
                    System.err.println("Error to insert"+user.getUserName());
                }
            } catch (ExceptionManager exceptionManager) {
                exceptionManager.printStackTrace();
                response.setCode(410);
                response.setMessage(exceptionManager.getMessage());
                errorList.add(response);
            } catch (Exception e) {
                e.printStackTrace();
                response.setCode(410);
                response.setMessage(e.getMessage());
                errorList.add(response);
            }
        }
    }
    private void errorsListPrint(){
        //int errorSize = errorList.size();
        if(errorList.isEmpty()){
            System.out.println("NO ERRORS, it's all OK");
        }else{
            for(Response response : errorList){
                System.err.println("ERROR code:"+response.getCode()+" message:"+response.getMessage());
            }
        }
    }

    private Response sendPost(String jsonParams, String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        OutputStream os = conn.getOutputStream();
        os.write(jsonParams.getBytes("UTF-8"));
        os.close();

        // read the response
        StringBuilder result = new StringBuilder();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            //System.out.println("RECORRE RESPUESTA "+inputLine);
            response.append(inputLine);
        }
        in.close();
        return gson.fromJson(String.valueOf(response), Response.class);
    }

    private String sendPut(String urlToRead) throws Exception{
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    private Response sendGet(String urlToRead) throws Exception{
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
        }
        rd.close();
        return gson.fromJson(String.valueOf(response), Response.class);
    }
}