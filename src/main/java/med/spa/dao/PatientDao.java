package med.spa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import med.spa.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
