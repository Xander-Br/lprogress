package ch.xndr.lprogress.controllers;

import ch.xndr.lprogress.controllers.requests.CreateCourse;
import ch.xndr.lprogress.domains.Course;
import ch.xndr.lprogress.domains.Monitor;
import ch.xndr.lprogress.domains.Student;
import ch.xndr.lprogress.domains.repositories.CourseRepository;
import ch.xndr.lprogress.domains.repositories.MonitorRepository;
import ch.xndr.lprogress.domains.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/course", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/all/{monitorId}")
    public List<Course> courseList(@PathVariable Long monitorId){
        return courseRepository.findAllByMonitorId(monitorId);
    }

    @PostMapping(value = "/create")
    public Course createCourse(@RequestBody CreateCourse course){
        Course newCourse = new Course();
        Optional<Monitor> monitor = monitorRepository.findById(course.getMonitorId());
        if (monitor.isPresent()) {
            newCourse.setMonitor(monitor.get());
            Optional<Student> student = studentRepository.findById(course.getStudentId());
            if (student.isPresent()) {
                newCourse.setStudent(student.get());
                newCourse.setDuration(course.getCourse().getDuration());
                newCourse.setStartAt(course.getCourse().getStartAt());
                //Cast to local date time
                newCourse.setDate(course.getCourse().getDate());
                newCourse.setTitle(course.getCourse().getTitle());
                return courseRepository.save(newCourse);
            }
        }
        return null;
    }



}
