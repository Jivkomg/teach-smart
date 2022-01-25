package bg.sofia.uni.fmi.piss.project.tm.repositories;

import bg.sofia.uni.fmi.piss.project.tm.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, String> {
    List<Contract> findByCourseId(String courseId);
}
