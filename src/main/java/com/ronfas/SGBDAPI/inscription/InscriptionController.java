package com.ronfas.SGBDAPI.inscription;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class InscriptionController {
    private final InscriptionService inscriptionService;

    public InscriptionController( InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping("")
    CollectionModel<EntityModel<Inscription>> all() {
        return CollectionModel.of(this.inscriptionService.getAllInscriptions(), linkTo(
                methodOn(InscriptionController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    EntityModel<Inscription> one(
            @PathVariable Long id
    ) {
        return this.inscriptionService.getInscriptionById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newInscription(
            @RequestBody @Valid Inscription newInscription
    ) {
        EntityModel<Inscription> inscriptionEntityModel = this.inscriptionService.saveInscription(newInscription);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                inscriptionEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(inscriptionEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateInscription(
            @RequestBody @Valid Inscription inscription,
            @PathVariable Long id
    ) {
        EntityModel<Inscription> inscriptionEntityModel = this.inscriptionService.updateInscription(inscription, id);

        return ResponseEntity
                .created(inscriptionEntityModel.getRequiredLink(
                        IanaLinkRelations.SELF
                        ).toUri()
                ).body(inscription);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        inscriptionService.deleteInscription(id);
        return ResponseEntity.noContent().build();
    }
}
