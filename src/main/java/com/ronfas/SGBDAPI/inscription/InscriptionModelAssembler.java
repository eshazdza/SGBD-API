package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.classes.Classe;
import com.ronfas.SGBDAPI.classes.ClasseController;
import com.ronfas.SGBDAPI.classes.ClasseEntity;
import com.ronfas.SGBDAPI.role.Role;
import com.ronfas.SGBDAPI.role.RoleController;
import com.ronfas.SGBDAPI.role.RoleEntity;
import com.ronfas.SGBDAPI.test.Test;
import com.ronfas.SGBDAPI.test.TestController;
import com.ronfas.SGBDAPI.test.TestEntity;
import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserController;
import com.ronfas.SGBDAPI.user.UserEntity;
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

@Component
public class InscriptionModelAssembler extends RepresentationModelAssemblerSupport<InscriptionEntity, Inscription> {

    public InscriptionModelAssembler() {
        super(InscriptionController.class, Inscription.class);
    }

    @Override
    public Inscription toModel(InscriptionEntity inscriptionEntity) {
        Inscription inscription = instantiateModel(inscriptionEntity);
        inscription.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .one(inscriptionEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withRel("inscriptions")
        );

        inscription.setId(inscriptionEntity.getId());
        inscription.setUser(toUserModel(inscriptionEntity.getUser()));
        inscription.setClasse(toClassModel(inscriptionEntity.getClasse()));
        inscription.setRole(toRoleModel(inscriptionEntity.getRole()));
        inscription.setUserTestList(toUserTestListModel(inscriptionEntity.getUserTestEntityList()));

        return inscription;
    }

    @Override
    public CollectionModel<Inscription> toCollectionModel(Iterable<? extends InscriptionEntity> inscriptions) {
        CollectionModel<Inscription> inscriptionModels = super.toCollectionModel(inscriptions);
        inscriptionModels.add(
                linkTo(
                        methodOn(InscriptionController.class)
                                .all()
                ).withSelfRel()
        );
        return inscriptionModels;
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
