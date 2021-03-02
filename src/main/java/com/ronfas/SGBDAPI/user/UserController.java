package com.ronfas.SGBDAPI.user;

import com.ronfas.SGBDAPI.error.EntityNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    public UserController(UserRepository userRepository, UserService userService, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping("")
    CollectionModel<EntityModel<User>> all() {
        return CollectionModel.of(this.userService.getAllUsers(), linkTo(
                methodOn(UserController.class).all()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    EntityModel<User> one(
            @PathVariable Long id
    ) {
        return this.userService.getUserById(id);
    }

    @PostMapping("")
    ResponseEntity<?> newUser(
            @RequestBody @Valid User newUser
    ) {
        EntityModel<User> userEntityModel = this.userService.saveUser(newUser);
//      Generates REST/IANA compliant Self link for the created resource and returns a 201 http status with the created resource
        return ResponseEntity.created(
                userEntityModel
                        .getRequiredLink(IanaLinkRelations.SELF)
                        .toUri())
                .body(userEntityModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUser(
            @RequestBody @Valid User user,
            @PathVariable Long id
    ) {
        EntityModel<User> userEntityModel = this.userService.updateUser(user, id);

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
