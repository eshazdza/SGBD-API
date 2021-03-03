package com.ronfas.SGBDAPI.classes;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping("")
    CollectionModel<EntityModel<Classes>> all() {
        return null;
    }

    @GetMapping("/{uid}")
    EntityModel<Classes> one(
            @PathVariable UUID uid
    ) {
        return null;
    }

    @GetMapping("/{id}")
    CollectionModel<EntityModel<Classes>> allById(
            @PathVariable String id
    ) {
        return null;
    }

    @PostMapping("")
    ResponseEntity<?> newClass(
            @RequestBody @Valid Classes newClass
    ) {
        return null;
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateClass(
            @RequestBody @Valid Classes classe,
            @PathVariable String id
    ) {
        return null;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(
            @PathVariable String id
    ) {
        return null;
    }
}
