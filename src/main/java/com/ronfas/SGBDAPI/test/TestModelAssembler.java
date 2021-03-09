package com.ronfas.SGBDAPI.test;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TestModelAssembler implements RepresentationModelAssembler<TestEntity, EntityModel<TestEntity>> {
    @Override
    public EntityModel<TestEntity> toModel(TestEntity testEntity) {
        return
                EntityModel.of(testEntity,
                        linkTo(
                                methodOn(TestController.class)
                                        .one(testEntity.getId())
                        ).withSelfRel(),
                        linkTo(methodOn(TestController.class).all()).withRel("tests")
                );
    }
}
