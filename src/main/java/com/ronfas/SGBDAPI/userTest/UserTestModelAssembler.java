package com.ronfas.SGBDAPI.userTest;

import com.ronfas.SGBDAPI.classes.Classe;
import com.ronfas.SGBDAPI.classes.ClasseController;
import com.ronfas.SGBDAPI.classes.ClasseEntity;
import com.ronfas.SGBDAPI.inscription.Inscription;
import com.ronfas.SGBDAPI.inscription.InscriptionController;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
import com.ronfas.SGBDAPI.test.Test;
import com.ronfas.SGBDAPI.test.TestController;
import com.ronfas.SGBDAPI.test.TestEntity;
import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserController;
import com.ronfas.SGBDAPI.user.UserEntity;
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
        userTest.setPoints(userTestEntity.getPoints());
        userTest.setPresent(userTestEntity.isPresent());
        userTest.setTest(toTestModel(userTestEntity.getTest()));
        userTest.setInscription(toInscriptionModel(userTestEntity.getInscription()));

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

    private Test toTestModel(TestEntity testEntity) {
        Test test = new Test();
        test.setDate(testEntity.getDate());
        test.setId(testEntity.getId());
        test.setClasse(toClassModel(testEntity.getClasse()));
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

    private Inscription toInscriptionModel(InscriptionEntity inscriptionEntity) {
        Inscription inscription = new Inscription();
        inscription.setUser(toUserModel(inscriptionEntity.getUser()));
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
}
