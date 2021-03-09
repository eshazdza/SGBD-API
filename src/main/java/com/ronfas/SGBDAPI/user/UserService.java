package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserModelAssembler userAssembler;

    public UserService(UserRepository userRepository, UserModelAssembler userAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
    }

    /**
     * Finds all the users
     *
     * @return List of Users in REST compliant model
     */
    public CollectionModel<User> getAllUsers() {
        return userAssembler.toCollectionModel(userRepository.findAll());
//        return userRepository.findAll()
//                .stream()
//                .map(userModelAssembler::toModel)
//                .collect(Collectors.toList());
    }

    /**
     * Find one user from provided id
     *
     * @param id Id of the requested user - Long
     * @return Found user in REST compliant model
     * @throws EntityNotFoundException when no user is found for the requested id
     */
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(UserEntity.class, "id", id.toString()));
        return userAssembler.toModel(userEntity);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param userEntity user entity to persist - User
     * @return REST compliant model of the persisted user
     */
    public User saveUser(UserEntity userEntity) {
        return userAssembler.toModel(userRepository.save(userEntity));
    }

    /**
     * Updates or create user
     *
     * @param userEntity User entity to persist
     * @param id   Of the user entity to update
     * @return REST compliant model of the persisted user
     */
    public User updateUser(UserEntity userEntity, Long id) {
        UserEntity updatedUserEntity = userRepository.findById(id)
                .map(foundUser -> {
                    foundUser.setFirstname(userEntity.getFirstname());
                    foundUser.setLastname(userEntity.getLastname());
                    return userRepository.save(foundUser);
                })
                .orElseGet(() -> {
                    userEntity.setId(id);
                    return userRepository.save(userEntity);
                });

        return userAssembler.toModel(updatedUserEntity);
    }

    /**
     * @param id of the user to delete
     */
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(UserEntity.class, "id", id.toString());
        }
    }
}
