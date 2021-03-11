package com.ronfas.SGBDAPI.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public CollectionModel<User> all() {
        return this.userService.getAllUsers();

    }

    @GetMapping("/{id}")
    public User one(
            @PathVariable Long id
    ) {
        return this.userService.getUserById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newUser(
            @RequestBody @Valid UserEntity newUserEntity
    ) {
        User userEntityModel = this.userService.saveUser(newUserEntity);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                userEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(userEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUser(
            @RequestBody @Valid UserEntity userEntity,
            @PathVariable Long id
    ) {
        User userEntityModel = this.userService.updateUser(userEntity, id);

        return ResponseEntity
                .created(userEntityModel.getRequiredLink(
                        IanaLinkRelations.SELF
                        ).toUri()
                ).body(userEntityModel);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
