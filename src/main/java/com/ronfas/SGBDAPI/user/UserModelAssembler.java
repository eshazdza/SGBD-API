package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.classes.Classe;
import com.ronfas.SGBDAPI.classes.ClasseController;
import com.ronfas.SGBDAPI.classes.ClasseEntity;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.inscription.InscriptionController;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
import com.ronfas.SGBDAPI.role.Role;
import com.ronfas.SGBDAPI.role.RoleController;
import com.ronfas.SGBDAPI.role.RoleEntity;
import com.ronfas.SGBDAPI.test.Test;
import com.ronfas.SGBDAPI.test.TestController;
import com.ronfas.SGBDAPI.test.TestEntity;
import com.ronfas.SGBDAPI.userTest.UserTest;
import com.ronfas.SGBDAPI.userTest.UserTestController;
import com.ronfas.SGBDAPI.userTest.UserTestEntity;
import org.springframework.hateoas.CollectionModel;
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
        user.setInscriptionList(toInscriptionListModel(userEntity.getInscriptionList()));

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

    private List<Inscription> toInscriptionListModel(List<InscriptionEntity> inscriptionEntities) {
        if (inscriptionEntities == null || inscriptionEntities.isEmpty())
            return Collections.emptyList();

        return inscriptionEntities.stream()
                .map(inscriptionEntity -> {
                    Inscription inscription = new Inscription();
                    inscription.setId(inscriptionEntity.getId());
                    inscription.setClasse(toClassModel(inscriptionEntity.getClasse()));
                    inscription.setRole(toRoleModel(inscriptionEntity.getRoleEntity()));
                    inscription.setUserTestList(toUserTestListModel(inscriptionEntity.getUserTestEntityList()));
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
                })
                .collect(Collectors.toList());
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

    private Role toRoleModel(RoleEntity roleEntity) {
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setRoleType(roleEntity.getRoleType());
        role.setDescription(roleEntity.getDescription());
        role.add(
                linkTo(
                        methodOn(RoleController.class)
                                .one(role.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(RoleController.class)
                                .all()
                ).withRel("roles")
        );
        return role;
    }

    private List<UserTest> toUserTestListModel(List<UserTestEntity> userTestEntities) {
        if (userTestEntities == null || userTestEntities.isEmpty())
            return Collections.emptyList();

        return userTestEntities.stream()
                .map(userTestEntity -> {
                    UserTest userTest = new UserTest();
                    userTest.setId(userTestEntity.getId());
                    userTest.setPresent(userTestEntity.isPresent());
                    userTest.setPoints(userTestEntity.getPoints());
                    userTest.setTest(toTestModel(userTestEntity.getTest()));
                    userTest.add(
                            linkTo(
                                    methodOn(UserTestController.class)
                                            .one(userTest.getId())
                            ).withSelfRel(),
                            linkTo(
                                    methodOn(UserTestController.class)
                                            .all()
                            ).withRel("usertests")
                    );
                    return userTest;
                })
                .collect(Collectors.toList());
    }

    private Test toTestModel(TestEntity testEntity) {
        Test test = new Test();
        test.setDate(testEntity.getDate());
        test.setId(testEntity.getId());
        test.add(
                linkTo(
                        methodOn(TestController.class)
                                .one(test.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(TestController.class)
                                .all()
                ).withRel("tests")
        );
        return test;
    }
}
