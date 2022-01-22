package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.EventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

public interface EventService {

    ResponseEntity<List<EventDto>> getAllEvents();

    ResponseEntity<List<EventDto>> getTop30Events();

    ResponseEntity<EventDto> getEventById(String id);

    ResponseEntity getPoster(@PathVariable String eventId);

    ResponseEntity getEventOrganizersById(String eventId);

    ResponseEntity getEventPerformersById(String eventId);
}
