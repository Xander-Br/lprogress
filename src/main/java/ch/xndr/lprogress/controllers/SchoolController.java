package ch.xndr.lprogress.controllers;

import ch.xndr.lprogress.domains.School;
import ch.xndr.lprogress.domains.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/school", produces = MediaType.APPLICATION_JSON_VALUE)
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping(value = "/all")
    public List<School> getAllSchools() {
        return this.schoolRepository.findAll();
    }



}
