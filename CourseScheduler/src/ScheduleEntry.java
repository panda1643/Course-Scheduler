
import java.sql.Timestamp;

/**
 *
 * @author advai
 */
public class ScheduleEntry {
    private final String semester;
    private final String coursecode;
    private final String studentid;
    private final String status;
    private final Timestamp timestamp;

    public ScheduleEntry(String semester, String coursecode, String studentid, String status, Timestamp timestamp) {
        this.semester = semester;
        this.coursecode = coursecode;
        this.studentid = studentid;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getSemester() {
        return semester;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
