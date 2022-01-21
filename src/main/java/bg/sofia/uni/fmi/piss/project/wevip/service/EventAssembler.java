package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.EventDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventAssembler {


    EventDto toEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setType(event.getType());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setDurationHours(event.getDurationHours());
        eventDto.setTicketPrice(event.getTicketPrice());
        eventDto.setTicketsSold(event.getTicketsSold());
        eventDto.setSaleEnd(event.getSaleEnd());
        eventDto.setDescription(event.getDescription());
        eventDto.setPosterLocation(event.getPosterLocation());

        return eventDto;
    }
}
