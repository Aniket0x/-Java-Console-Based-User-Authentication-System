package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private static final String STUDFILE_PATH = "C://project//app//src//Student.json" ;
    private static final String COURSEFILE_PATH = "C://project//app//src//course.json" ;
    private static final String ENROLLFILE_PATH = "C://project//app//src//enrollment.json" ;
    static final Gson gson = new Gson();

    public static List<Student> Loadstud(){
        try{
            FileReader reader = new FileReader(STUDFILE_PATH);
            Type StudListType = new TypeToken<List<Student>>() {}.getType();
            return  gson.fromJson(reader, StudListType);
        }catch(Exception e){

            System.out.println("File Not Found" + e.getMessage());
        }
        return null;
    }

    public void svaedStud(List<Student> stud){
        try{
            FileWriter writer  = new FileWriter(STUDFILE_PATH);
            gson.toJson(stud , writer);
        }catch (IOException e){
            System.out.println("Unable to Saved "+ e.getMessage());
        }
    }

    public static void registerService(Student newstudents) throws IOException {
        List<Student> students = Loadstud();

        if( students == null){
            students = new ArrayList<>();

        }students.add(newstudents);

        try(FileWriter writer = new FileWriter(STUDFILE_PATH)){
            gson.toJson(students , writer);
            System.out.println("*** Registration Successfully *** ");

        }catch(IOException e){
            System.out.println("Unable to Registered"+e.getMessage());

        }



    }

    public static Student auth(String email, String password) {
        List<Student> students = Loadstud(); // Load from JSON

        if (students != null) {
            for (Student stud : students) {
                if (stud.email != null && stud.password != null) {
                    if (stud.email.equals(email) && stud.password.equals(password)) {
                        return stud;
                    }
                }
            }
        }

        return null; // if no match found
    }

    public static void saveEnroll(Enrollments newEnrollment) throws IOException {
        List<Enrollments> enrollment = loadEnrollments();
        enrollment.add(newEnrollment);

        try(FileWriter writer = new FileWriter(ENROLLFILE_PATH );){
            new Gson().toJson(enrollment , writer);
            System.out.println("Enroll Successfully");
        }catch (IOException e){
            System.out.println("Error to Enroll !"+e.getMessage());
        }

    }

    private static List<Enrollments> loadEnrollments() {
        try {
            FileReader reader = new FileReader(ENROLLFILE_PATH);
            Type FileListType = new TypeToken<List<Enrollments>>() {
            }.getType();
            return gson.fromJson(reader, FileListType);
        }catch (IOException e){
            System.out.println("FILE NOT FOUND " +e.getMessage());
            return  new  ArrayList<>();
        }
    }





}
