package med.spa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import med.spa.entity.Treatments;

public interface TreatmentDao extends JpaRepository<Treatments, Long> {



  

}
