package bg.sofia.uni.fmi.piss.project.tm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity{

    private String tutorId;

    private String courseId;

    private String organizerId;


    public Contract() {
    }

    public Contract(String tutorId, String courseId, String organizerId) {
        this.tutorId = tutorId;
        this.courseId = courseId;
        this.organizerId = organizerId;
    }

    @Column(name = "tutor_id", nullable = false)
    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    @Column(name = "course_id", nullable = false)
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Column(name = "organizer_id", nullable = false)
    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }
}
