package med.spa.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Treatments {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long treatmentId;
  
  private String treatmentName;
  private String treatmentType;
  private String treatmentArea;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude  
  @ManyToMany(mappedBy = "treatments")
  private Set<MedSpa> medSpa = new HashSet<>();
  
  }
