package org.example;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    public static final String COUFILE_PATH = "C://project//app//src//course.json";
    static final Gson gson = new Gson();

    public static List<Course> LoadCou(){
        try{
            FileReader reader = new FileReader(COUFILE_PATH);
            Type TypeListCou = new TypeToken<List<Course>>() {}.getType();
            return  gson.fromJson(reader , TypeListCou);
        }catch (Exception e){
            System.out.println("Invalid Fetch"+ e.getMessage());
            return new ArrayList<>();

        }


    }
}
