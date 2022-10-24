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
public class CourseQueries {
    private static Connection connection;
    private static ArrayList<String> courseCodes = new ArrayList<String>();
    private static ArrayList<CourseEntry> allCourses = new ArrayList<CourseEntry>();
    private static int seats;
    private static PreparedStatement getAllCourses;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;    
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (semester,coursecode,description,seats) values (?,?,?,?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2,course.getCoursecode());
            addCourse.setString(3,course.getDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<CourseEntry> getAllCourses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> allCourses = new ArrayList<CourseEntry>();
        try
        {
            getAllCourses = connection.prepareStatement("select semester,coursecode,description,seats from app.course where semester = ?");
            getAllCourses.setString(1, semester);
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
               allCourses.add(new CourseEntry(resultSet.getString("semester"),resultSet.getString("coursecode"),resultSet.getString("description"),resultSet.getInt("seats")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return allCourses;
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> allCoursesCodes = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.course where semester = ?");
            getAllCourseCodes.setString(1,semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
               allCoursesCodes.add(resultSet.getString("coursecode"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return allCoursesCodes;
        
    } 
        
    public static int getCourseSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getCourseSeats = connection.prepareStatement("select seats from app.course where semester=? and coursecode = ?");
            getCourseSeats.setString(1,semester);
            getCourseSeats.setString(2,courseCode);
            resultSet = getCourseSeats.executeQuery();
            
            while(resultSet.next())
            {
               seats = (resultSet.getInt("seats"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;      
    }
    
    public static void dropCourse(String semester, String coursecode){
        connection = DBConnection.getConnection();
        try
        {
            dropCourse = connection.prepareStatement("delete from app.course where semester = (?) AND coursecode = (?)");
            dropCourse.setString(1, semester);
            dropCourse.setString(2, coursecode);
            dropCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
