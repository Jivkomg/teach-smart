package bg.sofia.uni.fmi.piss.project.tm.repositories;

import java.util.Optional;

import bg.sofia.uni.fmi.piss.project.tm.models.TeachSmartUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachSmartUserRepository extends JpaRepository<TeachSmartUser, String> {
    Optional<TeachSmartUser> findByUsername(String name);
}
