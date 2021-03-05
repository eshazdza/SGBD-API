package com.ronfas.SGBDAPI.userTest;

import com.ronfas.SGBDAPI.user.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserTestModelAssembler implements RepresentationModelAssembler<UserTest, EntityModel<UserTest>> {
    @Override
    public EntityModel<UserTest> toModel(UserTest userTest) {
        return
                EntityModel.of(userTest,
                        linkTo(
                                methodOn(UserTestController.class)
                                        .one(userTest.getId())
                        ).withSelfRel(),
                        linkTo(
                                methodOn(UserTestController.class)
                                        .all()
                        ).withRel("users")

                );
    }
}
