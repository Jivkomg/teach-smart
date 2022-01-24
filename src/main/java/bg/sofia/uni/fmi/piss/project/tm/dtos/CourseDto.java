package bg.sofia.uni.fmi.piss.project.tm.dtos;

public class CourseDto {

    private String courseId;

    private String name;

    private String type;

    private int duration;

    private long attendants;

    private String description;

    private String posterLocation;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getAttendants() {
        return attendants;
    }

    public void setAttendants(long attendants) {
        this.attendants = attendants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterLocation() {
        return posterLocation;
    }

    public void setPosterLocation(String posterLocation) {
        this.posterLocation = posterLocation;
    }
}
