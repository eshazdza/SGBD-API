package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.classes.Classe;
import com.ronfas.SGBDAPI.classes.ClasseController;
import com.ronfas.SGBDAPI.classes.ClasseEntity;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.inscription.InscriptionController;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
import com.ronfas.SGBDAPI.role.Role;
import com.ronfas.SGBDAPI.role.RoleController;
import com.ronfas.SGBDAPI.role.RoleEntity;
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
public class TestModelAssembler extends RepresentationModelAssemblerSupport<TestEntity, Test> {

    public TestModelAssembler() {
        super(TestController.class, Test.class);
    }

    @Override
    public Test toModel(TestEntity testEntity) {
        Test test = instantiateModel(testEntity);
        test.add(
                linkTo(
                        methodOn(TestController.class)
                                .one(testEntity.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(TestController.class)
                                .all()
                ).withRel("inscriptions")
        );

        test.setId(testEntity.getId());
        test.setDate(testEntity.getDate());
        test.setClasse(toClassModel(testEntity.getClasse()));
        test.setUserTestList(toUserTestModel(testEntity.getUserTestList()));
//        TODO SETTERS RELATIONS

        return test;
    }

    @Override
    public CollectionModel<Test> toCollectionModel(Iterable<? extends TestEntity> testEntities) {
        CollectionModel<Test> testCollectionModel = super.toCollectionModel(testEntities);
        testCollectionModel.add(
                linkTo(
                        methodOn(TestController.class)
                                .all()
                ).withSelfRel()
        );
        return testCollectionModel;
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

    private List<UserTest> toUserTestModel(List<UserTestEntity> userTestListEntities) {
        if (userTestListEntities==null || userTestListEntities.isEmpty())
            return Collections.emptyList();

        return userTestListEntities.stream()
                .map(userTestEntity -> {
                    UserTest userTest = new UserTest();
                    userTest.setId(userTestEntity.getId());
                    userTest.setPoints(userTestEntity.getPoints());
                    userTest.setPresent(userTestEntity.isPresent());
                    userTest.setInscription(toInscriptionModel(userTestEntity.getInscription()));
                    userTest.add(
                            linkTo(
                                    methodOn(UserTestController.class)
                                            .one(userTest.getId())
                            ).withSelfRel(),
                            linkTo(
                                    methodOn(UserTestController.class)
                                            .all()
                            ).withRel("classes")
                    );
                    return userTest;
                }).collect(Collectors.toList());
    }

    private Inscription toInscriptionModel(InscriptionEntity inscriptionEntity) {
        Inscription inscription = new Inscription();
        inscription.setUser(toUserModel(inscriptionEntity.getUser()));
        inscription.setId(inscriptionEntity.getId());
        inscription.setRole(toRoleModel(inscriptionEntity.getRole()));
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