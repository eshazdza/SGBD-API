package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.inscription.InscriptionController;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
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
public class ClasseModelAssembler extends RepresentationModelAssemblerSupport<ClasseEntity, Classe> {

    public ClasseModelAssembler() {
        super(ClasseController.class, Classe.class);
    }

    @Override
    public Classe toModel(ClasseEntity classeEntity) {
        Classe classe = instantiateModel(classeEntity);
        classe.add(
                linkTo(
                        methodOn(ClasseController.class)
                                .one(classeEntity.getUid())
                ).withSelfRel(),
                linkTo(
                        methodOn(ClasseController.class)
                                .all()
                ).withRel("classes")
        );

        classe.setId(classeEntity.getId());
        classe.setUuid(classeEntity.getUid());
        classe.setName(classeEntity.getName());
        classe.setDateBegin(classeEntity.getDateBegin());
        classe.setDateEnd(classeEntity.getDateEnd());
        classe.setCurrentFlag(classeEntity.isCurrentFlag());
        classe.setTestList(toTestListModel(classeEntity.getTestsList()));
        classe.setUserList(toUserListModel(classeEntity.getUsersList()));

        return classe;
    }

    @Override
    public CollectionModel<Classe> toCollectionModel(Iterable<? extends ClasseEntity> classeEntities) {
        CollectionModel<Classe> classes = super.toCollectionModel(classeEntities);
        classes.add(
                linkTo(
                        methodOn(ClasseController.class)
                                .all()
                ).withSelfRel());

        return classes;
    }


    private List<Inscription> toUserListModel(List<InscriptionEntity> userEntityList) {
        if (userEntityList == null || userEntityList.isEmpty())
            return Collections.emptyList();

        return userEntityList.stream()
                .map(this::toInscriptionModel).collect(Collectors.toList());
    }

    private List<Test> toTestListModel(List<TestEntity> testEntityList) {
        if (testEntityList == null || testEntityList.isEmpty())
            return Collections.emptyList();

        return testEntityList.stream()
                .map(testEntity -> {
                    Test test = new Test();
                    test.setId(testEntity.getId());
                    test.setDate(testEntity.getDate());
                    test.setUserTestList(toUserTestListModel(testEntity.getUserTestList()));
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
                })
                .collect(Collectors.toList());
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
                    userTest.setInscription(toInscriptionModel(userTestEntity.getInscription()));
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

    private Inscription toInscriptionModel(InscriptionEntity inscriptionEntity) {
        Inscription inscription = new Inscription();
        inscription.setUser(toUserModel(inscriptionEntity.getUser()));
        inscription.setRole(toRoleModel(inscriptionEntity.getRole()));
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
}
