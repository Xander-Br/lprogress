package ch.xndr.lprogress.domains.repositories;

import ch.xndr.lprogress.domains.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByMonitorId(Long monitorId);

}

