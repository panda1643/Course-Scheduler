
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author advai
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<ScheduleEntry> entry = new ArrayList<ScheduleEntry>();
    private static int ScheduledStudentCount;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement getCoursesByStudentID;
    private static ResultSet resultSet; 
    
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, coursecode, studentid, status,timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getCoursecode());
            addScheduleEntry.setString(3, entry.getStudentid());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentid)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedulestudent = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select semester,coursecode,studentid,status,timestamp from app.schedule where semester = (?) and studentid = (?)");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentid);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                schedulestudent.add(new ScheduleEntry(resultSet.getString("semester"),resultSet.getString("coursecode"), resultSet.getString("studentid"),resultSet.getString("status"),resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedulestudent; 
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int SchedueledStudents = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(studentid) from app.schedule where semester = (?) and coursecode = (?)");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
               SchedueledStudents = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return SchedueledStudents;        
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String coursecode){
    
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduledStudentsByCourse = connection.prepareStatement("select semester,coursecode,studentid,status,timestamp from app.schedule where semester = (?) and coursecode = (?) and status = (?)");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, coursecode);
            getScheduledStudentsByCourse.setString(3, "s");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString("semester"),resultSet.getString("coursecode"), resultSet.getString("studentid"),resultSet.getString("status"),resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;    
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String coursecode){
    
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlistedStudentsByCourse = connection.prepareStatement("select semester,coursecode,studentid,status,timestamp from app.schedule where semester = (?) and coursecode = (?) and status = (?)");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, coursecode);
            getWaitlistedStudentsByCourse.setString(3, "w");
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString("semester"),resultSet.getString("coursecode"), resultSet.getString("studentid"),resultSet.getString("status"),resultSet.getTimestamp("timestamp")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;    
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentid, String coursecode){
        connection = DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = (?) and studentid = (?) AND coursecode = (?)");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, studentid);
            dropStudentScheduleByCourse.setString(3, coursecode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    } 
    
    public static void dropScheduleByCourse(String semester, String coursecode){
        connection = DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = (?) AND coursecode = (?)");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, coursecode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void updateScheduleEntry(String semester, ScheduleEntry Entry){
        connection = DBConnection.getConnection();
        
        if(Entry.getStatus().equals("w")){
            dropStudentScheduleByCourse(semester, Entry.getStudentid(), Entry.getCoursecode());
            ScheduleEntry E = new ScheduleEntry(semester,Entry.getCoursecode(), Entry.getStudentid(), "s", Entry.getTimestamp());
            addScheduleEntry(E);        
        }       
    }
    
    public static ArrayList<String> getCoursesByStudentID(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> allCourses = new ArrayList<String>();
        try
        {
            getCoursesByStudentID = connection.prepareStatement("select coursecode from app.schedule where semester = ? and studentid = ? ");
            getCoursesByStudentID.setString(1, semester);
            getCoursesByStudentID.setString(2, studentID);
            resultSet = getCoursesByStudentID.executeQuery();
            
            while(resultSet.next())
            {
               allCourses.add(resultSet.getString("coursecode"));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return allCourses;        
    }
}
