package bg.sofia.uni.fmi.piss.project.tm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bg.sofia.uni.fmi.piss.project.tm.services.interfaces.CourseService;

@Controller
@RequestMapping(path = "/courses", produces = "application/json", consumes = "application/json")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity getCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity getCourseById(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/organizers/{courseId}")
    public ResponseEntity getCourseOrganizersById(@PathVariable String courseId) {
        return courseService.getCourseOrganizersById(courseId);
    }

    @GetMapping("/tutors/{courseId}")
    public ResponseEntity geCourseTutorById(@PathVariable String courseId) {
        return courseService.getCourseTutorById(courseId);
    }

    @GetMapping("/poster/{courseId}")
    public ResponseEntity getPoster(@PathVariable String courseId) {
        return courseService.getPoster(courseId);
    }
}
