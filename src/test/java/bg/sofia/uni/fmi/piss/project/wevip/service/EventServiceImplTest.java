package bg.sofia.uni.fmi.piss.project.wevip.service;


import bg.sofia.uni.fmi.piss.project.wevip.dto.EventDto;
import bg.sofia.uni.fmi.piss.project.wevip.dto.OrganizerDto;
import bg.sofia.uni.fmi.piss.project.wevip.dto.PerformerDto;
import bg.sofia.uni.fmi.piss.project.wevip.model.Contract;
import bg.sofia.uni.fmi.piss.project.wevip.model.Event;
import bg.sofia.uni.fmi.piss.project.wevip.model.Organizer;
import bg.sofia.uni.fmi.piss.project.wevip.model.Performer;
import bg.sofia.uni.fmi.piss.project.wevip.repository.ContractRepository;
import bg.sofia.uni.fmi.piss.project.wevip.repository.EventRepository;
import bg.sofia.uni.fmi.piss.project.wevip.repository.OrganizerRepository;
import bg.sofia.uni.fmi.piss.project.wevip.repository.PerformerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

//TODO extract hardcoded constants
@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private PerformerRepository performerRepository;

    @Mock
    private EventAssembler eventAssembler;

    @Mock
    private OrganizerAssembler organizerAssembler;

    @Mock
    private PerformerAssembler performerAssembler;

    @InjectMocks
    private EventServiceImpl eventService;


    @Test
    public void getAllEvents_NoEventsFound(){
        Mockito.when(eventRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<EventDto>> response = eventService.getAllEvents();
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void getAllEvents_EventsFound(){
        EventDto dto = new EventDto();
        dto.setEventId(1L);

        Mockito.when(eventRepository.findAll()).thenReturn(Arrays.asList(new Event()));
        Mockito.when(eventAssembler.toEventDto(Mockito.any())).thenReturn(dto);

        ResponseEntity<List<EventDto>> result = eventService.getAllEvents();
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(Arrays.asList(dto),result.getBody());
    }

    @Test
    public void getTop30Events_NoEventsFound(){
        ResponseEntity<List<EventDto>> response = eventService.getAllEvents();
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void getTop30Events_EventsFound(){
        EventDto dto = new EventDto();
        Mockito.when(eventRepository.findTop30SoldOut()).thenReturn(Arrays.asList(new Event(),new Event()));
        Mockito.when(eventAssembler.toEventDto(Mockito.any())).thenReturn(dto,dto);

        ResponseEntity<List<EventDto>> result = eventService.getTop30Events();
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(Arrays.asList(dto,dto),result.getBody());
    }

    @Test
    public void getEventById_NoEventsFound() {
        ResponseEntity<EventDto> result = eventService.getEventById(1);
        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventById_EventFound() {
        final long id = 1;
        Event event = new Event();
        EventDto dto = new EventDto();
        dto.setEventId(id);
        Mockito.when(eventRepository.findById(id)).thenReturn(event);
        Mockito.when(eventAssembler.toEventDto(event)).thenReturn(dto);

        ResponseEntity<EventDto> result = eventService.getEventById(id);
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(dto,result.getBody());
    }

    @Test
    public void getEventOrganizersById_NoContractsFound(){
        Mockito.when(contractRepository.findByEventId(Mockito.anyLong())).
                thenReturn(Collections.emptyList());
        ResponseEntity result = eventService.getEventOrganizersById(1);
        Mockito.verify(organizerRepository,Mockito.never()).findById(Mockito.anyLong());
        Mockito.verify(organizerAssembler,Mockito.never()).toOrganizerDto(Mockito.any());

        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventOrganizersById_NoOrganizersFound() {
        Contract contract = new Contract();
        contract.setOrganizerId(2);
        Mockito.when(contractRepository.findByEventId(1)).
                thenReturn(Collections.singletonList(contract));

        ResponseEntity result = eventService.getEventOrganizersById(1);
        Mockito.verify(organizerAssembler,Mockito.never()).toOrganizerDto(Mockito.any());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventOrganizersById_OrganizersFound() {
        Contract contract = new Contract();
        contract.setOrganizerId(2);
        Mockito.when(contractRepository.findByEventId(1)).
                thenReturn(Collections.singletonList(contract));

        Organizer org = new Organizer();
        Mockito.when(organizerRepository.findById(contract.getOrganizerId())).
                thenReturn(org);

        OrganizerDto dto = new OrganizerDto();
        Mockito.when(organizerAssembler.toOrganizerDto(org)).thenReturn(dto);

        ResponseEntity result = eventService.getEventOrganizersById(1);

        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(Arrays.asList(dto),result.getBody());
    }

    @Test
    public void getEventPerformersById_NoContractsFound(){
        Mockito.when(contractRepository.findByEventId(Mockito.anyLong())).
                thenReturn(Collections.emptyList());
        ResponseEntity result = eventService.getEventPerformersById(1);
        Mockito.verify(performerRepository,Mockito.never()).findById(Mockito.anyLong());
        Mockito.verify(performerAssembler,Mockito.never()).toPerformerDto(Mockito.any());

        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventPerformersById_NoPerformersFound() {
        Contract contract = new Contract();
        contract.setOrganizerId(2);
        Mockito.when(contractRepository.findByEventId(1)).
                thenReturn(Collections.singletonList(contract));

        ResponseEntity result = eventService.getEventPerformersById(1);
        Mockito.verify(performerAssembler,Mockito.never()).toPerformerDto(Mockito.any());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventPerformersById_PerformersFound() {
        Contract contract = new Contract();
        contract.setPerformerId(2);
        Mockito.when(contractRepository.findByEventId(1)).
                thenReturn(Collections.singletonList(contract));

        Performer performer = new Performer();
        Mockito.when(performerRepository.findById(contract.getPerformerId())).
                thenReturn(performer);

        PerformerDto dto = new PerformerDto();
        Mockito.when(performerAssembler.toPerformerDto(performer)).thenReturn(dto);

        ResponseEntity result = eventService.getEventPerformersById(1);

        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(Arrays.asList(dto),result.getBody());
    }

    @Test
    public void getPoster_NoEventsFound(){
        ResponseEntity result = eventService.getPoster(1);
        assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getPoster_NoPosterFound(){
        Event event = new Event();
        event.setPosterLocation("/some/path");
        Mockito.when(eventRepository.findById(1)).thenReturn(event);
        ResponseEntity result = eventService.getPoster(1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
        assertNull(result.getBody());
    }

    //TODO the success scenario in the poster exists
}
