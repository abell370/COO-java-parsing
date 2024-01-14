package hepl.masi.principsecu.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Authenticator {
    public static boolean isAuthenticate(String login, String password) throws NoSuchAlgorithmException {
        InputStream inputStream = Authenticator.class.getClassLoader().getResourceAsStream("users.json");
        boolean find = false;
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            JSONTokener tokener = new JSONTokener(bufferedReader);
            JSONArray users = new JSONArray(tokener);
            for(int i = 0; i < users.length(); i++) {
                JSONObject user = (JSONObject) users.get(i);
                String storedSalt = (String) user.get("salt");
                String passwordHashed = SecurityUtils.getSecurePassword(password, Base64.getDecoder().decode(storedSalt));
                if (user.get("login").equals(login) && user.get("password").equals(passwordHashed)) {
                    find = true;
                    break;
                }
            }
        }
        return find;
    }
}
