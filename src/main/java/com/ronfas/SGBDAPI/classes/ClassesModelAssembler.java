package com.ronfas.SGBDAPI.classes;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClassesModelAssembler implements RepresentationModelAssembler<Classes, EntityModel<Classes>> {
    @Override
    public EntityModel<Classes> toModel(Classes classe) {
        return
                EntityModel.of(classe,
                        linkTo(
                                methodOn(ClassesController.class)
                                        .one(classe.getUid())
                        ).withSelfRel(),
                        linkTo(
                                methodOn(ClassesController.class).all()
                        ).withRel("classes")
                );
    }
}
