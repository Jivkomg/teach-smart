package bg.sofia.uni.fmi.piss.project.wevip.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends BaseEntity{

    private String name;

    private String type;

    private LocalDateTime startTime;

    private int durationHours;

    private double ticketPrice;

    private long ticketsSold;

    private LocalDateTime saleEnd;

    private String description;

    private String posterLocation;

    public Event(String name, String type, LocalDateTime startTime,
                 int durationHours, double ticketPrice, long ticketsSold,
                 LocalDateTime saleEnd, String description, String posterLocation) {
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

    @Column(name = "start_time")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Column(name = "duration_hours")
    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    @Column(name = "ticket_price")
    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Column(name = "tickets_sold")
    public long getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(long ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    @Column(name = "sale_end")
    public LocalDateTime getSaleEnd() {
        return saleEnd;
    }

    public void setSaleEnd(LocalDateTime saleEnd) {
        this.saleEnd = saleEnd;
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
