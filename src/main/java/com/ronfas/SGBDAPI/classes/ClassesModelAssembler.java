package com.ronfas.SGBDAPI.classes;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClassesModelAssembler implements RepresentationModelAssembler<Classes, EntityModel<Classes>> {
    @Override
    public EntityModel<Classes> toModel(Classes classes) {
        return
                EntityModel.of(classes,
                        linkTo(
                                methodOn(ClassesController.class)
                                        .one(classes.getUid())
                        ).withSelfRel(),
                        linkTo(
                                methodOn(ClassesController.class)
                                        .all()
                        ).withRel("users")
                );
    }
}
