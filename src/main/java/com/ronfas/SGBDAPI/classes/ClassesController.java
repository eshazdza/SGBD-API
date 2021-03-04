package com.ronfas.SGBDAPI.classes;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/classes")
public class ClassesController {

    private final ClassesService classesService;

    public ClassesController( ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping("")
    CollectionModel<EntityModel<Classes>> all() {
        return CollectionModel.of(this.classesService.getAllClasses(), linkTo(
                methodOn(ClassesController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/{uid}")
    EntityModel<Classes> one(
            @PathVariable UUID uid
    ) {
        return this.classesService.getClasseByUid(uid);
    }

    @PostMapping("")
    ResponseEntity<?> newClasse (
            @RequestBody @Valid Classes newClasse
    ) {
        EntityModel<Classes> classesEntityModel = this.classesService.saveClasse(newClasse);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                classesEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(classesEntityModel);
    }

    @PutMapping("/{uid}")
    ResponseEntity<?> updateClasse(
            @RequestBody @Valid Classes classe,
            @PathVariable UUID uid
    ) {
        EntityModel<Classes> classesEntityModel = this.classesService.updateClasse(classe, uid);

        return ResponseEntity
                .created(classesEntityModel.getRequiredLink(
                        IanaLinkRelations.SELF
                        ).toUri()
                ).body(classesEntityModel);

    }

    @DeleteMapping("/{uid}")
    ResponseEntity<?> delete(
            @PathVariable UUID uid
    ) {
        classesService.deleteClasse(uid);
        return ResponseEntity.noContent().build();
    }
}
