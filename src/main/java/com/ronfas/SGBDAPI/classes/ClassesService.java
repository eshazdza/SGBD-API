package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClassesService {

    private final ClassesRepository classesRepository;
    private final ClassesModelAssembler classesModelAssembler;

    public ClassesService(ClassesRepository classesRepository, ClassesModelAssembler classesModelAssembler) {
        this.classesRepository = classesRepository;
        this.classesModelAssembler = classesModelAssembler;
    }

    /**
     * Finds all the Classes
     *
     * @return List of Classes in REST compliant model
     */
    public CollectionModel<ClasseModel> getAllClasses() {
        return classesModelAssembler.toCollectionModel(classesRepository.findAll());
    }

    /**
     * Find one Classes from provided id
     *
     * @param uuid Id of the requested Classes - Long
     * @return Found Classes in REST compliant model
     * @throws EntityNotFoundException when no Classes is found for the requested id
     */
    public ClasseModel getClasseByUid(UUID uuid) {
        Classes classes = classesRepository.findByUid(uuid).orElseThrow(
                () -> new EntityNotFoundException(Classes.class, "uuid", uuid.toString()));
        return classesModelAssembler.toModel(classes);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param classes Classes entity to persist - Classes
     * @return REST compliant model of the persisted Classes
     */
    public ClasseModel saveClasse(Classes classes) {
        return classesModelAssembler.toModel(classesRepository.save(classes));
    }

    /**
     * Updates or create Classes
     *
     * @param classes Classes entity to persist
     * @param uid   Of the Classes entity to update
     * @return REST compliant model of the persisted Classes
     */
    public ClasseModel updateClasse(Classes classes, UUID uid) {
        Classes updatedClasse = classesRepository.findByUid(uid)
                .map(foundClass -> {
                    foundClass.setName(classes.getName());
                    foundClass.setDateBegin(classes.getDateBegin());
                    foundClass.setDateEnd(classes.getDateEnd());
                    foundClass.setUsersList(classes.getUsersList());
                    foundClass.setCurrentFlag(classes.isCurrentFlag());
                    foundClass.setUid(classes.getUid());
                    return classesRepository.save(foundClass);
                })
                .orElseGet(() -> {
                    classes.setUid(uid);
                    return classesRepository.save(classes);
                });

        return classesModelAssembler.toModel(updatedClasse);
    }

    /**
     * @param uid of the Classes to delete
     */
    public void deleteClasse(UUID uid) {
        try {
            classesRepository.deleteById(uid);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(Classes.class, "uid", uid.toString());
        }
    }
}
