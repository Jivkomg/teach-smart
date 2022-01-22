package bg.sofia.uni.fmi.piss.project.tm.services.interfaces;

import bg.sofia.uni.fmi.piss.project.tm.dtos.CourseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CourseService {

    ResponseEntity<List<CourseDto>> getAllCourses();

    ResponseEntity<List<CourseDto>> getTop30Courses();

    ResponseEntity<CourseDto> getCourseById(String id);

    ResponseEntity getPoster(@PathVariable String courseId);

    ResponseEntity getCourseOrganizersById(String courseId);

    ResponseEntity getCourseTutorById(String courseId);
}
