/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author advai
 */
public class StudentQueries {
    private static Connection connection;
    private static ArrayList<StudentEntry> student = new ArrayList<StudentEntry>();
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (studentid, FirstName, LastName) values (?,?,?)");
            addStudent.setString(1, student.getStudentid());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> AllStudents = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("select studentid, FirstName, LastName from app.student");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                AllStudents.add(new StudentEntry(resultSet.getString("studentid"),resultSet.getString("FirstName"),resultSet.getString("LastName") ));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return AllStudents;       
    }
    
    public static StudentEntry getStudent(String studentid){
        connection = DBConnection.getConnection();
        StudentEntry student = new StudentEntry(studentid,"","");
        try
        {
            getStudent = connection.prepareStatement("select studentid,FirstName,LastName from app.student where studentid = (?)");
            getStudent.setString(1, studentid);
            resultSet = getStudent.executeQuery();
            
            while(resultSet.next())
            {
                student = new StudentEntry(resultSet.getString("studentid"), resultSet.getString("FirstName"), resultSet.getString("LastName"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    
    public static void dropStudent (String studentid){
        connection = DBConnection.getConnection();
        try
        {
            dropStudent = connection.prepareStatement("delete from app.student where studentid = (?)");
            dropStudent.setString(1, studentid);
            dropStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
