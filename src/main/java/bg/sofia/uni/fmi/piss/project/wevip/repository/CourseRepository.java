package bg.sofia.uni.fmi.piss.project.wevip.repository;

import bg.sofia.uni.fmi.piss.project.wevip.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(value = "SELECT * FROM courses ORDER BY tickets_sold DESC LIMIT 30", nativeQuery = true)
    List<Course> findTop30SoldOut();
}
