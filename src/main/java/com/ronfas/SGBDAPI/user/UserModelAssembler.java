package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.inscription.InscriptionModel;
import com.ronfas.SGBDAPI.inscription.InscriptionModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Generates REST compliant model from entity
 * by adding and binding links
 */

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    private final InscriptionModelAssembler inscriptionModelAssembler;

    public UserModelAssembler(InscriptionModelAssembler inscriptionModelAssembler) {
        super(UserController.class, UserModel.class);
        this.inscriptionModelAssembler = inscriptionModelAssembler;
    }

    @Override
    public UserModel toModel(User user) {

        UserModel userModel = instantiateModel(user);
        userModel.add(
                linkTo(
                        methodOn(UserController.class)
                                .one(user.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(UserController.class)
                                .all()
                ).withRel("users")
        );

        userModel.setId(user.getId());
        userModel.setFirstname(user.getFirstname());
        userModel.setLastname(user.getLastname());
        userModel.setAdmin(user.isAdmin());
//        userModel.setUserCoursList(toUserCoursModel(user.getUserCoursList()));

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> users) {
        CollectionModel<UserModel> userModels = super.toCollectionModel(users);
        userModels.add(linkTo(
                methodOn(
                        UserController.class
                )
                        .all()
        ).withSelfRel());

        return userModels;
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
