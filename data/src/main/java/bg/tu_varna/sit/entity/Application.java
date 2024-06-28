package bg.tu_varna.sit.entity;

import bg.tu_varna.sit.enums.ApplicationStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @CreationTimestamp
    private LocalDate applicationDate;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
