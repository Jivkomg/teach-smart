package bg.sofia.uni.fmi.piss.project.wevip.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "duration_hours")
    private int durationHours;

    @Column(name = "ticket_price")
    private double ticketPrice;

    @Column(name = "tickets_sold")
    private long ticketsSold;

    @Column(name = "sale_end")
    private LocalDateTime saleEnd;

    @Column(name = "description")
    private String description;

    @Column(name = "poster_location")
    private String posterLocation;

    public Event(Long id, String name, String type, LocalDateTime startTime,
                 int durationHours, double ticketPrice, long ticketsSold,
                 LocalDateTime saleEnd, String description, String posterLocation) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.durationHours = durationHours;
        this.ticketPrice = ticketPrice;
        this.ticketsSold = ticketsSold;
        this.saleEnd = saleEnd;
        this.description = description;
        this.posterLocation = posterLocation;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public long getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public LocalDateTime getSaleEnd() {
        return saleEnd;
    }

    public void setSaleEnd(LocalDateTime saleEnd) {
        this.saleEnd = saleEnd;
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
