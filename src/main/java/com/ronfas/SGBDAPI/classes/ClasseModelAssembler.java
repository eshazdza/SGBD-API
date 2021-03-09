package com.ronfas.SGBDAPI.classes;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClasseModelAssembler extends RepresentationModelAssemblerSupport<ClasseEntity, Classe> {

    public ClasseModelAssembler() {
        super(ClasseController.class, Classe.class);
    }

    @Override
    public Classe toModel(ClasseEntity classe) {
        Classe classeModel = instantiateModel(classe);
        classeModel.add(
                linkTo(
                        methodOn(ClasseController.class)
                                .one(classe.getUid())
                ).withSelfRel(),
                linkTo(
                        methodOn(ClasseController.class)
                                .all()
                ).withRel("classes")
        );

        classeModel.setId(classe.getId());
//        TODO SETTERS

        return classeModel;
    }

    @Override
    public CollectionModel<Classe> toCollectionModel(Iterable<? extends ClasseEntity> classes) {
        CollectionModel<Classe> classeModels = super.toCollectionModel(classes);
        classeModels.add(
                linkTo(
                        methodOn(ClasseController.class)
                                .all()
                ).withSelfRel());

        return classeModels;
    }
}
