package com.ecampus.controller.student;

import com.ecampus.config.RegistrationDeadlineConfig;
import com.ecampus.model.*;
import com.ecampus.repository.UserRepository;
import com.ecampus.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService registrationService;

    @Autowired
    private RegistrationDeadlineConfig deadlineConfig;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/student/registration")
    public String listStudentRegistrations( Authentication authentication, Model model) {

        String username = authentication.getName();

        Long studentId = userRepo.findIdByUname(username);

        Students st = registrationService.getStudentById(studentId);

        List<Semesters> smt = registrationService.getSemesterById(st.getStdbchid());

        List<StudentRegistrations> regs = registrationService.getRegistrationsByStudentId(studentId);

        Map<Long, StudentRegistrations> registrationsMap = regs.stream()
                .collect(Collectors.toMap(StudentRegistrations::getSrgstrid, Function.identity()));

        boolean isWithinDeadline = deadlineConfig.isWithinDeadline();

        Long maxStrid = registrationService.getMaxSemesterId(st.getStdbchid());

        model.addAttribute("isWithinDeadline", isWithinDeadline);
        model.addAttribute("maxStrid", maxStrid);
        model.addAttribute("studentbean", st);
        model.addAttribute("semestersbean", smt);
        model.addAttribute("StudentRegistrations",registrationsMap);
        return "student/student-registration";
    }
}