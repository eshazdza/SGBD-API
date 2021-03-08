package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;

    public UserService(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    /**
     * Finds all the users
     *
     * @return List of Users in REST compliant model
     */
    public CollectionModel<UserModel> getAllUsers() {
        return userModelAssembler.toCollectionModel(userRepository.findAll());
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
    public UserModel getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(User.class, "id", id.toString()));
        return userModelAssembler.toModel(user);
    }

    /**
     * Save the entity and creates a REST compliant model of said entity
     *
     * @param user user entity to persist - User
     * @return REST compliant model of the persisted user
     */
    public UserModel saveUser(User user) {
        return userModelAssembler.toModel(userRepository.save(user));
    }

    /**
     * Updates or create user
     *
     * @param user User entity to persist
     * @param id   Of the user entity to update
     * @return REST compliant model of the persisted user
     */
    public UserModel updateUser(User user, Long id) {
        User updatedUser = userRepository.findById(id)
                .map(foundUser -> {
                    foundUser.setFirstname(user.getFirstname());
                    foundUser.setLastname(user.getLastname());
                    return userRepository.save(foundUser);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });

        return userModelAssembler.toModel(updatedUser);
    }

    /**
     * @param id of the user to delete
     */
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(User.class, "id", id.toString());
        }
    }
}
