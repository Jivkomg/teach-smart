package bg.sofia.uni.fmi.piss.project.tm.repositories;

import bg.sofia.uni.fmi.piss.project.tm.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, String> {
}