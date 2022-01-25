package bg.sofia.uni.fmi.piss.project.tm.services;


import bg.sofia.uni.fmi.piss.project.tm.utils.EntityToDtoMapper;
import bg.sofia.uni.fmi.piss.project.tm.dtos.CourseDto;
import bg.sofia.uni.fmi.piss.project.tm.dtos.OrganizerDto;
import bg.sofia.uni.fmi.piss.project.tm.dtos.TutorDto;
import bg.sofia.uni.fmi.piss.project.tm.models.Contract;
import bg.sofia.uni.fmi.piss.project.tm.models.Course;
import bg.sofia.uni.fmi.piss.project.tm.models.Organizer;
import bg.sofia.uni.fmi.piss.project.tm.models.Tutor;
import bg.sofia.uni.fmi.piss.project.tm.repositories.ContractRepository;
import bg.sofia.uni.fmi.piss.project.tm.repositories.CourseRepository;
import bg.sofia.uni.fmi.piss.project.tm.repositories.OrganizerRepository;
import bg.sofia.uni.fmi.piss.project.tm.repositories.TutorRepository;
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
public class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ContractRepository contractRepository;

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private CourseServiceImpl eventService;


    @Test
    public void getAllEvents_NoEventsFound(){
        Mockito.when(courseRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<List<CourseDto>> response = eventService.getAllCourses();
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void getAllEvents_EventsFound(){
        CourseDto dto = new CourseDto();
        dto.setCourseId("1234");

        Mockito.when(courseRepository.findAll()).thenReturn(Collections.singletonList(new Course()));
        Mockito.when(EntityToDtoMapper.toCourseDto(Mockito.any())).thenReturn(dto);

        ResponseEntity<List<CourseDto>> result = eventService.getAllCourses();
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(Collections.singletonList(dto),result.getBody());
    }

    @Test
    public void getTop30Events_NoEventsFound() {
        ResponseEntity<List<CourseDto>> response = eventService.getAllCourses();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getEventById_NoEventsFound() {
        ResponseEntity<CourseDto> result = eventService.getCourseById("1");
        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventById_EventFound() {
        final String id = "1";
        Course course = new Course();
        CourseDto dto = new CourseDto();
        dto.setCourseId(id);
        Mockito.when(courseRepository.findById(id)).thenReturn(java.util.Optional.of(course));
        Mockito.when(EntityToDtoMapper.toCourseDto(course)).thenReturn(dto);

        ResponseEntity<CourseDto> result = eventService.getCourseById(id);
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(dto,result.getBody());
    }

    @Test
    public void getEventOrganizersById_NoContractsFound(){
        Mockito.when(contractRepository.findByCourseId(Mockito.anyString())).
                thenReturn(Collections.emptyList());
        ResponseEntity result = eventService.getCourseOrganizersById("1");
        Mockito.verify(organizerRepository,Mockito.never()).findById(Mockito.anyString());

        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventOrganizersById_NoOrganizersFound() {
        Contract contract = new Contract();
        contract.setOrganizerId("2");
        Mockito.when(contractRepository.findByCourseId("1")).
                thenReturn(Collections.singletonList(contract));

        ResponseEntity result = eventService.getCourseOrganizersById("1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getEventOrganizersById_OrganizersFound() {
        Contract contract = new Contract();
        contract.setOrganizerId("2");
        Mockito.when(contractRepository.findByCourseId("1")).
                thenReturn(Collections.singletonList(contract));

        Organizer org = new Organizer();
        Mockito.when(organizerRepository.findById(contract.getOrganizerId())).
                thenReturn(java.util.Optional.of(org));

        OrganizerDto dto = new OrganizerDto();
        Mockito.when(EntityToDtoMapper.toOrganizerDto(org)).thenReturn(dto);

        ResponseEntity result = eventService.getCourseOrganizersById("1");

        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(Collections.singletonList(dto),result.getBody());
    }

    @Test
    public void getCourseTutorsById_NoContractsFound(){
        Mockito.when(contractRepository.findByCourseId(Mockito.anyString())).
                thenReturn(Collections.emptyList());
        ResponseEntity result = eventService.getCourseTutorById("1");
        Mockito.verify(tutorRepository,Mockito.never()).findById(Mockito.anyString());

        assertEquals(HttpStatus.NOT_FOUND,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getCourseTutorsById_NoTutorsFound() {
        Contract contract = new Contract();
        contract.setOrganizerId("1");
        Mockito.when(contractRepository.findByCourseId("1")).
                thenReturn(Collections.singletonList(contract));

        ResponseEntity result = eventService.getCourseTutorById("1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getTutorsTutorsById_TutorsFound() {
        Contract contract = new Contract();
        contract.setTutorId("2");
        Mockito.when(contractRepository.findByCourseId("1")).
                thenReturn(Collections.singletonList(contract));

        Tutor tutor = new Tutor();
        Mockito.when(tutorRepository.findById(contract.getTutorId())).
                thenReturn(java.util.Optional.of(tutor));

        TutorDto dto = new TutorDto();
        Mockito.when(EntityToDtoMapper.toTutorDto(tutor)).thenReturn(dto);

        ResponseEntity result = eventService.getCourseTutorById("1");

        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(Collections.singletonList(dto),result.getBody());
    }

    @Test
    public void getPoster_NoEventsFound(){
        ResponseEntity result = eventService.getPoster("1");
        assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
        assertNull(result.getBody());
    }

    @Test
    public void getPoster_NoPosterFound(){
        Course course = new Course();
        course.setPosterLocation("/some/path");
        Mockito.when(courseRepository.findById("1")).thenReturn(java.util.Optional.of(course));
        ResponseEntity result = eventService.getPoster("1");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,result.getStatusCode());
        assertNull(result.getBody());
    }
}
