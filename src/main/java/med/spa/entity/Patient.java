package med.spa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private Long patientId;
  private String patientFirstName;
  private String patientLastName;
  private String patientPhoneNumber;
  
  @Column(unique = true)
  private String patientEmail;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "medSpa_id", nullable = false)
  private MedSpa medSpa;
  
}
