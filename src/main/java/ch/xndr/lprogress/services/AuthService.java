package ch.xndr.lprogress.services;

import ch.xndr.lprogress.domains.Student;
import ch.xndr.lprogress.domains.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    public Student login(String username, String password) {
        Optional<Student> student = studentRepository.findFirstByEmailAndPassword(username, password);
        return student.orElse(null);
    }



}
