package bg.sofia.uni.fmi.piss.project.tm.models;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    private String name;

    private String type;

    private int duration;

    private long attendants;

    private String description;

    private String posterLocation;

    public Course(String name, String type,
        int duration, long attendants,
        String description, String posterLocation) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.attendants = attendants;
        this.description = description;
        this.posterLocation = posterLocation;
    }

    public Course() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(name = "attendants")
    public long getAttendants() {
        return attendants;
    }

    public void setAttendants(long attendants) {
        this.attendants = attendants;
    }

    @Column(name = "description", length = 10000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "poster_location")
    public String getPosterLocation() {
        return posterLocation;
    }

    public void setPosterLocation(String posterLocation) {
        this.posterLocation = posterLocation;
    }
}
