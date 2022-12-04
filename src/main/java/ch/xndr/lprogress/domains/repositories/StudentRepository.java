package ch.xndr.lprogress.domains.repositories;

import ch.xndr.lprogress.domains.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findFirstByEmailAndPassword(String email, String password);

    List<Student> findAllBySchoolId(Long schoolId);

}
