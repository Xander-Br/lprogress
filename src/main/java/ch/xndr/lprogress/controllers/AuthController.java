package ch.xndr.lprogress.controllers;

import ch.xndr.lprogress.controllers.requests.LoginRequest;
import ch.xndr.lprogress.domains.Student;
import ch.xndr.lprogress.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login/user")
    public Student login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }




}
