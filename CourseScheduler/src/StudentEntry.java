/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author advai
 */
public class StudentEntry {
    private final String studentid;
    private final String FirstName;
    private final String LastName;

    public StudentEntry(String studentid, String FirstName, String LastName) {
        this.studentid = studentid;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    
   
    public String getStudentid() {
        return studentid;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
}
