package bg.sofia.uni.fmi.piss.project.tm.repositories;

import bg.sofia.uni.fmi.piss.project.tm.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    boolean existsByName(String name);
}
