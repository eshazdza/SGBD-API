package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserController;
import com.ronfas.SGBDAPI.user.UserModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClassesModelAssembler extends RepresentationModelAssemblerSupport<Classes, ClasseModel> {

    public ClassesModelAssembler() {
        super(ClassesController.class, ClasseModel.class);
    }

    @Override
    public ClasseModel toModel(Classes classe) {
        ClasseModel classeModel = instantiateModel(classe);
        classeModel.add(
                linkTo(
                        methodOn(ClassesController.class)
                                .one(classe.getUid())
                ).withSelfRel(),
                linkTo(
                        methodOn(ClassesController.class)
                                .all()
                ).withRel("classes")
        );

        classeModel.setId(classe.getId());
        // SETTERS

        return classeModel;
    }

    @Override
    public CollectionModel<ClasseModel> toCollectionModel(Iterable<? extends Classes> classes) {
        CollectionModel<ClasseModel> classeModels = super.toCollectionModel(classes);
        classeModels.add(
                linkTo(
                        methodOn(ClassesController.class)
                                .all()
                ).withSelfRel());

        return classeModels;
    }

}
