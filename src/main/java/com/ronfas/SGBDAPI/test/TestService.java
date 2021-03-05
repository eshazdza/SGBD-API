package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final TestModelAssembler testModelAssembler;

    public TestService(TestRepository testRepository, TestModelAssembler testModelAssembler) {
        this.testRepository = testRepository;
        this.testModelAssembler = testModelAssembler;
    }

    /**
     * Finds all the tests
     *
     * @return List of Tests in REST compliant model
     */
    public List<EntityModel<Test>> getAllTests() {
        return testRepository.findAll()
                .stream()
                .map(testModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Find one Tests from provided id
     *
     * @param id Id of the requested Tests - Long
     * @return Found Tests in REST compliant model
     * @throws EntityNotFoundException when no Tests is found for the requested id
     */
    public EntityModel<Test> getTestById(Long id) {
        Test test = testRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Test.class, "id", id.toString()));
        return testModelAssembler.toModel(test);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param test Tests entity to persist - Tests
     * @return REST compliant model of the persisted Tests
     */
    public EntityModel<Test> savetest(Test test) {
        return testModelAssembler.toModel(testRepository.save(test));
    }

    /**
     * Updates or create Tests
     *
     * @param test Tests entity to persist
     * @param id   Of the Tests entity to update
     * @return REST compliant model of the persisted Tests
     */
    public EntityModel<Test> updateTest(Test test, Long id) {
        Test updatedTest = testRepository.findById(id)
                .map(foundTest -> {
                    foundTest.setDate(test.getDate());
                    foundTest.setClasse(test.getClasse());
                    return testRepository.save(foundTest);
                })
                .orElseGet(() -> {
                    test.setId(id);
                    return testRepository.save(test);
                });

        return testModelAssembler.toModel(updatedTest);
    }

    /**
     * @param id of the Test to delete
     */
    public void deleteTest(Long id) {
        try {
            testRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(Test.class, "id", id.toString());
        }
    }
}
