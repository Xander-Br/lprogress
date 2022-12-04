package ch.xndr.lprogress.controllers.requests;

import ch.xndr.lprogress.domains.Course;

public class CreateCourse {

    private Long monitorId;

    private Long studentId;

    private Course course;

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
