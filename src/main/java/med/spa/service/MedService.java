package med.spa.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import med.spa.controller.model.MedSpaData;
import med.spa.controller.model.PatientData;
import med.spa.controller.model.TreatmentsData;
import med.spa.dao.MedSpaDao;
import med.spa.dao.PatientDao;
import med.spa.dao.TreatmentDao;
import med.spa.entity.MedSpa;
import med.spa.entity.Patient;
import med.spa.entity.Treatments;



@Service
public class MedService {
  
  @Autowired
  private MedSpaDao medSpaDao;
  
  @Autowired
  private PatientDao patientDao;
  
  @Autowired
  private TreatmentDao treatmentDao;
  
  @Transactional(readOnly = false)
  public MedSpaData saveMedSpa(MedSpaData medSpaData) {
    Long medSpaId = medSpaData.getMedSpaId();
    MedSpa medSpa = findOrCreateMedSpa(medSpaId);
    
    setFieldsInMedSpa(medSpa, medSpaData);
    return new  MedSpaData(medSpaDao.save(medSpa));  
   
  }

  private void setFieldsInMedSpa(MedSpa medSpa, MedSpaData medSpaData) {
    
    medSpa.setMedSpaName(medSpaData.getMedSpaName());
    medSpa.setMedSpaAddress(medSpaData.getMedSpaAddress());
    medSpa.setMedSpaCity(medSpaData.getMedSpaCity());
    medSpa.setMedSpaState(medSpaData.getMedSpaState());
    medSpa.setMedSpaZip(medSpaData.getMedSpaZip());
    medSpa.setMedSpaPhone(medSpaData.getMedSpaPhone());
    medSpa.setMedSpaEmail(medSpaData.getMedSpaEmail());
    
  }

  private MedSpa findOrCreateMedSpa(Long medSpaId){
    MedSpa medSpa;
    
       if (Objects.isNull(medSpaId)) {
         medSpa = new MedSpa();
       } 
       else {
     medSpa = findMedSpaById(medSpaId);
   }
     return medSpa;  
  }


  private MedSpa findMedSpaById(Long medSpaId) {
    return medSpaDao.findById(medSpaId).orElseThrow(() -> new NoSuchElementException(
        "MedSpa with ID=" + medSpaId + " was not found."));
  }

  @Transactional(readOnly = true)
  public List<MedSpaData> retrieveAllMedSpas() {
    List<MedSpa> medSpas = medSpaDao.findAll();
    List<MedSpaData> response = new LinkedList<>();
    
    for(MedSpa medSpa : medSpas) {
      response.add(new MedSpaData(medSpa));
    }
    
    return response;
   
    //@formatter:off
//    return medSpaDao.findAll()
//      .stream()
//      .map(MedSpaData::new)
//      .toList();
//    //@formatter:on
  }
  @Transactional(readOnly = true)
  public MedSpaData retrieveMedSpabyId(Long medSpaId) {
    MedSpa medSpa = findMedSpaById(medSpaId);
    return new MedSpaData(medSpa); 
  }
  @Transactional(readOnly = false)
  public void deleteMedSpaById(Long medSpaId) {
    MedSpa medSpa = findMedSpaById(medSpaId);
    medSpaDao.delete(medSpa);

}

  @Transactional(readOnly = false)
  public PatientData savePatient(Long medSpaId,
      PatientData patientData) {
   MedSpa medSpa = findMedSpaById(medSpaId);
   
   Patient patient = findOrCreatePatient(patientData.getPatientId());
   setPatientFields(patient, patientData);
   
   patient.setMedSpa(medSpa);
   medSpa.getPatients().add(patient);
   
   Patient dbPatient = patientDao.save(patient);
   return new PatientData(dbPatient);
    
  }
  private void setPatientFields(Patient patient, PatientData patientData) {
    patient.setPatientFirstName(patientData.getPatientFirstName());
    patient.setPatientLastName(patientData.getPatientLastName());
    patient.setPatientPhoneNumber(patientData.getPatientPhoneNumber());
    patient.setPatientId(patientData.getPatientId());
    patient.setPatientEmail(patientData.getPatientEmail());

  }

  private Patient findOrCreatePatient(Long patientId) {
    Patient patient;
    
    if(Objects.isNull(patientId)) {
      patient = new Patient();
    }
    else {
      patient = findPatientById(patientId);
    }  

    return patient;
    }


  
  private Patient findPatientById(Long patientId) {
    return patientDao.findById(patientId)
        .orElseThrow(() -> new NoSuchElementException(
            "Patient with ID=" + patientId + " does not exist"));

  }
  
  @Transactional(readOnly = true)
  public List<PatientData> retrieveAllPatient() {
    List<Patient> patients = patientDao.findAll();
    List<PatientData> response = new LinkedList<>();
    
    for(Patient patient : patients) {
      PatientData pd = new PatientData(patient);
      response.add(pd);
    }
    
    return response;
  }
  @Transactional(readOnly = true)
  public PatientData retrievePatientById(Long medSpaId, Long patientId) {
    findMedSpaById(medSpaId);
    Patient patient = findPatientById(patientId);
    
    if(patient.getMedSpa().getMedSpaId() != medSpaId) {
      throw new IllegalStateException("Patient with ID=" + patientId + 
          " is not owned by medSpa with ID=" + medSpaId);
    }
    return new PatientData(patient);
   
  } 
  @Transactional(readOnly = false)
  public void deletePatientById(Long medSpaId, Long patientId) {
    findMedSpaById(medSpaId);
    Patient patient = findPatientById(patientId);
    patientDao.delete(patient);

}
  
  @Transactional(readOnly = false)
  public TreatmentsData saveTreatment(Long medSpaId,
   TreatmentsData treatmentsData) {
   MedSpa medSpa = findMedSpaById(medSpaId);
      
   Treatments treatments = findOrCreateTreatment(treatmentsData.getTreatmentId());
   setTreatmentFields(treatments, treatmentsData);
   
   treatments.getMedSpa().add(medSpa);
   medSpa.getTreatments().add(treatments);
   
   Treatments dbTreatment = treatmentDao.save(treatments);
   return new TreatmentsData(dbTreatment);
    
  }
  private void setTreatmentFields(Treatments treatments, TreatmentsData treatmentsData) {
    treatments.setTreatmentName(treatmentsData.getTreatmentName());
    treatments.setTreatmentType(treatmentsData.getTreatmentType());
    treatments.setTreatmentId(treatmentsData.getTreatmentId());
    treatments.setTreatmentArea(treatmentsData.getTreatmentArea());
    
  }

  private Treatments findOrCreateTreatment(Long treatmentId) {
    Treatments treatments;
    
    if(Objects.isNull(treatmentId)) {
      treatments = new Treatments();
    }
    else {
      treatments = findTreatmentById(treatmentId);
    }  

    return treatments;
    }

  private Treatments findTreatmentById(Long treatmentId) {
    return treatmentDao.findById(treatmentId)
        .orElseThrow(() -> new NoSuchElementException(
            "Treatment with ID=" + treatmentId + " does not exist"));
        

    
  }
  @Transactional(readOnly = true)
public TreatmentsData retrieveTreatmentById(Long medSpaId, Long treatmentId) {

Treatments treatments = treatmentDao.findById(treatmentId)

.orElseThrow(() -> new IllegalStateException("Treatment with ID=" + treatmentId + "was not found."));

return new TreatmentsData(treatments);
   
  }

  public List<TreatmentsData> retrieveAlltreatments() {
    List<Treatments> treatments = treatmentDao.findAll();
    List<TreatmentsData> response = new LinkedList<>();
    
    for(Treatments treatment : treatments) {
      response.add(new TreatmentsData(treatment));
    }
    
    return response;
 
  }
  
}
    



