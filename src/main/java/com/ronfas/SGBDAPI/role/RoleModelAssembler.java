package com.ronfas.SGBDAPI.role;

import com.ronfas.SGBDAPI.classes.Classe;
import com.ronfas.SGBDAPI.classes.ClasseController;
import com.ronfas.SGBDAPI.classes.ClasseEntity;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.inscription.InscriptionController;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserController;
import com.ronfas.SGBDAPI.user.UserEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleModelAssembler extends RepresentationModelAssemblerSupport<RoleEntity, Role> {

    public RoleModelAssembler() {
        super(RoleController.class, Role.class);
    }

    @Override
    public Role toModel(RoleEntity roleEntity) {
        Role role = instantiateModel(roleEntity);
        role.add(
                linkTo(
                        methodOn(RoleController.class)
                                .one(roleEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(RoleController.class)
                                .all()
                ).withRel("roles")
        );

        role.setId(roleEntity.getId());
        role.setRoleType(roleEntity.getRoleType());
        role.setDescription(roleEntity.getDescription());
        role.setUserList(toUserListModel(roleEntity.getUserList()));
//        TODO SETTERS RELATIONS

        return role;
    }

    @Override
    public CollectionModel<Role> toCollectionModel(Iterable<? extends RoleEntity> roles) {
        CollectionModel<Role> roleModels = super.toCollectionModel(roles);
        roleModels.add(
                linkTo(
                        methodOn(RoleController.class)
                                .all()
                ).withSelfRel()
        );
        return roleModels;
    }

    private List<Inscription> toUserListModel(List<InscriptionEntity> userEntityList) {
        if (userEntityList == null || userEntityList.isEmpty())
            return Collections.emptyList();

        return userEntityList.stream()
                .map(this::toInscriptionModel).collect(Collectors.toList());
    }


    private Inscription toInscriptionModel(InscriptionEntity inscriptionEntity) {
        Inscription inscription = new Inscription();
        inscription.setUser(toUserModel(inscriptionEntity.getUser()));
        inscription.setClasse(toClassModel(inscriptionEntity.getClasse()));
        inscription.setId(inscriptionEntity.getId());
        inscription.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .one(inscription.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withRel("inscriptions")
        );
        return inscription;
    }

    private User toUserModel(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setAdmin(userEntity.isAdmin());
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.add(
                linkTo(
                        methodOn(UserController.class)
                                .one(user.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(UserController.class)
                                .all()
                ).withRel("users")
        );
        return user;
    }

    private Classe toClassModel(ClasseEntity classeEntity) {
        Classe classe = new Classe();
        classe.setId(classeEntity.getId());
        classe.setCurrentFlag(classeEntity.isCurrentFlag());
        classe.setDateBegin(classeEntity.getDateBegin());
        classe.setDateEnd(classeEntity.getDateEnd());
        classe.setName(classeEntity.getName());
        classe.setUuid(classeEntity.getUid());

        classe.add(
                linkTo(
                        methodOn(ClasseController.class)
                                .one(classe.getUuid())
                ).withSelfRel(),
                linkTo(
                        methodOn(ClasseController.class)
                                .all()
                ).withRel("classes")
        );

        return classe;
    }
}
