package com.ronfas.SGBDAPI.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Generates REST compliant model from entity
 * by adding and binding links
 */

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, User> {

    public UserModelAssembler() {
        super(UserController.class, User.class);
    }

//
//    public UserModelAssembler() {
//        super(UserController.class, UserModel.class);
//    }

    @Override
    public User toModel(UserEntity userEntity) {

        User user = instantiateModel(userEntity);
        user.add(
                linkTo(
                        methodOn(UserController.class)
                                .one(userEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(UserController.class)
                                .all()
                ).withRel("users")
        );

        user.setId(userEntity.getId());
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.setAdmin(userEntity.isAdmin());

        return user;
    }

    public User toShortModel(UserEntity userEntity){
        User user = instantiateModel(userEntity);
        user.add(
                linkTo(
                        methodOn(UserController.class)
                                .one(userEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(UserController.class)
                                .all()
                ).withRel("users")
        );

        user.setId(userEntity.getId());
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.setAdmin(userEntity.isAdmin());

        return user;
    }

    @Override
    public CollectionModel<User> toCollectionModel(Iterable<? extends UserEntity> usersEntity) {
        CollectionModel<User> users = super.toCollectionModel(usersEntity);
        users.add(
                linkTo(
                        methodOn(UserController.class)
                                .all()
                ).withSelfRel());

        return users;
    }

//    private List<InscriptionModel> toUserCoursModel(List<Inscription> userCoursList) {
//        if (userCoursList.isEmpty())
//            return Collections.emptyList();
//
//        return userCoursList.stream()
//                .map(inscriptionModelAssembler::toModel)
//                .collect(Collectors.toList());
//    }


}
