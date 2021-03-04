package com.ronfas.SGBDAPI.inscription;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<EntityModel<Inscription>> getAllInscriptions() {
        return inscriptionRepository.findAll()
                .stream()
                .map(inscriptionModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Find one user from provided id
     *
     * @param id Id of the requested user - Long
     * @return Found inscription in REST compliant model
     * @throws EntityNotFoundException when no user is found for the requested id
     */
    public EntityModel<Inscription> getInscriptionById(Long id) {
        Inscription inscription = inscriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Inscription.class, "id", id.toString()));
        return inscriptionModelAssembler.toModel(inscription);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param inscription inscription entity to persist - User
     * @return REST compliant model of the persisted user
     */
    public EntityModel<Inscription> saveInscription(Inscription inscription) {
        return inscriptionModelAssembler.toModel(inscriptionRepository.save(inscription));
    }

    /**
     * Updates or create user
     *
     * @param inscription inscription entity to persist
     * @param id          Of the inscription entity to update
     * @return REST compliant model of the persisted user
     */
    public EntityModel<Inscription> updateInscription(Inscription inscription, Long id) {
        Inscription updatedInscription = inscriptionRepository.findById(id)
                .map(foundInscription -> {
                    // TODO SET foundInscription.setblablab(babma)
                     return inscriptionRepository.save(foundInscription);
                })
                .orElseGet(() -> {
                    inscription.setId(id);
                    return inscriptionRepository.save(inscription);
                });

        return inscriptionModelAssembler.toModel(updatedInscription);
    }

    /**
     * @param id of the inscription to delete
     */
    public void deleteInscription(Long id) {
        try {
            inscriptionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(Inscription.class, "id", id.toString());
        }
    }
}
