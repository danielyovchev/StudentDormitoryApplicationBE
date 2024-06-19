package bg.tu_varna.sit.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Document extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column
    private byte[] file;
    private Boolean validated;
    @CreationTimestamp
    private LocalDate uploadDate;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
