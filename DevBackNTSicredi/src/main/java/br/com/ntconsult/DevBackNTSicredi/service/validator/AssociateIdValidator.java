package br.com.ntconsult.DevBackNTSicredi.service.validator;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AssociateIdValidator {

    private String readRequestContent(HttpURLConnection con) throws IOException {
        StringBuilder result = new StringBuilder();

        InputStream in = new BufferedInputStream(con.getInputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        JSONObject json = new JSONObject(result.toString());
        return (String) json.get("status");
    }

    public boolean validateAssociateId(String id) {
        try {
            URL obj = new URL("https://user-info.herokuapp.com/users/" + id);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            if(con.getResponseCode() == 200){
                String status = readRequestContent(con);
                con.disconnect();
                return status.equals("ABLE_TO_VOTE");
            }else{
                con.disconnect();
                return false;
            }
        } catch (IOException e){
            return false;
        }
    }
}
