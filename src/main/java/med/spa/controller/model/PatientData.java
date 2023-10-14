package med.spa.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import med.spa.entity.MedSpa;
import med.spa.entity.Patient;

@Data
@NoArgsConstructor
public class PatientData {

  private Long patientId;
  private String patientFirstName;
  private String patientLastName;
  private String patientPhoneNumber;
  private String patientEmail;
  private MedSpaResponse medSpa;

  public PatientData(Patient patient) {
    patientId = patient.getPatientId();
    patientFirstName = patient.getPatientFirstName();
    patientLastName = patient.getPatientLastName();
    patientPhoneNumber = patient.getPatientPhoneNumber();
    patientEmail = patient.getPatientEmail();
    medSpa = new MedSpaResponse(patient.getMedSpa());
   
    }
  


  @Data
  @NoArgsConstructor
  static class MedSpaResponse {
    private Long medSpaId;
    private String medSpaName;
    private String medSpaAddress;
    private String medSpaCity;
    private String medSpaState;
    private String medSpaZip;
    private String medSpaEmail;
    

    MedSpaResponse(MedSpa medSpa) {
      medSpaId = medSpa.getMedSpaId();
      medSpaName = medSpa.getMedSpaName();
      medSpaAddress = medSpa.getMedSpaAddress();
      medSpaCity = medSpa.getMedSpaCity();
      medSpaState = medSpa.getMedSpaState();
      medSpaZip = medSpa.getMedSpaZip();
      medSpaEmail = medSpa.getMedSpaEmail();


      }

    }
  }
  


