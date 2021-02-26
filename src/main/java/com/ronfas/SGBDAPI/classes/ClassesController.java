package com.ronfas.SGBDAPI.classes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    private ClassesRepository classesRepository;
}
