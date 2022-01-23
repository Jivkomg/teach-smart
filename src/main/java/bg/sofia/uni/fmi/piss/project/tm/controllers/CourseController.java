package bg.sofia.uni.fmi.piss.project.tm.controllers;

import bg.sofia.uni.fmi.piss.project.tm.services.interfaces.CourseService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/courses", produces = "application/json", consumes = "application/json")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/all")
    public ResponseEntity getCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/top30")
    public ResponseEntity getTop30Courses() {
        return courseService.getTop30Courses();
    }

    @PostMapping("/{courseId}")
    public ResponseEntity getCourseById(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/organizers/{courseId}")
    public ResponseEntity getCourseOrganizersById(@PathVariable String courseId) {
        return courseService.getCourseOrganizersById(courseId);
    }

    @PostMapping("/tutors/{courseId}")
    public ResponseEntity geCourseTutorById(@PathVariable String courseId) {
        return courseService.getCourseTutorById(courseId);
    }

    @PostMapping("/poster/{courseId}")
    public ResponseEntity getPoster(@PathVariable String courseId) {
        return courseService.getPoster(courseId);
    }
}
