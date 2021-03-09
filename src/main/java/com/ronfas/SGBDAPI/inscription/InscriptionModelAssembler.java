package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.classes.ClassesModelAssembler;
import com.ronfas.SGBDAPI.user.UserModel;
import com.ronfas.SGBDAPI.user.UserModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InscriptionModelAssembler extends RepresentationModelAssemblerSupport<Inscription, InscriptionModel> {

    private final ClassesModelAssembler classesModelAssembler;
    private final UserModelAssembler userModelAssembler;

    public InscriptionModelAssembler(ClassesModelAssembler classesModelAssembler, UserModelAssembler userModelAssembler) {
        super(InscriptionController.class, InscriptionModel.class);
        this.classesModelAssembler = classesModelAssembler;
        this.userModelAssembler = userModelAssembler;
    }

    @Override
    public InscriptionModel toModel(Inscription inscription) {
        InscriptionModel inscriptionModel = instantiateModel(inscription);

        inscriptionModel.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .one(inscription.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withRel("inscriptions")
        );

        inscriptionModel.setId(inscription.getId());
        inscriptionModel.setClasse(classesModelAssembler.toModel(inscription.getClasse()));
        inscriptionModel.setUser(userModelAssembler.toModel(inscription.getUser()));
//        inscriptionModel.setRole();
//        TODO set role

        return inscriptionModel;
    }

    @Override
    public CollectionModel<InscriptionModel> toCollectionModel(Iterable<? extends Inscription> inscriptions) {
        CollectionModel<InscriptionModel> inscriptionModels = super.toCollectionModel(inscriptions);
        inscriptionModels.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withSelfRel()
        );
        return inscriptionModels;
    }
//    TODO lists and relations

}
