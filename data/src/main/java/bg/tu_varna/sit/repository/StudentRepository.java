package bg.tu_varna.sit.repository;

import bg.tu_varna.sit.entity.Student;
import bg.tu_varna.sit.enums.ApplicationStatus;
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

    public List<Student> findVerifiedStudents() {
        return find("select s from Student s join s.applications a where a.status = ?1", ApplicationStatus.SUCCESSFUL).list();
    }
}
