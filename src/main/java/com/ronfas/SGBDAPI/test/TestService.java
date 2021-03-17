package com.ronfas.SGBDAPI.test;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

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
    public CollectionModel<Test> getAllTests() {
        return testModelAssembler.toCollectionModel(testRepository.findAll());
    }

    /**
     * Find one Tests from provided id
     *
     * @param id Id of the requested Tests - Long
     * @return Found Tests in REST compliant model
     * @throws EntityNotFoundException when no Tests is found for the requested id
     */
    public Test getTestById(Long id) {
        TestEntity testEntity = testRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(TestEntity.class, "id", id.toString()));
        return testModelAssembler.toModel(testEntity);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param testEntity Tests entity to persist - Tests
     * @return REST compliant model of the persisted Tests
     */
    public Test saveTest(TestEntity testEntity) {
        return testModelAssembler.toModel(testRepository.save(testEntity));
    }

    /**
     * Updates or create Tests
     *
     * @param testEntity Tests entity to persist
     * @param id   Of the Tests entity to update
     * @return REST compliant model of the persisted Tests
     */
    public Test updateTest(TestEntity testEntity, Long id) {
        TestEntity updatedTestEntity = testRepository.findById(id)
                .map(foundTest -> {
                    foundTest.setDate(testEntity.getDate());
                    foundTest.setClasse(testEntity.getClasse());
                    return testRepository.save(foundTest);
                })
                .orElseGet(() -> {
                    testEntity.setId(id);
                    return testRepository.save(testEntity);
                });

        return testModelAssembler.toModel(updatedTestEntity);
    }

    /**
     * @param id of the Test to delete
     */
    public void deleteTest(Long id) {
        try {
            testRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(TestEntity.class, "id", id.toString());
        }
    }

    public TestEntity getTestEntityById(Long id) {
        return testRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(TestEntity.class, "id", id.toString()));
    }
}
