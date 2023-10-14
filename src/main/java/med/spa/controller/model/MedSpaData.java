package med.spa.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.spa.entity.MedSpa;
import med.spa.entity.Patient;
import med.spa.entity.Treatments;


@Data
@NoArgsConstructor
public class MedSpaData {

  private Long medSpaId;
  private String medSpaName;
  private String medSpaAddress;
  private String medSpaCity;
  private String medSpaState;
  private String medSpaZip;
  private String medSpaPhone;
  private String medSpaEmail;
  private Set<MedSpaPatient> patients = new HashSet<>();;
  private Set<MedspaTreatmentsData> treatment = new HashSet<>();



  public MedSpaData(MedSpa medSpa) {

    medSpaId = medSpa.getMedSpaId();
    medSpaName = medSpa.getMedSpaName();
    medSpaAddress = medSpa.getMedSpaAddress();
    medSpaCity = medSpa.getMedSpaCity();
    medSpaState = medSpa.getMedSpaState();
    medSpaZip = medSpa.getMedSpaZip();
    medSpaPhone = medSpa.getMedSpaPhone();
    medSpaEmail = medSpa.getMedSpaEmail();
    

    for (Treatments treatments : medSpa.getTreatments()) {
      treatment.add(new MedspaTreatmentsData(treatments));

    for (Patient patient: medSpa.getPatients()) {
      patients.add(new MedSpaPatient(patient));
    }

    }
 }

  @Data
  @NoArgsConstructor
  public static class MedSpaPatient {
    private Long patientId;
    private String patientFirstName;
    private String patientLastName;
    private String patientPhoneNumber;
    private String patientEmail;
    

    public MedSpaPatient(Patient patient) {

      patientId = patient.getPatientId();
      patientFirstName = patient.getPatientFirstName();
      patientLastName = patient.getPatientLastName();
      patientPhoneNumber = patient.getPatientPhoneNumber();
      patientEmail = patient.getPatientEmail();

    }
  }

  @Data
  @NoArgsConstructor
  public static class MedspaTreatmentsData {
    private Long treatmentId;
    private String treatmentName;
    private String treatmentType;
    private String treatmentArea;


    public MedspaTreatmentsData(Treatments treatments) {
      treatmentId = treatments.getTreatmentId();
      treatmentName = treatments.getTreatmentName();
      treatmentType = treatments.getTreatmentType();
      treatmentArea = treatments.getTreatmentArea();


    }

  }

}
