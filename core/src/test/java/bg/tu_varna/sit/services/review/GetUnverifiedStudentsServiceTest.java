package bg.tu_varna.sit.services.review;

import bg.tu_varna.sit.base.Error;
import bg.tu_varna.sit.entity.Application;
import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.enums.ApplicationStatus;
import bg.tu_varna.sit.error.InternalError;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsRequest;
import bg.tu_varna.sit.model.application.unverified.GetUnverifiedStudentsResponse;
import bg.tu_varna.sit.model.dto.ApplicationDTO;
import bg.tu_varna.sit.repository.ApplicationRepository;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class GetUnverifiedStudentsServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private GetUnverifiedStudentsService getUnverifiedStudentsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess_Success() {
        // Arrange
        Student student1 = new Student();
        student1.setStudentNumber("12345");
        student1.setName("John Doe");

        Application application1 = new Application();
        application1.setStudent(student1);
        application1.setApplicationDate(LocalDate.now());
        application1.setStatus(ApplicationStatus.PENDING);

        Student student2 = new Student();
        student2.setStudentNumber("67890");
        student2.setName("Jane Doe");

        Application application2 = new Application();
        application2.setStudent(student2);
        application2.setApplicationDate(LocalDate.now());
        application2.setStatus(ApplicationStatus.PENDING);

        List<Application> unverifiedApplications = Arrays.asList(application1, application2);

        when(applicationRepository.findByUnverified()).thenReturn(unverifiedApplications);

        GetUnverifiedStudentsRequest request = new GetUnverifiedStudentsRequest();

        // Act
        Either<Error, GetUnverifiedStudentsResponse> response = getUnverifiedStudentsService.process(request);

        // Assert
        assertTrue(response.isRight());
        List<ApplicationDTO> applications = response.get().getApplications();
        assertEquals(2, applications.size());
        assertEquals("John Doe", applications.get(0).getStudentName());
        assertEquals("12345", applications.get(0).getStudentId());
        assertEquals(ApplicationStatus.PENDING.toString(), applications.get(0).getStatus());
        assertEquals("Jane Doe", applications.get(1).getStudentName());
        assertEquals("67890", applications.get(1).getStudentId());
        assertEquals(ApplicationStatus.PENDING.toString(), applications.get(1).getStatus());
    }

    @Test
    void testProcess_Failure() {
        // Arrange
        when(applicationRepository.findByUnverified()).thenThrow(new RuntimeException("Database error"));

        GetUnverifiedStudentsRequest request = new GetUnverifiedStudentsRequest();

        // Act
        Either<Error, GetUnverifiedStudentsResponse> response = getUnverifiedStudentsService.process(request);

        // Assert
        assertTrue(response.isLeft());
        assertEquals(InternalError.class, response.getLeft().getClass());
    }
}