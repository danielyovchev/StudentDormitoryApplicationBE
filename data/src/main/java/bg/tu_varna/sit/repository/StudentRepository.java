package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student, UUID> {
    public Optional<Student> findByStudentPersonalNumber(String studentNumber) {
        return find("studentNumber", studentNumber).firstResultOptional();
    }

    public List<Student> findUnverifiedStudents() {
        return getEntityManager()
                .createQuery("SELECT d.student from Document d where d.validated=false", Student.class)
                .getResultList();
    }
}
