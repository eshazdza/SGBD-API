package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final InscriptionModelAssembler inscriptionModelAssembler;

    public InscriptionService(InscriptionRepository inscriptionRepository, InscriptionModelAssembler inscriptionModelAssembler) {
        this.inscriptionRepository = inscriptionRepository;
        this.inscriptionModelAssembler = inscriptionModelAssembler;
    }

    /**
     * Finds all the Inscriptions
     *
     * @return List of Inscriptions in REST compliant model
     */
    public CollectionModel<Inscription> getAllInscriptions() {
        return inscriptionModelAssembler.toCollectionModel(inscriptionRepository.findAll());
//        return inscriptionRepository.findAll()
//                .stream()
//                .map(inscriptionModelAssembler::toModel)
//                .collect(Collectors.toList());
    }

    /**
     * Find one user from provided id
     *
     * @param id Id of the requested user - Long
     * @return Found inscription in REST compliant model
     * @throws EntityNotFoundException when no user is found for the requested id
     */
    public Inscription getInscriptionById(Long id) {
        InscriptionEntity inscriptionEntity = inscriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(InscriptionEntity.class, "id", id.toString()));
        return inscriptionModelAssembler.toModel(inscriptionEntity);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param inscriptionEntity inscription entity to persist - User
     * @return REST compliant model of the persisted user
     */
    public Inscription saveInscription(InscriptionEntity inscriptionEntity) {
        return inscriptionModelAssembler.toModel(inscriptionRepository.save(inscriptionEntity));
    }

    /**
     * Updates or create user
     *
     * @param inscriptionEntity inscription entity to persist
     * @param id          Of the inscription entity to update
     * @return REST compliant model of the persisted user
     */
    public Inscription updateInscription(InscriptionEntity inscriptionEntity, Long id) {
        InscriptionEntity updatedInscriptionEntity = inscriptionRepository.findById(id)
                .map(foundInscription -> {
                    // TODO SET foundInscription.setblablab(babma)
                    return inscriptionRepository.save(foundInscription);
                })
                .orElseGet(() -> {
                    inscriptionEntity.setId(id);
                    return inscriptionRepository.save(inscriptionEntity);
                });

        return inscriptionModelAssembler.toModel(updatedInscriptionEntity);
    }

    /**
     * @param id of the inscription to delete
     */
    public void deleteInscription(Long id) {
        try {
            inscriptionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(InscriptionEntity.class, "id", id.toString());
        }
    }

    public InscriptionEntity getInscriptionEntityById(Long id) {
        return inscriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(InscriptionEntity.class, "id", id.toString()));
    }
}
