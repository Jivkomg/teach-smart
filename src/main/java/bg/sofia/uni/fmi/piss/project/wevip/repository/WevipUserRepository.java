package bg.sofia.uni.fmi.piss.project.wevip.repository;

import bg.sofia.uni.fmi.piss.project.wevip.model.WevipUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WevipUserRepository extends JpaRepository<WevipUser, Long> {
  WevipUser findByUsername(String name);
}
