package med.spa.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.spa.entity.MedSpa;
import med.spa.entity.Treatments;

@Data
@NoArgsConstructor
public class TreatmentsData {
  
  private Long treatmentId;
  private String treatmentName;
  private String treatmentType;
  private String treatmentArea;
  private Set<TreatmentsMedSpa> medSpas = new HashSet<>();
  
  
  public TreatmentsData(Treatments treatments) {
    
    treatmentId = treatments.getTreatmentId();
    treatmentName = treatments.getTreatmentName();
    treatmentType = treatments.getTreatmentType();
    treatmentArea = treatments.getTreatmentArea();
    
    for (MedSpa medSpa : treatments.getMedSpa()) {
      medSpas.add(new TreatmentsMedSpa(medSpa));
  }
    
  }


@Data
@NoArgsConstructor
  static class TreatmentsMedSpa {
  private Long medSpaId;
  private String medSpaName;
  private String medSpaAddress;
  private String medSpaCity;
  private String medSpaState;
  private String medSpaZip;
  private String medSpaEmail;
  

 public TreatmentsMedSpa(MedSpa medSpa) {
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
