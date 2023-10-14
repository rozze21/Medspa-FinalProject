package med.spa.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import med.spa.controller.model.MedSpaData;
import med.spa.controller.model.PatientData;
import med.spa.controller.model.TreatmentsData;
import med.spa.service.MedService;

@RestController
@RequestMapping("/med_spa")
@Slf4j
public class MedSpaController {
  @Autowired
  private MedService medService;
  
  //MedSpa CRUD Operations
  
  @PostMapping("med_spa")
  @ResponseStatus(code = HttpStatus.CREATED)
  public MedSpaData insertMedSpa(
      @RequestBody MedSpaData medSpaData) {
    log.info("Creating Medical Spa {}", medSpaData);
    return medService.saveMedSpa(medSpaData);
  }
  

  @PutMapping("{medSpaId}")
  public MedSpaData updateMedSpa(@PathVariable Long medSpaId,
      @RequestBody MedSpaData medSpaData) {
    medSpaData.setMedSpaId(medSpaId);
    log.info("Updating Medical Spa {}", medSpaData);
    return medService.saveMedSpa(medSpaData);
  }  
  
  @GetMapping("/med_spa")
  public List<MedSpaData> retrieveAllmedSpas() {
    log.info("Retrieving all medSpas.");
    return medService.retrieveAllMedSpas();
  }
  @GetMapping("/med_spa/{medSpaId}")
  public MedSpaData retrieveMedSpaById(@PathVariable Long medSpaId) {
    log.info("Retrieving medSpa with ID={}", medSpaId);
    return medService.retrieveMedSpabyId(medSpaId); 
  } 
     
  @DeleteMapping("med_spa/{medSpaId}")
  public Map<String, String> deleteMedSpaById(
      @PathVariable Long medSpaId) {
    log.info("Deleting medSpa with ID={}", medSpaId);
    medService.deleteMedSpaById(medSpaId);
    
    return Map.of("message", "Deletion of medSpa with ID=" + medSpaId
        + " was successful.");
  }
  
  @DeleteMapping("/med_spa")
  public void deleteAllMedSpas() {
    log.info("Attempting to delete all medSpas");
    throw new UnsupportedOperationException(
        "Deleting all medSpas is not allowed.");
  }

  
  //Treatment CRU Operations
  
  @PostMapping("{medSpaId}/treatment")
  @ResponseStatus(code = HttpStatus.CREATED)
  public TreatmentsData insertTreatment(@PathVariable Long medSpaId,
      @RequestBody TreatmentsData treatmentId) {
    
    log.info("Creating treatment {} for medSpa with ID={}", medSpaId);
    
    return medService.saveTreatment(medSpaId, treatmentId);
  }
 
  @PutMapping("{medSpaId}/treatment/{treatmentId}")
  public TreatmentsData updateTreatment(@PathVariable Long medSpaId,
      @PathVariable Long treatmentId,
      @RequestBody TreatmentsData treatmentData) {
    
    treatmentData.setTreatmentId(treatmentId);
    
    log.info("Updating treatment {} for medSpa with ID={}", treatmentData,
        medSpaId);
    
    return medService.saveTreatment(medSpaId, treatmentData);
  }
  
  @GetMapping("/treatments")
  public List<TreatmentsData> retrieveAlltreatments() {
    log.info("Retrieving all medSpas.");
    return medService.retrieveAlltreatments();
  }
  
  @GetMapping("/{medSpaId}/treatment/{treatmentId}")
  public TreatmentsData retrieveTreatmentById(@PathVariable Long medSpaId,
      @PathVariable Long treatmentId) {
    log.info("Retrieving treatment with ID={} for medSpa with ID={}",
        treatmentId, medSpaId);
    
    return medService.retrieveTreatmentById(medSpaId, treatmentId);
 
  }

  // Patient CRU Operations
  
  @PostMapping("{medSpaId}/patient")
  @ResponseStatus(code = HttpStatus.CREATED)
  public PatientData insertPatient(@PathVariable Long medSpaId,
      @RequestBody PatientData patientData) {
    
    log.info("Creating Patient {} for medSpa with ID={}", medSpaId);
    
    return medService.savePatient(medSpaId, patientData);  
  }
  @PutMapping("{medSpaId}/patient/{patientId}")
  public PatientData updatePatient(@PathVariable Long medSpaId,
      @PathVariable Long patientId,
      @RequestBody PatientData patientData) {
    
    patientData.setPatientId(patientId);
    
    log.info("Updating Patient {} for medSpa with ID={}", patientData,
        medSpaId);
    
    return medService.savePatient(medSpaId, patientData);
  }
  @GetMapping("/patient")
  public List<PatientData> retrieveAllpatient() {
    log.info("Retrieving all medSpas.");
    return medService.retrieveAllPatient();
  }
  
  
  @GetMapping("/{medSpaId}/patient/{patientId}")
  public PatientData retrievePatientById(@PathVariable Long medSpaId,
      @PathVariable Long patientId) {
    log.info("Retrieving patient with ID={} for medSpa with ID={}",
        patientId, medSpaId);
    
    return medService.retrievePatientById(medSpaId, patientId);
 
  }
  @DeleteMapping("med_spa/{medSpaId}/patient/{patientId}")
  public Map<String, String> deletePatientById(@PathVariable Long medSpaId,
      @PathVariable Long patientId) {
    log.info("Deleting patient with ID={} from MedSpa",patientId, medSpaId);
    medService.deletePatientById(medSpaId, patientId);
    
    return Map.of("message", "Deletion of medSpa with ID=" + medSpaId
        + " was successful.");
  }
  
}
  