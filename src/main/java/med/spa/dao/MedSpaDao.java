package med.spa.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import med.spa.entity.MedSpa;

public interface MedSpaDao extends JpaRepository<MedSpa, Long> {

  Optional<MedSpa> findByMedSpaEmail(String medSpaEmail);
  
}
