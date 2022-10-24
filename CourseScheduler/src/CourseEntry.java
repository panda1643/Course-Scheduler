/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author advai
 */
public class CourseEntry {
    
    private final String semester;
    private final String coursecode;
    private final String description;
    private final int seats;
    
    public CourseEntry(String semester, String coursecode, String description, int seats) {
        this.semester = semester;
        this.coursecode = coursecode;
        this.description = description;
        this.seats = seats;
    }
    
    public String getSemester() {
        return semester;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getDescription() {
        return description;
    }

    public int getSeats() {
        return seats;
    }
}
