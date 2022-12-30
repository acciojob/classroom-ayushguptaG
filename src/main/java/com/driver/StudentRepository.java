package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentMap;
    private HashMap<String,Teacher> teacherMap;
    private HashMap<String, List<String>> studentTeacherMap;

    public StudentRepository(){
        this.studentMap= new HashMap<>();
        this.teacherMap= new HashMap<>();
        this.studentTeacherMap= new HashMap<>();
    }

    public void addStudent(Student student){
       String name=  student.getName();
       studentMap.put(name, student);
    }
    public void addTeacher(Teacher teacher) {
        String name= teacher.getName();
        teacherMap.put(name, teacher);
    }
    public Student getStudentByName(String name){

        return studentMap.getOrDefault(name, null);
    }
    public Teacher getTeacherByName(String name){

        return teacherMap.getOrDefault(name, null);
    }
    public void addStudentTeacherPair(String studentName, String teacherName){

        // if student list is not mapped to current teacher
        if(!studentTeacherMap.containsKey(teacherName)){
            List<String> students= new ArrayList<>();
            studentTeacherMap.put(teacherName, students);
        }

        //adding student to the list
        studentTeacherMap.get(teacherName).add(studentName);
    }
    public List<String> getStudentsByTeacherName(String name){

        return studentTeacherMap.getOrDefault(name, null);
    }
    public List<String> getAllStudents(){

        List<String> allStudents= new ArrayList<>();
        for(String name : studentMap.keySet()){
            allStudents.add(name);
        }
        return allStudents;
    }
    public void deleteTeacherByName(String teacherName){

        // deleting students of respective teachers from studentMap
         for(String studentName : studentTeacherMap.get(teacherName)){

             studentMap.remove(studentName);
         }

         //deleting teacher from teacherMap & student-teacherMap
         teacherMap.remove(teacherName);
         studentTeacherMap.remove(teacherName);
    }
    public void deleteAllTeachers(){

        // deleting students that are allotted teachers from studentMap
        for(List<String> students : studentTeacherMap.values()){
            for(String studentName : students){
                studentMap.remove(studentName);
            }
        }

        //deleting all teachers from teacherMap & student-teacherMap
        teacherMap.clear();
        studentTeacherMap.clear();
    }
}
