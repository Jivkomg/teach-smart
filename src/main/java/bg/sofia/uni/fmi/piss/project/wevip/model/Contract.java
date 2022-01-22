package bg.sofia.uni.fmi.piss.project.wevip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity{

    private String performerId;

    private String courseId;

    private String organizerId;


    public Contract() {
    }

    public Contract(String performerId, String courseId, String organizerId) {
        this.performerId = performerId;
        this.courseId = courseId;
        this.organizerId = organizerId;
    }

    @Column(name = "performer_id", nullable = false)
    public String getPerformerId() {
        return performerId;
    }

    public void setPerformerId(String performerId) {
        this.performerId = performerId;
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
