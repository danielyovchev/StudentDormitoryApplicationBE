package bg.tu_varna.sit.entity;

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
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column
    private byte[] file;
    @CreationTimestamp
    private LocalDate uploadDate;
    @UpdateTimestamp
    private LocalDate updatedAt;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
