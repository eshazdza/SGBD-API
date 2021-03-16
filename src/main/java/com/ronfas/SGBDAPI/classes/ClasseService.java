package com.ronfas.SGBDAPI.classes;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import com.ronfas.SGBDAPI.user.User;
import com.ronfas.SGBDAPI.user.UserEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClasseService {

    private final ClasseRepositoryInterface classeRepository;
    private final ClasseModelAssembler classeModelAssembler;

    public ClasseService(ClasseRepositoryInterface classeRepository, ClasseModelAssembler classeModelAssembler) {
        this.classeRepository = classeRepository;
        this.classeModelAssembler = classeModelAssembler;
    }

    /**
     * Finds all the Classes
     *
     * @return List of Classes in REST compliant model
     */
    public CollectionModel<Classe> getAllClasses() {
        return classeModelAssembler.toCollectionModel(classeRepository.findAll());
    }

    /**
     * Find one Classes from provided id
     *
     * @param uuid Id of the requested Classes - Long
     * @return Found Classes in REST compliant model
     * @throws EntityNotFoundException when no Classes is found for the requested id
     */
    public Classe getClasseByUid(UUID uuid) {
        ClasseEntity classeEntity = classeRepository.findByUid(uuid).orElseThrow(
                () -> new EntityNotFoundException(ClasseEntity.class, "uuid", uuid.toString()));
        return classeModelAssembler.toModel(classeEntity);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param classeEntity Classes entity to persist - Classes
     * @return REST compliant model of the persisted Classes
     */
    public Classe saveClasse(ClasseEntity classeEntity) {
        return classeModelAssembler.toModel(classeRepository.save(classeEntity));
    }

    /**
     * Updates or create Classes
     *
     * @param classeEntity Classes entity to persist
     * @param uid   Of the Classes entity to update
     * @return REST compliant model of the persisted Classes
     */
    public Classe updateClasse(ClasseEntity classeEntity, UUID uid) {
        ClasseEntity updatedClasse = classeRepository.findByUid(uid)
                .map(foundClass -> {
                    foundClass.setName(classeEntity.getName());
                    foundClass.setDateBegin(classeEntity.getDateBegin());
                    foundClass.setDateEnd(classeEntity.getDateEnd());
                    foundClass.setUsersList(classeEntity.getUsersList());
                    foundClass.setCurrentFlag(classeEntity.isCurrentFlag());
                    return classeRepository.save(foundClass);
                })
                .orElseGet(() -> {
                    classeEntity.setUid(uid);
                    return classeRepository.save(classeEntity);
                });

        return classeModelAssembler.toModel(updatedClasse);
    }

    /**
     * @param uid of the Classes to delete
     */
    public void deleteClasse(UUID uid) {
        try {
            classeRepository.deleteById(uid);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(ClasseEntity.class, "uid", uid.toString());
        }
    }

    /**
     *
     * @param uuid of the classe
     * @return User the teacher of the classe
     */
    public UserEntity getTeacherForClasse(UUID uuid){
        return classeRepository.findTeacherForClasse(uuid);
    }
}
