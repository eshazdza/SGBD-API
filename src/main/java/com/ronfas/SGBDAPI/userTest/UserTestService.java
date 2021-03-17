package com.ronfas.SGBDAPI.userTest;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import com.ronfas.SGBDAPI.inscription.InscriptionEntity;
import com.ronfas.SGBDAPI.inscription.InscriptionService;
import com.ronfas.SGBDAPI.test.TestEntity;
import com.ronfas.SGBDAPI.test.TestService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;


@Service
public class UserTestService {

    private final UserTestRepository userTestRepository;
    private final UserTestModelAssembler userTestModelAssembler;
    private final TestService testService;
    private final InscriptionService inscriptionService;

    public UserTestService(UserTestRepository userTestRepository, UserTestModelAssembler userTestModelAssembler, TestService testService, InscriptionService inscriptionService) {
        this.userTestRepository = userTestRepository;
        this.userTestModelAssembler = userTestModelAssembler;
        this.testService = testService;
        this.inscriptionService = inscriptionService;
    }

    /**
     * Finds all the users
     *
     * @return List of Users in REST compliant model
     */
    public CollectionModel<UserTest> getAllUserTests() {
        return userTestModelAssembler.toCollectionModel(userTestRepository.findAll());
    }

    /**
     * Find one user from provided id
     *
     * @param id Id of the requested user - Long
     * @return Found user in REST compliant model
     * @throws EntityNotFoundException when no user is found for the requested id
     */
    public UserTest getUserTestById(Long id) {
        UserTestEntity userTestEntity = userTestRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(UserTestEntity.class, "id", id.toString()));
        return userTestModelAssembler.toModel(userTestEntity);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param userTestEntity user entity to persist - User
     * @return REST compliant model of the persisted user
     */
    public UserTest saveUserTest(UserTestEntity userTestEntity) {
        TestEntity testEntity = testService.getTestEntityById(userTestEntity.getTest().getId());
        userTestEntity.setTest(testEntity);
        InscriptionEntity inscriptionEntity = inscriptionService.getInscriptionEntityById(userTestEntity.getInscription().getId());
        userTestEntity.setInscription(inscriptionEntity);
        return userTestModelAssembler.toModel(userTestRepository.save(userTestEntity));
    }

    /**
     * Updates or create user
     *
     * @param userTestEntity User entity to persist
     * @param id             Of the user entity to update
     * @return REST compliant model of the persisted user
     */
    public UserTest updateUserTest(UserTestEntity userTestEntity, Long id) {
        UserTestEntity updatedUserTestEntity = userTestRepository.findById(id)
                .map(foundUserTest -> {
                    foundUserTest.setTest(userTestEntity.getTest());
                    foundUserTest.setInscription(userTestEntity.getInscription());
                    foundUserTest.setPoints(userTestEntity.getPoints());
                    foundUserTest.setPresent(userTestEntity.isPresent());
                    return userTestRepository.save(foundUserTest);
                })
                .orElseGet(() -> {
                    userTestEntity.setId(id);
                    return userTestRepository.save(userTestEntity);
                });

        return userTestModelAssembler.toModel(updatedUserTestEntity);
    }

    /**
     * @param id of the user to delete
     */
    public void deleteUserTest(Long id) {
        try {
            userTestRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(UserTestEntity.class, "id", id.toString());
        }
    }
}
