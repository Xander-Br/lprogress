package ch.xndr.lprogress.controllers;

import ch.xndr.lprogress.domains.School;
import ch.xndr.lprogress.domains.Student;
import ch.xndr.lprogress.domains.repositories.SchoolRepository;
import ch.xndr.lprogress.domains.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping(value = "/all/{id}")
    public List<Student> getAllStudent(@PathVariable Long id) {
        return this.studentRepository.findAllBySchoolId(id);
    }

    @GetMapping(value = "/{id}")
    public Student getStudent(@PathVariable Long id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "/join/{id}")
    public Boolean joinSchool(@PathVariable Long id, @RequestBody Long studentId) {
        Optional<Student> optionalStudent = this.studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            Optional<School> schoolOptional = this.schoolRepository.findById(id);
            if (schoolOptional.isPresent()) {
                School school = schoolOptional.get();
                student.setSchool(school);
                this.studentRepository.save(student);
                return true;
            }
            return false;
        }
        return false;
    }
}
