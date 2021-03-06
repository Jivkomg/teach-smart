package bg.sofia.uni.fmi.piss.project.tm.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tutors")
public class Tutor extends BaseEntity {

    private String name;

    private String description;

    public Tutor(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Tutor() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
