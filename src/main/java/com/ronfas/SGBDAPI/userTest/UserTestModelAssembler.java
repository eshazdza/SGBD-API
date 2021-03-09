package com.ronfas.SGBDAPI.userTest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserTestModelAssembler extends RepresentationModelAssemblerSupport<UserTestEntity, UserTest> {

    public UserTestModelAssembler() {
        super(UserTestController.class, UserTest.class);
    }

    @Override
    public UserTest toModel(UserTestEntity userTestEntity) {
        UserTest userTest = instantiateModel(userTestEntity);
        userTest.add(
                linkTo(
                        methodOn(UserTestController.class)
                                .one(userTestEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(UserTestController.class)
                                .all()
                ).withRel("inscriptions")
        );

        userTest.setId(userTestEntity.getId());
//        TODO SETTERS

        return userTest;
    }

    @Override
    public CollectionModel<UserTest> toCollectionModel(Iterable<? extends UserTestEntity> userTestEntities) {
        CollectionModel<UserTest> userTestModels = super.toCollectionModel(userTestEntities);
        userTestModels.add(
                linkTo(
                        methodOn(UserTestController.class)
                                .all()
                ).withSelfRel()
        );
        return userTestModels;
    }
}
