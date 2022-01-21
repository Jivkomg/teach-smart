package bg.sofia.uni.fmi.piss.project.wevip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity{

    private String performerId;

    private String eventId;

    private String organizerId;


    public Contract() {
    }

    public Contract(String performerId, String eventId, String organizerId) {
        this.performerId = performerId;
        this.eventId = eventId;
        this.organizerId = organizerId;
    }

    @Column(name = "performer_id", nullable = false)
    public String getPerformerId() {
        return performerId;
    }

    public void setPerformerId(String performerId) {
        this.performerId = performerId;
    }

    @Column(name = "event_id", nullable = false)
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Column(name = "organizer_id", nullable = false)
    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }
}
