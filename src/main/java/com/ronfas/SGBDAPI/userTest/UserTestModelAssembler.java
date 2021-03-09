package com.ronfas.SGBDAPI.userTest;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserTestModelAssembler implements RepresentationModelAssembler<UserTestEntity, EntityModel<UserTestEntity>> {
    @Override
    public EntityModel<UserTestEntity> toModel(UserTestEntity userTestEntity) {
        return
                EntityModel.of(userTestEntity,
                        linkTo(
                                methodOn(UserTestController.class)
                                        .one(userTestEntity.getId())
                        ).withSelfRel(),
                        linkTo(
                                methodOn(UserTestController.class)
                                        .all()
                        ).withRel("users")

                );
    }
}
