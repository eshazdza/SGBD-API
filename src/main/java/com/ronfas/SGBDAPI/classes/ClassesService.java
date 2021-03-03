package com.ronfas.SGBDAPI.classes;

import org.springframework.stereotype.Service;

@Service
public class ClassesService {

    private final ClassesRepository classesRepository;
    private final ClassesModelAssembler classesModelAssembler;

    public ClassesService(ClassesRepository classesRepository, ClassesModelAssembler classesModelAssembler) {
        this.classesRepository = classesRepository;
        this.classesModelAssembler = classesModelAssembler;
    }

}
