package com.ronfas.SGBDAPI.inscription;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InscriptionModelAssembler extends RepresentationModelAssemblerSupport<InscriptionEntity, Inscription> {

    public InscriptionModelAssembler() {
        super(InscriptionController.class, Inscription.class);
    }

    @Override
    public Inscription toModel(InscriptionEntity inscriptionEntity) {
        Inscription inscription = instantiateModel(inscriptionEntity);
        inscription.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .one(inscriptionEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withRel("inscriptions")
        );

        inscription.setId(inscriptionEntity.getId());
//        TODO SETTERS RELATIONS

        return inscription;
    }

    @Override
    public CollectionModel<Inscription> toCollectionModel(Iterable<? extends InscriptionEntity> inscriptions) {
        CollectionModel<Inscription> inscriptionModels = super.toCollectionModel(inscriptions);
        inscriptionModels.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withSelfRel()
        );
        return inscriptionModels;
    }
}
