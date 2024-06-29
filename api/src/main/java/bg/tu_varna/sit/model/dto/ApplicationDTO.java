package bg.tu_varna.sit.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationDTO {
    private String studentName;
    private String studentId;
    private LocalDate applicationDate;
    private String status;
}
