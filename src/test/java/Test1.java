import app.content.ExceptionManager;
import app.content.modal.Response;
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
    Response response;
    Gson gson = new Gson();

    @Test
    void runAllTest(){
        insertUser();
        insertFourUsers();
        errorsListPrint();
    }

    @Test
    void insertUser(){
        System.out.println("****INSERT USER TEST****");
        Response resp = null;
        String path = "http://localhost:8080/user/new";

        int random = (int)(Math.random()*100);
            try {
                User user = new User("user"+random,"abc");
                String userSenParam = gson.toJson(user);
                resp = sendPost(userSenParam, path);
                if(resp.getCode()==200){
                    System.out.println("User insert OK: "+user.getUserName());
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
        for(int i=0; i<5; i++){
            insertUser();
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
        //User user = new User("prueba", "1234");
        //Gson gson = new Gson();

        //String query = "http://localhost:8080/user/new";
        //String json = "{\"userName\":\"hola\", \"password\":\"1234\"}";

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
        //System.out.println("RESPONSE MONTADA "+response);
        in.close();
        return gson.fromJson(String.valueOf(response), Response.class);

        /*StringBuilder result = new StringBuilder();
        URL url = new URL("http://localhost:8080/user/new");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        String urlParameters = "{\"userName\":\"prueba\",\"password\":\"hola\"}";
        // Send post request
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
        writer.write(urlParameters);
        writer.close();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return result.toString();*/
        //return "HOLIS";
    }
}