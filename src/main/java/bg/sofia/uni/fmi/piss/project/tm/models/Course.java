package bg.sofia.uni.fmi.piss.project.tm.models;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    private String name;

    private String type;

    private int durationHours;

    private long attendants;

    private String description;

    private String posterLocation;

    public Course(String name, String type,
        int durationHours, long attendants,
        String description, String posterLocation) {
        this.name = name;
        this.type = type;
        this.durationHours = durationHours;
        this.attendants = attendants;
        this.description = description;
        this.posterLocation = posterLocation;
    }

    public Course() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Column(name = "duration_hours")
    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    @Column(name = "attendants")
    public long getAttendants() {
        return attendants;
    }

    public void setAttendants(long attendants) {
        this.attendants = attendants;
    }

    @Column(name = "description")
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
