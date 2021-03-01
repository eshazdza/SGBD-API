package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.user.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TestModelAssembler implements RepresentationModelAssembler<Test, EntityModel<Test>> {
    @Override
    public EntityModel<Test> toModel(Test test) {
        return
                EntityModel.of(test,
                        linkTo(
                                methodOn(TestController.class)
                                        .one(test.getId())
                        ).withSelfRel(),
                        linkTo(methodOn(TestController.class).all()).withRel("tests")
                );
    }
}
