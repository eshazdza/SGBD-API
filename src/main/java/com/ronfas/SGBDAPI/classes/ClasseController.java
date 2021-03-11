package com.ronfas.SGBDAPI.classes;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/classes")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping("")
    public CollectionModel<Classe> all() {
        return this.classeService.getAllClasses();
    }

    @GetMapping("/{uid}")
    public Classe one(
            @PathVariable UUID uid
    ) {
        return this.classeService.getClasseByUid(uid);
    }

    @PostMapping("")
    ResponseEntity<?> newClasse (
            @RequestBody @Valid ClasseEntity newClasse
    ) {
        Classe classesEntityModel = this.classeService.saveClasse(newClasse);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                classesEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(classesEntityModel);
    }

    @PutMapping("/{uid}")
    ResponseEntity<?> updateClasse(
            @RequestBody @Valid ClasseEntity classe,
            @PathVariable UUID uid
    ) {
        Classe classesEntityModel = this.classeService.updateClasse(classe, uid);

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
        classeService.deleteClasse(uid);
        return ResponseEntity.noContent().build();
    }
}
