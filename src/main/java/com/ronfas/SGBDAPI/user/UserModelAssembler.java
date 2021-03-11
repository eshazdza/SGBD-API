package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.inscription.InscriptionController;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
public class UserModelAssembler extends RepresentationModelAssemblerSupport<UserEntity, User> {

    public UserModelAssembler() {
        super(UserController.class, User.class);
    }

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
        user.setLastname(userEntity.getLastname());
        user.setFirstname(userEntity.getFirstname());
        user.setAdmin(userEntity.isAdmin());
        user.setInscriptionList(toInscriptionModel(userEntity.getInscriptionList()));

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

    private List<Inscription> toInscriptionModel(List<InscriptionEntity> inscriptionEntities) {
        if (inscriptionEntities.isEmpty())
            return Collections.emptyList();

        return inscriptionEntities.stream()
                .map(inscriptionEntity -> {
                    Inscription inscription = new Inscription();
                    inscription.setId(inscriptionEntity.getId());
                    inscription.add(
                            linkTo(
                                    methodOn(InscriptionController.class)
                                            .one(inscription.getId())
                            ).withSelfRel()
                    );
                    return inscription;
                })
                .collect(Collectors.toList());
    }
}
