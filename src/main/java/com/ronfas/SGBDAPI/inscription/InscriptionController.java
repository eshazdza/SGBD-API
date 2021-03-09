package com.ronfas.SGBDAPI.inscription;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {
    private final InscriptionService inscriptionService;

    public InscriptionController( InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping("")
    CollectionModel<Inscription> all() {
        return inscriptionService.getAllInscriptions();
    }

    @GetMapping("/{id}")
    Inscription one(
            @PathVariable Long id
    ) {
        return this.inscriptionService.getInscriptionById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newInscription(
            @RequestBody @Valid InscriptionEntity newInscriptionEntity
    ) {
        Inscription inscriptionEntityModel = this.inscriptionService.saveInscription(newInscriptionEntity);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                inscriptionEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(inscriptionEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateInscription(
            @RequestBody @Valid InscriptionEntity inscriptionEntity,
            @PathVariable Long id
    ) {
        Inscription inscriptionEntityModel = this.inscriptionService.updateInscription(inscriptionEntity, id);

        return ResponseEntity
                .created(inscriptionEntityModel.getRequiredLink(
                        IanaLinkRelations.SELF
                        ).toUri()
                ).body(inscriptionEntity);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        inscriptionService.deleteInscription(id);
        return ResponseEntity.noContent().build();
    }
}
