package bg.sofia.uni.fmi.piss.project.wevip.service;

import bg.sofia.uni.fmi.piss.project.wevip.dto.EventDto;
import bg.sofia.uni.fmi.piss.project.wevip.dto.ImageDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.Contract;
import bg.sofia.uni.fmi.piss.project.wevip.model.Event;
import bg.sofia.uni.fmi.piss.project.wevip.model.Organizer;
import bg.sofia.uni.fmi.piss.project.wevip.model.Performer;
import bg.sofia.uni.fmi.piss.project.wevip.repository.ContractRepository;
import bg.sofia.uni.fmi.piss.project.wevip.repository.EventRepository;
import bg.sofia.uni.fmi.piss.project.wevip.repository.OrganizerRepository;
import bg.sofia.uni.fmi.piss.project.wevip.repository.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private PerformerRepository performerRepository;

    @Autowired
    private EventAssembler eventAssembler;

    @Autowired
    private OrganizerAssembler organizerAssembler;

    @Autowired
    private PerformerAssembler performerAssembler;

    @Override
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events
                .stream()
                .map(event -> eventAssembler.toEventDto(event))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EventDto>> getTop30Events() {
        List<Event> topEvents = eventRepository.findTop30SoldOut();
        if (topEvents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(topEvents
                .stream()
                .map(event -> eventAssembler.toEventDto(event))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EventDto> getEventById(long id) {
        Event event = eventRepository.findById(id);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventAssembler.toEventDto(event), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getEventOrganizersById(long eventId) {
        List <Contract> contracts = contractRepository.findByEventId(eventId);

        List<Organizer> organizers = contracts
                .stream()
                .map(contract -> organizerRepository.findById(contract.getOrganizerId()))
                .collect(Collectors.toList());

        if (organizers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (Organizer org : organizers){
            if (org == null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(organizers
                .stream()
                .map(organizer -> organizerAssembler.toOrganizerDto(organizer))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getEventPerformersById(long eventId) {
        List <Contract> contracts = contractRepository.findByEventId(eventId);

        List<Performer> performers = contracts
                .stream()
                .map(contract -> performerRepository.findById(contract.getPerformerId()))
                .collect(Collectors.toList());

        if (performers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (Performer perf : performers){
            if (perf == null){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(performers
                .stream()
                .map(performer -> performerAssembler.toPerformerDto(performer))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPoster(long eventId) {

        Event event = eventRepository.findById(eventId);

        if (event == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String location = event.getPosterLocation();

        ImageDto image = null;

        try {
            image = new ImageDto(location);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(image , HttpStatus.OK);
    }

}
