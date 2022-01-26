package bg.sofia.uni.fmi.piss.project.tm.services;

import bg.sofia.uni.fmi.piss.project.tm.services.interfaces.CourseService;
import bg.sofia.uni.fmi.piss.project.tm.utils.EntityToDtoMapper;
import bg.sofia.uni.fmi.piss.project.tm.dtos.CourseDto;
import bg.sofia.uni.fmi.piss.project.tm.dtos.ImageDto;
import bg.sofia.uni.fmi.piss.project.tm.models.Contract;
import bg.sofia.uni.fmi.piss.project.tm.models.Course;
import bg.sofia.uni.fmi.piss.project.tm.models.Organizer;
import bg.sofia.uni.fmi.piss.project.tm.models.Tutor;
import bg.sofia.uni.fmi.piss.project.tm.repositories.ContractRepository;
import bg.sofia.uni.fmi.piss.project.tm.repositories.CourseRepository;
import bg.sofia.uni.fmi.piss.project.tm.repositories.OrganizerRepository;
import bg.sofia.uni.fmi.piss.project.tm.repositories.TutorRepository;
import bg.sofia.uni.fmi.piss.project.tm.utils.ExceptionMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private ContractRepository contractRepository;

    private OrganizerRepository organizerRepository;

    private TutorRepository tutorRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, ContractRepository contractRepository,
        OrganizerRepository organizerRepository, TutorRepository tutorRepository) {
        this.courseRepository = courseRepository;
        this.contractRepository = contractRepository;
        this.organizerRepository = organizerRepository;
        this.tutorRepository = tutorRepository;
    }

    @Override
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courses
            .stream()
            .map(EntityToDtoMapper::toCourseDto)
            .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CourseDto> getCourseById(String id) {
        Optional<Course> course = courseRepository.findById(id);

        return course.map(value -> new ResponseEntity<>(EntityToDtoMapper.toCourseDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override public CourseDto createCourse(CourseDto courseDto) {
        if (this.courseRepository.existsByName(courseDto.getName())) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COURSE_NAME);
        }

        Course course = EntityToDtoMapper.toCourseEntity(courseDto);

        Course savedCourse = this.courseRepository.save(course);

        return EntityToDtoMapper.toCourseDto(savedCourse);
    }

    @Override
    public ResponseEntity getCourseOrganizersById(String courseId) {
        List<Contract> contracts = contractRepository.findByCourseId(courseId);

        List<Organizer> organizers = contracts
            .stream()
            .map(contract -> organizerRepository.findById(contract.getOrganizerId()).get())
            .collect(Collectors.toList());

        if (organizers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (Organizer org : organizers) {
            if (org == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(organizers
            .stream()
            .map(EntityToDtoMapper::toOrganizerDto)
            .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getCourseTutorById(String courseId) {
        List<Contract> contracts = contractRepository.findByCourseId(courseId);

        List<Tutor> tutors = contracts
            .stream()
            .map(contract -> tutorRepository.findById(contract.getId()).get())
            .collect(Collectors.toList());

        if (tutors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (Tutor perf : tutors) {
            if (perf == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(tutors
            .stream()
            .map(EntityToDtoMapper::toTutorDto)
            .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPoster(String courseId) {

        Optional<Course> course = courseRepository.findById(courseId);

        if (!course.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String location = course.get().getPosterLocation();

        ImageDto image;

        try {
            image = new ImageDto(location);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
