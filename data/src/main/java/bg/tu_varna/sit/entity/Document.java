package bg.tu_varna.sit.entity;

import bg.tu_varna.sit.enums.DocumentEnum;
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
    private String fileUrl;
    private String fileName;
    private Boolean validated;
    @Enumerated(EnumType.STRING)
    private DocumentEnum documentEnum;
    @CreationTimestamp
    private LocalDate uploadDate;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
