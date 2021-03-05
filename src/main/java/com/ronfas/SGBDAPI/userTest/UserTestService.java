package com.ronfas.SGBDAPI.userTest;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTestService {

    private final UserTestRepository userTestRepository;
    private final UserTestModelAssembler userTestModelAssembler;

    public UserTestService(UserTestRepository userTestRepository, UserTestModelAssembler userTestModelAssembler) {
        this.userTestRepository = userTestRepository;
        this.userTestModelAssembler = userTestModelAssembler;
    }

    /**
     * Finds all the users
     *
     * @return List of Users in REST compliant model
     */
    public List<EntityModel<UserTest>> getAllUserTests() {
        return userTestRepository.findAll()
                .stream()
                .map(userTestModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Find one user from provided id
     *
     * @param id Id of the requested user - Long
     * @return Found user in REST compliant model
     * @throws EntityNotFoundException when no user is found for the requested id
     */
    public EntityModel<UserTest> getUserTestById(Long id) {
        UserTest userTest = userTestRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(UserTest.class, "id", id.toString()));
        return userTestModelAssembler.toModel(userTest);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param userTest user entity to persist - User
     * @return REST compliant model of the persisted user
     */
    public EntityModel<UserTest> saveUserTest(UserTest userTest) {
        return userTestModelAssembler.toModel(userTestRepository.save(userTest));
    }

    /**
     * Updates or create user
     *
     * @param userTest User entity to persist
     * @param id   Of the user entity to update
     * @return REST compliant model of the persisted user
     */
    public EntityModel<UserTest> updateUserTest(UserTest userTest, Long id) {
        UserTest updatedUserTest = userTestRepository.findById(id)
                .map(foundUserTest -> {
                    foundUserTest.setTest(userTest.getTest());
                    foundUserTest.setInscription(userTest.getInscription());
                    foundUserTest.setPoints(userTest.getPoints());
                    foundUserTest.setPresent(userTest.isPresent());
                    return userTestRepository.save(foundUserTest);
                })
                .orElseGet(() -> {
                    userTest.setId(id);
                    return userTestRepository.save(userTest);
                });

        return userTestModelAssembler.toModel(updatedUserTest);
    }

    /**
     * @param id of the user to delete
     */
    public void deleteUserTest(Long id) {
        try {
            userTestRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(UserTest.class, "id", id.toString());
        }
    }
}
