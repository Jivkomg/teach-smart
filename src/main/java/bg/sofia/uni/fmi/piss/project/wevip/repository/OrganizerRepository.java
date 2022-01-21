package bg.sofia.uni.fmi.piss.project.wevip.repository;

import bg.sofia.uni.fmi.piss.project.wevip.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Organizer findById(long id);
}
