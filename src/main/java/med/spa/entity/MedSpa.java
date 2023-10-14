package med.spa.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class MedSpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long medSpaId;
  
  private String medSpaName;
  private String medSpaAddress;
  private String medSpaCity;
  private String medSpaState;
  private String medSpaZip;
  private String medSpaPhone;
  
  
  @Column(unique = true)
  private String medSpaEmail;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "medSpa", cascade = CascadeType.ALL)
   private Set<Patient> patients = new HashSet<>();
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "med_spa_treatment",
      joinColumns = @JoinColumn(name = "med_spa_id"),
      inverseJoinColumns = @JoinColumn(name = "treatment_id"))
  private Set<Treatments> treatments = new HashSet<>();
  
}

