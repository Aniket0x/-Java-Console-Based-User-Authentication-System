package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class UserService {

    // AT THIS LINE WE ARE INITIALIZE THE PATH OF OUR JSON FILE TO THE "FILE_PATH" VARIABLE TO CREATE THE LIST

    private static final String FILE_PATH = "C:\\project\\app\\src\\main\\java\\org\\example\\User.json";
    private static final Gson gson = new Gson();


    // AT THIS BLOCK WE ARE CREATE THE LIST TO ACCESS IT , WE ARE USER THE IO FILE , FOR READ AND WRITE

    public static List<User> loaduser() {
        // WE CREATE THE  EXCEPTION  , IF ANY ERROR OCCURS WHEN ACCESS THE FILE THE IT WILL HANDLED BY FILE NOT FOUND EXCEPTION
        try{

            FileReader reader =  new FileReader(FILE_PATH);
            Type userListtype = new TypeToken<List<User>>() {}.getType();
            return gson.fromJson(reader , userListtype);

        }catch (FileNotFoundException e){
            System.out.println("File not found"+ e.getMessage());
        }

        return null;

    }

    public void savedUser(List<User> users ){
        // EXCEPTION FOR , IF ANY ERROR OCCURRED DURING THE SAVED DATA THE IT WILL BE HANDLE
        try{
            FileWriter writer  = new FileWriter(FILE_PATH);
            gson.toJson(users , writer);


        }catch (Exception e){
            System.out.println("Unable To Saved" + e.getMessage());
        }
    }

    public static User authtication(String UserId , String Hashpass){
          List<User>  users = loaduser();
        if(users == null) return null;

        for (User user : users) {
                if (user.UserId.equals(UserId) && user.Hashpass.equals(Hashpass)) {
                    return user;
                }
        }

        return null;
    }




}



